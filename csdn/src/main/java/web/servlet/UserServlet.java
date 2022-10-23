package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.PageBean;
import pojo.User;
import service.Impl.UserServiceImpl;
import service.UserService;
import util.CheckUtil;
import util.DateUtils;
import util.JwtUtil;
import util.SendEmailUtils;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();


    public void selectUserByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //通过get方式来获取参数
        System.out.println("selectUserByName...");
        String name = new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        User user = userService.selectUserByName(name);
        // 没有传‘null’
        String jsonString = JSON.toJSONString(user);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectUserByEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过get方式来获取参数
        System.out.println("selectUserByName...");
        String email = request.getParameter("email");
        User user = userService.selectUserByName(email);
        // 没有传‘null’
        String jsonString = JSON.toJSONString(user);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectUserByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过get方式来获取参数
        System.out.println("selectUserByUid...");
        String uid = request.getParameter("uid");
        User user = userService.selectUserByUID(uid);
        // 没有则传 ‘null’
        String jsonString = JSON.toJSONString(user);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectUserByLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectUserByLike...");
        String q = new String(request.getParameter("q").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }
        PageBean<User> users = userService.selectUserByLike(q, page);
        String jsonString = JSON.toJSONString(users);


        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectUserByUserFocus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectUserByUserFocus...");
        String u = request.getParameter("u");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }
        PageBean<User> users = userService.selectUserByUserFocus(u, page);
        String jsonString = JSON.toJSONString(users);


        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectUserByFocusUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectUserByFocusUser...");
        String u = request.getParameter("u");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }
        PageBean<User> users = userService.selectUserByFocusUser(u, page);
        String jsonString = JSON.toJSONString(users);


        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login...");
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        User user = JSON.parseObject(readLine, User.class);

        // 去数据库里面找到对应的user
        user = userService.login(user);
        System.out.println(user);

        String jsonString = JSON.toJSONString(user);

        response.setContentType("text/json;charset=utf-8");
        if (user != null) {
            if (user.getPermission() < 1){
                response.getWriter().write("账号已被管理员封禁，请联系管理员！");
            } else {
                // 将用户信息写回前端并发送token
                String token = JwtUtil.createToken(user.getName(), user.getUid());
                response.addCookie(new Cookie("token", token));
                HttpSession session = request.getSession();
                session.setAttribute("uid", user.getUid());
                session.setAttribute("permission", user.getPermission());

                response.getWriter().write(jsonString);
            }
        } else {
            response.getWriter().write("账号或密码错误");
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("register...");

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        String code = request.getParameter("code");

        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("code");
        if (checkCode == null || !checkCode.equalsIgnoreCase(code)) {
            response.getWriter().write("验证码错误");
            return;
        }

        // 将用户填写的数据转成user对象
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        User user = JSON.parseObject(readLine, User.class);
        user.setSignature("这个少年很懒...");
        System.out.println(user);

        // 写进数据库
        String uid = userService.addUser(user);

        response.getWriter().write(JSON.toJSONString(uid));
    }

    public void sendCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("sendCode...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        String name = new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        String email = request.getParameter("email");

        //判断邮箱是否合法
        if (!CheckUtil.checkQQEmail(email)) {
            response.getWriter().write("邮箱不合法");
            return;
        }


        // 先判断name和email是否已经注册过
        if (userService.selectUserByName(name) != null) {
            response.getWriter().write("用户名已注册");
            return;
        }
        if (userService.selectUserByEmail(email) != null) {
            response.getWriter().write("邮箱已注册");
            return;
        }

        HttpSession session = request.getSession();
        String s = (String) session.getAttribute("code");
        System.out.println(s);
        if (!"null".equals(s + "")) {
            response.getWriter().write("验证码还在有效期内...");
            return;
        }

        // 发送验证码
        try {
            String code = SendEmailUtils.to(email, name);

            // 将code放入session中
            session.setMaxInactiveInterval(5 * 60);
            session.removeAttribute("code");
            session.setAttribute("code", code);
        } catch (Exception e) {
            response.getWriter().write("出错...");
            e.printStackTrace();
        }

        response.getWriter().write("ok");
    }

    public void leave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                cookie.setValue("");
            }
        }
    }

    public void changePersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");

        // 将数据转成user对象
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        User user = JSON.parseObject(readLine, User.class);
        System.out.println(user);

        userService.changePersonal(user);
        response.getWriter().write("ok");
    }

    public void updatePermission(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");

        String uid = request.getParameter("uid");
        String permissionStr = request.getParameter("permission");
        int permission = 1;
        if (permissionStr != null && permissionStr.length() > 0){
            permission = Integer.parseInt(permissionStr);
        }

        userService.updatePermission(uid, permission);
        response.getWriter().write("ok");
    }

    public void maAge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");

        // 得到数据并转为码龄
        long age = Long.parseLong(request.getParameter("uid")) >> 2;
        int maAge = DateUtils.maAge(age);


        response.getWriter().write(maAge + "");
    }

}

package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.UserCollects;
import pojo.UserLikes;
import service.BlogService;
import service.Impl.BlogServiceImpl;
import service.Impl.UserCollectsServiceImpl;
import service.Impl.UserLikesServiceImpl;
import service.UserCollectsService;
import service.UserLikesService;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userCollects/*")
public class UserCollectsServlet extends BaseServlet {
    private final UserCollectsService userCollectsService = new UserCollectsServiceImpl();


    public void selectUserCollects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectUserCollects...");

        // 将数据转成 UserLikes 对象
        UserCollects userCollects = new UserCollects(request.getParameter("userUid"), Integer.parseInt(request.getParameter("blogId")));

        userCollects = userCollectsService.selectUserCollects(userCollects);
        response.getWriter().write(JSON.toJSONString(userCollects));
    }

    public void changeUserCollects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("changeUserCollects...");

        // 将数据转成 UserLikes 对象
        UserCollects userCollects = new UserCollects(request.getParameter("userUid"), Integer.parseInt(request.getParameter("blogId")));

        try {
            if (userCollectsService.selectUserCollects(userCollects) == null){
                userCollectsService.addUserCollects(userCollects);
            } else {
                userCollectsService.deleteUserCollects(userCollects);
            }
            BlogService blogService = new BlogServiceImpl();
            blogService.upDataBlogLikeAndCollect(userCollects.getBlogId());

            response.getWriter().write("ok");
        } catch (Exception e) {
            response.getWriter().write("no");
        }
    }

    public void deleteUserCollects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("deleteUserCollects...");

        // 将数据转成 UserLikes 对象
        UserCollects userCollects = new UserCollects(request.getParameter("userUid"), Integer.parseInt(request.getParameter("blogId")));

        try {
            userCollectsService.deleteUserCollects(userCollects);
            response.getWriter().write("ok");
        } catch (Exception e) {
            response.getWriter().write("no");
        }
    }


}

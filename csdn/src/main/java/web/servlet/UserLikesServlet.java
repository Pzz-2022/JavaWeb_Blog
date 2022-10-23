package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.UserLikes;
import service.BlogService;
import service.Impl.BlogServiceImpl;
import service.Impl.UserLikesServiceImpl;
import service.UserLikesService;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userLikes/*")
public class UserLikesServlet extends BaseServlet {
    private final UserLikesService userLikesService = new UserLikesServiceImpl();

    public void selectUserLikes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectUserLikes...");

        // 将数据转成 UserLikes 对象
        UserLikes userLike = new UserLikes(request.getParameter("userUid"), Integer.parseInt(request.getParameter("blogId")));

        userLike = userLikesService.selectUserLikes(userLike);
        response.getWriter().write(JSON.toJSONString(userLike));
    }

    public void changeUserLikes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("changeUserLikes...");

        // 将数据转成 UserLikes 对象
        UserLikes userLike = new UserLikes(request.getParameter("userUid"), Integer.parseInt(request.getParameter("blogId")));

        try {
            if (userLikesService.selectUserLikes(userLike) == null){
                userLikesService.addUserLikes(userLike);
            }else {
                userLikesService.deleteUserLike(userLike);
            }
            BlogService blogService = new BlogServiceImpl();
            blogService.upDataBlogLikeAndCollect(userLike.getBlogId());

            response.getWriter().write("ok");
        } catch (Exception e) {
            response.getWriter().write("no");
        }
    }

    public void deleteUserLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("deleteUserLike...");

        // 将数据转成 UserLikes 对象
        UserLikes userLike = new UserLikes(request.getParameter("userUid"), Integer.parseInt(request.getParameter("blogId")));

        try {
            userLikesService.deleteUserLike(userLike);
            response.getWriter().write("ok");
        } catch (Exception e) {
            response.getWriter().write("no");
        }
    }


}

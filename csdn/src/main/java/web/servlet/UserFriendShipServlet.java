package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.UserFriendShip;
import service.Impl.UserFriendShipServiceImpl;
import service.UserFriendShipService;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userFriendShip/*")
public class UserFriendShipServlet extends BaseServlet {
    private final UserFriendShipService userFriendShipService = new UserFriendShipServiceImpl();

    public void changeUserFriendShip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("changeUserFriendShip...");

        // 将数据转成 UserFriendShip 对象
        UserFriendShip userFriendShip = new UserFriendShip(request.getParameter("UserUid"),
                request.getParameter("UserFriendUid"), Integer.parseInt(request.getParameter("status")));

        userFriendShipService.changeUserFriendShip(userFriendShip);

        response.getWriter().write("ok");
    }

    public void searchFriendShip(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("searchFriendShip...");

        // 将数据转成 UserFriendShip 对象
        UserFriendShip userFriendShip = new UserFriendShip(request.getParameter("UserUid"), request.getParameter("UserFriendUid"), 1);

        userFriendShip = userFriendShipService.searchFriendShip(userFriendShip);

        response.getWriter().write(JSON.toJSONString(userFriendShip));
    }
}

package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.Label;
import service.Impl.LabelServiceImpl;
import service.LabelService;
import web.BaseServlet;
import web.BlogUpLoadServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/label/*")
public class LabelServlet extends BaseServlet {
    private final LabelService labelService = new LabelServiceImpl();

    public void selectAllLabel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectAllLabel...");
        Label[] labels = labelService.selectAll();
        String jsonString = JSON.toJSONString(labels);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectAllByBlogId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectAllByBlogId...");
        String blogId = request.getParameter("blogId");
        int blog = Integer.parseInt(blogId);
        Label[] labels = labelService.selectAllByBlogId(blog);

        System.out.println(Arrays.toString(labels));

        String jsonString = JSON.toJSONString(labels);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void updateLabelDescription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateLabelDescription...");

        String description = request.getParameter("description");
        String labelIdStr = request.getParameter("labelId");
        int labelId = Integer.parseInt(labelIdStr);

        labelService.updateLabelDescription(labelId, description);

        response.getWriter().write("ok");
    }

    public void selectCountByLabelId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectCountByLabelId...");

        String labelIdStr = request.getParameter("labelId");
        int labelId = Integer.parseInt(labelIdStr);

        int count = labelService.selectCountByLabelId(labelId);

        response.getWriter().write(count + "");
    }


}

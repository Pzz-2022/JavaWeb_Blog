package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.Sort;
import service.Impl.SortServiceImpl;
import service.SortService;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/sort/*")
public class SortServlet extends BaseServlet {
    private final SortService sortService = new SortServiceImpl();


    public void selectAllSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectAllSort...");
        Sort[] sorts = sortService.selectAll();
        String jsonString = JSON.toJSONString(sorts);

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectAllSortByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectAllSortByUid...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");

        String uid = request.getParameter("Uid");
        Sort[] sorts = sortService.selectAllByUid(uid);
        String jsonString = JSON.toJSONString(sorts);

        response.getWriter().write(jsonString);
    }

    public void selectAllByBlogId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectAllByBlogId...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");

        String blogIdStr = request.getParameter("blogId");
        int blogId = Integer.parseInt(blogIdStr);
        Sort[] sorts = sortService.selectAllByBlogId(blogId);
        String jsonString = JSON.toJSONString(sorts);

        response.getWriter().write(jsonString);
    }

    public void selectCountBySortName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("selectCountBySortName...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");

        String name = new String(request.getParameter("name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        int count = sortService.selectAllBySortName(name);

        response.getWriter().write(count + "");
    }

    public void updateSortName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("updateSortName...");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");

        String lastName = new String(request.getParameter("lastName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String nextName = new String(request.getParameter("nextName").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String uid = request.getParameter("uid");

        String msg = "ok";
        if (lastName.equals(nextName)){
            msg = "名字未变。";
        } else {
            if (sortService.selectBySort(new Sort(uid, nextName)) != null){
                msg = "已有该分类。";
                System.out.println(sortService.selectBySort(new Sort(uid, nextName)));
            } else {
                sortService.updateSortName(lastName, nextName);
            }
        }

        response.getWriter().write(msg);
    }
}

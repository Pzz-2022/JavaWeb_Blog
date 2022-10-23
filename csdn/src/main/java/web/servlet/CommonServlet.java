package web.servlet;

import com.alibaba.fastjson.JSON;
import pojo.Common;
import pojo.PageBean;
import service.CommonService;
import service.Impl.CommonServiceImpl;
import util.DateUtils;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/common/*")
public class CommonServlet extends BaseServlet {
    private final CommonService commonService = new CommonServiceImpl();

    public void addRootCommon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("addRootCommon...");

        BufferedReader reader = request.getReader();
        String commonStr = reader.readLine();
        Common common = JSON.parseObject(commonStr, Common.class);
        common.setDate(DateUtils.getDate2());

        System.out.println(common);

        commonService.addRootCommon(common);
        System.out.println(common);

        response.getWriter().write("ok");
    }

    public void addChildCommon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("addChildCommon...");

        BufferedReader reader = request.getReader();
        String commonStr = reader.readLine();
        Common common = JSON.parseObject(commonStr, Common.class);
        common.setDate(DateUtils.getDate2());

        commonService.addChildCommon(common);
        System.out.println(common);

        response.getWriter().write("ok");
    }

    public void selectRootByBlogId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectRootByBlogId...");

        // 获取数据
        String blogIdStr = request.getParameter("blogId");
        int blogId = Integer.parseInt(blogIdStr);

        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }


        // 得到 Blog 对象
        PageBean<Common> commonPageBean = commonService.selectRootByBlogId(blogId, page);
        response.getWriter().write(JSON.toJSONString(commonPageBean));
    }


}

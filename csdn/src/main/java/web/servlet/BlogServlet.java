package web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.Blog;
import pojo.PageBean;
import service.BlogService;
import service.Impl.BlogServiceImpl;
import service.Impl.LabelServiceImpl;
import service.Impl.SortServiceImpl;
import service.LabelService;
import service.SortService;
import web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/blog/*")
public class BlogServlet extends BaseServlet {
    private final BlogService blogService = new BlogServiceImpl();

    public void addBlog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("addBlog...");

        // 将数据转成Blog对象
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        Blog blog = JSON.parseObject(readLine, Blog.class);
        JSONObject jsonObject = JSON.parseObject(readLine);

        String[] blogLabels = JSON.parseObject(JSON.toJSONString(jsonObject.get("blogLabel")), String[].class);
        String[] blogSorts = JSON.parseObject(JSON.toJSONString(jsonObject.get("blogSort")), String[].class);
//        System.out.println(Arrays.toString(blogLabels));
//        System.out.println(Arrays.toString(blogSorts));

        // 在中间表（标签表和分类表中添加数据）
        LabelService labelService = new LabelServiceImpl();
        SortService sortService = new SortServiceImpl();


        if (blog.getBlogId() == 0) {
            blogService.addBlog(blog);
        } else {
            blogService.changeBlog(blog);

            blogService.deleteLabelAndSort((int) blog.getBlogId());
        }
        labelService.addBlogLabel(blog.getBlogId(), blogLabels);
        sortService.addBlogSort(blog, blogSorts);
        System.out.println(blog);
        response.getWriter().write("ok");
    }

    public void selectBlogByBlogId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectBlogByBlogId...");

        // 获取数据
        String blogIdStr = request.getParameter("blogId");
        int blogId = Integer.parseInt(blogIdStr);

        // 浏览量+1 利用Session设置防刷
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(30 * 60);
        if (session.getAttribute("vis") == null) {
            List<Integer> vis = new ArrayList<>();
            session.setAttribute("vis", vis);
        }
        List<Integer> vis = (List<Integer>) session.getAttribute("vis");
        if (!vis.contains(blogId)) {
            blogService.updateViews(blogId);
            vis.add(blogId);
        }

        // 得到 Blog 对象
        Blog blog = blogService.selectBlogByBlogId(blogId);
        response.getWriter().write(JSON.toJSONString(blog));
    }

    public void selectAllByLikeAndUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAllByLikeAndUid...");

        String q = new String(request.getParameter("q").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String uid = request.getParameter("u");
        String pageStr = request.getParameter("page");
        String sortStr = request.getParameter("sort");
        String labelStr = request.getParameter("label");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }
        int sort = 0;
        if (sortStr != null && sortStr.length() > 0) {
            sort = Integer.parseInt(sortStr);
        }
        int label = 0;
        if (labelStr != null && labelStr.length() > 0) {
            label = Integer.parseInt(labelStr);
        }


        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页

        if ("".equals(uid)) {
            blogs = blogService.selectAllByLike(q, page, sort, label);
        } else {
            blogs = blogService.selectAllByLikeAndUid(q, uid, page, sort, label);
        }
        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void selectAllByUidDraft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAllByUidDraft...");

        String uid = request.getParameter("u");
        String statusStr = request.getParameter("status");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }
        int status = 2;
        if (statusStr != null && statusStr.length() > 0) {
            status = Integer.parseInt(statusStr);
        }

        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页
        blogs = blogService.selectAllByUidDraft(uid, page, status);

        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void selectAllByUidLikes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAllByUidLikes...");

        String uid = request.getParameter("u");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }

        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页
        blogs = blogService.selectAllByUidLikes(uid, page);

        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void selectAllByUidCollects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAllByUidCollects...");

        String uid = request.getParameter("u");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }

        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页
        blogs = blogService.selectAllByUidCollects(uid, page);

        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAll...");

        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }

        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页
        blogs = blogService.selectAll(page);

        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void selectAllByMyCreationCenter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAllByLikeAndUid...");

        String q = new String(request.getParameter("q").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String uid = request.getParameter("u");
        String sortStr = request.getParameter("sort");
        String time = request.getParameter("time");
        String pageStr = request.getParameter("page");
        String originalStr = request.getParameter("original");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }
        int original = -1;
        if (originalStr != null && originalStr.length() > 0) {
            original = Integer.parseInt(originalStr);
        }
        int sort = 0;
        if (sortStr != null && sortStr.length() > 0) {
            sort = Integer.parseInt(sortStr);
        }

        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页

        blogs = blogService.selectAllByMyCreationCenter(q, uid, page, sort, original, time);
        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void selectAllByViews(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAllByViews...");

        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页

        blogs = blogService.selectAllByViews();
        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void selectAllByPermission(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("selectAllByPermission...");

        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && pageStr.length() > 0) {
            page = Integer.parseInt(pageStr);
        }

        PageBean<Blog> blogs;
        // 将博文和用户的搜索分页

        blogs = blogService.selectAllByPermission(page);
        response.getWriter().write(JSON.toJSONString(blogs));
    }

    public void updateOrderByBlogId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("updateOrderByBlogId...");

        String blogIdStr = request.getParameter("blogId");
        int blogId = Integer.parseInt(blogIdStr);
        String orderStr = request.getParameter("order");
        int order = 1;
        if (orderStr != null && orderStr.length() > 0) {
            order = Integer.parseInt(orderStr);
        }


        blogService.updateOrderByBlogId(blogId, order);
        response.getWriter().write("ok");
    }

    public void updateStatusByBlogId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/json;charset=utf-8");
        System.out.println("updateOrderByBlogId...");

        String blogIdStr = request.getParameter("blogId");
        int blogId = Integer.parseInt(blogIdStr);
        String statusStr = request.getParameter("status");
        int status = -1;
        if (statusStr != null && statusStr.length() > 0) {
            status = Integer.parseInt(statusStr);
        }


        blogService.updateStatusByBlogId(blogId, status);
        response.getWriter().write("ok");
    }
}

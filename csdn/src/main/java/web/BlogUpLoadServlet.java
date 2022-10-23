package web;

import com.alibaba.fastjson.JSONObject;
import sun.nio.cs.HistoricallyNamedCharset;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@WebServlet("/blogUpLoadServlet")
@MultipartConfig(
        // 限制大小（可不加）
        maxFileSize = 15*1024*1024
)
public class BlogUpLoadServlet extends HttpServlet{
    private static final String PATH_IMG = "D:\\Software\\tutorial\\git2\\csdn\\src\\main\\webapp\\images/";
    // 博客中的图片上传, 返回JSON状态
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();

        Part part = request.getPart("editormd-image-file");
        String cd = part.getHeader("Content-Disposition");
        cd = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);
        String filename = UUID.randomUUID().toString() + cd.substring(cd.lastIndexOf('.'));

        try {
            part.write(PATH_IMG + filename);
            jsonObject.put("success", 1);
            jsonObject.put("url", "http://localhost:8080/brand-case/images/" + filename);
        } catch (IOException e) {
            jsonObject.put("success", 0);
        } finally {
            response.getWriter().write(jsonObject.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
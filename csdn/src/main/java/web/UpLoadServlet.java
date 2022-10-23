package web;

import service.Impl.UserServiceImpl;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


@MultipartConfig(
        maxFileSize = 15*1024*1024
)
@WebServlet("/upLoadServlet")
public class UpLoadServlet extends HttpServlet {
    private static final String PATH_IMG = "D:\\Software\\tutorial\\git2\\csdn\\src\\main\\webapp\\images/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 万能上传
        String type = request.getParameter("type");

        if ("head".equals(type)){
            String uid = request.getParameter("uid");
            Part headFile = request.getPart("headFile");
            if (headFile.getContentType().startsWith("image")){
//                String fileName = uid + "_" + headFile.getSubmittedFileName();
                String fileName = request.getParameter("fileName");

                headFile.write(PATH_IMG + fileName);

                UserService userService = new UserServiceImpl();
                userService.changeHead(uid, new String(fileName.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
                System.out.println(uid + "修改头像成功!");
            }
            response.sendRedirect("/brand-case/personalCenter.html");

        } else if ("blogCover".equals(type)){
            String imgName = request.getParameter("imgName");
            Part Img = request.getPart("blogCoverImg");
            if (Img.getContentType().startsWith("image")){
                Img.write(PATH_IMG + imgName);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

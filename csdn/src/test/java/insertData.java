import org.junit.Test;
import pojo.Blog;
import pojo.User;
import service.BlogService;
import service.Impl.BlogServiceImpl;
import service.Impl.UserServiceImpl;
import service.UserService;

public class insertData {
    @Test
    public void addUser() {
        UserService userService = new UserServiceImpl();

        User user = new User("2", "2", "e10adc3949ba59abbe56e057f20f883e", "2", "defaultHead.png", 1, '男', "2", "2022-10-16");
        for (int i = 2; i <= 1000; i++) {
            user.setUid(i + "");
            user.setName(i + "号");
            user.setEmail(i + "");
            user.setSignature(i + "");

            userService.addUser(user);
        }
    }

    @Test
    public void updateUser() {
        UserService userService = new UserServiceImpl();

        userService.updateUserSignature();
    }

    @Test
    public void addBlog() {
        BlogService blogService = new BlogServiceImpl();

        Blog blog = new Blog();
        blog.setBlogDate("2022-10-16");
        blog.setBlogViews(0);
        blog.setBlogLikes(0);
        blog.setBlogCollects(0);
        blog.setOrder(15);
        blog.setOriginal(1);
        blog.setStatus(2);
        blog.setBlogCover("");

        for (int i = 2; i <= 1000; i++) {
            blog.setUserUid(i + "");
            blog.setBlogTitle(i + "号博客");
            blog.setBlogContent("#" + i + "号博客");
            blog.setBlogAbstract(i + "号博客");

            blogService.addBlog(blog);
        }
    }
}

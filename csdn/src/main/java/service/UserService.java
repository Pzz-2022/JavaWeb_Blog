package service;

import pojo.PageBean;
import pojo.User;

import java.util.List;

public interface UserService {
   String addUser(User user);

   void updateUserSignature();

   boolean checkUser(String uid, String password);

   User selectUserByUID(String uid);

   User selectUserByEmail(String email);

   User selectUserByName(String name);

   PageBean<User> selectUserByLike(String q, int page);

   User login(User user);

   void  changePersonal(User user);

   void changeHead(String uid, String imgName);

   void updatePermission(String uid, int permission);

    PageBean<User> selectUserByUserFocus(String u, int page);

    PageBean<User> selectUserByFocusUser(String u, int page);
}

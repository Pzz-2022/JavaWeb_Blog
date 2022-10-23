package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.User;

public interface UserMapper {

    User selectUserByUID(String uid);

    User selectUserByEmail(String email);

    void addUser(User user);

    void updateUserSignature();

    User selectUserByName(String name);

    User login(@Param("uid") String uid, @Param("email") String email);

    void updateUser(User user);

    void updateHeadPortrait(@Param("uid")String uid, @Param("imgName")String imgName);

    User[] selectUserByLike(@Param("q") String q, @Param("index") int index);

    User[] selectUserByUserFocus(@Param("u") String u, @Param("index") int index);

    User[] selectUserByFocusUser(@Param("u") String u, @Param("index") int index);

    int selectUserByLikeTotal(@Param("q") String q);

    int selectUserByUserFocusTotal(@Param("u") String u);

    int selectUserByFocusUserTotal(@Param("u") String u);

    void updatePermission(@Param("uid") String uid, @Param("permission") int permission);
}

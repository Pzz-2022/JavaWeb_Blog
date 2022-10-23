package mapper;

import pojo.UserCollects;

public interface UserCollectsMapper {
    UserCollects selectUserCollect(UserCollects userCollects);

    void addUserCollect(UserCollects userCollects);

    void deleteUserCollect(UserCollects userCollects);
}

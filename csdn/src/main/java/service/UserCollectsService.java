package service;

import pojo.UserCollects;

public interface UserCollectsService {
    UserCollects selectUserCollects(UserCollects userCollects);

    void addUserCollects(UserCollects userCollects);

    void deleteUserCollects(UserCollects userCollects);
}

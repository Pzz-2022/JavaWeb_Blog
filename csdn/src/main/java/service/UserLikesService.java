package service;

import pojo.UserLikes;

public interface UserLikesService {
    UserLikes selectUserLikes(UserLikes userLikes);

    void addUserLikes(UserLikes userLikes);

    void deleteUserLike(UserLikes userLikes);
}

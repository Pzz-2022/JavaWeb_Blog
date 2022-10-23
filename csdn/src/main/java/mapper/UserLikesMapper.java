package mapper;

import pojo.UserLikes;

public interface UserLikesMapper {
    UserLikes selectUserLikes(UserLikes userLikes);

    void addUserLikes(UserLikes userLikes);

    void deleteUserLike(UserLikes userLikes);
}

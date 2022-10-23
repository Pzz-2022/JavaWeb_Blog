package mapper;

import pojo.UserFriendShip;

public interface UserFriendShipMapper {
    void addUserFriendShip(UserFriendShip userFriendShip);

    UserFriendShip searchFriendShip(UserFriendShip userFriendShip);

    void updateStatusByUserUidAndUserFriendUid(UserFriendShip userFriendShip);
}

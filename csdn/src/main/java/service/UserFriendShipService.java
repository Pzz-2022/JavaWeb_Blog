package service;

import pojo.UserFriendShip;

public interface UserFriendShipService {
    void changeUserFriendShip(UserFriendShip userFriendShip);

    UserFriendShip searchFriendShip(UserFriendShip userFriendShip);
}

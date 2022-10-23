package service.Impl;

import mapper.UserFriendShipMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.UserFriendShip;
import service.UserFriendShipService;
import util.SqlSessionFactoryUtils;

public class UserFriendShipServiceImpl implements UserFriendShipService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public void changeUserFriendShip(UserFriendShip userFriendShip) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserFriendShipMapper mapper = sqlSession.getMapper(UserFriendShipMapper.class);

        // 如果没有则未 null
        if (mapper.searchFriendShip(userFriendShip) == null) {
            mapper.addUserFriendShip(userFriendShip);
        } else {
            mapper.updateStatusByUserUidAndUserFriendUid(userFriendShip);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public UserFriendShip searchFriendShip(UserFriendShip userFriendShip) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserFriendShipMapper mapper = sqlSession.getMapper(UserFriendShipMapper.class);

        userFriendShip = mapper.searchFriendShip(userFriendShip);

        sqlSession.close();
        return userFriendShip;
    }

}

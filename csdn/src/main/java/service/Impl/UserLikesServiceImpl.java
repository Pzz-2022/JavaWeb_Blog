package service.Impl;

import mapper.UserLikesMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import pojo.UserLikes;
import service.UserLikesService;
import util.SqlSessionFactoryUtils;

public class UserLikesServiceImpl implements UserLikesService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public UserLikes selectUserLikes(UserLikes userLikes) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserLikesMapper mapper = sqlSession.getMapper(UserLikesMapper.class);
        userLikes = mapper.selectUserLikes(userLikes);

        sqlSession.close();
        return userLikes;
    }

    @Override
    public void addUserLikes(UserLikes userLikes) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserLikesMapper mapper = sqlSession.getMapper(UserLikesMapper.class);
        mapper.deleteUserLike(userLikes);
        sqlSession.commit();

        mapper.addUserLikes(userLikes);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteUserLike(UserLikes userLikes) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserLikesMapper mapper = sqlSession.getMapper(UserLikesMapper.class);
        mapper.deleteUserLike(userLikes);

        sqlSession.commit();
        sqlSession.close();
    }

}

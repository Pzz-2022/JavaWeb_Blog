package service.Impl;

import mapper.UserCollectsMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.UserCollects;
import service.UserCollectsService;
import util.SqlSessionFactoryUtils;

public class UserCollectsServiceImpl implements UserCollectsService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public UserCollects selectUserCollects(UserCollects userCollects) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserCollectsMapper mapper = sqlSession.getMapper(UserCollectsMapper.class);
        userCollects = mapper.selectUserCollect(userCollects);

        sqlSession.close();
        return userCollects;
    }

    @Override
    public void addUserCollects(UserCollects userCollects) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserCollectsMapper mapper = sqlSession.getMapper(UserCollectsMapper.class);
        mapper.deleteUserCollect(userCollects);
        sqlSession.commit();

        mapper.addUserCollect(userCollects);
        sqlSession.commit();

        sqlSession.close();
    }

    @Override
    public void deleteUserCollects(UserCollects userCollects) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserCollectsMapper mapper = sqlSession.getMapper(UserCollectsMapper.class);
        mapper.deleteUserCollect(userCollects);

        sqlSession.commit();
        sqlSession.close();
    }
}

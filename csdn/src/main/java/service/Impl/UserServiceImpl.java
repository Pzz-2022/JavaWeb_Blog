package service.Impl;

import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.PageBean;
import pojo.User;
import service.UserService;
import util.SnowIDUtils;
import util.SqlSessionFactoryUtils;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public String addUser(User user) {
        //使用雪花算法生成UID和添加默认头像 封装在user中
        String uid = String.valueOf(SnowIDUtils.getSnowID());
        user.setUid(uid);
        user.setHeadPortrait("defaultHead.png");
        user.setSignature("这个少年很懒...");

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);

        sqlSession.commit();
        sqlSession.close();
        return uid;
    }

    @Override
    public void updateUserSignature() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateUserSignature();

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public boolean checkUser(String uid, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByUID(uid);

        sqlSession.close();
        return user.getPassword().equals(password);
    }

    @Override
    public User selectUserByEmail(String email) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByEmail(email);

        sqlSession.close();
        return user;
    }

    @Override
    public User selectUserByUID(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByUID(uid);
        user.setPassword("");

        sqlSession.close();
        return user;
    }

    @Override
    public User selectUserByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectUserByName(name);

        sqlSession.close();
        return user;
    }

    @Override
    public PageBean<User> selectUserByLike(String name, int page) {
        int index = (page - 1) * 10;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String sign = "%";
        User[] users = userMapper.selectUserByLike(sign + name + sign, index);
        int totalCount = userMapper.selectUserByLikeTotal(sign + name + sign);
        int totalPage = totalCount % 10 == 0 ? totalCount / 10 : totalCount / 10 + 1;

        PageBean<User> userPageBean = new PageBean<User>(totalCount, totalPage, page, 10, Arrays.asList(users));

        sqlSession.close();
        return userPageBean;
    }

    @Override
    public User login(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User userResult = userMapper.login(user.getUid(), user.getUid());

        sqlSession.close();
        if (userResult.getPassword().equals(user.getPassword())){
            userResult.setPassword("");
            return userResult;
        }
        return null;
    }

    @Override
    public void changePersonal(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(user);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void changeHead(String uid, String imgName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateHeadPortrait(uid, imgName);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updatePermission(String uid, int permission) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updatePermission(uid, permission);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<User> selectUserByUserFocus(String u, int page) {
        int index = (page - 1) * 10;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User[] users = userMapper.selectUserByUserFocus(u, index);
        int totalCount = userMapper.selectUserByUserFocusTotal(u);
        int totalPage = totalCount % 10 == 0 ? totalCount / 10 : totalCount / 10 + 1;

        PageBean<User> userPageBean = new PageBean<User>(totalCount, totalPage, page, 10, Arrays.asList(users));

        sqlSession.close();
        return userPageBean;
    }

    @Override
    public PageBean<User> selectUserByFocusUser(String u, int page) {
        int index = (page - 1) * 10;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User[] users = userMapper.selectUserByFocusUser(u, index);
        int totalCount = userMapper.selectUserByFocusUserTotal(u);
        int totalPage = totalCount % 10 == 0 ? totalCount / 10 : totalCount / 10 + 1;

        PageBean<User> userPageBean = new PageBean<User>(totalCount, totalPage, page, 10, Arrays.asList(users));

        sqlSession.close();
        return userPageBean;
    }

}

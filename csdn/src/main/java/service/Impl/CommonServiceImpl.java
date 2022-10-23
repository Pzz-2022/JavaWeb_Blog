package service.Impl;

import mapper.CommonMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Common;
import pojo.PageBean;
import service.CommonService;
import util.CommonUtil;
import util.SqlSessionFactoryUtils;

import java.util.Arrays;

public class CommonServiceImpl implements CommonService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public void addRootCommon(Common common) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        mapper.addRootCommon(common);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void addChildCommon(Common common) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);
        mapper.addChildCommon(common);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Common> selectRootByBlogId(int blogId, int page) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CommonMapper mapper = sqlSession.getMapper(CommonMapper.class);

        int pageSize = 5;
        int index = (page - 1) * pageSize;

        int total = mapper.selectRootByBlogIdTot(blogId);
        Common[] commons = mapper.selectRootByBlogId(blogId, index, pageSize);

        CommonUtil.addChildCommon(commons);

        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        PageBean<Common> commonPageBean = new PageBean<>(total, totalPage, page, pageSize, Arrays.asList(commons));

        sqlSession.close();
        return commonPageBean;
    }
}

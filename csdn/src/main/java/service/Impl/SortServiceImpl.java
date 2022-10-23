package service.Impl;

import mapper.BlogSortMapper;
import mapper.SortMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Blog;
import pojo.BlogSort;
import pojo.Sort;
import service.SortService;
import util.SqlSessionFactoryUtils;

public class SortServiceImpl implements SortService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public Sort[] selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper sortMapper = sqlSession.getMapper(SortMapper.class);
        Sort[] sorts = sortMapper.selectAll();

        sqlSession.close();
        return sorts;
    }

    @Override
    public Sort[] selectAllByUid(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper sortMapper = sqlSession.getMapper(SortMapper.class);
        Sort[] sorts = sortMapper.selectAllByUid(uid);

        sqlSession.close();
        return sorts;
    }

    @Override
    public void addSort(Sort sort) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper sortMapper = sqlSession.getMapper(SortMapper.class);
        sortMapper.addSort(sort);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Sort selectBySort(Sort sort) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper sortMapper = sqlSession.getMapper(SortMapper.class);
        sort = sortMapper.selectSort(sort);

        sqlSession.close();
        return sort;
    }

    @Override
    public void addBlogSort(Blog blog, String[] sorts) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper sortMapper = sqlSession.getMapper(SortMapper.class);
        BlogSortMapper blogSortMapper = sqlSession.getMapper(BlogSortMapper.class);

        for (String sortStr : sorts) {
            Sort sort = new Sort(blog.getUserUid(), sortStr);
            Sort sort1 = sortMapper.selectSort(sort);
            if (sort1 == null){
                sortMapper.addSort(sort);
            } else {
                sort = sort1;
            }

            blogSortMapper.addBlogSort(new BlogSort((int) blog.getBlogId(), sort.getSortId()));
            sqlSession.commit();
        }

        sqlSession.close();
    }

    @Override
    public Sort[] selectAllByBlogId(int blogId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper mapper = sqlSession.getMapper(SortMapper.class);
        Sort[] sorts = mapper.selectAllByBlogId(blogId);

        sqlSession.close();
        return sorts;
    }

    @Override
    public void updateSortName(String lastName, String nextName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper mapper = sqlSession.getMapper(SortMapper.class);
        mapper.updateSortName(lastName, nextName);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public int selectAllBySortName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SortMapper mapper = sqlSession.getMapper(SortMapper.class);
        int count = mapper.selectAllBySortName(name);

        sqlSession.close();
        return count;
    }
}

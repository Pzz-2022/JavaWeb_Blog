package service.Impl;

import mapper.BlogMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Blog;
import pojo.PageBean;
import service.BlogService;
import util.DateUtils;
import util.SqlSessionFactoryUtils;

import java.util.Arrays;


public class BlogServiceImpl implements BlogService {
    private static final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public void addBlog(Blog blog) {
        blog.setBlogDate(DateUtils.getDate1());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.addBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void changeBlog(Blog blog) {
        blog.setBlogDate(DateUtils.getDate1());
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.changeBlog(blog);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateViews(int blogId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.updateViews(blogId);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteLabelAndSort(int blogId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.deleteLabel(blogId);
        blogMapper.deleteSort(blogId);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Blog selectBlogByBlogId(int blogId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectAllByBlogId(blogId);


        sqlSession.close();
        return blog;
    }

    @Override
    public void upDataBlogLikeAndCollect(int blogId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        blogMapper.updateBlogLikesAndBlogCollects(blogId);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updateOrderByBlogId(int blogId, int order) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        if (order != 0)
            blogMapper.updateOrderByBlogId(blogId, order);
        else
            blogMapper.deleteByBlogId(blogId);

        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Blog> selectAllByLikeAndUid(String q, String uid, int page, int sort, int label) {
        q = "%" + q + "%";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByLikeAndUid(q, uid, page * 10 - 10, sort, label);
        int total = mapper.selectTotBySearchUid(q, uid, sort, label);
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAllByMyCreationCenter(String q, String uid, int page, int sort, int original, String time) {
        q = "%" + q + "%";
        time = time + "%";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByMyCreationCenter(q, uid, page * 10 - 10, sort, original, time);
        int total = mapper.selectTotByMyCreationCenter(q, uid, sort, original, time);
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAllByLike(String q, int page, int sort, int label) {
        q = "%" + q + "%";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByLike(q, page * 10 - 10, sort, label);
        int total = mapper.selectTotBySearchLike(q, sort, label);
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));
        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAllByUidDraft(String uid, int page, int status) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByUidDraft(uid, page * 10 - 10, status);
        int total = mapper.selectTotByUidDraft(uid, status);
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAllByUidLikes(String uid, int page) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByUidLikes(uid, page * 10 - 10);
        int total = mapper.selectTotByUidLikes(uid);
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAllByUidCollects(String uid, int page) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByUidCollects(uid, page * 10 - 10);
        int total = mapper.selectTotByUidCollects(uid);
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAll(int page) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAll(page * 10 - 10);
        int total = mapper.selectAllTot();
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAllByPermission(int page) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByPermission(page * 10 - 10);
        int total = mapper.selectAllByPermissionTot();
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, page, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public PageBean<Blog> selectAllByViews() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);

        Blog[] blogs = mapper.selectAllByViews();
        int total = mapper.selectAllTot();
        int totalPage = total % 10 == 0 ? total / 10 : total / 10 + 1;

        PageBean<Blog> blogPageBean = new PageBean<Blog>(total, totalPage, 1, 10, Arrays.asList(blogs));

        sqlSession.close();
        return blogPageBean;
    }

    @Override
    public void updateStatusByBlogId(int blogId, int status) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        if (status != -2)
            blogMapper.updateStatusByBlogId(blogId, status);
        else
            blogMapper.deleteByBlogId(blogId);

        sqlSession.commit();
        sqlSession.close();
    }

}

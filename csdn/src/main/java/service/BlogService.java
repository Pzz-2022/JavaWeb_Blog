package service;

import pojo.Blog;
import pojo.PageBean;

public interface BlogService {
    void addBlog(Blog blog);

    void changeBlog(Blog blog);

    void updateViews(int blogId);

    void deleteLabelAndSort(int blogId);

    Blog selectBlogByBlogId(int blogId);

    void upDataBlogLikeAndCollect(int blogId);

    void updateOrderByBlogId(int blogId, int order);

    PageBean<Blog> selectAllByLikeAndUid(String q, String uid, int page, int sort, int label);

    PageBean<Blog> selectAllByMyCreationCenter(String q, String uid, int page, int sort, int original, String time);

    PageBean<Blog> selectAllByLike(String q, int page, int sort, int label);

    PageBean<Blog> selectAllByUidDraft(String uid, int page, int status);

    PageBean<Blog> selectAllByUidLikes(String uid, int page);

    PageBean<Blog> selectAllByUidCollects(String uid, int page);

    PageBean<Blog> selectAll(int page);

    PageBean<Blog> selectAllByPermission(int page);

    PageBean<Blog> selectAllByViews();

    void updateStatusByBlogId(int blogId, int status);
}

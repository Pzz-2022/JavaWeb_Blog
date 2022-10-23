package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Blog;

public interface BlogMapper {
    int addBlog(Blog blog);

    int changeBlog(Blog blog);

    Blog selectAllByBlogId(int blogId);

    void deleteByBlogId(int blogId);

    void updateViews(int blogId);

    void deleteLabel(int blogId);

    void deleteSort(int blogId);

    int updateBlogLikesAndBlogCollects(int blogId);

    int updateOrderByBlogId(@Param("blogId") int blogId,@Param("order") int order);

    Blog[] selectAllByLikeAndUid(@Param("q") String q, @Param("uid") String uid, @Param("index") int page, @Param("sort") int sort, @Param("label") int label);

    Blog[] selectAllByMyCreationCenter(@Param("q") String q, @Param("uid") String uid, @Param("index") int page, @Param("sort") int sort, @Param("original") int original, @Param("time") String time);

    Blog[] selectAllByUidDraft(@Param("uid") String uid, @Param("index") int page, @Param("status") int status);

    Blog[] selectAllByLike(@Param("q") String q, @Param("index") int page, @Param("sort") int sort, @Param("label") int label);

    Blog[] selectAllByUidLikes(@Param("uid") String uid, @Param("index") int page);

    Blog[] selectAllByUidCollects(@Param("uid") String uid, @Param("index") int page);

    int selectTotBySearchLike(@Param("q") String q, @Param("sort") int sort, @Param("label") int label);

    int selectTotBySearchUid(@Param("q") String q, @Param("uid") String uid, @Param("sort") int sort, @Param("label") int label);

    int selectTotByMyCreationCenter(@Param("q") String q, @Param("uid") String uid, @Param("sort") int sort, @Param("original") int original, @Param("time") String time);

    int selectTotByUidDraft( @Param("uid") String uid, @Param("status") int status);

    int selectTotByUidLikes(String uid);

    int selectTotByUidCollects(String uid);

    int selectAllTot();

    int selectAllByPermissionTot();

    Blog[] selectAll(int index);

    Blog[] selectAllByPermission(int index);

    Blog[] selectAllByViews();

    void updateStatusByBlogId(@Param("blogId")int blogId, @Param("status") int status);
}

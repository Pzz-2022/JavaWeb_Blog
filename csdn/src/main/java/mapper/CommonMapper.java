package mapper;


import org.apache.ibatis.annotations.Param;
import pojo.Common;

public interface CommonMapper {
    void addRootCommon(Common common);

    void addChildCommon(Common common);

    Common[] selectRootByBlogId(@Param("blogId") int blogId, @Param("index") int index,@Param("pageSize")int pageSize);

    int selectRootByBlogIdTot(@Param("blogId") int blogId);

    Common[] selectChildByRootId(int rootId);
}

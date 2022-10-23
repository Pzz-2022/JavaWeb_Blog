package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Sort;

public interface SortMapper {
    void addSort(Sort sort);

    void updateSortName(@Param("lastName") String lastName, @Param("nextName") String nextName);

    Sort[] selectAll();

    Sort[] selectAllByUid(String Uid);

    Sort selectSort(Sort sort);

    Sort[] selectAllByBlogId(int blogId);

    int selectAllBySortName(String name);
}

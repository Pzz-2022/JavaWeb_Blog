package service;

import pojo.Blog;
import pojo.Sort;

public interface SortService {
    Sort[] selectAll();

    Sort[] selectAllByUid(String uid);

    void addSort(Sort sort);

    Sort selectBySort(Sort sort);

    void addBlogSort(Blog blog, String[] sorts);

    Sort[] selectAllByBlogId(int blogId);

    void updateSortName(String lastName, String nextName);

    int selectAllBySortName(String name);
}

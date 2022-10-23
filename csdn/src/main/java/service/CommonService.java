package service;

import pojo.Common;
import pojo.PageBean;

public interface CommonService {
    void addRootCommon(Common common);

    void addChildCommon(Common common);

    PageBean<Common> selectRootByBlogId(int blogId, int page);
}


package service;

import pojo.Label;

public interface LabelService {
    Label[] selectAll();

    Label selectLabelByName(String name);

    void addLabel(Label label);

    void addBlogLabel(long blogId, String[] labels);

    Label[] selectAllByBlogId(int blogId);

    void updateLabelDescription(int labelId, String description);

    int selectCountByLabelId(int labelId);
}


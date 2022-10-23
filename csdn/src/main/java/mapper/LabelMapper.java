package mapper;

import org.apache.ibatis.annotations.Param;
import pojo.Label;

public interface LabelMapper {
    void addLabel(Label label);

    Label[] selectAll();

    Label selectLabelByName(String name);

    Label[] selectAllByBlogId(int blogId);

    void updateLabelDescription(@Param("labelId") int labelId, @Param("description") String description);

    int selectCountByLabelId(int labelId);
}

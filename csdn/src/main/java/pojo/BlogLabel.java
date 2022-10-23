package pojo;

public class BlogLabel {
    private int id;
    private int blogId;
    private int labelId;

    public BlogLabel() {
    }

    public BlogLabel(int blogId, int labelId) {
        this.blogId = blogId;
        this.labelId = labelId;
    }

    public BlogLabel(int id, int blogId, int labelId) {
        this.id = id;
        this.blogId = blogId;
        this.labelId = labelId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    @Override
    public String toString() {
        return "BlogLabel{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", labelId=" + labelId +
                '}';
    }
}

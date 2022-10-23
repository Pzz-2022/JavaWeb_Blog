package pojo;

public class BlogSort {
    private int id;
    private int blogId;
    private int sortId;


    public BlogSort() {
    }

    public BlogSort(int blogId, int sortId) {
        this.blogId = blogId;
        this.sortId = sortId;
    }

    public BlogSort(int id, int blogId, int sortId) {
        this.id = id;
        this.blogId = blogId;
        this.sortId = sortId;
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

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    @Override
    public String toString() {
        return "BlogSort{" +
                "id=" + id +
                ", blogId=" + blogId +
                ", sortId=" + sortId +
                '}';
    }
}

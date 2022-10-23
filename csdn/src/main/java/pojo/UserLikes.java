package pojo;

public class UserLikes {
    private long id;
    private String userUid;
    private int blogId;

    public UserLikes() {
    }

    public UserLikes(String userUid, int blogId) {
        this.userUid = userUid;
        this.blogId = blogId;
    }

    public UserLikes(long id, String userUid, int blogId) {
        this.id = id;
        this.userUid = userUid;
        this.blogId = blogId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Override
    public String toString() {
        return "UserLikes{" +
                "id=" + id +
                ", userUid='" + userUid + '\'' +
                ", blogId=" + blogId +
                '}';
    }
}

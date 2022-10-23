package pojo;

public class Blog {
    private long blogId;//'博文ID',
    private String userUid;//'用户ID',
    private String blogTitle;//'博文标题',
    private String blogContent;//'博文内容',
    private String blogDate;//'发布时间',
    private int blogViews;//'博文浏览量',
    private int blogLikes;//'博文点赞数量',
    private int blogCollects;//'博文收藏数量',
    private int order;//博客排名
    private int original;// 是否原创
    private int status;// 博客状态
    private String blogCover;// 博客封面
    private String blogAbstract;// 博客摘要


    public Blog() {
    }

    public Blog(String userUid, String blogTitle, String blogContent, String blogDate, int blogViews, int blogLikes, int blogCollects, int order, int original, int status, String blogCover, String blogAbstract) {
        this.userUid = userUid;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.blogDate = blogDate;
        this.blogViews = blogViews;
        this.blogLikes = blogLikes;
        this.blogCollects = blogCollects;
        this.order = order;
        this.original = original;
        this.status = status;
        this.blogCover = blogCover;
        this.blogAbstract = blogAbstract;
    }

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(String blogDate) {
        this.blogDate = blogDate;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public int getBlogViews() {
        return blogViews;
    }

    public void setBlogViews(int blogViews) {
        this.blogViews = blogViews;
    }

    public int getBlogLikes() {
        return blogLikes;
    }

    public void setBlogLikes(int blogLikes) {
        this.blogLikes = blogLikes;
    }

    public int getBlogCollects() {
        return blogCollects;
    }

    public void setBlogCollects(int blogCollects) {
        this.blogCollects = blogCollects;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBlogCover() {
        return blogCover;
    }

    public void setBlogCover(String blogCover) {
        this.blogCover = blogCover;
    }

    public String getBlogAbstract() {
        return blogAbstract;
    }

    public void setBlogAbstract(String blogAbstract) {
        this.blogAbstract = blogAbstract;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", userUid='" + userUid + '\'' +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContent='" + blogContent + '\'' +
                ", blogDate='" + blogDate + '\'' +
                ", blogViews=" + blogViews +
                ", blogLikes=" + blogLikes +
                ", blogCollects=" + blogCollects +
                ", order=" + order +
                ", original=" + original +
                ", status=" + status +
                ", blogCover='" + blogCover + '\'' +
                ", blogAbstract='" + blogAbstract + '\'' +
                '}';
    }
}

package pojo;

import java.util.List;

public class Common {
    private int id;// 评论的ID
    private String userUid;// 评论的用户ID
    private int blogId;// 评论的博客ID
    private String content;// 评论的内容
    private String date;// 评论的时间

    private int parentId;// 父评论的ID
    private String parentUserUid;// 父评论的用户ID
    private int rootId;// 根评论的ID

    private List<Common> childs;

    public Common() {
    }

    public Common(int id, String userUid, int blogId, String content, String date, int parentId, String parentUserUid, int rootID, List<Common> childs) {
        this.id = id;
        this.userUid = userUid;
        this.blogId = blogId;
        this.content = content;
        this.date = date;
        this.parentId = parentId;
        this.parentUserUid = parentUserUid;
        this.rootId = rootID;
        this.childs = childs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentUserUid() {
        return parentUserUid;
    }

    public void setParentUserUid(String parentUserUid) {
        this.parentUserUid = parentUserUid;
    }

    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootID) {
        this.rootId = rootID;
    }

    public List<Common> getChilds() {
        return childs;
    }

    public void setChilds(List<Common> childs) {
        this.childs = childs;
    }

    @Override
    public String toString() {
        return "Common{" +
                "id=" + id +
                ", userUid='" + userUid + '\'' +
                ", blogId=" + blogId +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", parentId=" + parentId +
                ", parentUserUid='" + parentUserUid + '\'' +
                ", rootID=" + rootId +
                ", childs=" + childs +
                '}';
    }
}

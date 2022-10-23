package pojo;

public class Sort {
    private int sortId;// 分类的ID
    private String userUid;// 用户UID
    private String sortName;// 分类名称
    private String sortDescription;// 分类描述


    public Sort() {
    }

    public Sort(String userUid, String sortName) {
        this.userUid = userUid;
        this.sortName = sortName;
    }

    public Sort(String userUid, String sortName, String sortDescription) {
        this.userUid = userUid;
        this.sortName = sortName;
        this.sortDescription = sortDescription;
    }

    public Sort(int sortId, String userUid, String sortName, String sortDescription) {
        this.sortId = sortId;
        this.userUid = userUid;
        this.sortName = sortName;
        this.sortDescription = sortDescription;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortDescription() {
        return sortDescription;
    }

    public void setSortDescription(String sortDescription) {
        this.sortDescription = sortDescription;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "sortId=" + sortId +
                ", userUid='" + userUid + '\'' +
                ", sortName='" + sortName + '\'' +
                ", sortDescription='" + sortDescription + '\'' +
                '}';
    }
}

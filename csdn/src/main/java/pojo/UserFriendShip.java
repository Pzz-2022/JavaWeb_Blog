package pojo;

public class UserFriendShip {
    private long id;
    private String userUid;
    private String userFriendUid;
    private int status;

    public UserFriendShip() {
    }

    public UserFriendShip(String userUid, String userFriendUid) {
        this.userUid = userUid;
        this.userFriendUid = userFriendUid;
    }

    public UserFriendShip(String userUid, String userFriendUid, int status) {
        this.userUid = userUid;
        this.userFriendUid = userFriendUid;
        this.status = status;
    }

    public UserFriendShip(long id, String userUid, String userFriendUid, int status) {
        this.id = id;
        this.userUid = userUid;
        this.userFriendUid = userFriendUid;
        this.status = status;
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

    public String getUserFriendUid() {
        return userFriendUid;
    }

    public void setUserFriendUid(String userFriendUid) {
        this.userFriendUid = userFriendUid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserFriendShip{" +
                "id=" + id +
                ", userUid='" + userUid + '\'' +
                ", userFriendUid='" + userFriendUid + '\'' +
                ", status=" + status +
                '}';
    }
}

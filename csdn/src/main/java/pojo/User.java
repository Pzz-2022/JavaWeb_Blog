package pojo;


public class User {
    private String uid;
    private String name;
    private String password;
    private String email;
    private String headPortrait;
    private int permission;
    private char gender;
    private String signature;
    private String birthday;

    public User() {
    }

    public User(String uid, String name, String password, String email, String headPortrait, int permission, char gender, String signature, String birthday) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.email = email;
        this.headPortrait = headPortrait;
        this.permission = permission;
        this.gender = gender;
        this.signature = signature;
        this.birthday = birthday;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", permission=" + permission +
                ", gender=" + gender +
                ", signature='" + signature + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}

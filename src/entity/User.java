package entity;

public class User {
    private int uid;
    private String uname;
    private String upwd;
    private String phoneNum;
    private int depId;
    private String salary;
    private int totalTime;
    private String identity;

    public User(){}

    public User(int uid, String uname, String upwd, int depId, String salary, int totalTime, String identity) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.depId = depId;
        this.salary = salary;
        this.totalTime = totalTime;
        this.identity = identity;
    }

    public User(String uname, String upwd, int depId) {
        this.uname = uname;
        this.upwd = upwd;
        this.depId = depId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", depId=" + depId +
                ", salary='" + salary + '\'' +
                ", totalTime=" + totalTime +
                '}';
    }
}

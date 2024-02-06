
public class User {

    public static boolean userLogged = false;
    private String userName;
    private String password;
    private int userID;
    public User(){};
    public User(String userName,String password){
        this.userName = userName;
        this.password = password;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

package accounts;

public class UserProfile {

    private String login;
    private String pass;

    public UserProfile(String login, String password){
        this.login = login;
        this.pass = password;
    }

    public UserProfile(String login){
        this.login = login;
        this.pass = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }
}

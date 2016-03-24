package dbService.DataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "users")
public class UsersDataSet implements Serializable{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="login", unique = true, updatable = false)
    private String login;
    
    @Column(name = "password", updatable = true)
    private String password;

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(){}

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String login){
        this.setId(id);
        this.setLogin(login);
    }

    public UsersDataSet(String login){
        this.setLogin(login);
        this.setId(-1);
    }

    public UsersDataSet(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    

    public String toString(){
        return "UsersDataSet{" + "id=" + id + ", login='" + login + "\'" + "}";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPassword(){
        return password;
    }
}

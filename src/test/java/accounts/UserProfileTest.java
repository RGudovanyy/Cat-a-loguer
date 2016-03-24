package accounts;

import org.junit.Assert;
import org.junit.Test;

public class UserProfileTest {

    @Test
    public void creating_user_using_constructor(){
        String login = "login";
        String password = "pass";
        UserProfile testUser = new UserProfile(login, password);
        Assert.assertEquals(testUser.getLogin(),login);
        Assert.assertEquals(testUser.getPass(),password);
    }


}
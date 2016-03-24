package accounts;

import dbService.DBService;
import dbService.DBServiceImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
    DBService dbService = new DBServiceImpl();
    private final Map<String, UserProfile> sessionIdToProfile;

    public AccountServiceImpl() {
        sessionIdToProfile = new HashMap<>();
    }


    @Override
    public void addNewUser(UserProfile userProfile) {
        try {

            dbService.addUser(userProfile.getLogin(), userProfile.getPass());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public UserProfile getUserProfile(String login) {
        try {
            return  dbService.getUser(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId,userProfile);
    }

    @Override
    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }


}

package accounts;

public interface AccountService {
    //Method to adding new User
    void addNewUser(UserProfile userProfile);

    UserProfile getUserProfile(String login);

    //Starting User session with some ID
    void addSession(String sessionId, UserProfile userProfile);

    //Delete session when User go away
    void deleteSession(String sessionId);

}

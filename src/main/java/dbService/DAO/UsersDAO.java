package dbService.DAO;

import dbService.DataSets.UsersDataSet;

import java.sql.SQLException;

public interface UsersDAO {
    void createTable() throws SQLException;

    long insertUser(String login, String password) throws SQLException;

    long getUserId(String login) throws SQLException;

    UsersDataSet getUser(long id) throws  SQLException;

    boolean userExists(String login) throws SQLException;

}

package dbService;

import accounts.UserProfile;
import dbService.DataSets.LinksDataSet;
import dbService.DataSets.UsersDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.util.List;

public interface DBService {
    Configuration getMySqlConfiguration();

    SessionFactory createSessionFactory(Configuration configuration);

    UsersDataSet getUser(long id) ;

    UserProfile getUser(String login) throws SQLException;

    long addUser(String name, String password) throws SQLException;

    List<LinksDataSet> getLink (String owner);

    List<LinksDataSet> getLinks(String owner) throws SQLException;

    long addLink (String URL, String owner) throws SQLException;

    void printConnectInfo();



}

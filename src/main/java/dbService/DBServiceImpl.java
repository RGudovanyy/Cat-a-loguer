package dbService;

import accounts.UserProfile;
import dbService.DAO.LinkDAO;
import dbService.DAO.LinkDAOImpl;
import dbService.DAO.UsersDAO;
import dbService.DAO.UsersDAOImpl;
import dbService.DataSets.LinksDataSet;
import dbService.DataSets.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBServiceImpl implements DBService{
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";
    private final SessionFactory sessionFactory;
    private Connection connection;

    public DBServiceImpl() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }


    @Override
    @SuppressWarnings("UnusedDeclaration")
    public Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);
        configuration.addAnnotatedClass(LinksDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/BCDB");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password","root");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    @Override
    public SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public UsersDataSet getUser(long id) {
        return null;
    }

    @Override
    public UserProfile getUser(String login) throws SQLException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAOImpl(session);
            long userId = dao.getUserId(login);
            UsersDataSet dataSet = dao.getUser(userId);
            session.close();
            return new UserProfile(dataSet.getLogin(), dataSet.getPassword());
        }
        catch (HibernateException e){
            throw new SQLException(e);
        }
    }

    @Override
    public long addUser(String login, String password) throws SQLException {
        try{
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAOImpl(session);
            long id = 0;
            if(!dao.userExists(login)) {
                id = dao.insertUser(login, password);
            }
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<LinksDataSet> getLink(String owner) {
        return null;
    }

    @Override
    public List<LinksDataSet> getLinks(String owner) throws SQLException {

            Session session = sessionFactory.openSession();
            LinkDAO dao = new LinkDAOImpl(session);
            List<LinksDataSet> result = dao.getLinksByOwner(owner);
            session.close();
            return result;
    }

    @Override
    public long addLink(String URL, String owner) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LinkDAO dao = new LinkDAOImpl(session);
        long id = dao.insertLink("test",URL,owner);
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public void printConnectInfo() {

    }
}

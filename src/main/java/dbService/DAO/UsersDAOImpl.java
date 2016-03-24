package dbService.DAO;

import dbService.DataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;

public class UsersDAOImpl implements UsersDAO {

    private Session session;
    public UsersDAOImpl(Session session){
        this.session = session;
    }


    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public long insertUser(String login, String password) throws SQLException {
        return (Long) session.save(new UsersDataSet(login, password));
    }

    @Override
    public long getUserId(String login) throws SQLException {
        //TODO добавить комментарий
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("login",login)).uniqueResult()).getId();
    }

    @Override
    public UsersDataSet getUser(long id) throws SQLException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    @Override
    public boolean userExists(String login) throws SQLException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId() > 0;
    }
}

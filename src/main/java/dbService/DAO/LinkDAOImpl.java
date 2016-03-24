package dbService.DAO;

import dbService.DataSets.LinksDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

public class LinkDAOImpl implements LinkDAO {

    private Session session;
    public LinkDAOImpl(Session session){this.session = session;}

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public long insertLink(String name, String URL, String owner) throws SQLException {
       return (long) session.save(new LinksDataSet(name, URL, owner));
    }

    @Override
    public long getLinkId(String name, String owner) throws SQLException {
        return 0;
    }

    @Override
    public List<LinksDataSet> getLinksByOwner(String owner) throws SQLException {
        Criteria criteria = session.createCriteria(LinksDataSet.class);
        criteria.add(Restrictions.eq("owner", owner));
        return criteria.list();
    }

    @Override
    public LinksDataSet getLink(long id, String owner) throws SQLException {
        return null;
    }
}

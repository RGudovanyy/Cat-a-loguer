package dbService.DAO;

import dbService.DataSets.LinksDataSet;

import java.sql.SQLException;
import java.util.List;

public interface LinkDAO {

    //creating table of
    void createTable() throws SQLException;

    long insertLink(String name, String URL, String owner) throws SQLException;

    long getLinkId(String name, String owner) throws SQLException;

    List<LinksDataSet> getLinksByOwner(String owner) throws SQLException;

    LinksDataSet getLink(long id, String owner) throws SQLException;
}

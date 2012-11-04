/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.InterestSourcesDAO;
import com.surpriseme.entities.InterestSources;
import com.surpriseme.entities.Source;
import com.surpriseme.helper.InterestSourcesPK;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class InterestSourcesDAOImpl implements InterestSourcesDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public Integer saveOrUpdate(InterestSources entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<InterestSources> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InterestSources findById(InterestSourcesPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<InterestSources> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(InterestSourcesPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<InterestSources> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Source> getSourcesOfIntereset(Integer interestid) throws SQLException {
        List<Source> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select sourceid from interestsources where interestid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, interestid);

                rs = con.customQuery(pstmt);

                SourceDAOImpl sourceDao = new SourceDAOImpl();

                while (rs.next()) {

                    Source source = null;

                    Integer sourceid = rs.getInt("sourceid");

                    source = sourceDao.findById(sourceid);

                    retval.add(source);
                }

            }

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }

        return retval;
    }
}

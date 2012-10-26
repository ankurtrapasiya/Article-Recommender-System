/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.DAOMisc;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class DAOMiscImpl implements DAOMisc {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(TagDAOImpl.class);

    @Override
    public Integer getLastInsertedId() throws SQLException {
        Integer retval = 0;
        ResultSet rs = null;
        try {
            con = new DBConnection();
            if (con.connect()) {

                String sql = "select last_insert_id()";

                rs = con.customQuery(sql);

                while (rs.next()) {
                    retval = rs.getInt(1);
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

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
}

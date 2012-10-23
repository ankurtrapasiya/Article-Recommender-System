package com.surpriseme.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 *
 * @author ankur
 */
public class DBConnection {

    ResourceBundle bundle;
    private String dbUrl;
    private String dbUser;
    private String dbPass;
    private String dbClass;
    private Connection connection;
    private Statement dbStatement;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DBConnection() {
        bundle = ResourceBundle.getBundle("com.surpriseme.config.database");
        dbUrl = bundle.getString("url");
        dbUser = bundle.getString("username");
        dbPass = bundle.getString("password");
        dbClass = bundle.getString("driver");
    }

    /**
     *
     * Method can be used to connect to the database. It is necessary to get
     * connected to the database before doing any work.
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean connect() throws SQLException, ClassNotFoundException {
        boolean result = false;
        Class.forName(dbClass);
        if (connection == null) {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            if (!connection.isClosed()) {
                result = true;
            }
        }
        return result;
    }

    /**
     *
     * Method is used to disconnect the connection made to the database
     *
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    /**
     *
     * Method can be used for any kind of updation in the database using query
     *
     * @param customquery
     * @return
     * @throws SQLException
     */
    public boolean executeQuery(String sql) throws SQLException {
        boolean retval = true;
        try {
            dbStatement = connection.createStatement();
            dbStatement.executeUpdate(sql);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            dbStatement = null;
        }
        return retval;
    }

    /**
     *
     * Method can be used to get all the records from any table
     *
     * @param tableName
     * @return
     * @throws SQLException
     */
    public ResultSet selectRecords(String tableName) throws SQLException {
        ResultSet dbResultSet = null;
        try {
            dbStatement = connection
                    .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            dbResultSet = dbStatement
                    .executeQuery("select * from " + tableName);
        } catch (SQLException ex) {
            throw ex;
        }
        return dbResultSet;
    }

    /**
     *
     * Method can be used to obtain certain datasets based on the query given
     *
     * @param sqlQuery
     * @return
     * @throws SQLException
     */
    public ResultSet customQuery(String sqlQuery) throws SQLException {
        ResultSet dbResultSet = null;
        try {
            dbStatement = connection.createStatement();
            dbResultSet = dbStatement.executeQuery(sqlQuery);
        } catch (SQLException ex) {
            throw ex;
        }
        return dbResultSet;
    }

    /**
     *
     * Method can be used to save or update the record
     *
     * @param cstmt
     * @return
     * @throws SQLException
     */
    public ResultSet saveOrUpdate(CallableStatement cstmt) throws SQLException {
        ResultSet dbResultSet = null;
        try {
            dbResultSet = cstmt.executeQuery();
        } catch (SQLException ex) {
            throw ex;
        }
        return dbResultSet;
    }

    /**
     *
     * Method can be used to delete the record
     *
     * @param cstmt
     * @return
     * @throws SQLException
     */
    public boolean delete(CallableStatement cstmt) throws SQLException {
        boolean retval = true;
        try {
            cstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            retval = false;
        } finally {
            cstmt.close();
        }
        return retval;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.BlockedUsersDAO;
import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.helper.BlockedUserPK;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class BlockedUserDAOImpl implements BlockedUsersDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public BlockedUserPK saveOrUpdate(BlockedUsers entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<BlockedUsers> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BlockedUsers findById(BlockedUserPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BlockedUsers> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(BlockedUserPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<BlockedUsers> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean blockUserRequest(BlockedUsers entity) throws SQLException {

        boolean retval = false;

        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_blockedusers(?,?,?,?,?)}");

                cstmt.setInt("p_userid", entity.getUserid());
                cstmt.setInt("p_blockerid", entity.getBlockerid());
                cstmt.setTimestamp("p_timestamp", entity.getTimestamp());
                cstmt.setString("p_reason", entity.getReason());

                // has to be true
                cstmt.setBoolean("p_isactive", entity.isIsActive());

                rs = con.saveOrUpdate(cstmt);

            }
            retval = true;

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }

        return retval;
    }

    @Override
    public boolean blockUser(Integer userid, Integer blockerid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_blockedusers(?,?,?,?,?)}");

                Timestamp stamp = new Timestamp(new Date().getTime());

                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_blockerid", blockerid);
                cstmt.setTimestamp("p_timestamp", new java.sql.Timestamp(stamp.getTime()));
                cstmt.setString("p_reason", "");
                cstmt.setBoolean("p_isactive", false);

                rs = con.customQuery(cstmt);

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

    @Override
    public boolean unblockUser(Integer userid, Integer blockerid) throws SQLException {

        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_blockedusers(?,?)}");

                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_blockerid", blockerid);

                rs = con.saveOrUpdate(cstmt);

            }

            retval = true;

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }

        return retval;
    }

    @Override
    public List<BlockedUsers> getBlockedUsers() throws SQLException {
        List<BlockedUsers> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from blockedusers where isactive=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setBoolean(1, true);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    BlockedUsers user = new BlockedUsers();

                    user.setUserid(rs.getInt("userid"));
                    user.setBlockerid(rs.getInt("blockerid"));
                    user.setTimestamp(rs.getTimestamp("timestamp"));
                    user.setReason(rs.getString("reason"));
                    user.setIsActive(rs.getBoolean("isactive"));

                    retval.add(user);
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

    @Override
    public List<BlockedUsers> getAllBlocked() throws SQLException {
        List<BlockedUsers> retval = new ArrayList<BlockedUsers>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_blockedusers()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    BlockedUsers user = new BlockedUsers();

                    user.setUserid(rs.getInt("userid"));
                    user.setBlockerid(rs.getInt("blockerid"));
                    user.setTimestamp(rs.getTimestamp("timestamp"));
                    user.setReason(rs.getString("reason"));
                    user.setIsActive(rs.getBoolean("isactive"));

                    retval.add(user);
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

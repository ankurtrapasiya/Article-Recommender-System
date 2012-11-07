/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.FavouritesDAO;
import com.surpriseme.entities.Favourites;
import com.surpriseme.helper.FavouritesPK;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class FavouritesDAOImpl implements FavouritesDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public FavouritesPK saveOrUpdate(Favourites entity) throws SQLException {
        ResultSet rs = null;
        FavouritesPK retval = null;
        Favourites isUpdate = null;

        try {

            isUpdate = findById(new FavouritesPK(entity.getUserid(), entity.getArticleid()));

            con = new DBConnection();
            if (con.connect()) {

                if (isUpdate != null) {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_favourites(?,?,?,?)}");
                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_favourites(?,?,?,?)}");
                }

                System.out.println("entity" + entity);
                cstmt.setInt("p_userid", entity.getUserid());
                cstmt.setInt("p_articleid", entity.getArticleid());
                cstmt.setBoolean("p_readlater", entity.isReadlater());
                cstmt.setBoolean("p_isfav", entity.isIsfav());

                rs = con.saveOrUpdate(cstmt);

            }

            retval = new FavouritesPK(entity.getUserid(), entity.getArticleid());


        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            cstmt.close();
            con.disconnect();
        }

        return retval;
    }

    @Override
    public void saveOrUpdateAll(List<Favourites> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Favourites findById(FavouritesPK key) throws SQLException {

        Favourites fav = null;

        ResultSet rs = null;
        try {
            con = new DBConnection();
            if (con.connect()) {

                String sql = "select * from favourites where articleid=" + key.getArticleId() + " and userid=" + key.getUserId();

                rs = con.customQuery(sql);

                while (rs.next()) {
                    fav = new Favourites();
                    fav.setArticleid(key.getArticleId());
                    fav.setUserid(key.getUserId());
                    fav.setReadlater(rs.getBoolean("readlater"));
                    fav.setIsfav(rs.getBoolean("isfav"));
                }

            }
        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }

        return fav;
    }

    @Override
    public List<Favourites> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(FavouritesPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<Favourites> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

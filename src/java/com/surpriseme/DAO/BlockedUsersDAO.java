/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.helper.BlockedUserPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface BlockedUsersDAO extends GenericDAO<BlockedUsers, BlockedUserPK> {

    boolean blockUserRequest(BlockedUsers entity) throws SQLException;

    boolean blockUser(Integer userid, Integer blockerid) throws SQLException;

    boolean unblockUser(Integer userid, Integer blockerid) throws SQLException;

    List<BlockedUsers> getBlockedUsers() throws SQLException;

    List<BlockedUsers> getAllBlocked() throws SQLException;
}

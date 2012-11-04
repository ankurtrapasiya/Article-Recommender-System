/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserActivation;
import com.surpriseme.entities.UserSuggestions;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserDAO extends GenericDAO<User, Integer> {

    boolean blockUserRequest(BlockedUsers entity) throws SQLException;

    boolean blockUser(Integer userid, Integer blockerid) throws SQLException;

    boolean unblockUser(Integer userid, Integer blockerid) throws SQLException;

    List<User> searchUser(String firstName, String lastName, String email) throws SQLException;

    boolean addToCircle(Integer friendId, Integer userId) throws SQLException;

    boolean removeFromCircle(Integer friendId, Integer userId) throws SQLException;

    User findByUsername(String username) throws SQLException;

    boolean insertIntoUserActivation(UserActivation ua) throws SQLException;

    boolean addInterestToUser(Integer interestid, Integer userid) throws SQLException;

    boolean removeInterestFromUser(Integer interestid, Integer userid) throws SQLException;

    List<Interest> getUserInterests(Integer userid, boolean include) throws SQLException;

    List<User> getAllFriends(Integer userid) throws SQLException;

    List<BlockedUsers> getBlockedUsers() throws SQLException;

    List<UserSuggestions> getUserSuggestions(Integer userid) throws SQLException;

    List<User> updateUserGraph() throws SQLException;

    List<BlockedUsers> getAllBlocked() throws SQLException;

    User isValidUser(String username, String password) throws SQLException;

    boolean isEmailAvailable(String email) throws SQLException;

    boolean isUsernameAvailable(String username) throws SQLException;
}

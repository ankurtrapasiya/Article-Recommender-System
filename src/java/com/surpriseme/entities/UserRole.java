package com.surpriseme.entities;

public class UserRole {

    private String rolename;
    private Integer userid;
    private String username;

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.rolename != null ? this.rolename.hashCode() : 0);
        hash = 79 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 79 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserRole other = (UserRole) obj;
        if ((this.rolename == null) ? (other.rolename != null) : !this.rolename.equals(other.rolename)) {
            return false;
        }
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserRole{" + "rolename=" + rolename + ", userid=" + userid + ", username=" + username + '}';
    }
}
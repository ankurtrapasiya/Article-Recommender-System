package com.surpriseme.entities;

import java.util.*;

public class User {

    private Integer userid;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Date dob;
    private String state;
    private String city;
    private String country;
    private Boolean isactive;
    private Date timeofregistration;
    private String image;

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

    public User(String username, String password, String email, String firstname, String lastname, Date dob, String country, Boolean isactive, Date timeofregistration) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.country = country;
        this.isactive = isactive;
        this.timeofregistration = timeofregistration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Date getTimeofregistration() {
        return timeofregistration;
    }

    public void setTimeofregistration(Date timeofregistration) {
        this.timeofregistration = timeofregistration;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 53 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 53 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 53 * hash + (this.firstname != null ? this.firstname.hashCode() : 0);
        hash = 53 * hash + (this.lastname != null ? this.lastname.hashCode() : 0);
        hash = 53 * hash + (this.dob != null ? this.dob.hashCode() : 0);
        hash = 53 * hash + (this.state != null ? this.state.hashCode() : 0);
        hash = 53 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 53 * hash + (this.country != null ? this.country.hashCode() : 0);
        hash = 53 * hash + (this.isactive != null ? this.isactive.hashCode() : 0);
        hash = 53 * hash + (this.timeofregistration != null ? this.timeofregistration.hashCode() : 0);
        hash = 53 * hash + (this.image != null ? this.image.hashCode() : 0);
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
        final User other = (User) obj;
        if (this.userid != other.userid && (this.userid == null || !this.userid.equals(other.userid))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.firstname == null) ? (other.firstname != null) : !this.firstname.equals(other.firstname)) {
            return false;
        }
        if ((this.lastname == null) ? (other.lastname != null) : !this.lastname.equals(other.lastname)) {
            return false;
        }
        if (this.dob != other.dob && (this.dob == null || !this.dob.equals(other.dob))) {
            return false;
        }
        if ((this.state == null) ? (other.state != null) : !this.state.equals(other.state)) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if ((this.country == null) ? (other.country != null) : !this.country.equals(other.country)) {
            return false;
        }
        if (this.isactive != other.isactive && (this.isactive == null || !this.isactive.equals(other.isactive))) {
            return false;
        }
        if (this.timeofregistration != other.timeofregistration && (this.timeofregistration == null || !this.timeofregistration.equals(other.timeofregistration))) {
            return false;
        }
        if ((this.image == null) ? (other.image != null) : !this.image.equals(other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", username=" + username + ", password=" + password + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", dob=" + dob + ", state=" + state + ", city=" + city + ", country=" + country + ", isactive=" + isactive + ", timeofregistration=" + timeofregistration + ", image=" + image + '}';
    }
}

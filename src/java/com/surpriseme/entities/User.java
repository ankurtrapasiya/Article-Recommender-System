/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ankur
 */
@Entity
@Table(name = "user", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userid = :userid"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByDob", query = "SELECT u FROM User u WHERE u.dob = :dob"),
    @NamedQuery(name = "User.findByState", query = "SELECT u FROM User u WHERE u.state = :state"),
    @NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city"),
    @NamedQuery(name = "User.findByCountry", query = "SELECT u FROM User u WHERE u.country = :country"),
    @NamedQuery(name = "User.findByIsactive", query = "SELECT u FROM User u WHERE u.isactive = :isactive"),
    @NamedQuery(name = "User.findByTimeofregistration", query = "SELECT u FROM User u WHERE u.timeofregistration = :timeofregistration")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid", nullable = false)
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 50)
    private String password;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 20)
    private String email;
    @Basic(optional = false)
    @Column(name = "firstname", nullable = false, length = 20)
    private String firstname;
    @Basic(optional = false)
    @Column(name = "lastname", nullable = false, length = 20)
    private String lastname;
    @Basic(optional = false)
    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @Column(name = "state", nullable = false, length = 20)
    private String state;
    @Basic(optional = false)
    @Column(name = "city", nullable = false, length = 20)
    private String city;
    @Basic(optional = false)
    @Column(name = "country", nullable = false, length = 20)
    private String country;
    @Basic(optional = false)
    @Column(name = "isactive", nullable = false)
    private boolean isactive;
    @Basic(optional = false)
    @Column(name = "timeofregistration", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeofregistration;
    @ManyToMany(mappedBy = "userList", fetch = FetchType.EAGER)
    private List<Interest> interestList;
    @JoinTable(name = "favourites", joinColumns = {
        @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "articleid", referencedColumnName = "articleid", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Article> articleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Userrole> userroleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid", fetch = FetchType.EAGER)
    private List<Usersuggestions> usersuggestionsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Blockedusers> blockedusersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1", fetch = FetchType.EAGER)
    private List<Blockedusers> blockedusersList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid", fetch = FetchType.EAGER)
    private List<Useractivation> useractivationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Userhistory> userhistoryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Usergraph> usergraphList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1", fetch = FetchType.EAGER)
    private List<Usergraph> usergraphList1;

    public User() {
    }

    public User(Integer userid) {
        this.userid = userid;
    }

    public User(Integer userid, String username, String password, String email, String firstname, String lastname, Date dob, String state, String city, String country, boolean isactive, Date timeofregistration) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.state = state;
        this.city = city;
        this.country = country;
        this.isactive = isactive;
        this.timeofregistration = timeofregistration;
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

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public Date getTimeofregistration() {
        return timeofregistration;
    }

    public void setTimeofregistration(Date timeofregistration) {
        this.timeofregistration = timeofregistration;
    }

    @XmlTransient
    public List<Interest> getInterestList() {
        return interestList;
    }

    public void setInterestList(List<Interest> interestList) {
        this.interestList = interestList;
    }

    @XmlTransient
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @XmlTransient
    public List<Userrole> getUserroleList() {
        return userroleList;
    }

    public void setUserroleList(List<Userrole> userroleList) {
        this.userroleList = userroleList;
    }

    @XmlTransient
    public List<Usersuggestions> getUsersuggestionsList() {
        return usersuggestionsList;
    }

    public void setUsersuggestionsList(List<Usersuggestions> usersuggestionsList) {
        this.usersuggestionsList = usersuggestionsList;
    }

    @XmlTransient
    public List<Blockedusers> getBlockedusersList() {
        return blockedusersList;
    }

    public void setBlockedusersList(List<Blockedusers> blockedusersList) {
        this.blockedusersList = blockedusersList;
    }

    @XmlTransient
    public List<Blockedusers> getBlockedusersList1() {
        return blockedusersList1;
    }

    public void setBlockedusersList1(List<Blockedusers> blockedusersList1) {
        this.blockedusersList1 = blockedusersList1;
    }

    @XmlTransient
    public List<Useractivation> getUseractivationList() {
        return useractivationList;
    }

    public void setUseractivationList(List<Useractivation> useractivationList) {
        this.useractivationList = useractivationList;
    }

    @XmlTransient
    public List<Userhistory> getUserhistoryList() {
        return userhistoryList;
    }

    public void setUserhistoryList(List<Userhistory> userhistoryList) {
        this.userhistoryList = userhistoryList;
    }

    @XmlTransient
    public List<Usergraph> getUsergraphList() {
        return usergraphList;
    }

    public void setUsergraphList(List<Usergraph> usergraphList) {
        this.usergraphList = usergraphList;
    }

    @XmlTransient
    public List<Usergraph> getUsergraphList1() {
        return usergraphList1;
    }

    public void setUsergraphList1(List<Usergraph> usergraphList1) {
        this.usergraphList1 = usergraphList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.User[ userid=" + userid + " ]";
    }
    
}

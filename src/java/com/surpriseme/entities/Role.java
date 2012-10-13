/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ankur
 */
@Entity
@Table(name = "role", catalog = "surpriseme", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByRolename", query = "SELECT r FROM Role r WHERE r.rolename = :rolename"),
    @NamedQuery(name = "Role.findByDescription", query = "SELECT r FROM Role r WHERE r.description = :description")})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rolename", nullable = false, length = 20)
    private String rolename;
    @Basic(optional = false)
    @Column(name = "description", nullable = false, length = 200)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.EAGER)
    private List<Userrole> userroleList;

    public Role() {
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Role(String rolename, String description) {
        this.rolename = rolename;
        this.description = description;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Userrole> getUserroleList() {
        return userroleList;
    }

    public void setUserroleList(List<Userrole> userroleList) {
        this.userroleList = userroleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolename != null ? rolename.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.rolename == null && other.rolename != null) || (this.rolename != null && !this.rolename.equals(other.rolename))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.surpriseme.entities.Role[ rolename=" + rolename + " ]";
    }
    
}

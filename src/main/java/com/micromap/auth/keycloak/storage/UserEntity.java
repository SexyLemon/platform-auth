package com.micromap.auth.keycloak.storage;

import javax.persistence.*;

/**
 * @author limeng 2018/3/30
 */
@NamedQueries({
        @NamedQuery(name = "getUserByUsername", query = "select u from UserEntity u where u.username = :username and enabled = 1 and deleteState = 0"),
        @NamedQuery(name = "getUserByEmail", query = "select u from UserEntity u where u.email = :email and enabled = 1 and deleteState = 0"),
        @NamedQuery(name = "getUserCount", query = "select count(u) from UserEntity u where enabled = 1 and deleteState = 0"),
        @NamedQuery(name = "getAllUsers", query = "select u from UserEntity u where enabled = 1 and deleteState = 0"),
        @NamedQuery(name = "searchForUser", query = "select u from UserEntity u where enabled = 1 and deleteState = 0 and " +
                "( lower(u.username) like :search or u.email like :search ) order by u.username"),
})
@Table(name = "sys_user")
@Entity
public class UserEntity {
    @Id
    private String id;

    @Column(name = "full_name")
    private String fullName;
    private String username;
    private String email;
    private String password;
    private String mobile;
    private Integer enabled;
    @Column(name = "delete_state")
    private Integer deleteState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }
}

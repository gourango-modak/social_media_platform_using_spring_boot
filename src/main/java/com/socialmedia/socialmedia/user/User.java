package com.socialmedia.socialmedia.user;

import com.socialmedia.socialmedia.user.role.UserRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    @Column(unique=true)
    private String username;
    private String password;
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
            name = "User_UserRole_map",
            joinColumns = @JoinColumn (
                    name = "user_id",
                    referencedColumnName = "userId"
            ),
            inverseJoinColumns = @JoinColumn (
                    name = "userrole_id",
                    referencedColumnName = "roleId"
            )
    )
    private Collection<UserRole> userRole = new ArrayList<>();

    public User() {
    }

    public User(String name, String username, String password, List<UserRole> userRole) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public Collection<UserRole> getUserRoles() {
        return userRole;
    }

    public void setUserRole(Collection<UserRole> userRole) {
        this.userRole = userRole;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}

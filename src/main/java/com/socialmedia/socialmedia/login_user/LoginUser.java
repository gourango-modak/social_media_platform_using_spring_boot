package com.socialmedia.socialmedia.login_user;

import com.socialmedia.socialmedia.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Login_users")
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long login_id;
    @OneToOne
    private User user;
    private LocalDateTime date;

    public LoginUser(User user, LocalDateTime date) {
        this.user = user;
        this.date = date;
    }

    public LoginUser() {

    }

    public Long getLogin_id() {
        return login_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "login_id=" + login_id +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}

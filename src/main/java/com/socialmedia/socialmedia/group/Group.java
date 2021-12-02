package com.socialmedia.socialmedia.group;

import com.socialmedia.socialmedia.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "social_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String name;
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable(
            name = "group_user_map",
            joinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "groupId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "userId"
            )
    )
    private List<User> users;

    public Group(Long groupId, String name, List<User> user) {
        this.groupId = groupId;
        this.name = name;
        this.users = user;
    }

    public Group() {

    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUser(List<User> users) {
        this.users = users;
    }
}

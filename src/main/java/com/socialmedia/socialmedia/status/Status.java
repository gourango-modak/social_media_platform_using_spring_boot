package com.socialmedia.socialmedia.status;

import com.socialmedia.socialmedia.group.Group;
import com.socialmedia.socialmedia.location.Location;
import com.socialmedia.socialmedia.user.User;

import javax.persistence.*;

@Entity
@Table(name = "Status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private String statusDescription;
    private String privacy;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Group group;
    @ManyToOne
    private User user;

    public Status(String statusDescription, String privacy, Group group) {
        this.statusDescription = statusDescription;
        this.privacy = privacy;
        this.group = group;
    }

    public Status() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Status{" +
                "statusId=" + statusId +
                ", statusDescription='" + statusDescription + '\'' +
                ", privacy='" + privacy + '\'' +
                ", location=" + location +
                ", group=" + group +
                ", user=" + user +
                '}';
    }
}

package com.socialmedia.socialmedia.status;

import com.socialmedia.socialmedia.group.Group;
import com.socialmedia.socialmedia.location.Location;
import com.socialmedia.socialmedia.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;
    private String statusDescription;
    private int privacy;
    @ManyToOne (fetch = FetchType.LAZY)
    private Location location;
    @ManyToOne (fetch = FetchType.LAZY)
    private Group group;
    @ManyToOne (fetch = FetchType.LAZY)
    private User user;

    public Status(String statusDescription, int privacy, Group group) {
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

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

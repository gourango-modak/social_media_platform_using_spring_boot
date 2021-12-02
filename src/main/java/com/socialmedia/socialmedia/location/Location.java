package com.socialmedia.socialmedia.location;

import javax.persistence.*;

@Entity
@Table(schema = "Locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locId;
    private String name;

    public Location(Long locId, String name) {
        this.locId = locId;
        this.name = name;
    }

    public Location() {

    }

    public Long getLocId() {
        return locId;
    }

    public void setLocId(Long locId) {
        this.locId = locId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

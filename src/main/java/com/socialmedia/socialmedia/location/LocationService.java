package com.socialmedia.socialmedia.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LocationService {
    @Autowired
    private final ILocationRepository locationRepository;

    public LocationService(ILocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }
    public Location findByLocationName(String name) {
        return locationRepository.findByName(name);
    }
}

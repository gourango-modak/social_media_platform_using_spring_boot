package com.brainstation.socialmedia.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainstation.socialmedia.model.Location;
import com.brainstation.socialmedia.repository.LocationRepository;
import com.brainstation.socialmedia.service.LocationIF;

@Service
public class LocationService implements LocationIF {

	@Autowired
	private LocationRepository locationRepository;
	
	public LocationService(LocationRepository locationRepository) {
		super();
		this.locationRepository = locationRepository;
	}

	@Override
	public List<Location> getAllLocations() {
		List<Location> locations = new ArrayList<Location>();
		locationRepository.findAll().forEach((loc)-> locations.add(loc));
		return locations;
	}

}

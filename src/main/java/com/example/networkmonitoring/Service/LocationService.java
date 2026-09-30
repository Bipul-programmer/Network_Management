package com.example.networkmonitoring.Service;

import java.util.List;

import com.example.networkmonitoring.DTO.CreateLocationRequest;
import com.example.networkmonitoring.DTO.LocationResponse;
import com.example.networkmonitoring.Repository.LocationRepository;
import com.example.networkmonitoring.Model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service 
public class LocationService {
    private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Transactional 
    public LocationResponse create(CreateLocationRequest request) {
        Location location = Location.builder()
            .name(request.name())
            .description(request.description())
            .build();

        return toResponse(locationRepository.save(location));
    }   


    @Transactional(readOnly = true)
    public LocationResponse getById(Long locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Location not found: " + locationId));
        return toResponse(location);
    }

    @Transactional(readOnly = true)
    public List<LocationResponse> getAll() {
        return locationRepository.findAll().stream()
                .map(LocationService::toResponse)
                .toList();
    }

    public static LocationResponse toResponse(Location location) {
        return new LocationResponse(
            location.getId(),
            location.getName(),
            location.getDescription(),
            location.getCreatedAt()
        );
    }
}

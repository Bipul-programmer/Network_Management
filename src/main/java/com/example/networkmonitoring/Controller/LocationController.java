package com.example.networkmonitoring.Controller;

import java.net.URI;
import java.util.List;
import com.example.networkmonitoring.Service.LocationService;
import com.example.networkmonitoring.DTO.CreateLocationRequest;
import com.example.networkmonitoring.DTO.LocationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationResponse> create(@Valid @RequestBody CreateLocationRequest request) {
        LocationResponse created = locationService.create(request);
        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(locationUri).body(created);
    }

    @GetMapping("/{locationId}")
    public LocationResponse getById(@PathVariable Long locationId) {
        return locationService.getById(locationId);
    }

    @GetMapping
    public List<LocationResponse> getAll() {
        return locationService.getAll();
    }
}
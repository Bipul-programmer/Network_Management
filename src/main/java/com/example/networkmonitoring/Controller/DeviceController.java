package com.example.networkmonitoring.Controller;

import java.net.URI;
import java.util.List;
import com.example.networkmonitoring.Service.DeviceService;
import com.example.networkmonitoring.DTO.CreateDeviceRequest;
import com.example.networkmonitoring.DTO.DeviceResponse;
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
@RequestMapping("/api/v1/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceResponse> create(@Valid @RequestBody CreateDeviceRequest request) {
        DeviceResponse created = deviceService.create(request);
        URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(locationUri).body(created);
    }

    @GetMapping("/{deviceId}")
    public DeviceResponse getById(@PathVariable Long deviceId) {
        return deviceService.getById(deviceId);
    }

    @GetMapping
    public List<DeviceResponse> getAll() {
        return deviceService.getAll();
    }
}
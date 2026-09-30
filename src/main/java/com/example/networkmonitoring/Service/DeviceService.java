package com.example.networkmonitoring.Service;

import java.util.List;

import com.example.networkmonitoring.DTO.CreateDeviceRequest;
import com.example.networkmonitoring.DTO.DeviceResponse;
import com.example.networkmonitoring.Model.Location;
import com.example.networkmonitoring.Model.Device;
import com.example.networkmonitoring.Repository.LocationRepository;
import com.example.networkmonitoring.Repository.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final LocationRepository locationRepository;

    public DeviceService(DeviceRepository deviceRepository, LocationRepository locationRepository) {
        this.deviceRepository = deviceRepository;
        this.locationRepository = locationRepository;
    }

    @Transactional
    public DeviceResponse create(CreateDeviceRequest request) {
        Location location = locationRepository.findById(request.locationId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Location not found: " + request.locationId()));

        Device device = Device.builder()
                .location(location)
                .name(request.name())
                .host(request.host())
                .deviceType(request.deviceType())
                .probeType(request.probeType())
                .checkIntervalSeconds(request.checkIntervalSeconds() != null ? request.checkIntervalSeconds() : 60)
                .build();

        return toResponse(deviceRepository.save(device));
    }

    @Transactional(readOnly = true)
    public DeviceResponse getById(Long deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Device not found: " + deviceId));
        return toResponse(device);
    }

    @Transactional(readOnly = true)
    public List<DeviceResponse> getAll() {
        return deviceRepository.findAll().stream()
                .map(DeviceService::toResponse)
                .toList();
    }

    private static DeviceResponse toResponse(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getLocation().getId(),
                device.getLocation().getName(),
                device.getName(),
                device.getHost(),
                device.getDeviceType(),
                device.getProbeType(),
                device.getStatus(),
                device.isEnabled(),
                device.getCheckIntervalSeconds(),
                device.getCreatedAt());
    }
}
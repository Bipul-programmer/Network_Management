package com.example.networkmonitoring.DTO;

import java.time.Instant;
import com.example.networkmonitoring.Model.DeviceStatus;
import com.example.networkmonitoring.Model.DeviceType;
import com.example.networkmonitoring.Model.ProbeType;
import java.util.Objects;

public record DeviceResponse(
        Long id,
        Long locationId,
        String locationName,
        String name,
        String host,
        DeviceType deviceType,
        ProbeType probeType,
        DeviceStatus status,
        boolean enabled,
        int checkIntervalSeconds,
        Instant createdAt) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Long locationId;
        private String locationName;
        private String name;
        private String host;
        private DeviceType deviceType;
        private ProbeType probeType;
        private DeviceStatus status;
        private boolean enabled;
        private int checkIntervalSeconds;
        private Instant createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder locationId(Long locationId) {
            this.locationId = locationId;
            return this;
        }

        public Builder locationName(String locationName) {
            this.locationName = locationName;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder deviceType(DeviceType deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public Builder probeType(ProbeType probeType) {
            this.probeType = probeType;
            return this;
        }

        public Builder status(DeviceStatus status) {
            this.status = status;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder checkIntervalSeconds(int checkIntervalSeconds) {
            this.checkIntervalSeconds = checkIntervalSeconds;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public DeviceResponse build() {
            return new DeviceResponse(
                    Objects.requireNonNull(id, "id is required"),
                    Objects.requireNonNull(locationId, "locationId is required"),
                    Objects.requireNonNull(locationName, "locationName is required"),
                    Objects.requireNonNull(name, "name is required"),
                    Objects.requireNonNull(host, "host is required"),
                    Objects.requireNonNull(deviceType, "deviceType is required"),
                    Objects.requireNonNull(probeType, "probeType is required"),
                    Objects.requireNonNull(status, "status is required"),
                    enabled,
                    checkIntervalSeconds,
                    Objects.requireNonNull(createdAt, "createdAt is required"));
        }
    }
}
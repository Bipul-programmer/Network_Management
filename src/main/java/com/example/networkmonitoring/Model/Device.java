package com.example.networkmonitoring.Model;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 255)
    private String host;

    @Enumerated(EnumType.STRING)
    @Column(name = "device_type", nullable = false, length = 30)
    private DeviceType deviceType;

    @Enumerated(EnumType.STRING)
    @Column(name = "probe_type", nullable = false, length = 30)
    private ProbeType probeType;

    @Column(name = "check_interval_seconds", nullable = false)
    private int checkIntervalSeconds;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private DeviceStatus status;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected Device() {
        // Required by JPA.
    }

    private Device(
            Long id,
            Location location,
            String name,
            String host,
            DeviceType deviceType,
            ProbeType probeType,
            int checkIntervalSeconds) {
        this.id = id;
        this.location = Objects.requireNonNull(location, "location is required");
        this.name = Objects.requireNonNull(name, "name is required");
        this.host = Objects.requireNonNull(host, "host is required");
        this.deviceType = Objects.requireNonNull(deviceType, "deviceType is required");
        this.probeType = Objects.requireNonNull(probeType, "probeType is required");

        if (checkIntervalSeconds <= 0) {
            throw new IllegalArgumentException(
                    "checkIntervalSeconds must be positive");
        }

        this.checkIntervalSeconds = checkIntervalSeconds;
        this.status = DeviceStatus.UNKNOWN;
        this.enabled = true;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Location location;
        private String name;
        private String host;
        private DeviceType deviceType;
        private ProbeType probeType;
        private int checkIntervalSeconds = 60;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder location(Location location) {
            this.location = location;
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

        public Builder checkIntervalSeconds(int checkIntervalSeconds) {
            this.checkIntervalSeconds = checkIntervalSeconds;
            return this;
        }

        public Device build() {
            return new Device(
                    id,
                    location,
                    name,
                    host,
                    deviceType,
                    probeType,
                    checkIntervalSeconds);
        }
    }

    @PrePersist
    void setCreatedAtBeforeInsert() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public ProbeType getProbeType() {
        return probeType;
    }

    public int getCheckIntervalSeconds() {
        return checkIntervalSeconds;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public boolean isEnabled() {
        return enabled != null && enabled;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public void setProbeType(ProbeType probeType) {
        this.probeType = probeType;
    }

    public void setCheckIntervalSeconds(int checkIntervalSeconds) {
        this.checkIntervalSeconds = checkIntervalSeconds;
    }

    public void setStatus(DeviceStatus status) {
        this.status = status;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
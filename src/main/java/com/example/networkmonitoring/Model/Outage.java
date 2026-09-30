package com.example.networkmonitoring.Model;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Index;

@Entity
@Table(name = "outages", indexes = {
        @Index(name = "idx_outages_device_started_at", columnList = "device_id, started_at")
})
public class Outage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "device_id", nullable = false, updatable = false)
    private Device device;

    @Column(name = "started_at", nullable = false, updatable = false)
    private Instant startedAt;

    @Column(name = "ended_at")
    private Instant endedAt;

    @Column(length = 1000)
    private String cause;

    protected Outage() {
        // Required by JPA.
    }

    public Outage(Device device, String cause) {
        this.device = Objects.requireNonNull(device, "Enter the device");
        this.cause = Objects.requireNonNull(cause, "Enter the cause");
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Device device;
        private String cause;

        public Builder device(Device device) {
            this.device = device;
            return this;
        }

        public Builder cause(String cause) {
            this.cause = cause;
            return this;
        }

        public Outage build() {
            return new Outage(device, cause);
        }
    }

    @PrePersist
    void setStartedAtBeforeInsert() {
        if (startedAt == null) {
            startedAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Device getDevice() {
        return device;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getEndedAt() {
        return endedAt;
    }

    public String getCause() {
        return cause;
    }

    public boolean isOpen() {
        return endedAt == null;
    }

    public void close(Instant endedAt) {
        if (this.endedAt == null) {
            this.endedAt = endedAt;
        }
    }
}
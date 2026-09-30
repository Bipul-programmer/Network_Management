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
import jakarta.persistence.Index;

@Entity
@Table(name = "check_results", indexes = {
        @Index(name = "idx_check_results_device_checked_at", columnList = "device_id, checked_at")
})
public class CheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "device_id", nullable = false, updatable = false)
    private Device device;

    @Column(name = "checked_at", nullable = false, updatable = false)
    private Instant checkedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, updatable = false)
    private Check_Status status;

    @Column(name = "latency_ms", updatable = false)
    private Integer latencyMs;

    @Column(name = "error_message", length = 1000, updatable = false)
    private String errorMessage;

    protected CheckResult() {
        // Required by JPA.
    }

    public CheckResult(Device device, Check_Status status, Integer latencyMs, String errorMessage) {
        this.device = Objects.requireNonNull(device, "device is required");
        this.status = Objects.requireNonNull(status, "status is required");

        if (latencyMs != null && latencyMs < 0) {
            throw new IllegalArgumentException("latencyMs cannot be negative");
        }

        this.latencyMs = latencyMs;
        this.errorMessage = errorMessage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Device device;
        private Check_Status status;
        private Integer latencyMs;
        private String errorMessage;

        public Builder device(Device device) {
            this.device = device;
            return this;
        }

        public Builder status(Check_Status status) {
            this.status = status;
            return this;
        }

        public Builder latencyMs(Integer latency_Ms) {
            this.latencyMs = latency_Ms;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public CheckResult build() {
            return new CheckResult(device, status, latencyMs, errorMessage);
        }
    }

    @PrePersist
    void setCheckedAtBeforeInsert() {
        if (checkedAt == null) {
            checkedAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Device getDevice() {
        return device;
    }

    public Instant getCheckedAt() {
        return checkedAt;
    }

    public Check_Status getStatus() {
        return status;
    }

    public Integer getLatencyMs() {
        return latencyMs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
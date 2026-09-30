package com.example.networkmonitoring.DTO;

import java.time.Instant;
import java.util.Objects;

public record LocationResponse(
        Long id,
        String name,
        String description,
        Instant createdAt) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Instant createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public LocationResponse build() {
            return new LocationResponse(
                    Objects.requireNonNull(id, "id is required"),
                    Objects.requireNonNull(name, "name is required"),
                    Objects.requireNonNull(description, "description is required"),
                    Objects.requireNonNull(createdAt, "createdAt is required"));
        }
    }
}
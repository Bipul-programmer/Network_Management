package com.example.networkmonitoring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import com.example.networkmonitoring.Model.DeviceType;
import com.example.networkmonitoring.Model.ProbeType;

public record CreateDeviceRequest(
        @NotNull(message = "Location ID is required") @Positive(message = "Location ID must be positive") Long locationId,

        @NotBlank(message = "Device name is required") @Size(max = 120, message = "Device name must be 120 characters or fewer") String name,

        @NotBlank(message = "Host is required") @Size(max = 255, message = "Host must be 255 characters or fewer") String host,

        @NotNull(message = "Device type is required") DeviceType deviceType,

        @NotNull(message = "Probe type is required") ProbeType probeType,

        @NotNull(message = "Check interval is required") @Positive(message = "Check interval must be greater than zero") Integer checkIntervalSeconds) {

}

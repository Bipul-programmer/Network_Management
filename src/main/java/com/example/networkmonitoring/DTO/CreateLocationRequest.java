package com.example.networkmonitoring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLocationRequest(

                @NotBlank(message = "Location name is required") @Size(max = 100, message = "Location name must be 100 characters or fewer") String name,

                @Size(max = 500, message = "Description must be 500 characters or fewer") String description) {

}
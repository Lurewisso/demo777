package com.example.demo.DTO;

import jakarta.validation.constraints.*;

public record PassportDTO(

        Long id,
        @NotBlank
        @Size(min = 2, max = 12, message = "Passport number must be 2-12 chars")
        String number
) {
}



package com.example.demo.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountsDTO(
        @NotNull
        @Size(min = 2, max = 50)
        String title
        ) {
}

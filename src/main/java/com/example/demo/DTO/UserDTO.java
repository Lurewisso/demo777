package com.example.demo.DTO;

import com.example.demo.entity.Passport;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public record UserDTO(

        @Id
        @Min(2)
        @Max(50)
        String name,
        @NotNull
        @Min(18)
        @Max(100)
        int age,
        @NotNull
        Passport passport,
        List<AccountsDTO> accounts,
        Set<String> hobbySet

) {
}

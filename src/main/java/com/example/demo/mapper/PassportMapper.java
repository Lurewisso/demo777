package com.example.demo.mapper;

import com.example.demo.DTO.PassportDTO;
import com.example.demo.entity.Passport;

public class PassportMapper {

    public static Passport toEntity(PassportDTO dto) {
        Passport passport = new Passport();
        passport.setId(dto.id());
        passport.setNumber(dto.number());
        return passport;
    }

    public static PassportDTO toDto(Passport passport) {
        return new PassportDTO(
                passport.getId(),
                passport.getNumber()
        );
    }
}

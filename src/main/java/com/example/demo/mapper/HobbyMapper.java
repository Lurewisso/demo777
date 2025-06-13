package com.example.demo.mapper;

import com.example.demo.DTO.HobbiesDTO;
import com.example.demo.entity.Hobby;

public class HobbyMapper {
    public static Hobby toEntity(HobbiesDTO dto) {
        return Hobby.builder()
                .type(dto.type())
                .build();
    }

    public static HobbiesDTO toDto(Hobby hobby) {
        return new HobbiesDTO(hobby.getType());
    }
}
package com.example.demo.services;

import com.example.demo.DTO.HobbiesDTO;

import java.util.List;

public interface HobbyService {
    HobbiesDTO Create(HobbiesDTO hobbiesDTO);
    HobbiesDTO findById(Long id);
    List<HobbiesDTO> getAll();

    HobbiesDTO update(Long id, HobbiesDTO hobbiesDTO);

    void delete(Long id);
}

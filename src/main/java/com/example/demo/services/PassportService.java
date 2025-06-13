package com.example.demo.services;

import com.example.demo.DTO.PassportDTO;

import java.util.List;

public interface PassportService {

    PassportDTO Create(PassportDTO passportDTO);
    PassportDTO findById(Long id);
    List<PassportDTO> getAll();

    PassportDTO update(Long id, PassportDTO passportDTO);

    void delete(Long id);
}

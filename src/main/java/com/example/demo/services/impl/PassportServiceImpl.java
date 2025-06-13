package com.example.demo.services.impl;



import com.example.demo.DTO.PassportDTO;
import com.example.demo.entity.Passport;
import com.example.demo.mapper.PassportMapper;
import com.example.demo.repositories.PassportReposetory;
import com.example.demo.services.PassportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class  PassportServiceImpl implements PassportService {
    private final PassportReposetory passportRepository;

    @Override
    public PassportDTO Create(PassportDTO passportDTO) {
        Passport passport = PassportMapper.toEntity(passportDTO);
        return PassportMapper.toDto(passportRepository.save(passport));
    }

    @Override
    public PassportDTO findById(Long id) {
        return passportRepository.findById(id)
                .map(PassportMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Passport not found"));
    }

    @Override
    public List<PassportDTO> getAll() {
        return passportRepository.findAll().stream()
                .map(PassportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PassportDTO update(Long id, PassportDTO passportDTO) {
        Passport existingPassport = passportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passport not found"));
        existingPassport.setNumber(passportDTO.number());
        return PassportMapper.toDto(passportRepository.save(existingPassport));
    }

    @Override
    public void delete(Long id) {
        passportRepository.deleteById(id);
    }
}
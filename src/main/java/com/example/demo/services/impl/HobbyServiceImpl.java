package com.example.demo.services.impl;

import com.example.demo.DTO.HobbiesDTO;
import com.example.demo.entity.Hobby;
import com.example.demo.mapper.HobbyMapper;
import com.example.demo.repositories.HobbiesReposetory;
import com.example.demo.services.HobbyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HobbyServiceImpl implements HobbyService {
    private final HobbiesReposetory hobbiesRepository;



    @Override
    public HobbiesDTO Create(HobbiesDTO hobbiesDTO) {
        Hobby hobby = HobbyMapper.toEntity(hobbiesDTO);
        Hobby savedHobby = hobbiesRepository.save(hobby);
        return HobbyMapper.toDto(savedHobby);
    }

    @Override
    public HobbiesDTO findById(Long id) {
        return hobbiesRepository.findById(id)
                .map(HobbyMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Hobby not found"));
    }

    @Override
    public List<HobbiesDTO> getAll() {
        return hobbiesRepository.findAll().stream()
                .map(HobbyMapper::toDto)
                .toList();
    }

    @Override
    public HobbiesDTO update(Long id, HobbiesDTO hobbiesDTO) {
        Hobby hobby = hobbiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hobby not found"));
        hobby.setType(hobbiesDTO.type());
        return HobbyMapper.toDto(hobbiesRepository.save(hobby));
    }

    @Override
    public void delete(Long id) {
        hobbiesRepository.deleteById(id);
    }
}
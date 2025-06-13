package com.example.demo.services.impl;


import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Hobby;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.HobbiesReposetory;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final HobbiesReposetory hobbiesReposetory;

//    @Override
//    public UserDTO Create(UserDTO user) {
//        Set<Hobby> hobbies = user.hobbySet().stream()
//                .map(name -> hobbiesReposetory.
//                        findByName(name).orElseGet(()->hobbiesReposetory.save(Hobby.builder().type(name).build()))).collect(Collectors.toSet());
//        User userToSave = UserMapper.toEntity(user,List.copyOf(hobbies));
//
//        return UserMapper.toDto(userRepository.save(userToSave));
//    }

    @Override
    public UserDTO Create(UserDTO userDTO) {
        Set<Hobby> hobbies = userDTO.hobbySet().stream()
                .map(type -> hobbiesReposetory
                        .findByType(type)  // Используем findByType вместо findByName
                        .orElseGet(() -> hobbiesReposetory.save(
                                Hobby.builder().type(type).build()
                        ))
                )
                .collect(Collectors.toSet());

        User user = UserMapper.toEntity(userDTO, List.copyOf(hobbies));
        return UserMapper.toDto(userRepository.save(user));
    }


    @Override
    public UserDTO findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto).orElseThrow(()-> new NoSuchElementException("User not found"));
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserDTO update(Long id, UserDTO user) {

      User user_ = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("нет элемента"));
      user_.setName(user.name());
      user_.setAge(user.age());

      return UserMapper.toDto(userRepository.save(user_));

    }

    @Override
    public void delete(Long id) {

        userRepository.deleteById(id);

    }
}

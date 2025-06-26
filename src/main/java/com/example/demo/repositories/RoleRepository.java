package com.example.demo.repositories;

import com.example.demo.entity.Credential;
import com.example.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Credential> findByName(String name);


}

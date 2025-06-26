package com.example.demo.repositories;

import com.example.demo.entity.Credential;
import com.example.demo.entity.Role;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credential,Long> {

    Optional<Credential> findByUserName(String name);
}

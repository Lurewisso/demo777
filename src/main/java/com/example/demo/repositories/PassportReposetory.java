package com.example.demo.repositories;

import com.example.demo.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportReposetory extends JpaRepository<Passport,Long>{
}

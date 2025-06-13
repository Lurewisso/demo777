package com.example.demo.controllers;


import com.example.demo.DTO.PassportDTO;
import com.example.demo.entity.Passport;
import com.example.demo.entity.User;
import com.example.demo.services.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/passport")
@RequiredArgsConstructor


@Tag(name = "Passport api", description = "CRUD операции для паспорта")
public class PassportController {


    private final PassportService passportService;

@Operation(summary = "get all passports", tags = {"Passport"}, description = "get all passport ",responses = {
@ApiResponse(responseCode = "200", description = "Passports found awesome dude ! ")})
    @GetMapping()
    public ResponseEntity<List<PassportDTO>> getPassports(){
        return ResponseEntity.ok()
                .body(passportService.getAll());
    }

    @Operation(summary = "Find passport by id", tags = {"Passport"}, description = "find passport by id ",responses = {
            @ApiResponse(responseCode = "200", description = "Passport find awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Passport not found awesome dude ! ")})
    @GetMapping("/{userid}")
    ResponseEntity<PassportDTO> getPassportById(@PathVariable Long id){
        return ResponseEntity.ok().body(passportService.findById(id));

    }

    @Operation(summary = "Create a passport", tags = {"Passport"}, description = "creation passport ",responses = {
            @ApiResponse(responseCode = "200", description = "Passport create awesome dude ! ")})
    @PostMapping
    public ResponseEntity<PassportDTO> createPassport(@RequestBody PassportDTO passportDTO){
        return ResponseEntity.ok().body(passportService.Create(passportDTO));
    }


    @Operation(summary = " passport  update", tags = {"Passport"}, description = "updating passport ",responses = {
            @ApiResponse(responseCode = "200", description = "Passport update awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Passport not found dude ! ")})
    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id,@RequestBody PassportDTO passportDTO){
        return ResponseEntity.ok().body(passportService.update(id,passportDTO));
    }

    @Operation(summary = " delete  update", tags = {"Passport"}, description = "delete passport ",responses = {
            @ApiResponse(responseCode = "200", description = "Passport delete awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Passport not found  dude ! ")})
    @DeleteMapping("/{id}")
    public ResponseEntity<PassportDTO> deletePassport(@PathVariable Long id){
        passportService.delete(id);
        return ResponseEntity.noContent().build();
    }




}

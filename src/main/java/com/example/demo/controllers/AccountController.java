package com.example.demo.controllers;


import com.example.demo.DTO.AccountsDTO;
import com.example.demo.DTO.HobbiesDTO;
import com.example.demo.services.AccountServices;
import com.example.demo.services.PassportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor

@Tag(name = "Account api", description = "CRUD операции для аккаунта")
public class AccountController {

    private final AccountServices accountServices;


    @Operation(summary = "get all Account",  description = "get all Accounts ",responses = {
            @ApiResponse(responseCode = "200", description = "Accounts found awesome dude ! ")})
    @GetMapping()
    public ResponseEntity<List<AccountsDTO>> getAccounts(){
        return ResponseEntity.ok()
                .body(accountServices.getAll());
    }

    @Operation(summary = "Find Account by id",  description = "find Account by id ",responses = {
            @ApiResponse(responseCode = "200", description = "Account find awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Account not found awesome dude ! ")})
    @GetMapping("/{userid}")
    ResponseEntity<AccountsDTO> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok().body(accountServices.findById(id));

    }

    @Operation(summary = "Create an Account",  description = "creation Account ",responses = {
            @ApiResponse(responseCode = "200", description = "Account create awesome dude ! ")})
    @PostMapping
    public ResponseEntity<AccountsDTO> createAccount(@RequestBody AccountsDTO accountsDTO){
        return ResponseEntity.ok().body(accountServices.Create(accountsDTO));
    }


    @Operation(summary = " Account  update",  description = "updating Account ",responses = {
            @ApiResponse(responseCode = "200", description = "Account update awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Account not found dude ! ")})
    @PutMapping("/{id}")
    public ResponseEntity<AccountsDTO> updateAccount(@PathVariable Long id,@RequestBody AccountsDTO accountsDTO){
        return ResponseEntity.ok().body(accountServices.update(id,accountsDTO));
    }

    @Operation(summary = " delete  update",  description = "Account hobby ",responses = {
            @ApiResponse(responseCode = "200", description = "Account delete awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Account not found  dude ! ")})
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountsDTO> deleteAccount(@PathVariable Long id){
        accountServices.delete(id);
        return ResponseEntity.noContent().build();
    }

}

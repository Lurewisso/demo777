package com.example.demo.controllers;


import com.example.demo.DTO.AccountsDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.services.AccountServices;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

@Tag(name = "User api", description = "CRUD операции для юзера")
public class UserController {

    private final UserService userService;


    @Operation(summary = "get all Users", description = "get all Users ",responses = {
            @ApiResponse(responseCode = "200", description = "Users found awesome dude ! ")})
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok()
                .body(userService.getAll());
    }

    @Operation(summary = "Find User by id",  description = "find User by id ",responses = {
            @ApiResponse(responseCode = "200", description = "User find awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "User not found awesome dude ! ")})
    @GetMapping("/{userid}")
    ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.findById(id));

    }

    @Operation(summary = "Create a User",  description = "creation User ",responses = {
            @ApiResponse(responseCode = "200", description = "User create awesome dude ! ")})
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.Create(userDTO));
    }


    @Operation(summary = " User  update",  description = "updating User ",responses = {
            @ApiResponse(responseCode = "200", description = "User update awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "User not found dude ! ")})
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.update(id,userDTO));
    }


    /// сюда порпобуем засунуть новый контроллер
    @Operation(summary = " User  update",  description = "updating User ",responses = {
            @ApiResponse(responseCode = "200", description = "User update awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "User not found dude ! ")})
    @PutMapping("/{id}/getAdmin")
    public ResponseEntity<UserDTO> updateUserToAdmin(@PathVariable Long id,@RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.update(id,userDTO));
    }




    @Operation(summary = " delete  update",  description = "User delete ",responses = {
            @ApiResponse(responseCode = "200", description = "User delete awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "User not found  dude ! ")})
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

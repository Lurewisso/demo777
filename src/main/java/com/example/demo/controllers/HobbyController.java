package com.example.demo.controllers;


import com.example.demo.DTO.HobbiesDTO;
import com.example.demo.services.HobbyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hobby")
@RequiredArgsConstructor


@Tag(name = "Hobby api", description = "CRUD операции для хобби")
public class HobbyController {



    private final HobbyService hobbyService;

    @Operation(summary = "get all hobby",  description = "get all hobby ",responses = {
            @ApiResponse(responseCode = "200", description = "Hobby found awesome dude ! ")})
    @GetMapping()
    public ResponseEntity<List<HobbiesDTO>> getHobbies(){
        return ResponseEntity.ok()
                .body(hobbyService.getAll());
    }

    @Operation(summary = "Find hobby by id",  description = "find hobby by id ",responses = {
            @ApiResponse(responseCode = "200", description = "Hobby find awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Hobby not found awesome dude ! ")})
    @GetMapping("/{userid}")
    ResponseEntity<HobbiesDTO> getHobbyById(@PathVariable Long id){
        return ResponseEntity.ok().body(hobbyService.findById(id));

    }

    @Operation(summary = "Create a hobby",  description = "creation hobby ",responses = {
            @ApiResponse(responseCode = "200", description = "Hobby create awesome dude ! ")})
    @PostMapping
    public ResponseEntity<HobbiesDTO> createHobby(@RequestBody HobbiesDTO hobbiesDTO){
        return ResponseEntity.ok().body(hobbyService.Create(hobbiesDTO));
    }


    @Operation(summary = " hobby  update",  description = "updating hobby ",responses = {
            @ApiResponse(responseCode = "200", description = "Hobby update awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "Hobby not found dude ! ")})
    @PutMapping("/{id}")
    public ResponseEntity<HobbiesDTO> updateHobby(@PathVariable Long id,@RequestBody HobbiesDTO hobbiesDTO){
        return ResponseEntity.ok().body(hobbyService.update(id,hobbiesDTO));
    }

    @Operation(summary = " delete  update",  description = "delete hobby ",responses = {
            @ApiResponse(responseCode = "200", description = "hobby delete awesome dude ! "),
            @ApiResponse(responseCode = "404", description = "hobby not found  dude ! ")})
    @DeleteMapping("/{id}")
    public ResponseEntity<HobbiesDTO> deleteHobby(@PathVariable Long id){
        hobbyService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

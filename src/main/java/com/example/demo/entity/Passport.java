package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(schema = "users_schema", name = "t_passport")



public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "c_number")
    String number;

//    @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL)
//    private User user;

}

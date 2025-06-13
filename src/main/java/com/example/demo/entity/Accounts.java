package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(schema = "users_schema", name = "t_accounts")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "c_title")
    String title;

    @ManyToOne
    @JoinColumn(name = "c_user_id")
    User user;


}


//
//package com.example.demo.entity;
//
//import jakarta.persistence.*;
//        import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(schema = "users_schema", name = "t_accounts")
//public class Accounts {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "c_title", nullable = false, length = 50)
//    private String title;
//
//    @ManyToOne
//    @JoinColumn(name = "c_user_id", referencedColumnName = "id")
//    private User user;
//}
//

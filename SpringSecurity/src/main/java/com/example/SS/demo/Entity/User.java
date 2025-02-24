package com.example.SS.demo.Entity;

import com.example.SS.demo.Enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String username;
    private String password;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Roles> roles;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    private LocalDate createdAt;
}

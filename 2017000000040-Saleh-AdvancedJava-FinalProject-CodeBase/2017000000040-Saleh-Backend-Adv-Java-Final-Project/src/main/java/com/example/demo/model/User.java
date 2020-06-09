package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private Integer id;
    private String userName;
    private String password;
    private String fullName;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String country;
    private String city;
    private String areaCode;
    private String bio;
    private String profession;
    private String imagePath;

}

package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserFavorite {
    @Id
    private Integer id;
    private LocalDate dateAdded;
    @OneToOne
    private User favouriteMaker;
    @OneToOne
    private User favouriteUser;
}

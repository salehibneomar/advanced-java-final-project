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
public class BlockedUser {
    @Id
    private Integer id;
    private LocalDate dateBlocked;
    @OneToOne
    private User blockedUser;
    @OneToOne
    private User blockerUser;
}

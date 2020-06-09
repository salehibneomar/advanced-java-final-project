package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserMessage {
    @Id
    private Integer id;
    private LocalDate sentTime;
    private String message;
    private boolean seenStatus;
    @OneToOne
    private User sender;
    @OneToOne
    private User recipient;
}

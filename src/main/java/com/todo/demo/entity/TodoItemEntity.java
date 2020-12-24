package com.todo.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
//Lombok usage for readable code
//For compile the code, have to install lombok plugin
public @Data class TodoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private LocalDate date;
    private LocalDate createDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String owner;
}

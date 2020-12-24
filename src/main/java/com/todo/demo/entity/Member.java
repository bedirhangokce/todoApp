package com.todo.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
//Lombok usage for readable code
//For compile the code, have to install lombok plugin
public @Data class Member {
    @Id
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String role;
}

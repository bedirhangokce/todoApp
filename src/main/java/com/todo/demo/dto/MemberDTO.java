package com.todo.demo.dto;

import lombok.Data;

//Lombok usage for readable code
public @Data class MemberDTO {
    private String username;
    private String password;
    private String role;
}

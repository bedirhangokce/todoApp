package com.todo.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Member {

    @Id
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String role;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Member(@NotEmpty String username, @NotEmpty String password, @NotEmpty String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Member() {
    }
}

package com.todo.demo.entity;

import javax.persistence.*;


@Entity
@Table
public class TodoUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    long password;

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.todo.demo.repository;

import com.todo.demo.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoUserRepository extends CrudRepository<UserEntity,Long> {
    List<UserEntity> findAll();
    UserEntity findByUsername(String username);

}

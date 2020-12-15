package com.todo.demo.repository;

import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.entity.TodoUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository  extends CrudRepository<TodoItemEntity,Long> {
    List<TodoItemEntity> findAll();
}

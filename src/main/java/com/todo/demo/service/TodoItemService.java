package com.todo.demo.service;

import com.todo.demo.dto.NewTodoDTO;
import com.todo.demo.entity.Status;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TodoItemService {
    @Autowired
    private TodoRepository todoRepository;

    public List<TodoItemEntity> findAll(){
        return todoRepository.findAll();
    }

    public TodoItemEntity save(NewTodoDTO dto){
        TodoItemEntity entity= new TodoItemEntity();
        entity.setDescription(dto.getDescription());
        entity.setStatus(Status.UNDONE);
        entity.setCreateDate(dto.getCreateDate());
        if (dto.getDate() == null){
            entity.setDate(LocalDate.now().plusMonths(1));
        }
        else entity.setDate(dto.getDate());

        entity = todoRepository.save(entity);
        return entity;
    }

    public void update(long id) {
        TodoItemEntity entity = todoRepository.findById(id).get();
        entity.setStatus(Status.DONE);

        todoRepository.save(entity);
    }
    public void delay(long id){
        TodoItemEntity entity = todoRepository.findById(id).get();
        entity.setStatus(Status.DELAYED);
        entity.setDate(entity.getDate().plusDays(7));

        todoRepository.save(entity);
    }

    public void delete(long id){
        TodoItemEntity entity = todoRepository.findById(id).get();
        todoRepository.delete(entity);
    }
}

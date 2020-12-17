package com.todo.demo.controller;

import com.todo.demo.dto.NewTodoDTO;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.entity.UserEntity;
import com.todo.demo.service.TodoItemService;
import com.todo.demo.service.TodoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class HomePageController {

    @Autowired
    private TodoItemService todoItemService;
    @Autowired
    private TodoUserService todoUserService;

    @RequestMapping("/")
    public ModelAndView defaultHome() {
        return new ModelAndView("home");
    }
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String openTodosPage(Model model){
        List<TodoItemEntity> todos = todoItemService.findAll();
        List<UserEntity> users = todoUserService.findAll();
        model.addAttribute("todos",todos);
        model.addAttribute("users",users);
        return "todos";
    }
    @RequestMapping(value = "/addTodo",method = RequestMethod.POST)
    @ResponseBody
    public TodoItemEntity save(@RequestBody NewTodoDTO dto){ return todoItemService.save(dto); }

    @RequestMapping(value = "/todo/{todo-id}",method = RequestMethod.PUT)
    @ResponseBody
    public void save(@PathVariable("todo-id")long id){
        todoItemService.update(id);
    }

    @RequestMapping(value = "/todoDelay/{todo-id}",method = RequestMethod.PUT)
    @ResponseBody
    public void delay(@PathVariable("todo-id")long id){
        todoItemService.delay(id);
    }

    @RequestMapping(value = "/todoDelete/{todo-id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("todo-id") long id) {
        todoItemService.delete(id);
    }
}

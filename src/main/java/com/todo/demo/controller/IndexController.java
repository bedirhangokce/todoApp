package com.todo.demo.controller;

import com.todo.demo.dto.NewTodoDTO;
import com.todo.demo.entity.Member;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.service.MemberService;
import com.todo.demo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private TodoItemService todoItemService;
    @Autowired
    private MemberService memberService;
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String openTodosPage(Model model){
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        String username = principal.getName();
        List<Member> users = memberService.findAll();
        List<TodoItemEntity> todos;
        //If logged in user an admin show all todos Else just logged in user's
        if (principal.getAuthorities().toString().contains("ROLE_ADMIN")){
            todos = todoItemService.findAll();
        }else {
            todos = todoItemService.findAllByOwner(username);
        }
        model.addAttribute("todos",todos);
        model.addAttribute("users",users);
        return "index";
    }
    //Todos add and manipulate
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
    public void delete(@PathVariable("todo-id") long id) { todoItemService.delete(id); }

    //Member add and manipulate
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Member save(@RequestBody Member member){ return memberService.save(member); }

    @RequestMapping(value = "/changeRole/{username}",method = RequestMethod.PUT)
    @ResponseBody
    public void delay(@PathVariable("username")String username){ memberService.changeRole(username); }

    @RequestMapping(value = "/userDelete/{username}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable("username") String username) {
        memberService.deleteMember(username);
    }

    //TODO add change user password
}
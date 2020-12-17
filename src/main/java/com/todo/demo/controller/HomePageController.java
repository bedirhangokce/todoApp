package com.todo.demo.controller;

import com.todo.demo.dto.NewTodoDTO;
import com.todo.demo.dto.UserDTO;
import com.todo.demo.entity.Member;
import com.todo.demo.entity.TodoItemEntity;
import com.todo.demo.repository.MemberRepository;
import com.todo.demo.service.MemberService;
import com.todo.demo.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomePageController {

    @Autowired
    private TodoItemService todoItemService;
    @Autowired
    private MemberService memberService;
    @RequestMapping("/")
    public ModelAndView defaultHome() {
        Member member = new Member();
        member.setUsername("bedo");
        member.setPassword("bedo");
        member.setRole("ADMIN");
        memberService.save(member);
        return new ModelAndView("home");
    }
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String openTodosPage(Model model){
        List<TodoItemEntity> todos = todoItemService.findAll();
        List<Member> users = memberService.findAll();
        model.addAttribute("todos",todos);
        model.addAttribute("users",users);
        return "todos";
    }
    @RequestMapping(value = "/addTodo",method = RequestMethod.POST)
    @ResponseBody
    public TodoItemEntity save(@RequestBody NewTodoDTO dto){ return todoItemService.save(dto); }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public Member save(@RequestBody Member member){ return memberService.save(member); }

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

    @RequestMapping(value = "/userDelete/{username}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable("username") String username) {

        memberService.deleteMember(username);
    }

}

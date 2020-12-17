package com.todo.demo.service;

import com.todo.demo.dto.NewUserDTO;

import com.todo.demo.entity.UserEntity;
import com.todo.demo.repository.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TodoUserService implements UserDetailsService {

    @Autowired
    private TodoUserRepository todoUserRepository;

    @Autowired
    private final PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();;

    public List<UserEntity> findAll(){
        return todoUserRepository.findAll();
    }

    public void saveUser(NewUserDTO dto){
        UserEntity entity= new UserEntity();
        entity.setUsername(dto.getName());
        entity.setPassword(dto.getPassword());
        todoUserRepository.save(entity);
    }
    public void updateUsername(long id,String username) {
        UserEntity entity = todoUserRepository.findById(id).get();
        entity.setUsername(username);

        todoUserRepository.save(entity);
    }
    public void updatePassword(long id,String password){
        UserEntity entity = todoUserRepository.findById(id).get();
        entity.setPassword(encoder.encode(password));

        todoUserRepository.save(entity);
    }
    public void deleteUser(long id){
        UserEntity entity = todoUserRepository.findById(id).get();
        todoUserRepository.delete(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = todoUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
}


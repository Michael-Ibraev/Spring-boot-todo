package com.example.todo.services;

import com.example.todo.entity.UserEntity;
import com.example.todo.exceptions.UserAlreadyExistException;
import com.example.todo.exceptions.UserNotFoundException;
import com.example.todo.repository.UserRepo;
import com.example.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepo.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if(user == null){
            throw new UserNotFoundException("Пользователь не был найден");
        }
        return User.toModel(user);
    }

    public Long delete(Long id){
        userRepo.deleteById(id);
        return id;
    }
}

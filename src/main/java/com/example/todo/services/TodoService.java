package com.example.todo.services;

import com.example.todo.entity.TodoEntity;
import com.example.todo.entity.UserEntity;
import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepo;
import com.example.todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 30:11
@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public Todo createTodo(TodoEntity todo, Long userId){
        UserEntity user = userRepo.findById(userId).get();
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo completeTodo(Long Id){
        TodoEntity todo = todoRepo.findById(Id).get();
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}

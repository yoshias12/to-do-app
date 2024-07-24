package com.enigmacamp.service;


import com.enigmacamp.model.Task;
import com.enigmacamp.model.User;
import com.enigmacamp.repository.TodoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TodoService {
    private TodoRepository todoRepository;
    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Task> getTodosByUser(User user) {
        return todoRepository.findByUser(user);
    }

    public void addTodo(Task todo) {
        todoRepository.save(todo);
    }
    
    public void deleteTodo(Task todo) {
        todoRepository.delete(todo);
    }

    public void updateTodo(Task todo) {
        todoRepository.update(todo);
    }

    public Task getTodoById(Integer id) {
        return todoRepository.findById(id);
    }


}

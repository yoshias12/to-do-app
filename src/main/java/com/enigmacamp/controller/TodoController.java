package com.enigmacamp.controller;

import com.enigmacamp.config.HibernateUtil;
import com.enigmacamp.model.Task;
import com.enigmacamp.model.User;
import com.enigmacamp.service.TodoService;
import com.enigmacamp.util.Helper;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TodoController {
    private TodoService todoService;
    Helper helper = new Helper();

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    public void createTodo(User user) {
        String todo = helper.inputString("todolist");

        Task task = new Task(todo);
        task.setTodolist(todo);
        task.setUser(user);

        todoService.addTodo(task);
        System.out.println("Todo berhasil ditambahkan...");
    }

    public void listTodo(User user) {
        List<Task> todos = todoService.getTodosByUser(user);
        todos.forEach(todo -> System.out.println(todo));
//        for (Task task : todos) {
//
//            System.out.println("Todo: " + task.getTodolist());
//            System.out.println("Completed: " + task.getCompleted());
//            System.out.println("--------------");
//        }
    }

    public void deleteTodo(User user) {

        Integer id = helper.inputInt("Masukkan id todo yang ingin dihapus");
        Task todo = todoService.getTodosByUser(user).stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
//        Task todo = todoService.getTodoById(id);

        if (todo != null && todo.getUser().equals(user)) {
            todoService.deleteTodo(todo);
            System.out.println("Todo berhasil dihapus...");
        } else {
            System.out.println("Id tidak ditemukan...");
        }
    }

    public void updateTodo(User user) {
        Integer id = helper.inputInt("Masukkan id todo yang ingin diupdate");
        Task todoId = todoService.getTodosByUser(user).stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
        if (todoId != null && todoId.getUser().equals(user)) {
            String todoList = helper.inputStringU("Masukkan todo (kosongkan jika tidak ingin ada perubahan)");

            if (!todoList.isEmpty() || todoList.isBlank()) {
                todoList = todoId.getTodolist();
            }
            todoId.setTodolist(todoList);

            boolean valid = true;
            do {
                String cek = helper.inputString("Completed (1 = true/ 2 = false)");
                switch (cek) {
                    case "1":
                        todoId.setCompleted(true);
                        valid = false;
                        break;
                    case "2":
                        todoId.setCompleted(false);
                        valid = false;
                        break;
                    default:
                        System.out.println("Input salah");
                        break;
                }
            } while (valid);

            todoService.updateTodo(todoId);
            System.out.println("Todo berhasil diupdate..");
        } else {
            System.out.println("Id tidak ditemukan...");
        }
    }
}

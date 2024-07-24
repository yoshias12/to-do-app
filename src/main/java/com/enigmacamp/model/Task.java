package com.enigmacamp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "m_todolists")
public class Task {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "todolist", nullable = false)
    private String todolist;
    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Task(String todolist) {
        this.todolist = todolist;
        this.completed = false;
    }



    public Integer getId() {
        return id;
    }

    public String getTodolist() {
        return todolist;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTodolist(String todolist) {
        this.todolist = todolist;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", todolist='" + todolist + '\'' +
                ", completed=" + completed +
                ", user=" + user +
                '}';
    }
}

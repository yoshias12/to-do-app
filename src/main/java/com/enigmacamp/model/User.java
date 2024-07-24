package com.enigmacamp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "m_user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_name" , nullable = false, unique = true )
    private String userName;

    @Column(name = "password" , nullable = false)
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}

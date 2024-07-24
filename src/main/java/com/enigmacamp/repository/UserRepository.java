package com.enigmacamp.repository;

import com.enigmacamp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepository {
    private final EntityManager em;
    @Autowired
    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findByUsername(String username) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :username",User.class);
        query.setParameter("username", username);
        return query.getResultList().stream().findFirst();
    }

    public void save(User user) {
        EntityManager transaction = em;
        transaction.getTransaction().begin();
        transaction.persist(user);
        transaction.getTransaction().commit();
    }
}


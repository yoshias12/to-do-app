package com.enigmacamp.repository;


import com.enigmacamp.model.Task;
import com.enigmacamp.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TodoRepository {
    private EntityManager em;
    @Autowired
    public TodoRepository(EntityManager em) {
        this.em = em;
    }

    public List<Task> findByUser(User user) {
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t WHERE t.user =:user",Task.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public void save(Task task) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(task);
        transaction.commit();
    }

    public void delete(Task task) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.contains(task) ? task : em.merge(task));
//        penjelasan  "?" = ternary
//        if (em.contains(task)) {
//            em.remove(task);
//        } else {
//            em.remove(em.merge(task));
//        }

        transaction.commit();
    }

    public void update (Task task) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(task);
        transaction.commit();
    }

    public  Task findById(Integer id) {
        return em.find(Task.class, id);
    }
}

package com.enigmacamp.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ComponentScan("com.enigmacamp")
public class HibernateUtil {
    private static EntityManagerFactory emf;

    public static void getEntityManagerFactory(){
        emf = Persistence
                .createEntityManagerFactory("enigma-persistence");
    }
//    @Bean
//    public static EntityManager getEntityManagerFactory() {
//        if (emf.isOpen()) {
//            return emf.createEntityManager();
//        }
//        return null;
//    }

    @Bean
    public static EntityManager getEntityManager() {
        if (emf == null) {
            getEntityManagerFactory();
        }
        return emf.createEntityManager();
    }
}

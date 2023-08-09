package com.alura.jpa_hibernate.mercado.utils;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class JpaUtils {
    private static EntityManagerFactory FACTORY= Persistence.createEntityManagerFactory("mercado");
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
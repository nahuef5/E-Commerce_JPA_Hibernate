package com.alura.jpa_hibernate.mercado;

import com.alura.jpa_hibernate.mercado.entity.Categoria;
import com.alura.jpa_hibernate.mercado.utils.JpaUtils;
import javax.persistence.EntityManager;
public class Mercado {
    public static void main(String[] args) {
        Categoria categoria = new Categoria("Celular");
        EntityManager entityManager=JpaUtils.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(categoria);
        categoria.setNombre("Tecnologia");
        entityManager.flush();
        entityManager.clear();
        categoria =entityManager.merge(categoria);
        categoria.setNombre("Tecno");
        entityManager.flush();
        entityManager.clear();
        categoria=entityManager.merge(categoria);
        entityManager.remove(categoria);
        entityManager.flush();
    }
}
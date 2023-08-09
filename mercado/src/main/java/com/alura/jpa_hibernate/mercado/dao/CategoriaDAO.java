package com.alura.jpa_hibernate.mercado.dao;

import com.alura.jpa_hibernate.mercado.entity.Categoria;
import javax.persistence.EntityManager;

public class CategoriaDAO {
    private EntityManager entityManager;

    public CategoriaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Categoria categoria){
        this.entityManager.persist(categoria);
    }
    public void update(Categoria categoria){
        this.entityManager.merge(categoria);
    }
    public void delete(Categoria categoria){
        categoria= this.entityManager.merge(categoria);
        this.entityManager.remove(categoria);
    }
    public Categoria getByNombre(String nombre){
		String jpql =" SELECT C FROM Categoria AS C WHERE C.nombre=:nombre ";
		return entityManager.createQuery(jpql,Categoria.class).setParameter("nombre", nombre).getSingleResult();
    }
}
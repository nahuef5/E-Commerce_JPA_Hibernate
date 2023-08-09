package com.alura.jpa_hibernate.mercado.dao;

import com.alura.jpa_hibernate.mercado.entity.Producto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
public class ProductoDAO {
    private EntityManager entityManager;

    public ProductoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Producto producto){
        this.entityManager.persist(producto);
    }
    public Producto getById(Integer id){
        return entityManager.find(Producto.class,id);
    }
    public List<Producto> findAll(){
        String jpql="SELECT p FROM Producto AS p";
        return entityManager.createQuery(jpql,Producto.class).getResultList();
    }
    public List<Producto> findAllByNombre(String nombre){
        String jpql="SELECT p FROM Producto AS p WHERE p.nombre=:nombre";
        return entityManager.
                createQuery(jpql,Producto.class)
                .setParameter("nombre",nombre)
                .getResultList();
    }
    public List<Producto> findAllByCategoria(String nombre){
        String jpql="SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
        return entityManager.
                createQuery(jpql,Producto.class)
                .setParameter("nombre",nombre)
                .getResultList();
    }
    public BigDecimal getPrecioByNombre(String nombre){
        String jpql="SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre";
        return entityManager.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
    public BigDecimal PrecioByNombreFromNamedQuery(String nombre){
        return entityManager.createNamedQuery("Producto.consultarPrecio", BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
    public List<Producto> getByParameter(String nombre, BigDecimal precio, LocalDate fechaRegistro){
        CriteriaBuilder builder=entityManager.getCriteriaBuilder();
        CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
        Root<Producto> from = query.from(Producto.class);
        
        Predicate filter = builder.and();
        if(nombre!=null && !nombre.trim().isEmpty())
            filter=builder.and(filter,builder.equal(from.get("nombre"), nombre));
        if(precio!=null && !precio.equals(new BigDecimal(0)))
            filter=builder.and(filter,builder.equal(from.get("precio"), precio));        
        if(fechaRegistro!=null)
            filter=builder.and(filter,builder.equal(from.get("fechaRegistro"), fechaRegistro));
        query = query.where(filter);
        return entityManager.createQuery(query).getResultList();
    }
}

package com.alura.jpa_hibernate.mercado.dao;
import com.alura.jpa_hibernate.mercado.entity.*;
import com.alura.jpa_hibernate.mercado.vo.Relatorio_VentaVO;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
public class CarritoDAO{
    private EntityManager entityManager;
    public CarritoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Carrito carrito){
        this.entityManager.persist(carrito);
    }
    public Carrito getById(Integer id){
        return entityManager.find(Carrito.class,id);
    }
    public List<Carrito> findAll(){
        String jpql="SELECT c FROM Carrito AS c";
        return entityManager.createQuery(jpql,Carrito.class).getResultList();
    }
    
    public BigDecimal valorTotalVendido(){
        String jpql="SELECT SUM(c.totalValor) FROM Carrito c";
        return entityManager.createQuery(jpql, BigDecimal.class).getSingleResult();
    }
    public List<Object[]> relatorioVentas(){
        String jpql=
                "SELECT producto.nombre, "
                +"SUM(pedido.cantidad), "
                +"MAX(carrito.fecha) "
                +"FROM Carrito carrito "
                +"JOIN carrito.pedidos pedido "
                +"JOIN pedido.producto producto "
                +"GROUP BY producto.nombre "
                +"ORDER BY pedido.cantidad DESC";
        return entityManager.createQuery(jpql, Object[].class).getResultList();
    }
    public List<Relatorio_VentaVO> relatorioVentasVO(){
        String jpql=
                "SELECT new com.alura.jpa_hibernate.mercado.vo.Relatorio_VentaVO(producto.nombre, "
                +"SUM(pedido.cantidad), "
                +"MAX(carrito.fecha)) "
                +"FROM Carrito carrito "
                +"JOIN carrito.pedidos pedido "
                +"JOIN pedido.producto producto "
                +"GROUP BY producto.nombre "
                +"ORDER BY pedido.cantidad DESC";
        return entityManager.createQuery(jpql, Relatorio_VentaVO.class).getResultList();
    }
    public Carrito getCarritoWithCliente(int id){
        String jpql=
                "SELECT c FROM Carrito c "
                +"JOIN FETCH c.cliente "
                +"LEFT JOIN FETCH c.pedidos "
                +"WHERE c.id=:id";
        return entityManager
                .createQuery(jpql,Carrito.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

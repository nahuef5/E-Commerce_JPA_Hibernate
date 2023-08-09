package com.alura.jpa_hibernate.mercado.tests;

import com.alura.jpa_hibernate.mercado.dao.CarritoDAO;
import com.alura.jpa_hibernate.mercado.dao.ProductoDAO;
import com.alura.jpa_hibernate.mercado.entity.Carrito;
import com.alura.jpa_hibernate.mercado.entity.Producto;
import com.alura.jpa_hibernate.mercado.utils.JpaUtils;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
public class TestDesempenio {
    public static void main(String[] args){
        try{
            LoadRecords.cargarRegistros();
            EntityManager entityManager=JpaUtils.getEntityManager();
            CarritoDAO carritoDAO = new CarritoDAO(entityManager);
            Carrito carritoWithCliente = carritoDAO.getCarritoWithCliente(3);
            System.out.println("\n\n\n\n");
            System.out.println("----------------------carritoWithCliente-------------------------");
            System.out.println(carritoWithCliente.getFecha());
            System.out.println(carritoWithCliente.getPedido().size());
            System.out.println(carritoWithCliente.getCliente().getNombre());
            ProductoDAO productoDAO=new ProductoDAO(entityManager);
            List<Producto> list=productoDAO.getByParameter("Motorola",new BigDecimal(1000.00), null);
            System.out.println(list.get(0).getDescripcion());
            entityManager.close();
        }
        catch(FileNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
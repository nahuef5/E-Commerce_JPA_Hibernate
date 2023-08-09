package com.alura.jpa_hibernate.mercado.tests;
import com.alura.jpa_hibernate.mercado.dao.*;
import com.alura.jpa_hibernate.mercado.entity.*;
import com.alura.jpa_hibernate.mercado.utils.JpaUtils;
import com.alura.jpa_hibernate.mercado.vo.Relatorio_VentaVO;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
public class TestRegistroPedido {
    public static void main(String[] args) {
        saveProduct();
        EntityManager entityManager = JpaUtils.getEntityManager();
        ProductoDAO productoDAO= new ProductoDAO(entityManager);
        Producto producto =productoDAO.getById(1);
        ClienteDAO clienteDAO= new ClienteDAO(entityManager);
        CarritoDAO carritoDAO = new CarritoDAO(entityManager);
        Cliente cliente = new Cliente("Nahuel", "12345");
        Carrito carrito= new Carrito(cliente);
        carrito.addItems(new Pedido(5,producto, carrito));
        
        entityManager.getTransaction().begin();
        clienteDAO.save(cliente);
        carritoDAO.save(carrito);
        entityManager.getTransaction().commit();
        
        BigDecimal total = carritoDAO.valorTotalVendido();
        System.out.println("\n\n____________$"+total);
        List<Object[]> listaRelatorioVentas = carritoDAO.relatorioVentas();
        for(Object[] o:listaRelatorioVentas){
            System.out.println("---------------------------------------");
            System.out.println(o[0]);
            System.out.println(o[1]);
            System.out.println(o[2]);
        }
        System.out.println("--------------Con Valor Object-------------------------");
        
        List<Relatorio_VentaVO> listaRelatorioVentasVO = carritoDAO.relatorioVentasVO();
        listaRelatorioVentasVO.forEach(
                relatorioVentasVO -> System.out.println(
                        "--------------------------------\n"+relatorioVentasVO.toString()));
    }
    private static void saveProduct(){
        Categoria categoria=new Categoria("Celular");
        Producto producto= new Producto("Samsung","Usado",25,new BigDecimal("100"),categoria);
        EntityManager entityManager=JpaUtils.getEntityManager();
        CategoriaDAO categoriaDAO=new CategoriaDAO(entityManager);
        ProductoDAO productoDAO=new ProductoDAO(entityManager);
        entityManager.getTransaction().begin();
        categoriaDAO.save(categoria);
        productoDAO.save(producto);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}
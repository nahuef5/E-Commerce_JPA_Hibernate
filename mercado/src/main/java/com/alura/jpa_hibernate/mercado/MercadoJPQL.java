package com.alura.jpa_hibernate.mercado;
import com.alura.jpa_hibernate.mercado.dao.*;
import com.alura.jpa_hibernate.mercado.entity.*;
import com.alura.jpa_hibernate.mercado.utils.JpaUtils;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
public class MercadoJPQL {
    public static void main(String[] args) {
        saveProduct();
        EntityManager entityManager=JpaUtils.getEntityManager();
        ProductoDAO productoDAO=new ProductoDAO(entityManager);
        Producto prod=productoDAO.getById(1);
        System.out.println(
                "\n\n________________________________________________________\n\n"
                + prod.getNombre()
                + "\n________________________________________________________");
        
        List<Producto> productos=productoDAO.findAll();
        productos.forEach(p -> System.out.println(p.getDescripcion()));
        
        List<Producto> porNombres=productoDAO.findAllByNombre("Samsung");
        porNombres.forEach(p-> System.out.println(
                p.getNombre()+" -"
                +p.getDescripcion()
                +" -"+p.getPrecio()));
        
        List<Producto> porCtegoria=productoDAO.findAllByNombre("Celular");
        porNombres.forEach(p-> System.out.println(p.getNombre()+" -"+p.getCategoria().getNombre()));
        
        System.out.println("-----------------consulta con JPQL---------------------------\n");
        BigDecimal porPrecio=productoDAO.getPrecioByNombre("Samsung");
        System.out.println("$ "+porPrecio);
        System.out.println("-------------------------por named query--------------------\n");
        BigDecimal porPrecioPorNamedQuery=productoDAO.PrecioByNombreFromNamedQuery("Samsung");
        System.out.println("$ "+porPrecioPorNamedQuery);
    }
    private static void saveProduct(){
        Categoria categoria=new Categoria("Celular");
        Producto producto= new Producto("Samsung","Usado",25,new BigDecimal("951413"),categoria);
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
package com.alura.jpa_hibernate.mercado.tests;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import com.alura.jpa_hibernate.mercado.dao.*;
import com.alura.jpa_hibernate.mercado.entity.*;
import com.alura.jpa_hibernate.mercado.utils.JpaUtils;

public class LoadRecords{
    public static void cargarRegistros() throws FileNotFoundException {
		EntityManager em = JpaUtils.getEntityManager();
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProductoDAO productoDAO = new ProductoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		CarritoDAO carritoDAO = new CarritoDAO(em);
		em.getTransaction().begin();
		
		loadCategoria("categoria",categoriaDAO,em);
		
		loadProducto("producto",productoDAO,categoriaDAO,em);
		
		loadCliente("cliente",clienteDAO,em);
		
		List<Cliente> clientesList = clienteDAO.findAll();
		List<Carrito> carritoList= new ArrayList<>();
		
		for(Cliente cl:clientesList) {
			carritoList.add(new Carrito(cl));
		}
		
		for(int i=0;i<carritoList.size();i++) {
			carritoList.get(i).addItems(new Pedido(i+1,productoDAO.getById((int) (i+1)),carritoList.get(i)));
			carritoDAO.save(carritoList.get(i));
		}
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void loadProducto(String type, ProductoDAO productoDAO,CategoriaDAO categoriaDAO, EntityManager em) throws FileNotFoundException {
		List<String> productosTxt =readFile(type);
		for(int i=0;i<productosTxt.size();i++) {
			String[] line = productosTxt.get(i).split(";");
			if(line.length>1) {
				Categoria categoria=categoriaDAO.getByNombre(line[3]);
				Producto producto = new Producto(line[4],line[0],new BigDecimal(line[1]),categoria);
				productoDAO.save(producto);
				em.flush();
			}
		}
	}

	private static void loadCategoria(String type, CategoriaDAO categoriaDao,EntityManager em) throws FileNotFoundException {
		List<String> categoriasTxt =readFile(type);		
		for(int i=0;i<categoriasTxt.size();i++) {
			String[] line = categoriasTxt.get(i).split(";");
			if(line.length==1) {
				Categoria categoria = new Categoria(categoriasTxt.get(i));
				categoriaDao.save(categoria);
				em.flush();	
			}
		}
	}

	private static void loadCliente(String type, ClienteDAO clienteDao,EntityManager em) throws FileNotFoundException {
		List<String> clientesTxt =readFile(type);		
		for(int i=0;i<clientesTxt.size();i++) {
			String[] line = clientesTxt.get(i).split("~");
			System.out.println(line[0]+line[1]);
			if(line.length>1) {
				Cliente cliente= new Cliente(line[0],line[1]);
				clienteDao.save(cliente);
				em.flush();	
			}
		}
	}
	
	private static List<String> readFile(String type) throws FileNotFoundException {
		File file = new File("C:\\User\\Desktop\\Alura\\JPA_Hibernate_II\\mercado\\src\\main\\resources\\utils\\"+type+".txt");
		Scanner scan = new Scanner(file);
		List<String> carrito= new ArrayList<>();
		while(scan.hasNextLine()){
			carrito.add(scan.nextLine());
		}
		scan.close();
		return carrito;
                
	}
}

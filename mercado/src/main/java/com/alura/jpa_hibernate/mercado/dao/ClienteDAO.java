package com.alura.jpa_hibernate.mercado.dao;
import com.alura.jpa_hibernate.mercado.entity.Cliente;
import java.util.List;
import javax.persistence.EntityManager;

public class ClienteDAO {
    private EntityManager entityManager;
    public ClienteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public void save(Cliente cliente){
        this.entityManager.persist(cliente);
    }
    public List<Cliente> findAll(){
		String jqpl= "SELECT c FROM Cliente AS c";
		return entityManager.createQuery(jqpl,Cliente.class).getResultList();
	}
}

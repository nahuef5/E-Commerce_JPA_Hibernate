package com.alura.jpa_hibernate.mercado.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
@Entity
public class Carrito implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private LocalDate fecha = LocalDate.now();
    private BigDecimal totalValor=new BigDecimal("0");
    @ManyToOne (fetch= FetchType.LAZY)
    private Cliente cliente;
    @OneToMany (mappedBy="carrito", cascade=CascadeType.ALL)//,fetch = FetchType.EAGER)
    List<Pedido> pedidos = new ArrayList<>();
    public Carrito() {
    }
    public Carrito(Cliente cliente) {
        this.cliente = cliente;
    }
    public void addItems(Pedido pedido){
        pedido.setCarrito(this);
        this.pedidos.add(pedido);
        this.totalValor=totalValor.add(pedido.getValorPorCantidad());
    }
    public int getId() {
        return id;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotalValor() {
        return totalValor;
    }

    public void setTotalValor(BigDecimal totalValor) {
        this.totalValor = totalValor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Pedido> getPedido() {
        return pedidos;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedidos = pedido;
    }
}
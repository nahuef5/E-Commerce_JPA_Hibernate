package com.alura.jpa_hibernate.mercado.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
@Entity
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private long cantidad;
    private BigDecimal precioUnidad;
    @ManyToOne(fetch=FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch=FetchType.LAZY)
    private Carrito carrito;

    public Pedido() {
    }

    public Pedido(long cantidad, Producto producto, Carrito carrito) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.carrito = carrito;
        this.precioUnidad=producto.getPrecio();
    }
    public BigDecimal getValorPorCantidad(){
        return this.precioUnidad.multiply(new BigDecimal(this.cantidad));
    }

    public int getId() {
        return id;
    }
    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}
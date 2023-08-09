package com.alura.jpa_hibernate.mercado.vo;
import java.time.LocalDate;
public class Relatorio_VentaVO {
    private String nombreProducto;
    private long cantidadProducto;
    private LocalDate fechaUltimaVenta;
    public Relatorio_VentaVO(){}
    public Relatorio_VentaVO(String nombreProducto, long cantidadProducto, LocalDate fechaUltimaVenta) {
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.fechaUltimaVenta = fechaUltimaVenta;
    }
    @Override
    public String toString() {
        return "Nombre de Producto: " 
                + nombreProducto 
                + ", cantidad: " 
                + cantidadProducto 
                + ", fecha de la ultima venta: " 
                + fechaUltimaVenta ;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public long getCantidadProducto() {
        return cantidadProducto;
    }
    public void setCantidadProducto(long cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
    public LocalDate getFechaUltimaVenta() {
        return fechaUltimaVenta;
    }
    public void setFechaUltimaVenta(LocalDate fechaUltimaVenta) {
        this.fechaUltimaVenta = fechaUltimaVenta;
    }
}
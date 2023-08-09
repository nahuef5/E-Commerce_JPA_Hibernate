package com.alura.jpa_hibernate.mercado.entity;
import com.alura.jpa_hibernate.mercado.models.InformacionPersonal;
import java.io.Serializable;
import javax.persistence.*;
@Entity
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Embedded
    private InformacionPersonal informacionPersonal;
    public Cliente() {
    }

    public Cliente(String nombre, String dni) {
        this.informacionPersonal=new InformacionPersonal(nombre,dni);
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return informacionPersonal.getNombre();
    }

    public void setNombre(String nombre) {
        this.informacionPersonal.setNombre(nombre);
    }

    public String getDni() {
        return informacionPersonal.getDni();
    }

    public void setDni(String dni) {
        this.informacionPersonal.setDni(dni);
    }
}
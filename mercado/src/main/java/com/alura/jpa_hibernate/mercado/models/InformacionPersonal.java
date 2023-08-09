package com.alura.jpa_hibernate.mercado.models;
import java.io.Serializable;
import javax.persistence.Embeddable;
@Embeddable
public class InformacionPersonal implements Serializable {
    private String nombre;
    private String dni;
    public InformacionPersonal() {
    }
    public InformacionPersonal(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    @Override
    public String toString() {
        return "InformacionPersonal{" + "nombre=" + nombre + ", dni=" + dni + '}';
    }
}
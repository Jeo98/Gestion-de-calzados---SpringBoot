package estructura.Clases;

import jakarta.persistence.Entity;

import java.util.Date;
@Entity
public class Empleado {

    private int legajo; //legajo es como un codigo de identificacion
    private String nombre;
    private Integer DNI;
    private String domicilio;
    private Integer telefono;
    private Date fechaIngreso;
    //podemos usar Date o sino String


    public Empleado(int legajo, String nombre, Integer DNI, String domicilio, Integer telefono, Date fechaIngreso) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.DNI = DNI;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.fechaIngreso = fechaIngreso;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDNI() {
        return DNI;
    }

    public void setDNI(Integer DNI) {
        this.DNI = DNI;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}

package estructura.Clases;

import jakarta.persistence.Entity;

@Entity
public class Sucursal {

    private String nombre;
    private String domicilio;
    private String ciudad;
    private Cliente clientes [];



    public Sucursal(String nombre, String domicilio, String ciudad) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

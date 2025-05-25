package estructura.Clases;

import jakarta.persistence.Entity;

@Entity
public class Calzado {

    private String codigo;
    private String descripcion;
    private String marca;
    private float precioCosto;
    private float precioVenta;
    private String color;
    private float talle;
    private int stock;

    public Calzado(String codigo, String descripcion, String marca, float precioCosto, float precioVenta, String color, float talle, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.color = color;
        this.talle = talle;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(float precioCosto) {
        this.precioCosto = precioCosto;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTalle() {
        return talle;
    }

    public void setTalle(float talle) {
        this.talle = talle;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

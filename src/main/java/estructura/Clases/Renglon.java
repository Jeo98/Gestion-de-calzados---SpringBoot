package estructura.Clases;

public class Renglon {

    private String calzado; //va el calzado que eligio el cliente para comprar
    private int cantidad;

    public Renglon(){

    }
    public Renglon(String renglon, int cantidad) {
        this.calzado = renglon;
        this.cantidad = cantidad;
    }

    public String getCalzado() {
        return calzado;
    }

    public void setCalzado(String calzado) {
        this.calzado = calzado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String detalleRenglon() {
        return "calzado='" + calzado + " | " +"cantidad=" + cantidad + "\n";
    }
}

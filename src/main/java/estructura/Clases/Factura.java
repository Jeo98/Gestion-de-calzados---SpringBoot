package estructura.Clases;
import estructura.Servicios.FacturaService;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;



@Entity //entidad para base de datos, crea una.. no me acuerdo el nombre, pero tipo excel... ;D
public class Factura {
    @Id



    private Integer idUnico; //debe generarse de forma aleatoria y automatica al ejecutar una venta
    private LocalDate fecha; //fecha de forma local

    private String nombreEmpleado;//!!!!!TEMPORALES PARA TEST
    private String Nombrecliente;//!!!!!TEMPORALES PARA TEST

    private Empleado empleado;
    private Cliente cliente;

    private List<Renglon> renglones; // lista de renglones que tendra la factura

    private FacturaService facturaService;

    public Factura(){

    }
    public Factura(Integer idUnico, LocalDate fecha, List<Renglon> renglones, String nombreEmpleado, String nombreCliente) { //constructor
        this.idUnico = idUnico;
        this.fecha = fecha;
        this.renglones = renglones;
        this.nombreEmpleado = nombreEmpleado;
        this.Nombrecliente =nombreCliente;
        //de empleado y cliente solo me interesa saber los nombres
    }

//funcion para crear el id aleatorio y unico
        public void setIdUnico() {
            Random random = new Random();
            Integer numeroAleatorio = random.nextInt();
            this.idUnico = numeroAleatorio;

        }

    public boolean tieneRenglones(Factura factura){

        return (factura.renglones.isEmpty()); //si la factura esta vacia de renglones, true. sino false
    }
    public void mostrarFactura(){

        System.out.println("id: " + getIdUnico());
        System.out.println(fecha);
        System.out.println("nombre empleado: "+ getNombreEmpleado());
        System.out.println("nombre cliente: " + getNombrecliente());
        System.out.println("--->calzados comprados<--- ");
        for (int i = 0; i < this.renglones.size(); i++) {
            System.out.println(getRenglones().get(i)); //Muestra el contenido de cada posicion de la lista tipo Renglon
        }
    }

//------------------------------------SETTERS Y GETTERS---------------------------------
    public Integer getIdUnico() {
        return idUnico;
    }

    public void setIdUnico(Integer idUnico) {
        this.idUnico = idUnico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombrecliente() {
        return Nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.Nombrecliente = nombrecliente;
    }

    public List<Renglon> getRenglones() {
        return renglones;
    }

    public void setRenglones(List<Renglon> renglones) {
        this.renglones = renglones;
    }
//------------------------------------SETTERS Y GETTERS---------------------------------





}

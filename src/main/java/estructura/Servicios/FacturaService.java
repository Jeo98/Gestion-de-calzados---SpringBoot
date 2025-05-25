package estructura.Servicios;

import estructura.Clases.*;
import estructura.DTO.FacturaDTO;
import estructura.Repositorio.CalzadoRepositorio;
import estructura.Repositorio.ClienteRepositorio;
import estructura.Repositorio.EmpleadoRepositorio;
import estructura.Repositorio.FacturaRepositorio;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service //capa de servicio, se encarga de la logica de negocio del CU
@Id
public class FacturaService {


    @Autowired//conexion y vinculo a clase Repositorio
    private FacturaRepositorio facturaRepositorio; //conexion y vinculo a clase Repositorio

    @Autowired
    private ClienteRepositorio clienteRepository;

    @Autowired
    private EmpleadoRepositorio empleadoRepository;

    @Autowired
    private CalzadoRepositorio calzadoRepository;



    //Cargar factura

        public Factura cargarFactura(FacturaDTO dto) {
            Cliente cliente = ClienteRepositorio.findById(dto.getClienteId()).orElseThrow(...);
            Empleado empleado = empleadoRepo.findById(dto.getEmpleadoId()).orElseThrow(...);

            List<Renglon> renglones = Renglon.getRenglones().stream()
                    .map(r -> {
                        Calzado calzado = calzadoRepo.findById(r.getCalzadoId()).orElseThrow(...);
                        return new Renglon(calzado, r.getCantidad());
                    }).toList();

            Factura factura = new Factura();
            factura.setCliente(cliente);
            factura.setEmpleado(empleado);
            factura.setRenglones(renglones);
            factura.setFecha(LocalDate.now());

            return facturaRepositorio.save(factura);


    }

    //Eliminar factura CU
    public void EliminarFactura(Integer idUnico) throws Exception {
        int decision = 0;
        Scanner lectura = new Scanner(System.in);
        try {
            Optional facturaBD = facturaRepositorio.findById(idUnico);
            if(facturaBD.isPresent()) {
                Factura facturaBDo = (Factura) facturaBD.get(); //casteo explicito a Factura
                //busca en bd la factura por id y se lo asigna a facturaBDo


                System.out.println("-------------FACTURA A ELIMINAR-------------");
                facturaBDo.mostrarFactura();
                try {
                    System.out.print("\n\n1---ELIMINAR FACTURA\n2---CANCELAR\ningresar: ");
                    decision = lectura.nextInt();
                    while(decision != 1 && decision != 0 ) {

                        System.out.println("ERROR... VALOR INGRESADO NO ES VALIDO. INGRESE NUEVAMENTE");
                        decision= lectura.nextInt();

                    }// luego de que ingreso de datos correcto...
                        if (decision == 1)
                            facturaRepositorio.delete(facturaBDo); //elimina factura de bs
                        else
                            System.out.println("eliminar factura CANCELADO...");

                } catch (Exception e) {
                    throw new Exception("Valor ingresado no valido");
                }
            } // fin if
            else System.out.println("factura no encontrada...");
        } catch (Exception e) {
            throw new Exception("Ocurrio un error(FacturaService)");
        }
    }
}

package estructura.Servicios;

import estructura.Clases.*;
import estructura.DTO.FacturaDTO;
import estructura.DTO.RenglonDTO;
import estructura.Repositorio.CalzadoRepositorio;
import estructura.Repositorio.ClienteRepositorio;
import estructura.Repositorio.EmpleadoRepositorio;
import estructura.Repositorio.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service //capa de servicio, se encarga de la logica de negocio del CU

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


            Cliente cliente = clienteRepository.findByDni(dto.getCliente().getDocumento())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

            Empleado empleado = empleadoRepository.findByDni(dto.getEmpleado().getDNI())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

            // 2. Crear los renglones de la factura
            List<Renglon> renglones = new ArrayList<>();
            for (RenglonDTO renglonDTO : dto.getRenglones()) {
                Calzado calzado = calzadoRepository.findById(renglonDTO.getCalzadoId())
                        .orElseThrow(() -> new RuntimeException("Calzado no encontrado"));

                Renglon renglon = new Renglon();
                renglon.setCantidad(renglonDTO.getCantidad());
                renglon.setCalzado(calzado.getMarca());
                renglones.add(renglon);
            }

            // 3. Crear y cargar la factura
            Factura factura = new Factura();
            factura.setFecha(LocalDate.now());
            factura.setNombrecliente(cliente.getNombre());
            factura.setNombreEmpleado(empleado.getNombre());
            factura.setRenglones(renglones);

            // 4. Guardar en la base de datos
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

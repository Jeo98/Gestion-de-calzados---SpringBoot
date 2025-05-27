package estructura.DTO;

import estructura.Clases.Cliente;
import estructura.Clases.Empleado;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.util.List;

public class FacturaDTO {

        @NotNull
        private String nombrecliente;

        @NotNull
        private String nombreEmpleado;

        @NotNull
        private List<RenglonDTO> renglones;

        private Cliente cliente; //consulta
        private Empleado empleado;//consulta

        public String getNombrecliente() {
                return cliente.getNombre();
        }

        public void setNombrecliente(String nombrecliente) {
                this.nombrecliente = nombrecliente;
        }


        public Empleado getEmpleado() {
                return empleado;
        }

        public Cliente getCliente() {
                return cliente;
        }

        public String getNombreEmpleado() {
                return empleado.getNombre();
        }

        public void setNombreEmpleado(String nombreEmpleado) {
                this.nombreEmpleado = nombreEmpleado;
        }

        public List<RenglonDTO> getRenglones() {
                return renglones;
        }

        public void setRenglones(List<RenglonDTO> renglones) {
                this.renglones = renglones;
        }
}



package estructura.DTO;

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



        public String getNombrecliente() {
                return nombrecliente;
        }

        public void setNombrecliente(String nombrecliente) {
                this.nombrecliente = nombrecliente;
        }

        public String getNombreEmpleado() {
                return nombreEmpleado;
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



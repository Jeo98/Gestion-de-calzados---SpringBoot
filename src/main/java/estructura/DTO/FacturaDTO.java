package estructura.DTO;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public class FacturaDTO {

        @NotNull
        private String nombrecliente;

        @NotNull
        private String nombreEmpleado;

        @NotNull
        private List<RenglonDTO> renglones;


}



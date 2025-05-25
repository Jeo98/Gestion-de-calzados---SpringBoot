package estructura.DTO;

import org.antlr.v4.runtime.misc.NotNull;

public class RenglonDTO {

    @NotNull
    private Long calzadoId;

    @NotNull
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getCalzadoId() {
        return calzadoId;
    }

    public void setCalzadoId(Long calzadoId) {
        this.calzadoId = calzadoId;
    }
}

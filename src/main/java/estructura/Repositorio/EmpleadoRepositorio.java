package estructura.Repositorio;

import estructura.Clases.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepositorio extends JpaRepository<Empleado,Long> {
}

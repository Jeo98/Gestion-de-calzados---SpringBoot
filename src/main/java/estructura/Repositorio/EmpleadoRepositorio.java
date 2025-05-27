package estructura.Repositorio;

import estructura.Clases.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepositorio extends JpaRepository<Empleado,Long> {


    Optional<Empleado> findByDni(int dni); //busca empleado por DNI

}

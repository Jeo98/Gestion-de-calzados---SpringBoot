package estructura.Repositorio;

import estructura.Clases.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalzadoRepositorio extends JpaRepository<Cliente,Long> {



}

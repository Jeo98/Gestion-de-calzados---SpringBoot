package estructura.Repositorio;

import estructura.Clases.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepositorio extends JpaRepository<Cliente,Long> {

    Optional<Cliente> findByDni(int dni); //busca cliente por DNI





}

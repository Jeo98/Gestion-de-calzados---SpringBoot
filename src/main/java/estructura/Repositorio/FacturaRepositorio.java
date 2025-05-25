package estructura.Repositorio;

import estructura.Clases.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository //interactua con al base de datos.
    public interface  FacturaRepositorio extends JpaRepository<Factura,Long> {

    Optional<Factura>findById(Integer idUnico);

    Factura save(Factura factura);
    //carga factura en base de datos
    void delete(Factura factura); //Elimina factura enviada por parametro en base de datos

    void deleteById(Long id);
    //Elimina factura en base de datos. busca en bd la factura por id, y la elimina.

    // Verifica EN BASE DE DATOS si una factura existe
    boolean existsById(Long idUnico);

    // Buscar todas las facturas por fecha EN BASE DE DATOS

    List<Factura> findAllByFecha(LocalDate fecha);

}

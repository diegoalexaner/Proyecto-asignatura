package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleSubasta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleSubastaRepo extends JpaRepository <DetalleSubasta, Integer> {

    //Consulta que me obtiene informacion de Detalle Subasta
    Page<DetalleSubasta> findAllBy (Pageable paginador);
}

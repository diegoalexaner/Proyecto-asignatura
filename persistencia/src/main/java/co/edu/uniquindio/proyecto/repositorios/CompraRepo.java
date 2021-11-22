package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    //Me permite traer la informacion de una compra por el id
    Optional<Compra> findById(int id);


    //@Query("select d.producto from Compra c join c.detalleCompras d where c.usuario.codigo= :codigo "
    //List<Producto> obtenerListaProductosComprados(String codigo);

}
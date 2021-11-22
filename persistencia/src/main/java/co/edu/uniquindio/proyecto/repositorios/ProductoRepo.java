package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {




    Page<Producto> findAll(Pageable paginador);

    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%') ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select p from Producto p where p.comentarios is empty")
    List<Producto> obtenerProductosSinComentarios();




   /*
    @Query("select p.vendedor.nombre from Producto p where p.codigo= :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select p from Producto p where p.comentarios is empty");
    List<Producto> obtenerProductosSinComentarios();
*/



}

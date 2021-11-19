package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Page<Producto> findAll(Pageable paginador);

    @Query("select p.vendedor.nombre from Producto p where p.codigo= :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select c from Comentario c where c.producto.codigo= :id ")
    List<Comentario> obtenerComentarios(Integer id);

    @Query("select p.nombre ,c from Producto p  left  join  p.comentarios c")
    List<Object[]> listarProductosYComentarios();

    @Query("select distinct c.usuario from Producto p    join  p.comentarios c where  p.codigo = :id")
    List<Usuario> listarUsuariosComentarios(Integer id);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido( p.nombre,p.descripcion,p.precio,p.ciudad.nombreCiudad)  from Producto p where :fechaActual < p.fecha_limite")
    List<ProductoValido> listatProductosValidos(LocalDateTime fechaActual);


}

package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;


    @Test
    @Sql("classpath:data.sql")
    public void obtenerNombreVendedorTest(){

        String nombre = productoRepo.obtenerNombreVendedor(2);
        Assertions.assertEquals("Maria", nombre);
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarProductosYComentariosTest(){

        List<Object[]> respuesta = productoRepo.listarProductosYComentarios();

        for (Object[] objeto: respuesta){
            System.out.println(objeto[0]+ "-----"   + objeto[1]);
        }

    }


    @Test
    @Sql("classpath:data.sql")
    public void listarUsuariosComentarioTest(){
        List<Usuario> usuarios = productoRepo.listarUsuariosComentarios(1);
        usuarios.forEach(System.out::println);
     }

    @Test
    @Sql("classpath:data.sql")
    public void listarPructosValidosTest(){
        List<ProductoValido> productos = productoRepo.listatProductosValidos(LocalDateTime.now());
        productos.forEach(System.out::println);
    }

}

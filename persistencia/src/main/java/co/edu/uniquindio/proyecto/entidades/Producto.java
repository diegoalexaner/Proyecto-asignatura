package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(nullable = false, length= 100)
    private String nombre;

    @Column(nullable = false)
    private String nombrePublicacion;

    @Lob
    @Column(nullable = false, length= 1000)
    private String descripcion;

    @Column( nullable=false)
    private Integer unidades;

    @Column(nullable=false)
    private LocalDateTime fecha_limite;

    @Column(nullable = false)
    private Float precio;


    @Column( nullable=false)
    private Float descuento;

    @ElementCollection
    @Column(nullable = false)
    private List<Categoria> categorias;

    @ElementCollection
    private List<String> imagenes;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detalleCompras;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Subasta> subastas;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Chat> chats;

    @ManyToMany(mappedBy = "productosFavoritos")
    @ToString.Exclude
    private List<Usuario> favoritos;


    public Producto(Integer codigo, String nombre, String nombrePublicacion, String descripcion, Integer unidades, LocalDateTime fecha_limite, Float precio, Float descuento, Usuario vendedor, Ciudad ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nombrePublicacion = nombrePublicacion;
        this.descripcion = descripcion;
        this.unidades = unidades;
        this.fecha_limite = fecha_limite;
        this.precio = precio;
        this.descuento = descuento;
        this.vendedor = vendedor;
        this.ciudad = ciudad;
    }
}
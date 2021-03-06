package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Chat implements Serializable {
    //Declaracion  de atributos  de la clase con su respectiva  parametrizacion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //usuario Comprador
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "chats")
    @ToString.Exclude
    private List<Mensaje> mensajes;


    //Constructor de la clase Chat
    @Builder
    public Chat(Integer codigo, Usuario usuario, Producto producto) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.producto = producto;
    }
}

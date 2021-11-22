package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import co.edu.uniquindio.proyecto.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@ViewScoped
public class ProductoBean implements Serializable {


    @Getter
    @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Value("${upload.url}")
    private String urlUploads;

    private ArrayList<String> imagenes;


    @PostConstruct
    public void inicializar() {
        this.producto = new Producto();
        this.imagenes = new ArrayList<>();
    }

    public void crerProducto(){
        try {
            if(!imagenes.isEmpty()) {
                Usuario usuario = usuarioServicio.obtenerUsuario("123");
                producto.setVendedor(usuario);
                producto.setImagenes(imagenes);
                productoServicio.publicarProducto(producto);

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "Producto creado");
                FacesContext.getCurrentInstance().addMessage(null,msg);

            }else{
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Alerta", "Es necesario subir al menos una imagen");
                FacesContext.getCurrentInstance().addMessage(null,msg);
            }

        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,fm);
        }

    }

    public void subirImagenes(FileUploadEvent event) {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!=null) {
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen){

        try {
            File archivo = new File(urlUploads + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }
         return null;
    }





}

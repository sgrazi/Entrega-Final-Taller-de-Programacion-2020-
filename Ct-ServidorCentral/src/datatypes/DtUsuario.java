package datatypes;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({DtArtista.class,DtEspectador.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuario {
		
	private String nickname;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private Date fechaDeNacimiento;
	private String contrasena;
	private String imagen;
	
	public DtUsuario(String nick, String nombre, String apellido, String email, Date fechaNac) {
		this.nickname = nick;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = email;
        this.fechaDeNacimiento = fechaNac;
    }


    public String toString() {
        return this.getCorreoElectronico();
    }
    
    public String getApellido() {
        return this.apellido;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }
    
    public Date getFechaDeNacimiento() {
        return this.fechaDeNacimiento;
    }
    
    public String getNickName() {
        return this.nickname;
    }
    
    public void setNickname(String value) {
        this.nickname = value;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String value) {
        this.nombre = value;
    }
    
    public void setApellido(String value) {
        this.apellido = value;
    }
    
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }
    
    public void setFechaDeNacimiento(Date value) {
        this.fechaDeNacimiento = value;
    }
            
    public String getContrasena() {
        return this.contrasena;
    }

    public String getImagen() {
        return this.imagen;
    }
    
    public void setImagen(String value) {
        this.imagen = value;
    }
    
    public void setContrasena(String value) {
        this.contrasena = value;
    }
    
	
}


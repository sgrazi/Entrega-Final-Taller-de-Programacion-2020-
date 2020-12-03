package datatypes;

import java.util.Date;

public class DtArtista extends DtUsuario {
	private String descripcion;
	private String biografia;
	private String url;
	private String[] espectaculos;
	
	public DtArtista(String nick, String nombre, String apellido, String correo, Date fechaNacimiento, String desc, String bio, String url, String[] esp) {
		super(nick, nombre, apellido, correo, fechaNacimiento);
		this.descripcion = desc;
        this.biografia = bio;
        this.url = url;
        this.espectaculos = esp;
    }
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getBiografia() {
		return this.biografia;
	}
	
	public String getUrl() {
		return this.url;
	}

	public String[] getEspectaculos() {
		return this.espectaculos;
	}

}

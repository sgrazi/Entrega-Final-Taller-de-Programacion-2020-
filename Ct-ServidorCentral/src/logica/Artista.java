package logica;

import java.util.Map;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import datatypes.DtArtista;

public class Artista extends Usuario {

	private String descripcion;
	private String biografia;
	private String sitioWeb;
	private Map<String, Espectaculo> espectaculos;

	public Artista(String nick, String nom, String ape, String email, Date fecNac, String des, String bio, String web, String pass,
			String img) {
		super(nick, nom, ape, email, fecNac, pass, img); // LLAMO AL CONSTRUCTOR DE LA CLASE BASE

		this.setDescripcion(des);
		this.setBiografia(bio);
		this.setSitioWeb(web);

		this.espectaculos = new HashMap<String, Espectaculo>();
	}

	public void clear() {
		super.clear();

		setDescripcion("");
		setBiografia("");
		setSitioWeb("");

		espectaculos.clear();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getBiografia() {
		return this.biografia;
	}

	public String getSitioWeb() {
		return this.sitioWeb;
	}

	public void setDescripcion(String value) {
		this.descripcion = value;
	}

	public void setBiografia(String value) {
		this.biografia = value;
	}

	public void setSitioWeb(String value) {
		this.sitioWeb = value;
	}

	public void agregarEspectaculo(Espectaculo esp) {

		this.addEspectaculo(esp);

	}

	public void addEspectaculo(Espectaculo espectaculo) {
		this.espectaculos.put(espectaculo.getNombre(), espectaculo);
	}

	public String[] obtenerEspectaculos() {

		Collection<Espectaculo> espectaculos = this.espectaculos.values();
		Object[] obj = espectaculos.toArray();
		String[] nombres = new String[obj.length];

		for (int i = 0; i < obj.length; i++) {
			nombres[i] = ((Espectaculo) obj[i]).getNombre();
		}

		return nombres;
	}

	public DtArtista getDt() {
		String[] espectaculos = obtenerEspectaculos();

		DtArtista data = new DtArtista(this.getNickName(), this.getNombre(), this.getApellido(),
				this.getCorreoElectronico(), this.getFechaDeNacimiento(), this.getDescripcion(), this.getBiografia(),
				this.getSitioWeb(), espectaculos);
		data.setContrasena(this.getContrasena());
		data.setImagen(this.getImagen());

		return data;
	}

	public Map<String, Espectaculo> getEspectaculos() {
		return this.espectaculos;
	}

}

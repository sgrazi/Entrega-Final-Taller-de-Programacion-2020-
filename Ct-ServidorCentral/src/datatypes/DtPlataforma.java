package datatypes;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPlataforma {
	private String nombre;
	private String url;
	private String descripcion;
	private Set<DtEspectaculo> espectaculos;
	
	

	public DtPlataforma(String nombre, String url, String desc, Set<DtEspectaculo> esps) {
		this.nombre = nombre;
		this.url = url;
		this.descripcion = desc;
		this.espectaculos = esps;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getUrl() {
		return url;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Set<DtEspectaculo> getEspectaculos() {
		return espectaculos;
	}

	
	

}

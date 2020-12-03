package datatypes;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPaquete {
	private String nombre;
	private String descripcion;
	private Date inicio;
	private Date fin;
	private float descuento;
	private float costo;
	private String[] espectaculos;
	private Set<String> categorias;
	private String imagen;

	public DtPaquete(String nombre, String desc, Date inicio, Date fin, float descuento, float costo) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.inicio = inicio;
		this.fin = fin;
		this.descuento = descuento;
		this.costo = costo;
		this.espectaculos = new String[0];
		this.imagen = "img/nodisponible.png";
	}

	public DtPaquete(String nombre, String desc, Date inicio, Date fin, float descuento, float costo,
			String[] espectaculos) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.inicio = inicio;
		this.fin = fin;
		this.descuento = descuento;
		this.costo = costo;
		this.espectaculos = espectaculos;
		this.imagen = "img/nodisponible.png";
	}

	public DtPaquete(String nombre, String desc, Date inicio, Date fin, float descuento, float costo,
			String[] espectaculos, Set<String> categorias) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.inicio = inicio;
		this.fin = fin;
		this.descuento = descuento;
		this.costo = costo;
		this.espectaculos = espectaculos;
		this.categorias = categorias;
		this.imagen = "img/nodisponible.png";
	}

	public DtPaquete(String nombre, String desc, Date inicio, Date fin, float descuento, float costo,
			String[] espectaculos, Set<String> categorias, String imagen) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.inicio = inicio;
		this.fin = fin;
		this.descuento = descuento;
		this.costo = costo;
		this.espectaculos = espectaculos;
		this.categorias = categorias;
		this.imagen = imagen;
	}

	public DtPaquete() {
		this.nombre = "";
		this.descripcion = "";
		this.inicio = new Date(); 
		this.fin = new Date(); 
		this.descuento = 0f;
		this.costo = 0f;
		this.espectaculos = new String[0]; 
		this.categorias = new HashSet<String>();
		this.imagen = ""; 
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public Date getFin() {
		return this.fin;
	}

	public float getDescuento() {
		return this.descuento;
	}

	public float getCosto() {
		return this.costo;
	}

	public String[] getEspectaculos() {
		return this.espectaculos;
	}

	public Set<String> getCategorias() {
		return this.categorias;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public void setEspectaculos(String[] espectaculos) {
		this.espectaculos = espectaculos;
	}

	public void setCategorias(Set<String> categorias) {
		this.categorias = categorias;
	}

}

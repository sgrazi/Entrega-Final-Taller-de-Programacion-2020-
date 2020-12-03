package datatypes;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtEspectaculo {

	private String nombre;
	private String descripcion;
	private int duracion;
	private String url;
	private int cantMinEspectadores;
	private int cantMaxEspectadores;
	private int costo;
	private Date fechaDeRegistro;
	private Set<String> categorias;
	private String imagen;
	private String artista;
	private String estado;
	private Set<DtFuncion> funciones;
	private Set<String> paquetes;
	private String plataforma;
	private int cantSorteosPorFuncion;
	private String sorteoDescripcion;
	private String videoUrl;
	private Map<String, Integer> valoraciones;
	private int promedioVal;
	private int cantfavoritos;
	
	public int getCantfavoritos() {
		return cantfavoritos;
	}

	public void setCantfavoritos(int cantfavoritos) {
		this.cantfavoritos = cantfavoritos;
	}

	public DtEspectaculo() {
		this.funciones = new HashSet<DtFuncion>();
		this.paquetes = new HashSet<String>();
		this.categorias = new HashSet<String>();
		this.valoraciones = new HashMap<String, Integer>();
		this.setPromedioVal(0);
	}

	public DtEspectaculo(String nom, String desc, String url, int costo, int capMax, int capMin, int minut, Date fecha,
			Set<String> categorias, String imagen) {
		this.nombre = nom;
		this.descripcion = desc;
		this.duracion = minut;
		this.url = url;
		this.cantMinEspectadores = capMin;
		this.cantMaxEspectadores = capMax;
		this.costo = costo;
		this.fechaDeRegistro = fecha;
		this.categorias = categorias;
		this.imagen = imagen;

		this.artista = "";
		this.estado = "";
		this.plataforma = "";
		this.funciones = new HashSet<DtFuncion>();
		this.paquetes = new HashSet<String>();
		this.categorias = new HashSet<String>();
		this.cantSorteosPorFuncion = 0;
		this.sorteoDescripcion = "";
		this.videoUrl = "";
		this.valoraciones = new HashMap<String, Integer>();
		this.setPromedioVal(0);
		this.cantfavoritos = 0;
	}

	public void setNombre(String value) {
		this.nombre = value;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescripcion(String value) {
		this.descripcion = value;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDuracion(int value) {
		this.duracion = value;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setURL(String value) {
		this.url = value;
	}

	public String getURL() {
		return url;
	}

	public void setCantMinEspectadores(int value) {
		this.cantMinEspectadores = value;
	}

	public int getCantMinEspectadores() {
		return cantMinEspectadores;
	}

	public void setCantMaxEspectadores(int value) {
		this.cantMaxEspectadores = value;
	}

	public int getCantMaxEspectadores() {
		return cantMaxEspectadores;
	}

	public void setCosto(int value) {
		this.costo = value;
	}

	public int getCosto() {
		return costo;
	}

	public void setFechaDeRegistro(Date value) {
		this.fechaDeRegistro = value;
	}

	public Date getFechaDeRegistro() {
		return fechaDeRegistro;
	}

	public void setCategorias(Set<String> value) {
		this.categorias = value;
	}

	public void setCategorias(List<String> value) {
		this.categorias = new HashSet<>(value);
	}

	public void setCategorias(String[] value) {
		// this.categorias = new HashSet<>(value);
		System.out.print("");
	}

	public Set<String> getCategorias() {
		return categorias;
	}

	public void setFunciones(Set<DtFuncion> funciones) {
		this.funciones = funciones;
	}

	public void setFunciones(List<DtFuncion> funciones) {
		this.funciones = new HashSet<>(funciones);
	}

	public Set<DtFuncion> getFunciones() {
		return this.funciones;
	}

	public void setPaquetes(Set<String> paquetes) {
		this.paquetes = paquetes;
	}

	public Set<String> getPaquetes() {
		return this.paquetes;
	}

	public void setPlataforma(String plat) {
		this.plataforma = plat;
	}

	public String getPlataforma() {
		return this.plataforma;
	}

	public void setImagen(String value) {
		this.imagen = value;
	}

	public String getImagen() {
		return imagen;
	}

	public void setArtista(String value) {
		this.artista = value;
	}

	public String getArtista() {
		return artista;
	}

	public void setEstado(String value) {
		this.estado = value;
	}

	public String getEstado() {
		return estado;
	}

	public int getCantSorteosPorFuncion() {
		return cantSorteosPorFuncion;
	}

	public void setCantSorteosPorFuncion(int cantSorteosPorFuncion) {
		this.cantSorteosPorFuncion = cantSorteosPorFuncion;
	}

	public String getSorteoDescripcion() {
		return sorteoDescripcion;
	}

	public void setSorteoDescripcion(String sorteoDescripcion) {
		this.sorteoDescripcion = sorteoDescripcion;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	
	public void setValoraciones(Map<String, Integer> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public Map<String, Integer> getValoraciones() {
		return valoraciones;
	}

	public void addValoracion(String nickEspectador, int puntaje) {
		if (puntaje == 0)
			this.valoraciones.remove(nickEspectador);
		else
			this.valoraciones.put(nickEspectador, puntaje);
	}

	public boolean valoradoPor(String nickEspectador) {
		return this.valoraciones.containsKey(nickEspectador);
	}

	public int getValoracion(String nickEspectador) {
		if (valoradoPor(nickEspectador))
			return this.valoraciones.get(nickEspectador);
		else
			return 0;
	}

	public int getPromedioVal() {
		return promedioVal;
	}

	public void setPromedioVal(int promedioVal) {
		this.promedioVal = promedioVal;
	}

}

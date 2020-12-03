package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DtEspectaculo;
import datatypes.DtFuncion;

public class Espectaculo {

	enum TipoEstado {
		INGRESADO, ACEPTADO, RECHAZADO, FINALIZADO
	}
	private Map<String, Categoria> categorias;
	private Set<String> paquetes;
	private Set<String> invitados;
	private Map<String, Funcion> funciones;
	private String organizador;
	private String plataforma;
	private String nombre;
	private String descripcion;
	private int duracion;
	private String url;
	private int cantMinEspectadores;
	private int cantMaxEspectadores;
	private int costo;
	private Date fechaDeRegistro;
	private Map<String, Paquete> paqueMap;
	private TipoEstado estado;
	private String imagen;
	private int cantSorteosPorFuncion;
	private String sorteoDescripcion;
	private String videoUrl;
	private Map<String,Integer> valoraciones;
	private Date fechaFinalizacion;
	
	private int cantidadFavoritos;

	public int getCantSorteosPorFuncion() {
		return cantSorteosPorFuncion;
	}

	public void setCantSorteosPorFuncion(int cantSorteosPorFuncion) {
		this.cantSorteosPorFuncion = cantSorteosPorFuncion;
	}

	public Espectaculo(String plat, String art, String nom, String desc, String url, int costo, int capMax, int capMin,
			int minut, Date fec, Map<String, Categoria> categorias, String imagen, int cantSorteosPorFuncion) {
		this.organizador = art;
		this.plataforma = plat;
		this.nombre = nom;
		this.descripcion = desc;
		this.duracion = minut;
		this.url = url;
		this.imagen = imagen;
		this.cantMinEspectadores = capMin;
		this.cantMaxEspectadores = capMax;
		this.costo = costo;
		this.fechaDeRegistro = fec;
		this.funciones = new HashMap<String, Funcion>();
		this.invitados = new HashSet<String>();
		this.paquetes = new HashSet<String>();
		this.paqueMap = new HashMap<String, Paquete>();
		this.categorias = categorias;
		this.estado = TipoEstado.INGRESADO;
		this.cantSorteosPorFuncion = cantSorteosPorFuncion;
		this.sorteoDescripcion = "";
		this.videoUrl = "";
		this.valoraciones = new HashMap<String, Integer>();
	}

	public Espectaculo() {
		this.funciones = new HashMap<String, Funcion>();
		this.invitados = new HashSet<String>();
		this.paquetes = new HashSet<String>();
		this.paqueMap = new HashMap<String, Paquete>();
		this.cantidadFavoritos = 0;
		this.valoraciones = new HashMap<String, Integer>();
	}

	public void agregarFuncion(Funcion fun) {
		funciones.put(fun.getNombre(), fun);
	}

	public Set<DtFuncion> getDtFunciones() {
		Set<DtFuncion> dtfunciones = new HashSet<DtFuncion>();
		for (Map.Entry<String, Funcion> pair : funciones.entrySet()) {
			dtfunciones.add(pair.getValue().getDt());
		}
		return dtfunciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getURL() {
		return url;
	}

	public void setURL(String uRL) {
		url = uRL;
	}

	public int getCantMinEspectadores() {
		return cantMinEspectadores;
	}

	public void setCantMinEspectadores(int cantMinEspectadores) {
		this.cantMinEspectadores = cantMinEspectadores;
	}

	public int getCantMaxEspectadores() {
		return cantMaxEspectadores;
	}

	public void setCantMaxEspectadores(int cantMaxEspectadores) {
		this.cantMaxEspectadores = cantMaxEspectadores;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Date getFechaDeRegistro() {
		return fechaDeRegistro;
	}

	public void setFechaDeRegistro(Date fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}

	public Funcion getFuncion(String nombreFuncion) {
		return funciones.get(nombreFuncion);
	}

	public DtEspectaculo getDt() {
		Set<String> col = new HashSet<String>();
		for (Map.Entry<String, Categoria> pair : this.categorias.entrySet()) {
			col.add(pair.getKey());
		}

		DtEspectaculo res = new DtEspectaculo(this.nombre, this.descripcion, this.url, this.costo,
				this.cantMaxEspectadores, this.cantMinEspectadores, this.duracion, this.fechaDeRegistro, col,
				this.imagen);
		res.setArtista(this.organizador);
		res.setEstado(this.getEstadoStr());
		res.setCantSorteosPorFuncion(this.getCantSorteosPorFuncion());
		res.setSorteoDescripcion(this.getSorteoDescripcion());
		res.setVideoUrl(this.getVideoUrl());

		res.setCategorias(this.categorias.keySet());
		
		Map<String, Integer> valsCopy = new HashMap<String, Integer>();
		valsCopy.putAll(this.valoraciones);
		res.setValoraciones(valsCopy);
		res.setPromedioVal(getValoracionesPromedio());

		Set<DtFuncion> funciones = this.getDtFunciones();
		res.setFunciones(funciones);

		res.setPaquetes(this.paqueMap.keySet());
		res.setPlataforma(this.plataforma);
		res.setCantfavoritos(cantidadFavoritos);
		return res;
	}

	public Set<String> getNombresFunciones() {
		Set<String> nombres = new HashSet<String>();
		for (Map.Entry<String, Funcion> pair : funciones.entrySet()) {
			nombres.add(pair.getKey());
		}
		return nombres;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Set<String> getInvitados() {
		return invitados;
	}

	public void setInvitados(Set<String> invitados) {
		this.invitados = invitados;
	}

	public Set<String> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(Set<String> paquetes) {
		this.paquetes = paquetes;
	}

	public void agregarPaquete(Paquete paq) {
		this.paqueMap.put(paq.getNombre(), paq);
	}

	public Map<String, Paquete> getPaqueMap() {
		return paqueMap;
	}

	public void setPaqueMap(Map<String, Paquete> paqueMap) {
		this.paqueMap = paqueMap;
	}

	public Map<String, Categoria> getCategorias() {
		return categorias;
	}

	public void cambiarEstado(boolean est) {
		if (est)
			this.estado = TipoEstado.ACEPTADO;
		else
			this.estado = TipoEstado.RECHAZADO;
	}

	public boolean estadoEsIngresado() {
		return estado == TipoEstado.INGRESADO;
	}

	public boolean estadoEsAceptado() {
		return estado == TipoEstado.ACEPTADO;
	}

	public boolean estaAceptado() {
		return estado == TipoEstado.ACEPTADO;
	}

	public boolean estaRechazado() {
		return estado == TipoEstado.RECHAZADO;
	}

	public String getEstadoStr() {
		String resStr = "NO DEFINIDO";

		if (this.estado == TipoEstado.INGRESADO) {
			resStr = "INGRESADO";
		}
		if (this.estado == TipoEstado.RECHAZADO) {
			resStr = "RECHAZADO";
		}
		if (this.estado == TipoEstado.ACEPTADO) {
			resStr = "ACEPTADO";
		}
		if (this.estado == TipoEstado.FINALIZADO) {
			resStr = "FINALIZADO";
		}

		return resStr;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public TipoEstado getEstado() {
		return this.estado;
	}
	
	public void incrementarFavoritos() {
		this.cantidadFavoritos++;
	}
	
	public void decrementarFavoritos() {
		this.cantidadFavoritos--;
	}
	
	public int getCantidadFavoritos() {
		return this.cantidadFavoritos;
	}
	
	public void finalizarEspectaculo(Date cuando) {
		this.estado = TipoEstado.FINALIZADO;
		
		this.fechaFinalizacion = cuando;
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

	public String toString() {
		String res = this.nombre + ". Categorias (" + this.categorias.size() + "): \n";
		
		Set<String> keys = this.categorias.keySet();
		for (String k: keys) {
			res = res + this.categorias.get(k).getNombre() + "\n";
		}
		res = res + "\n";
		
		return res;
		
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

	public int getValoracionesPromedio() {
		if (valoraciones.isEmpty())
			return 0;

		int suma = 0;
		for (int valor : this.valoraciones.values())
			suma += valor;

		return suma / valoraciones.size();
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	
}

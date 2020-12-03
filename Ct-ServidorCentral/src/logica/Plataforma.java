package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DtEspectaculo;
import datatypes.DtPlataforma;

public class Plataforma {
	private String nombre;
	private String url;
	private String descripcion;
	private Map<String, Espectaculo> espectaculos;

	public Plataforma(String nombre, String url, String desc) {
		this.nombre = nombre;
		this.url = url;
		this.descripcion = desc;
		this.espectaculos = new HashMap<String, Espectaculo>();
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Set<String> getNombresEsp() {
		Set<String> nombres = new HashSet<String>();
		for (Map.Entry<String, Espectaculo> pair : espectaculos.entrySet()) {
			nombres.add(pair.getKey());
		}
		return nombres;
	}

	public Set<Espectaculo> getEspectaculosSet() {
		return new HashSet<Espectaculo>(espectaculos.values());
	}

	public void agregarEspectaculo(Espectaculo esp) {
		this.espectaculos.put(esp.getNombre(), esp);
	}

	public void eliminarEspectaculo(String esp) {
		this.espectaculos.remove(esp);
	}

	public DtPlataforma getDt() {

		Set<DtEspectaculo> col = new HashSet<DtEspectaculo>();
		for (Map.Entry<String, Espectaculo> pair : espectaculos.entrySet()) {
			col.add(pair.getValue().getDt());
		}

		DtPlataforma data = new DtPlataforma(this.nombre, this.url, this.descripcion, col);

		return data;

	}
}

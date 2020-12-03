package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import datatypes.DtPaquete;

public class Paquete {
	private String nombre;
	private String descripcion;
	private Date inicio;
	private Date fin;
	private float descuento;
	private Map<String, Espectaculo> espectaculos;
	private String imagen;

	public Paquete(String nombre, String desc, Date inicio, Date fin, float descuento) {
		this.nombre = nombre;
		this.descripcion = desc;
		this.inicio = inicio;
		this.fin = fin;
		this.descuento = descuento;
		this.espectaculos = new HashMap<String, Espectaculo>();
		this.imagen = "img/nodisponible.png";
	}

	public DtPaquete getData() {
		String[] esp = new String[this.espectaculos.size()];
		int iter = 0;
		Set<String> cat = new HashSet<String>();

		for (Espectaculo e : this.espectaculos.values()) {
			esp[iter++] = e.getNombre();

			for (Categoria c : e.getCategorias().values()) {
				cat.add(c.getNombre());
			}
		}

		return new DtPaquete(this.getNombre(), this.getDescripcion(), (Date) this.getInicio().clone(),
				(Date) this.getFin().clone(), this.getDescuento(), this.getCosto(), esp, cat, this.imagen);
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
		float costo = 0;
		for (Map.Entry<String, Espectaculo> pair : espectaculos.entrySet())
			costo += pair.getValue().getCosto();

		return costo - (costo * this.descuento);
	}

	public boolean pertenece(Espectaculo esp) {
		return this.espectaculos.containsKey(esp.getNombre());
	}

	public void agregarEspectaculo(Espectaculo esp) {
		this.espectaculos.put(esp.getNombre(), esp);
	}

	public void setImagen(String img) {
		this.imagen = img;
	}

	public String getImagen() {
		return this.imagen;
	}

}

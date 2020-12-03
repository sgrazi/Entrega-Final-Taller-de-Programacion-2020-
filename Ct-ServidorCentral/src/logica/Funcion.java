package logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datatypes.DtFuncion;
import datatypes.DtHora;

public class Funcion {

	private String nombre;
	private Date fecha;
	private DtHora hora;
	private Map<String, Artista> artistas; // LA CLAVE ES UN NICKNAME
	private Date fechaAlta;
	private Date fechaFinalizacion;
	private List<Registro> registros;
	private int cantidadRegistros;
	private String imagen;
	private Espectaculo esp;
	private boolean sorteoRealizado;
	private List<Premio> premios;

	public Funcion(String nombreFuncion, Date fecha, DtHora hora, Set<String> artistasNick, Date fechaAlta, String imag,
			Espectaculo esp) {
		this.nombre = nombreFuncion;
		this.fecha = fecha;
		this.hora = hora;
		this.fechaAlta = fechaAlta;
		this.cantidadRegistros = 0;
		this.imagen = imag;
		this.esp = esp;
		int duracion = esp.getDuracion();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.MINUTE, duracion);
		
		this.fechaFinalizacion = cal.getTime();
						
				
		this.artistas = new HashMap<String, Artista>();
		this.registros = new ArrayList<Registro>();
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		for (String temp : artistasNick) {
			artistas.put(temp, (Artista) manUsu.obtenerUsuarioPorNickName(temp));
		}
		if(esp.getCantSorteosPorFuncion()==0) {
			this.sorteoRealizado = true;			
		}else {
			this.sorteoRealizado = false;
		}
		
		this.premios = new ArrayList<Premio>();
	}

	
	public List<Registro> getRegistros() {
		return registros;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getImagen() {
		return this.imagen;
	}

	public boolean getSorteoRealizado() {
		return sorteoRealizado;
	}
	
	public void realizarSorteo() {
		this.sorteoRealizado = true;
	}


	public DtFuncion getDt() {
		return new DtFuncion(this.nombre, this.fecha, this.hora, this.fechaAlta, this.cantidadRegistros, this.imagen,
				this.esp.getCosto(), this.esp.getNombre(), this.fechaFinalizacion, this.sorteoRealizado);
	}

	public int getCantidadRegistros() {
		return this.cantidadRegistros;
	}

	public void incrementarRegistro() {
		this.cantidadRegistros = this.cantidadRegistros + 1;
	}

	public DtHora getHora() {
		return this.hora;
	}

	public void setHora(DtHora hora) {
		this.hora = hora;
	}

	public void setImagen(String imag) {
		this.imagen = imag;
	}

	public List<String> getArtistas() {
		List<String> lista = new ArrayList<String>();
		for (Map.Entry<String, Artista> entry : artistas.entrySet()) {
			ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
			Usuario art;
			art = manUsu.obtenerUsuarioPorNickName(entry.getKey());
			if (art == null) {
				art = manUsu.obtenerUsuario(entry.getKey());
			}
			lista.add(art.getNombre() + " " + art.getApellido());
		}
		return lista;
	}

	public void agregarArtista(String nickArtista) {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		artistas.put(nickArtista, (Artista) manUsu.obtenerUsuarioPorNickName(nickArtista));
	}

	public String getNombreEspectaculo() {
		return this.esp.getNombre();
	}

	public List<Premio> getPremios() {
		return premios;
	}
	
	public void agregarPremio(Premio pre) {
		this.premios.add(pre);
	}

	public void setPremios(List<Premio> premios) {
		this.premios = premios;
	}
	
	public void agregarRegistro(Registro reg) {
		registros.add(reg);
	}
	
}

package logica;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Una funcion es unica ya que queda determinada por su fecha por lo tanto el map de registros del espectador
 * contendra como clave el nombre de la funcion y como value el registro de esa funcion
 * @author rdelavega
 *
 */

import datatypes.DtEspectador;
import datatypes.DtPremio;

public class Espectador extends Usuario {

	private Set<Compra> compras;
	private Map<String, Registro> registros;
	private Map<String, Premio> premios;
	private Set<Espectaculo> favoritos;

	public Espectador(String nick, String nom, String ape, String email, Date fecNac, String pass, String img) {
		super(nick, nom, ape, email, fecNac, pass, img); // LLAMO AL CONSTRUCTOR DE LA CLASE BASE
		compras = new HashSet<Compra>();
		registros = new HashMap<String, Registro>();
		premios = new HashMap<String, Premio>();
		favoritos = new HashSet<Espectaculo>();
	}

	public void clear() {
		super.clear();

		registros.clear();
	}

	// ESTA FUNCION NO LISTA LOS REGISTROS QUE TENGAN COSTO 0!
	public Set<String> getRegistrosNoCanjeados() {
		Set<String> dtregistros = new HashSet<String>();

		for (Map.Entry<String, Registro> pair : registros.entrySet()) {
			Registro reg = pair.getValue();

			if (!reg.fueCanjeado() && (reg.getCosto() != 0)) {
				dtregistros.add(pair.getKey());
			}

		}

		return dtregistros;
	}

	public boolean estaRegistrado(String nombreFuncion) {
		return registros.get(nombreFuncion) != null;
	}

	public Registro getRegistro(String nombreFuncion) {
		return this.registros.get(nombreFuncion);
	}

	public void addRegistro(Registro reg) {
		this.registros.put(reg.getFuncion().getNombre(), reg);
	}

	public Funcion[] obtenerFunciones() {
		if (this.registros.isEmpty()) {
			return null;
		} else {

			Collection<Registro> registros = this.registros.values();

			Object[] obj = registros.toArray();
			Funcion[] funciones = new Funcion[obj.length];

			for (int i = 0; i < obj.length; i++) {
				Funcion fun = ((Registro) obj[i]).getFuncion();

				funciones[i] = fun;
			}

			return funciones;

		}
	}

	public String[] obtenerNombresDeFunciones() {
		if (this.registros.isEmpty()) {
			// return null;
			return new String[0];
		} else {
			Funcion[] funciones = this.obtenerFunciones();
			String[] resNombres = new String[funciones.length];

			for (int i = 0; i < funciones.length; i++) {
				resNombres[i] = funciones[i].getNombre();
			}

			return resNombres;
		}

	}
	
	public Map<String, Registro> getRegistros() {
		return this.registros;
	}

	public DtEspectador getDt() {
		String[] funNombres = obtenerNombresDeFunciones();
		Set<DtPremio> premios = new HashSet<DtPremio>();
		for (Map.Entry<String, Premio> pair : this.premios.entrySet()) {
			premios.add(pair.getValue().getDt());
		}

		DtEspectador data = new DtEspectador(this.getNickName(), this.getNombre(), this.getApellido(),
				this.getCorreoElectronico(), this.getFechaDeNacimiento(), funNombres, premios);
		data.setContrasena(this.getContrasena());
		data.setImagen(this.getImagen());

		return data;
	}

	public void comprarPaquete(Compra com) {
		compras.add(com);
	}

	public Set<Compra> getCompras() {
		return this.compras;
	}

	public void agregarPremio(Premio pre) {
		premios.put(pre.getNombreEspectaculo(), pre);
	}

	public Map<String, Premio> getPremios() {
		return premios;
	}
	
	public Set<Espectaculo> getFavoritos() {
		return this.favoritos;
	}
	
	public void setFavorito(Espectaculo esp) {
		this.favoritos.add(esp);
	}
	
	public boolean esFavorito(Espectaculo esp) {
		return this.favoritos.contains(esp);
	}
	
	public void borrarDeFavoritos(Espectaculo esp) {
		this.favoritos.remove(esp);
	}
	
}

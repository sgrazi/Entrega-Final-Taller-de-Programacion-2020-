package datatypes;

import java.util.Date;
import java.util.Set;

public class DtEspectador extends DtUsuario {

	private String[] funciones;
	private Set<DtPremio> premios;

	public DtEspectador(String nick, String nombre, String apellido, String email, Date fechaNac, String[] funciones, Set<DtPremio> premios) {
		super(nick, nombre, apellido, email, fechaNac); // LLAMO AL CONSTRUCTOR DE LA CLASE BASE

		this.funciones = funciones;
		this.premios = premios;
	}

	public String[] getFunciones() {
		return this.funciones;
	}
	
	public Set<DtPremio> getPremios(){
		return this.premios;
	}

}

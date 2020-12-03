package datatypes;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import logica.Artista;
import logica.Espectaculo;
import logica.Espectador;
import logica.Funcion;
import logica.Paquete;
import logica.Premio;
import logica.Usuario;

@XmlAccessorType(XmlAccessType.FIELD)
public class ColDtsPerfil {

	private List<DtUsuario> dtSeguidores = new ArrayList<DtUsuario>();
	private List<DtUsuario> dtSeguidos = new ArrayList<DtUsuario>();
	private List<DtEspectaculo> dtEspectaculos = new ArrayList<DtEspectaculo>();
	private List<DtFuncion> dtFunciones = new ArrayList<DtFuncion>();
	private List<DtPaquete> dtPaquetes = new ArrayList<DtPaquete>();
	private List<DtEspectaculo> favoritos = new ArrayList<DtEspectaculo>();
	private List<DtPremio> premios = new ArrayList<DtPremio>();
	
	public ColDtsPerfil(Map<String, Usuario> seguidores, Map<String, Usuario> seguidos, Map<String, Espectaculo> espectaculos, Funcion[] funciones, Set<Paquete> paquetes, Set<Espectaculo> favs, Map<String,Premio> premiosAsignados) {
		
		//seteo el array de seguidores
		for(Map.Entry<String, Usuario> segres: seguidores.entrySet()) {
			if (segres.getValue() instanceof Artista) {
				dtSeguidores.add(((Artista) segres.getValue()).getDt());
			} else {
				dtSeguidores.add(((Espectador) segres.getValue()).getDt());
			}
		}
		
		//seteo el array de seguidos
		for(Map.Entry<String, Usuario> segs: seguidos.entrySet()) {
			if (segs.getValue() instanceof Artista) {
				dtSeguidos.add(((Artista) segs.getValue()).getDt());
			} else {
				dtSeguidos.add(((Espectador) segs.getValue()).getDt());
			}
		}
		
		//seteo el array de espectaculos
		
		if (espectaculos == null) dtEspectaculos = null; else {
		for(Map.Entry<String, Espectaculo> esp: espectaculos.entrySet()) {
				dtEspectaculos.add(esp.getValue().getDt());	
			}
		}
		
		if (premiosAsignados == null) premios = null; else {
			for(Map.Entry<String, Premio> premio: premiosAsignados.entrySet()) {
					premios.add(premio.getValue().getDt());	
				}
			}
		
		if (funciones == null) dtFunciones = null; else {
		//seteo las funciones
		for(int i= 0; i < funciones.length; i++) {
			dtFunciones.add(funciones[i].getDt());
			}
		}
		
		if (paquetes == null) dtPaquetes = null; else {
		//seteo los paquetes
		for(Paquete paq: paquetes) {
			dtPaquetes.add(paq.getData());
			}
		}
		
		if (favs == null) favoritos = null; else {
			//seteo los favoritos
			for(Espectaculo esp: favs) {
				favoritos.add(esp.getDt());
				}
			}
	}
	
	public List<DtEspectaculo> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<DtEspectaculo> favoritos) {
		this.favoritos = favoritos;
	}
	public List<DtPremio> getPremio() {
		return premios;
	}

	public void setPrmeios(ArrayList<DtPremio> premios) {
		this.premios = premios;
	}

	public List<DtUsuario> getDtSeguidores() {
		return dtSeguidores;
	}
	public void setDtSeguidores(List<DtUsuario> dtSeguidores) {
		this.dtSeguidores = dtSeguidores;
	}
	public List<DtUsuario> getDtSeguidos() {
		return dtSeguidos;
	}
	public void setDtSeguidos(List<DtUsuario> dtSeguidos) {
		this.dtSeguidos = dtSeguidos;
	}
	public List<DtEspectaculo> getDtEspectaculos() {
		return dtEspectaculos;
	}
	public void setDtEspectaculos(List<DtEspectaculo> dtEspectaculos) {
		this.dtEspectaculos = dtEspectaculos;
	}
	public List<DtFuncion> getDtFunciones() {
		return dtFunciones;
	}
	public void setDtFunciones(List<DtFuncion> dtFunciones) {
		this.dtFunciones = dtFunciones;
	}
	public List<DtPaquete> getDtPaquetes() {
		return dtPaquetes;
	}
	public void setDtPaquetes(List<DtPaquete> dtpaquetes) {
		this.dtPaquetes = dtpaquetes;
	}
}

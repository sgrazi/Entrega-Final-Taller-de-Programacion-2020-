package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import excepciones.EspectaculoNoExistenteException;

public class ManejadorEspectaculo {
	private Map<String, Espectaculo> espectaculos;
	private Map<String, Funcion> funciones;
	private Set<Espectaculo> papelera;

	private static ManejadorEspectaculo instancia = null;

	public ManejadorEspectaculo() {
		espectaculos = new HashMap<String, Espectaculo>();
		funciones = new HashMap<String, Funcion>();
		papelera = new HashSet<Espectaculo>();
	}

	public static ManejadorEspectaculo getInstance() {
		if (instancia == null) {
			instancia = new ManejadorEspectaculo();
		}
		return instancia;
	}

	public void addEspectaculo(Espectaculo esp) {
		espectaculos.put(esp.getNombre(), esp);
	}

	public Espectaculo getEspectaculo(String nombre) {
		return espectaculos.get(nombre);
	}

	public DtEspectaculo getDtEspectaculo(String nombre) {
		Espectaculo espec = espectaculos.get(nombre);
		return espec.getDt();
	}
	
	public DtFuncion obtenerDatosFuncion(String nombre) throws EspectaculoNoExistenteException {
		Funcion fun = funciones.get(nombre);
		
    	if (fun == null) {
    		throw new EspectaculoNoExistenteException("No se encontro la funcion");
    	}
    	
    	DtFuncion dt = fun.getDt();
    	
		return dt;
	}

	public List<String> obtenerArtistasFuncion(String nombre) {
		return funciones.get(nombre).getArtistas();
	}

	public boolean existeFuncion(String nombreFuncion) {
		return funciones.containsKey(nombreFuncion);
	}

	public void agregarFuncion(Funcion fun) {
		funciones.put(fun.getNombre(), fun);
	}
	
	public Funcion getFuncion(String fun) {
		return funciones.get(fun);
	}

	public Set<String> getEspectaculosIngresados() {
		Set<String> set = new HashSet<String>();
		for (Map.Entry<String, Espectaculo> entry : espectaculos.entrySet()) {
			if (entry.getValue().estadoEsIngresado())
				set.add(entry.getKey());
		}

		return set;
	}

	public Set<Espectaculo> getEspectaculosAceptados() {

		Set<Espectaculo> set = new HashSet<>();
		for (Map.Entry<String, Espectaculo> entry : espectaculos.entrySet()) {

			if (entry.getValue().estaAceptado()) {
				set.add(entry.getValue());
			}

		}

		return set;
	}

	public Set<String> getNombresEspectaculosAceptados() {
		Set<String> set = new HashSet<String>();
		for (Map.Entry<String, Espectaculo> entry : espectaculos.entrySet()) {
			if (entry.getValue().estadoEsAceptado())
				set.add(entry.getKey());
		}
		return set;
	}

	public void moverAPapelera(String nom) {

		Espectaculo esp = espectaculos.get(nom);
		espectaculos.remove(nom);
		papelera.add(esp);

	}
	
	public Set<DtFuncion> obtenerFunciones(){
		Set<DtFuncion> resultado = new HashSet<DtFuncion>();
		
		for (Map.Entry<String, Funcion> entry : funciones.entrySet()) {
		    resultado.add(entry.getValue().getDt());
		}
		
		return resultado;
	}
}

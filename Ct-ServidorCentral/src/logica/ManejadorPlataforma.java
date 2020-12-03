package logica;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.PlataformaNoExisteException;

public class ManejadorPlataforma {

	private static ManejadorPlataforma instancia = null;
	private Map<String, Plataforma> colPlataformas;


	public ManejadorPlataforma() {
		colPlataformas = new HashMap<String, Plataforma>();
	}

	public static ManejadorPlataforma getInstance() {
		if (instancia == null) {
			instancia = new ManejadorPlataforma();
		}
		return instancia;
	}

	
	public Set<String> getNombresPlataformas() {
		Set<String> nombres = new HashSet<String>();
		for (Map.Entry<String, Plataforma> pair : colPlataformas.entrySet()) {
		    nombres.add(pair.getKey());
		}
		return nombres;
	}

	public Plataforma getPlataforma(String nombre) {
		return (Plataforma) colPlataformas.get(nombre);
	}

	public void addPlataforma(Plataforma pla) {
		String nom = pla.getNombre();
		colPlataformas.put(nom, pla);
	}

	public Plataforma[] getPlataformas() {
		if (colPlataformas.isEmpty())
			return null;
		else {
			Collection<Plataforma> plats = colPlataformas.values();
			Object[] obj = plats.toArray();
			Plataforma[] plataformas = new Plataforma[obj.length];
			for (int i = 0; i < obj.length; i++) {
				plataformas[i] = (Plataforma) obj[i];
			}

			return plataformas;
		}
	}
	
	public Set<String> obtenerEspectaculosDePlataforma(String plat) throws PlataformaNoExisteException{
		
		Set<String> colEspectaculos = new HashSet<String>();
		
		Plataforma pla = colPlataformas.get(plat);
		
		if (pla == null) {
			throw new PlataformaNoExisteException("No existe la plataforma " + plat);
		}
		
		colEspectaculos = pla.getNombresEsp();
		
		return colEspectaculos;
	}
	
	public void agregarEspectaculoPlataforma(String plat, Espectaculo esp) throws PlataformaNoExisteException {
		Plataforma pla = (Plataforma) colPlataformas.get(plat);
		
		if (pla == null) {
			throw new excepciones.PlataformaNoExisteException("No existe la plataforma " + plat);
		}
		
		pla.agregarEspectaculo(esp);
		
	}
	
	public void destruirEspectaculo(String plat, String esp) {
		Plataforma pla = colPlataformas.get(plat);
		pla.eliminarEspectaculo(esp);
		
	}
	
}

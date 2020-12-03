package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DtPaquete;

public class ManejadorPaquete {
	private static ManejadorPaquete instancia;
	private Map<String, Paquete> colPaquetes;

	public ManejadorPaquete() {
		colPaquetes = new HashMap<String, Paquete>();
	}

	public static ManejadorPaquete getInstance() {
		if (instancia == null) {
			instancia = new ManejadorPaquete();
		}
		return instancia;
	}

	public Set<String> getPaquetes() {
		Set<String> res = new HashSet<String>();
		for (Map.Entry<String, Paquete> pair : colPaquetes.entrySet())
			res.add(pair.getKey());

		return res;
	}

	public Paquete getPaquete(String nombre) {
		return (Paquete) colPaquetes.get(nombre);
	}

	public void addPaquete(Paquete paq) {
			
		colPaquetes.put(paq.getNombre(), paq);
	}
	
	public Set<DtPaquete> getDataPaquetes() {
		Set<DtPaquete> res = new HashSet<DtPaquete>();
		for (Paquete paquete : colPaquetes.values())
			res.add(paquete.getData());

		return res;
	}
}

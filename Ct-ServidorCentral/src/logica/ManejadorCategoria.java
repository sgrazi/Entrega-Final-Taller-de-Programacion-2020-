package logica;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ManejadorCategoria {
	
	private static ManejadorCategoria instancia;
	private Map<String, Categoria> colCategorias;

	public ManejadorCategoria() {
		colCategorias = new HashMap<String, Categoria>();
	}

	public static ManejadorCategoria getInstance() {
		if (instancia == null) {
			instancia = new ManejadorCategoria();
		}
		return instancia;
	}
	
	public Set<String> getCategorias() {
		Set<String> res = new HashSet<String>();
		for (Entry<String, Categoria> pair : colCategorias.entrySet())
			res.add(pair.getKey());

		return res;
	}
	
	public Categoria getCategoria(String nombre) {
		return (Categoria) colCategorias.get(nombre);
	}
	
	public void addCategoria(Categoria cat) {
		
		colCategorias.put(cat.getNombre(), cat);
	}


}

package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class subMenus {

	private Map<String, String> items;
	
	public subMenus() {
		this.items = new HashMap<>();
	}
	
	public void inicializar() {
		this.items.clear();
	}
	
	public void add(String nombre, String link) {
		this.items.put(nombre, link);
	}
	
	public void delete(String nombre) {
		this.items.remove(nombre);
	}
	
	public List<String> getItemsNombres() {
		List<String> elementos = this.items.keySet().stream().collect(Collectors.toList());
		
		return elementos;
	}
		
	public Map<String, String> getItems() {
		return this.items;
	}

	
}

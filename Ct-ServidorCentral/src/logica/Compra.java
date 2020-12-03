package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Compra {
	private Paquete paquete;
	private Map<String, Registro> generados;
	private Date fechaCompra;

	public Compra(Paquete paq) {
		this.paquete = paq;
		this.generados = new HashMap<String, Registro>();
		this.fechaCompra = new Date();
	}

	public Paquete getPaquete() {
		return this.paquete;
	}
	
	public Date getFechaCompra() {
		return this.fechaCompra;
	}
	
	public Map<String, Registro> getGenerados() {
		return this.generados;
	}
	
}

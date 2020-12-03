package datatypes;

import java.util.Date;

public class DtRegistro {
	
	private String nombreFuncion;
	private Date fecha;
	private float costo;
	private boolean fueCanjeado;
	
	public DtRegistro(String nombreFuncion, Date fecha, float costo, boolean fueCanjeado) {
		this.nombreFuncion = nombreFuncion;
		this.fecha = fecha;
		this.costo = costo;
		this.fueCanjeado = fueCanjeado;
	}
	
	private String getNombreFuncion() {
		return this.nombreFuncion;
	}
	
	private Date getFecha() {
		return this.fecha;
	}
	
	private float getCosto() {
		return this.costo;
	}
	
	private boolean getFueCanjeado() {
		return this.fueCanjeado;
	}
}

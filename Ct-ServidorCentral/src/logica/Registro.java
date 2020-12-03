package logica;


import java.util.Date;

import datatypes.DtRegistro;

public class Registro {

	private Funcion funcion;
	private Date fecha;
	private Float costo;
	private Registro registroHijo;
	private Espectador esp;

	
	public Registro(Funcion fun, Espectador esp, Date fecRegistro, Float cos, Registro rHijo) {
		this.funcion = fun;
		this.fecha = fecRegistro;
		this.costo = cos;
		this.registroHijo = rHijo;
		this.esp = esp;
	}
	
	public void setFecha(Date fec) {
		this.fecha = fec;
	}
	
	public void setCosto(Float cos) {
		this.costo = cos;
	}
	
	public void setRegistroHijo(Registro reg) {
		this.registroHijo = reg;
	}
	
	public Date getFecha() {
		return this.fecha;
	}
	
	public Float getCosto() {
		return this.costo;
	}
	
	public Registro getRegistroHijo() {
		return this.registroHijo;
	}
	
	public boolean fueCanjeado() {
		return this.registroHijo != null;
	}
	
	public DtRegistro getDt() {
		return new DtRegistro(this.funcion.getNombre(), this.fecha, this.costo, this.fueCanjeado());
	}
	
	public Funcion getFuncion() {
		return this.funcion;
	}

	public Espectador getEsp() {
		return esp;
	}

	public void setEsp(Espectador esp) {
		this.esp = esp;
	}
	

}
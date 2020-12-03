package logica;

import java.util.Date;

import datatypes.DtPremio;

public class Premio {
	private Date fechaSorteo;
	private Espectador esp;
	private Funcion fun;
	private String descripcion;
	
	public Premio(Date fecha, Espectador esp, Funcion fun, String descripcion){
		this.fechaSorteo = fecha;
		this.esp = esp;
		this.fun = fun;
		this.setDescripcion(descripcion);
	}
	
	public Premio(Date fecha){
		this.fechaSorteo = fecha;
	}
	
	public Date getFechaSorteo() {
		return fechaSorteo;
	}
	
	public String getNombreEspectaculo() {
		return fun.getNombreEspectaculo();
	}
	
	public DtPremio getDt() {
		DtPremio res = new DtPremio(this.fechaSorteo, this.getNombreEspectaculo(), this.fun.getNombre(), this.descripcion);
		
		return res;
	}

	public Espectador getEsp() {
		return esp;
	}

	public void setEsp(Espectador esp) {
		this.esp = esp;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
}

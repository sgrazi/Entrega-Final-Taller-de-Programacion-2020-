package datatypes;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtFuncion {

	private String nombre;
	private Date fecha;
	private DtHora hora;
	private Date fechaDeRegistro;
	private int cantidadRegistros;
	private String imagen;
	private int costo;
	private String nombreEsp;
	private Date fechaFinalizacion;
	private boolean sorteada;
	
	public DtFuncion() {
		//DEFAULT PARA WS
	}
	
	public DtFuncion(String nombre, Date fecha, DtHora hora, Date fechaDeRegistro, int cantidadRegistros, String imag, int costo, String nombreEsp, Date fechaFinalizacion, boolean sorteada) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.hora = hora;
		this.fechaDeRegistro = fechaDeRegistro;
		this.cantidadRegistros = cantidadRegistros;
		this.imagen = imag;
		this.costo = costo;
		this.nombreEsp = nombreEsp;
		this.fechaFinalizacion = fechaFinalizacion;
		this.sorteada = sorteada;
	}
	
	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	public boolean isSorteada() {
		return sorteada;
	}
	public void setSorteada(boolean sorteada) {
		this.sorteada = sorteada;
	}
	public String getNombreEspectaculo() {
		return this.nombreEsp;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public Date getFecha() {
		return this.fecha;
	}
	
	public Date getFechaDeRegistro() {
		return this.fechaDeRegistro;
	}
	public int getCantidadRegistros() {
		return this.cantidadRegistros;
	}
	
	public DtHora getHora() {
		return this.hora;
	}
	
	public void setHora(DtHora hora) {
		this.hora = hora;
	}
	
	public String getImagen() {
		return this.imagen;
	}
	
	public int getCosto() {
		return this.costo;
	}

}

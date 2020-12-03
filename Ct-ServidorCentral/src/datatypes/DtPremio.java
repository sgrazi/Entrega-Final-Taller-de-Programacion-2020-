package datatypes;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtPremio {
	private Date fechaSorteo;
	private String esp;
	private String fun;
	private String descripcion;
	
	public DtPremio(Date fechaSorte, String esp, String fun, String descripcion) {
		this.fechaSorteo = fechaSorte;
		this.esp = esp;
		this.fun = fun;
		this.descripcion = descripcion;
	}
	
	public String getFun() {
		return fun;
	}
	
	public Date getFechaSorteo() {
		return fechaSorteo;
	}
	
	public String getEsp() {
		return esp;
	}

	public String getDescripcion() {
		return descripcion;
	}

}

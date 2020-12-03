package datatypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtHora {
	
	private int horas;
	private int minutos;
	
	public DtHora() {
		this.horas = 00;
		this.minutos = 00;
	}
	
	public DtHora(int horas, int mins) {
		this.horas = horas;
		this.minutos = mins;
	}
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public void setMinutos(int mins) {
		this.minutos = mins;
	}
	
	public int getHoras() {
		return this.horas;
	}
	
	public int getMinutos() {
		return this.minutos;
	}
	
	public void setValores(int horas, int mins) {
		this.horas = horas;
		this.minutos = mins;
	}

}

package datatypes;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtColPlataformas {
	
	private Set<String> plataformas;
	
	public DtColPlataformas(){
		this.plataformas = new HashSet<>();
	}

	public Set<String> getPlataformas() {
		return this.plataformas;
	}

	public void setPlataformas(Set<String> col) {
		this.plataformas = col;
	}
	
	
}

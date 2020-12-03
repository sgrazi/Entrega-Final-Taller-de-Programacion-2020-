package datatypes;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtColCategorias {
	private Set<String> categorias;
	
	public DtColCategorias(){
		this.categorias = new HashSet<>();
	}

	public Set<String> getCategorias() {
		return this.categorias;
	}

	public void setCategorias(Set<String> col) {
		this.categorias = col;
	}
	
}

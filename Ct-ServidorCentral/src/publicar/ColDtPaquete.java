package publicar;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtPaquete;

@XmlAccessorType(XmlAccessType.FIELD)
public class ColDtPaquete {
	private Set<DtPaquete> paquetes;

	public ColDtPaquete(Set<DtPaquete> paquetes) {
		this.paquetes = paquetes;
	}

	public Set<DtPaquete> getPaquetes() {
		return this.paquetes;
	}

}

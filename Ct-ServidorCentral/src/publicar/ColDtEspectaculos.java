package publicar;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtEspectaculo;


@XmlAccessorType(XmlAccessType.FIELD)
public class ColDtEspectaculos {
	
		private Set<DtEspectaculo> espectaculos;

		public ColDtEspectaculos(Set<DtEspectaculo> esp) {
			this.espectaculos = esp;
		}

		public Set<DtEspectaculo> getEspectaculos() {
			return this.espectaculos;
		}

	
}

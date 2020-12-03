package publicar;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtFuncion;


@XmlAccessorType(XmlAccessType.FIELD)
public class ColDtFuncion {
	
		private Set<DtFuncion> funs;

		public ColDtFuncion(Set<DtFuncion> esp) {
			this.funs = esp;
		}

		public Set<DtFuncion> getFuncioness() {
			return this.funs;
		}

	
}
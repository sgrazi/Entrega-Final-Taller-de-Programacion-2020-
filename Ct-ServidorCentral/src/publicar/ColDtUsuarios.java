package publicar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import datatypes.DtUsuario;


@XmlAccessorType(XmlAccessType.FIELD)
public class ColDtUsuarios {
	
		private DtUsuario[] usuarios;

		public ColDtUsuarios(DtUsuario[] users) {
			this.usuarios = users;
		}

		public DtUsuario[] getUsuarios() {
			return this.usuarios;
		}
		
}
	
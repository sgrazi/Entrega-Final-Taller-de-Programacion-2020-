package excepciones;

import javax.xml.ws.WebFault;

@WebFault(name="EspectaculoAgregadoYaExisteExcepcion")
public class EspectaculoAgregadoYaExisteExcepcion extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EspectaculoAgregadoYaExisteExcepcion() {
		super();
	}
	
	public EspectaculoAgregadoYaExisteExcepcion(String string) {
		super(string);
	}

}

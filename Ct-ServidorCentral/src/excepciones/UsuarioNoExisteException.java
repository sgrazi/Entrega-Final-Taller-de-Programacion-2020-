package excepciones;

import javax.xml.ws.WebFault;

@WebFault(name="UsuarioNoExisteException")
public class UsuarioNoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioNoExisteException() {
		super();
	}
	
	public UsuarioNoExisteException(String string) {
		super(string);
	}
	
}

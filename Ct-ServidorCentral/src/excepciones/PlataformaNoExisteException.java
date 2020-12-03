package excepciones;

import javax.xml.ws.WebFault;

@WebFault(name="PlataformaNoExisteException")
public class PlataformaNoExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PlataformaNoExisteException() {
		super();
	}
	
	public PlataformaNoExisteException(String string) {
		super(string);
	}
}

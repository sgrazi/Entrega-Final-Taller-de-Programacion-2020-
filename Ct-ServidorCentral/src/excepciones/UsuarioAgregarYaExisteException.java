package excepciones;

public class UsuarioAgregarYaExisteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioAgregarYaExisteException(String string) {
		super(string);
	}
	
}

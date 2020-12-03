package excepciones;

public class CategoriaConNombreVacioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CategoriaConNombreVacioException(String string) {
		super(string);
	}
}
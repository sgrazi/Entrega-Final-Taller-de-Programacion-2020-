package logica;

import java.util.Date;
import java.util.Set;

import datatypes.DtUsuario;
import excepciones.EspectaculoNoExistenteException;
import excepciones.PuntajeInvalidoException;
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.UsuarioNoEsEspectadorException;
import excepciones.UsuarioNoExisteException;

public interface IControladorUsuario {

	public abstract void confirmarAlta(boolean esArtista, String nick, String nom, String ape, String email, Date fechaN,
			String descripcion, String biografia, String url, String contrasena, String img)
			throws UsuarioAgregarDatosInvalidosException, UsuarioAgregarYaExisteException;

	public abstract void confirmarModificacion(String nick, String nom, String ape, String email, Date fechaN,
			String descripcion, String biografia, String url, String contrasena, String img)
			throws UsuarioAgregarDatosInvalidosException, UsuarioAgregarYaExisteException, UsuarioNoExisteException;

	public abstract DtUsuario[] obtenerUsuarios() throws UsuarioNoExisteException;

	public abstract DtUsuario obtenerUsuario(String email) throws UsuarioNoExisteException;

//	public abstract Set<String> obtenerUsuarios();

	public abstract Set<String> obtenerArtistas();

	public abstract Set<String> getArtistasNick();

	public abstract void inicioDeSesion(String emailONick, String contrasena) throws UsuarioNoExisteException;

	public abstract void seguirAUnUsuario(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteException;

	public abstract void dejarDeSeguirAUnUsuario(String nickSeguidor, String nickSeguido)
			throws UsuarioNoExisteException;

	public abstract void comprarPaquete(String nickEspectador, String nombrePaquete);

	public abstract Set<String> getRegistrosNoCanjeadosUsuario(String user);

	public abstract String[] obtenerEspectaculosAceptadosArtista(String nickUser);
	
	public abstract void marcarEspectaculoComoFavorito(String nickUsuario, String nombreEspectaculo) throws UsuarioNoExisteException, UsuarioNoEsEspectadorException, EspectaculoNoExistenteException;
	
	public abstract void desmarcarEspectaculoComoFavorito(String nickUsuario, String nombreEspectaculo) throws UsuarioNoExisteException, UsuarioNoEsEspectadorException, EspectaculoNoExistenteException;
	
	public abstract void valorarEspectaculo(String nickEspectador, String espectaculo, int puntaje) throws PuntajeInvalidoException, UsuarioNoExisteException, EspectaculoNoExistenteException;
	
	public abstract boolean accederRegistrosAdmin(String idLink);
	
	public abstract String generarLinkAdmin();
}

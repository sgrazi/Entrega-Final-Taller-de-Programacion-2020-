package logica;

import excepciones.EspectaculoNoExistenteException;
import excepciones.PuntajeInvalidoException;
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioNoEsEspectadorException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import datatypes.DtUsuario;

public class ControladorUsuario implements IControladorUsuario {

	private static Map<String, Date> linksSecretos = new HashMap<String, Date>();

	public ControladorUsuario() {
	}

	public void confirmarAlta(boolean esArtista, String nick, String nom, String ape, String email, Date fecNac,
			String descripcion, String biografia, String url, String contrasena, String img)
			throws UsuarioAgregarDatosInvalidosException, UsuarioAgregarYaExisteException {

		if (email.isEmpty()) {
			throw new UsuarioAgregarDatosInvalidosException("Debe ingresar un correo electronico");
		}

		if (nick.isEmpty()) {
			throw new UsuarioAgregarDatosInvalidosException("Debe ingresar un nickname");
		}

		if (contrasena.isEmpty()) {
			throw new UsuarioAgregarDatosInvalidosException("Debe ingresar una contraseña");
		}

		// VOY A BUSCAR AL MANEJADOR PARA VER SI EXISTE EL USUARIO
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		if (manUsu.existe(email)) {
			throw new UsuarioAgregarYaExisteException("Ya existe un usuario con ese email");
		}

		if (manUsu.existePorNickname(nick)) {
			throw new UsuarioAgregarYaExisteException("Ya existe un usuario con ese nickname");
		}

		if (img.isEmpty()) {
			img = "img/nodisponible.png";
		}

		Usuario usuario = null;
		// CREO EL NUEVO USUARIO
		if (esArtista) {

			Artista artista = new Artista(nick, nom, ape, email, fecNac, descripcion, biografia, url, contrasena, img);
			usuario = artista;

		} else {

			Espectador espectador = new Espectador(nick, nom, ape, email, fecNac, contrasena, img);
			usuario = espectador;
		}

		// AGREGO AL MANEJADOR EL NUEVO USUARIO
		manUsu.agregarUsuario(usuario);
	}

	public void confirmarModificacion(String nick, String nom, String ape, String email, Date fecNac,
			String descripcion, String biografia, String url, String contrasena, String img)
			throws UsuarioAgregarDatosInvalidosException, UsuarioAgregarYaExisteException, UsuarioNoExisteException {

		if (email.isEmpty()) {
			throw new UsuarioAgregarDatosInvalidosException("Debe ingresar un correo electronico");
		}

		if (nick.isEmpty()) {
			throw new UsuarioAgregarDatosInvalidosException("Debe ingresar un nickname");
		}

		if (contrasena.isEmpty()) {
			throw new UsuarioAgregarDatosInvalidosException("Debe ingresar una contraseña");
		}

		// VOY A BUSCAR AL MANEJADOR PARA VER SI EXISTE EL USUARIO
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		Usuario usu = manUsu.obtenerUsuario(email);

		if (usu == null) {
			throw new UsuarioNoExisteException("No existe un usuario con ese email");
		}

		if (!email.equals(usu.getCorreoElectronico())) {
			throw new UsuarioAgregarDatosInvalidosException("El correo electronico no se puede modificar");
		}

		if (!nick.equals(usu.getNickName())) {
			throw new UsuarioAgregarDatosInvalidosException("El nickname no se puede modificar");
		}

		// SETEO DATOS
		usu.setNombre(nom);
		usu.setApellido(ape);
		usu.setfechaDeNacimiento(fecNac);
		usu.setContrasena(contrasena);
		usu.setImagen(img);

		// AHORA SI ES ARTISTA SETEO LO QUE FALTA
		if (usu instanceof Artista) {
			Artista art = (Artista) usu;

			art.setDescripcion(descripcion);
			art.setBiografia(biografia);
			art.setSitioWeb(url);
		}

	}

	private DtUsuario getDtUsuario(Usuario usu) {
		if (usu instanceof Artista) {

			Artista art = (Artista) usu;

			return art.getDt();

		} else {

			Espectador esp = (Espectador) usu;

			return esp.getDt();
		}

	}

	public DtUsuario[] obtenerUsuarios() throws UsuarioNoExisteException {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		Usuario[] usrs = manUsu.obtenerUsuarios();

		if (usrs.length == 0) {
			throw new UsuarioNoExisteException("No existen usuarios registrados en el sistema");
		} else {
			DtUsuario[] resLista = new DtUsuario[usrs.length];

			Usuario usu;
			for (int i = 0; i < usrs.length; i++) {
				usu = usrs[i];

				DtUsuario data = getDtUsuario(usu);

				resLista[i] = data;
			}

			return resLista;
		}

	}

	public DtUsuario obtenerUsuario(String email) throws UsuarioNoExisteException {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();

		Usuario usuario = manUsu.obtenerUsuario(email);

		DtUsuario data = getDtUsuario(usuario);

		return data;
	}

	public Set<String> getArtistasNick() {

		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();

		Set<String> artistas = manUsu.getArtistasNick();

		return artistas;
	}

	public Set<String> obtenerArtistas() {

		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();

		Set<String> artistas = manUsu.getArtistas();

		return artistas;
	}

	public void inicioDeSesion(String emailONick, String contrasena) throws UsuarioNoExisteException {
		// VOY A BUSCAR AL MANEJADOR PARA VER SI EXISTE EL USUARIO
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();

		// PRIMERO VERIFICO SI PUSO EL EMAIL
		Usuario usu = manUsu.obtenerUsuario(emailONick);

		if (usu == null) {
			// NO ES UN EMAIL VALIDO => TRATO DE BUSCAR POR EL NICK E INTENTO DE NUEVO

			usu = manUsu.obtenerUsuarioPorNickName(emailONick);
			if (usu == null) {
				throw new UsuarioNoExisteException("No se encontro el usuario");
			}
		}

		// SI PASA ENTONCES VERIFICO CONTRASE�A
		if (!contrasena.equals(usu.getContrasena())) {
			throw new UsuarioNoExisteException("Contraseña incorrecta");
		}

		// SI NO TIRA EXCEPCION ENTONCES TODO SALIO BIEN Y EL USUARIO SE LOGEA
		// CORRECTAMENTE.
	}

	public void seguirAUnUsuario(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteException {

		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		Usuario uSeguidor = manUsu.obtenerUsuarioPorNickName(nickSeguidor);
		Usuario uSeguido = manUsu.obtenerUsuarioPorNickName(nickSeguido);

		if (uSeguidor == null)
			throw new UsuarioNoExisteException("No se encontro el usuario " + nickSeguidor);
		if (uSeguido == null)
			throw new UsuarioNoExisteException("No se encontro el usuario " + nickSeguido);

		uSeguidor.setSeguido(uSeguido); // AGREGO EL USUARIO SEGUIDO A LA COLECCION DE LOS SEGUIDOS DEL SEGUIDOR
		uSeguido.setSeguidor(uSeguidor); // AGREGO EL USUARIO QUE EMPEZO A SEGUIR A LA COLECCION DE SEGUIDORES DEL
											// USUARIO

	}

	public void dejarDeSeguirAUnUsuario(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteException {

		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		Usuario uSeguidor = manUsu.obtenerUsuarioPorNickName(nickSeguidor);
		Usuario uSeguido = manUsu.obtenerUsuarioPorNickName(nickSeguido);

		if (uSeguidor == null)
			throw new UsuarioNoExisteException("No se encontro el usuario " + nickSeguidor);
		if (uSeguido == null)
			throw new UsuarioNoExisteException("No se encontro el usuario " + nickSeguido);

		uSeguidor.borrarSeguido(nickSeguido);
		uSeguido.borrarSeguidor(nickSeguidor);
	}

	public void comprarPaquete(String nickEspectador, String nombrePaquete) {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		ManejadorPaquete manPaq = ManejadorPaquete.getInstance();
		Espectador esp = manUsu.getEspectador(nickEspectador);
		Paquete paq = manPaq.getPaquete(nombrePaquete);
		Compra com = new Compra(paq);
		esp.comprarPaquete(com);
	}

	public Set<String> getRegistrosNoCanjeadosUsuario(String user) {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		Espectador esp = manUsu.getEspectadorPorNick(user);
		return esp.getRegistrosNoCanjeados();
	}

	public String[] obtenerEspectaculosAceptadosArtista(String nickUser) {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		Artista art = manUsu.getArtista(nickUser);
		return art.obtenerEspectaculos();
	}

	public void marcarEspectaculoComoFavorito(String nickUsuario, String nombreEspectaculo)
			throws UsuarioNoExisteException, UsuarioNoEsEspectadorException, EspectaculoNoExistenteException {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		ManejadorEspectaculo manEsp = ManejadorEspectaculo.getInstance();

		Usuario usuario = manUsu.obtenerUsuarioPorNickName(nickUsuario);
		Espectaculo espectaculo = manEsp.getEspectaculo(nombreEspectaculo);

		if (usuario == null)
			throw new UsuarioNoExisteException("No se encontro el usuario " + nickUsuario);
		if (!(usuario instanceof Espectador))
			throw new UsuarioNoEsEspectadorException("El usuario " + nickUsuario + " no es un espectador");
		if (espectaculo == null)
			throw new EspectaculoNoExistenteException("No existe el espectaculo " + nombreEspectaculo);

		Espectador espectador = (Espectador) usuario;

		// agrego el favorito a la coleccion e incremento la cantidad de favoritos del
		// espectaculo
		if (!espectador.esFavorito(espectaculo)) {

			espectador.setFavorito(espectaculo);
			espectaculo.incrementarFavoritos();
		}

	}

	public void desmarcarEspectaculoComoFavorito(String nickUsuario, String nombreEspectaculo)
			throws UsuarioNoExisteException, UsuarioNoEsEspectadorException, EspectaculoNoExistenteException {
		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		ManejadorEspectaculo manEsp = ManejadorEspectaculo.getInstance();

		Usuario usuario = manUsu.obtenerUsuarioPorNickName(nickUsuario);
		Espectaculo espectaculo = manEsp.getEspectaculo(nombreEspectaculo);

		if (usuario == null)
			throw new UsuarioNoExisteException("No se encontro el usuario " + nickUsuario);
		if (!(usuario instanceof Espectador))
			throw new UsuarioNoEsEspectadorException("El usuario " + nickUsuario + " no es un espectador");
		if (espectaculo == null)
			throw new EspectaculoNoExistenteException("No existe el espectaculo " + nombreEspectaculo);

		Espectador espectador = (Espectador) usuario;

		// borro el favorito de la coleccion y decremento la cantidad de favoritos del
		// espectaculo
		if (espectador.esFavorito(espectaculo)) {

			espectador.borrarDeFavoritos(espectaculo);
			espectaculo.decrementarFavoritos();
		}

	}

	public void valorarEspectaculo(String nickEspectador, String nombreEspectaculo, int puntaje)
			throws PuntajeInvalidoException, UsuarioNoExisteException, EspectaculoNoExistenteException {

		if (puntaje > 5)
			throw new PuntajeInvalidoException("El puntaje no debe ser mayor a 5");

		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();

		Espectador espectador = manejU.getEspectadorPorNick(nickEspectador);
		Espectaculo espectaculo = manejE.getEspectaculo(nombreEspectaculo);

		if (espectador == null)
			throw new UsuarioNoExisteException("No se ha encontradao el espectador '" + nickEspectador + "'");

		if (espectaculo == null)
			throw new EspectaculoNoExistenteException(
					"No se ha encontradao el espectaculo '" + nombreEspectaculo + "'");

		espectaculo.addValoracion(nickEspectador, puntaje);
	}

	public boolean accederRegistrosAdmin(String idLink) {
		boolean permitir = false;
		if (linksSecretos.containsKey(idLink)) {
			Date hoy = new Date();
			Date fecha = linksSecretos.get(idLink);
			permitir = fecha.after(hoy);

			if (!permitir)
				linksSecretos.remove(idLink);
		}

		return permitir;
	}

	public String generarLinkAdmin() {
		final long ONE_MINUTE_IN_MILLIS = 60000; // millisecs

		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 8;
		Random random = new Random();
		String generatedString;
		do {
			generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		} while (linksSecretos.containsKey(generatedString));

		Date hoy = new Date();
		Date fecha = new Date(hoy.getTime() + (3 * ONE_MINUTE_IN_MILLIS)); // Lo avanzo 3 minutos

		linksSecretos.put(generatedString, fecha);

		return generatedString;
	}

}

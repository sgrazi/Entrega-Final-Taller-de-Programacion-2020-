package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.DtArtista;
import datatypes.DtEspectador;
import datatypes.DtUsuario;
import excepciones.CategoriaConNombreVacioException;
import excepciones.CategoriaRepetidaException;
import excepciones.EspectaculoAgregadoYaExisteExcepcion;
import excepciones.EspectaculoNoExistenteException;
import excepciones.PlataformaNoExisteException;
import excepciones.PlataformaRepetidaException;
import excepciones.PuntajeInvalidoException;
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.UsuarioNoEsEspectadorException;
import excepciones.UsuarioNoExisteException;

import logica.Fabrica;
import logica.IControladorEspectaculo;
import logica.IControladorUsuario;
import logica.ManejadorEspectaculo;
import logica.ManejadorUsuario;
import logica.Artista;
import logica.Espectaculo;
import logica.Espectador;

class ControladorUsuarioTest {

	private static IControladorEspectaculo ctrlE;
	private static IControladorUsuario ctrlU;
	private static ManejadorUsuario manejU;
	private static ManejadorEspectaculo manejE;

	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		ctrlE = fabrica.getIControladorEspectaculo();
		ctrlU = fabrica.getIControladorUsuario();
		manejU = ManejadorUsuario.getInstance();
		manejE = ManejadorEspectaculo.getInstance();

	}

	@Test
	void testConfirmarAltaEspectadorOk() {
		String nick = "NickA";
		String nom = "UsuA";
		String ape = "";
		String email = "usuA@mail";
		Date fechaN = null;
		String des = "";
		String bio = "";
		String web = "";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(false, nick, nom, ape, email, fechaN, des, bio, web, con, img);

			DtUsuario data = ctrlU.obtenerUsuario(email);

			if (!(data instanceof DtEspectador)) {
				fail("El usuario no es un espectador");
			}

			DtEspectador data2 = (DtEspectador) data;

			String[] funciones = data2.getFunciones();
			int largo = funciones.length;

			assertEquals(data2.getNickName(), nick);
			assertEquals(data2.getNombre(), nom);
			assertEquals(data2.getApellido(), ape);
			assertEquals(data2.getCorreoElectronico(), email);
			assertEquals(data2.getFechaDeNacimiento(), fechaN);
			assertEquals(largo, 0);
			assertEquals(data2.getContrasena(), con);
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

	}

	@Test
	void testConfirmarAltaArtistaOk() {
		String nick = "NickB";
		String nom = "UsuB";
		String ape = "";
		String email = "usuB@mail";
		Date fechaN = null;
		String des = "";
		String bio = "miBio";
		String web = "www.web.com";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);

			String[] hi = ctrlU.obtenerEspectaculosAceptadosArtista("NickB");
			DtUsuario data = ctrlU.obtenerUsuario(email);

			if (!(data instanceof DtArtista)) {
				fail("El usuario no es un artista");
			}

			DtArtista dta = (DtArtista) data;

			String[] espectaculos = dta.getEspectaculos();
			int largo = espectaculos.length;

			assertEquals(dta.getNickName(), nick);
			assertEquals(dta.getNombre(), nom);
			assertEquals(dta.getApellido(), ape);
			assertEquals(dta.getCorreoElectronico(), email);
			assertEquals(dta.getFechaDeNacimiento(), fechaN);
			assertEquals(dta.getDescripcion(), des);
			assertEquals(dta.getBiografia(), bio);
			assertEquals(dta.getUrl(), web);
			assertEquals(largo, 0);
			assertEquals(dta.getContrasena(), con);
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

	}

	@Test
	void testConfirmarModificacionEspectadorOk() {
		String nick = "02102020";
		String nom = "UsuA";
		String ape = "";
		String email = "02102020@mail";
		Date fechaN = null;
		String des = "";
		String bio = "";
		String web = "";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(false, nick, nom, ape, email, fechaN, des, bio, web, con, img);

			ctrlU.confirmarModificacion(nick, nom, ape, email, fechaN, des, bio, web, "nueva", "IMG");
			Set<String> aaaa = ctrlU.getRegistrosNoCanjeadosUsuario("02102020");
			
			DtUsuario data = ctrlU.obtenerUsuario(email);
			assertEquals(data.getNickName(), nick);
			assertEquals(data.getNombre(), nom);
			assertEquals(data.getApellido(), ape);
			assertEquals(data.getCorreoElectronico(), email);
			assertEquals(data.getFechaDeNacimiento(), fechaN);
			assertEquals(data.getContrasena(), "nueva");
			assertEquals(data.getImagen(), "IMG");
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

	}

	@Test
	void testConfirmarModificacionArtistaOk() {
		String nick = "02102020A";
		String nom = "UsuA";
		String ape = "";
		String email = "02102020A@mail";
		Date fechaN = null;
		String des = "";
		String bio = "BIO";
		String web = "";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);
			Set<String> aaa = ctrlU.getArtistasNick();
			ctrlU.confirmarModificacion(nick, nom, ape, email, fechaN, des, bio, web, "nueva", "IMG");

			DtUsuario data = ctrlU.obtenerUsuario(email);
			if (!(data instanceof DtArtista)) {
				fail("El usuario no es un artista");
			}

			DtArtista dta = (DtArtista) data;
			assertEquals(dta.getNickName(), nick);
			assertEquals(dta.getNombre(), nom);
			assertEquals(dta.getApellido(), ape);
			assertEquals(dta.getCorreoElectronico(), email);
			assertEquals(dta.getFechaDeNacimiento(), fechaN);
			assertEquals(dta.getContrasena(), "nueva");
			assertEquals(dta.getImagen(), "IMG");

		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

	}

	@Test
	void testConfirmarAltaFailYaExistePorEmail() {
		String nick = "NickC";
		String nom = "UsuC";
		String ape = "";
		String email = "usuC@mail";
		Date fechaN = null;
		String des = "";
		String bio = "";
		String web = "www.web2.com";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);

		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

		assertThrows(UsuarioAgregarYaExisteException.class, () -> {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);
		});
	}

	@Test
	void testConfirmarAltaFailYaExistePorNickname() {
		String nick = "NickD";
		String nom = "UsuD";
		String ape = "";
		String email = "usuD@mail";
		Date fechaN = null;
		String des = "";
		String bio = "";
		String web = "www.web2.com";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);

		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

		assertThrows(UsuarioAgregarYaExisteException.class, () -> {
			ctrlU.confirmarAlta(true, nick, nom, ape, "usuDD@mail", fechaN, des, bio, web, con, img);
		});

	}

	@Test
	void testAltaFailEmailVacio() {
		String nick = "NickE";
		String nom = "UsuE";
		String ape = "";
		String email = "";
		Date fechaN = null;
		String des = "";
		String bio = "miBio";
		String web = "";
		String con = "pass";
		String img = "";

		assertThrows(UsuarioAgregarDatosInvalidosException.class, () -> {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);
		});

	}

	@Test
	void testAltaFailNickNameVacio() {
		String nick = "";
		String nom = "UsuF";
		String ape = "";
		String email = "mimail@mail";
		Date fechaN = null;
		String des = "";
		String bio = "miBio";
		String web = "";
		String con = "pass";
		String img = "";

		assertThrows(UsuarioAgregarDatosInvalidosException.class, () -> {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);
		});

	}

	@Test
	void testObtenerUsuariosFailNoHayUsuarios() {
		manejU.eliminarUsuarios();

		assertThrows(UsuarioNoExisteException.class, () -> {
			ctrlU.obtenerUsuarios();
		});
	}

	@Test
	void testObtenerUsuariosOk() {
		manejU.eliminarUsuarios();

		String nick = "NickG";
		String nom = "UsuG";
		String ape = "";
		String email = "usuG@mail";
		Date fechaN = null;
		String des = "";
		String bio = "";
		String web = "";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(false, nick, nom, ape, email, fechaN, des, bio, web, con, img);

			DtUsuario[] usuarios = ctrlU.obtenerUsuarios();
			assertEquals(usuarios.length, 1);

		} catch (UsuarioNoExisteException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

	}

	@Test
	void testObtenerArtistasVacio() {

		manejU.eliminarUsuarios();

		Set<String> usuarios = ctrlU.obtenerArtistas();

		assertEquals(usuarios.size(), 0);

	}

	@Test
	void testObtenerArtistasOk() {
		manejU.eliminarUsuarios();

		String nick = "NickH";
		String nom = "UsuH";
		String ape = "";
		String email = "usuH@mail";
		Date fechaN = null;
		String des = "";
		String bio = "";
		String web = "";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(false, nick, nom, ape, email, fechaN, des, bio, web, con, img);

			nick = "NickI";
			email = "usuI@mail";
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);

			Set<String> usuarios = ctrlU.obtenerArtistas();

			assertEquals(usuarios.size(), 1);

		} catch (UsuarioAgregarDatosInvalidosException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}

	}

	@Test
	void testDts() {
		Date date = new Date();
		Artista art = new Artista("nick", "n", "ap", "e", date, "d", "b", "s", "pass", "IMG");
		DtArtista dtA = art.getDt();
		Espectador esp = new Espectador("nick", "n", "ap", "e", date, "pass", "IMG");
		DtEspectador dtE = esp.getDt();

		assertEquals(art.getNickName(), dtA.getNickName());
		assertEquals(art.getNombre(), dtA.getNombre());
		assertEquals(art.getApellido(), dtA.getApellido());
		assertEquals(art.getCorreoElectronico(), dtA.getCorreoElectronico());
		assertEquals(art.getFechaDeNacimiento(), dtA.getFechaDeNacimiento());
		assertEquals(art.getDescripcion(), dtA.getDescripcion());
		assertEquals(art.getBiografia(), dtA.getBiografia());
		assertEquals(art.getSitioWeb(), dtA.getUrl());

		assertEquals(esp.getNickName(), dtE.getNickName());
		assertEquals(esp.getNombre(), dtE.getNombre());
		assertEquals(esp.getApellido(), dtE.getApellido());
		assertEquals(esp.getCorreoElectronico(), dtE.getCorreoElectronico());
		assertEquals(esp.getFechaDeNacimiento(), dtE.getFechaDeNacimiento());

	}

	@Test
	void testOperacionesManejador() {

		try {
			Date date = new Date();
			ctrlU.confirmarAlta(true, "nickname123", "nom", "ape", "email", date, "des", "bio", "web", "pass", "IMG");
			Set<String> setA = manejU.getArtistasNick();
			assertEquals(setA.isEmpty(), false);

			ctrlU.confirmarAlta(false, "nickname1234", "nom", "ape", "email2", date, "des", "bio", "web", "pass",
					"IMG");
			Set<String> setB = manejU.getEspectadoresPorNick();
			assertEquals(setB.isEmpty(), false);

			Set<String> setC = manejU.getEspectadores();
			assertEquals(setC.isEmpty(), false);

			Espectador esp = manejU.getEspectadorPorNick("nickname1234");
			assertEquals(esp.getNombre(), "nom");

			Espectador esp2 = manejU.getEspectador("nickname1234");
			assertEquals(esp2.getNombre(), "nom");

		} catch (UsuarioAgregarDatosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testIniciarSesion() {
		String nick = "NickB";
		String nom = "UsuB";
		String ape = "";
		String email = "usuB@mail";
		Date fechaN = null;
		String des = "";
		String bio = "miBio";
		String web = "www.web.com";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);
			ctrlU.inicioDeSesion(nick, con);
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}
	}

	@Test
	void testSeguirYDejarDeSeguir() {
		String nick = "NickB";
		String nom = "UsuB";
		String ape = "";
		String email = "usuB@mail";
		Date fechaN = null;
		String des = "";
		String bio = "miBio";
		String web = "www.web.com";
		String con = "pass";
		String img = "";

		try {
			ctrlU.confirmarAlta(true, nick, nom, ape, email, fechaN, des, bio, web, con, img);
			ctrlU.confirmarAlta(true, "NickA", nom, ape, "usuA@mail", fechaN, des, bio, web, con, img);
			ctrlU.seguirAUnUsuario(nick, "NickA");
			ctrlU.dejarDeSeguirAUnUsuario(nick, "NickA");
		} catch (UsuarioNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			fail(e1.getMessage());
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e2) {
			fail(e2.getMessage());
			e2.printStackTrace();
		}
	}
	
	@Test
	void testMarcaryDesmarcarEspectaculoComoFavorito() {
		
		try {

			
			ctrlE.altaPlataforma("PlataformaM", "testM", "PruebaOKM");
			Date date = new Date();
			ctrlE.altaCategoria("categ");
			Set<String> categoria = new HashSet<String>();
			categoria.add("categ");
			ctrlU.confirmarAlta(true, "artistaM", "nomM", "ape", "emailAM", date, "descripcion", "biografia",
					"url", "contra", "img");
			ctrlU.confirmarAlta(false, "espectadorM", "nomM", "ape", "emailM", date, "descripcion", "biografia",
					"url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaM", "artistaM", "EspectaculoOKM", "desc", "url", 0, 10,
					0, 30, date, categoria, "img",0,"","");
			
			Espectador espect = (Espectador) manejU.obtenerUsuarioPorNickName("espectadorM");
			Espectaculo espectaculo = manejE.getEspectaculo("EspectaculoOKM");
			
			//PRUEBO MARCAR
			ctrlU.marcarEspectaculoComoFavorito("espectadorM", "EspectaculoOKM");
			assertEquals(espect.esFavorito(espectaculo), true);
			assertEquals(espectaculo.getCantidadFavoritos(), 1);
			
			
			//PRUEBO DESMARCAR
			ctrlU.desmarcarEspectaculoComoFavorito("espectadorM", "EspectaculoOKM");
			assertEquals(espect.esFavorito(espectaculo), false);
			assertEquals(espectaculo.getCantidadFavoritos(), 0);
			
			
			
		} catch (UsuarioAgregarDatosInvalidosException | UsuarioAgregarYaExisteException | PlataformaRepetidaException | CategoriaRepetidaException | CategoriaConNombreVacioException | EspectaculoAgregadoYaExisteExcepcion | UsuarioNoExisteException | PlataformaNoExisteException | UsuarioNoEsEspectadorException | EspectaculoNoExistenteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testValorarEspectaculo() {
		
		try {
			assertThrows(UsuarioNoExisteException.class, () -> {
				ctrlU.valorarEspectaculo("espectadorMPM", "EspectaculoOKMPM", 1);
			});
			
			ctrlE.altaPlataforma("PlataformaMPM", "testM", "PruebaOKM");
			Date date = new Date();
			ctrlE.altaCategoria("categPM");
			Set<String> categoria = new HashSet<String>();
			categoria.add("categPM");
			ctrlU.confirmarAlta(true, "artistaMPM", "nomM", "ape", "emailAMPM", date, "descripcion", "biografia",
					"url", "contra", "img");
			ctrlU.confirmarAlta(false, "espectadorMPM", "nomM", "ape", "emailMPM", date, "descripcion", "biografia",
					"url", "contra", "img");
			
			assertThrows(EspectaculoNoExistenteException.class, () -> {
				ctrlU.valorarEspectaculo("espectadorMPM", "EspectaculoOKMPM", 1);
			});
			
			
			ctrlE.altaEspectaculo("PlataformaMPM", "artistaMPM", "EspectaculoOKMPM", "desc", "url", 0, 10,
					0, 30, date, categoria, "img",0,"","");
			
			assertThrows(PuntajeInvalidoException.class, () -> {
				ctrlU.valorarEspectaculo("espectadorMPM", "EspectaculoOKMPM", 7);
			});
			
			
			
			ctrlU.valorarEspectaculo("espectadorMPM", "EspectaculoOKMPM", 1);
			
		} catch (UsuarioAgregarDatosInvalidosException | UsuarioAgregarYaExisteException | PlataformaRepetidaException | CategoriaRepetidaException | CategoriaConNombreVacioException | EspectaculoAgregadoYaExisteExcepcion | UsuarioNoExisteException | PlataformaNoExisteException | EspectaculoNoExistenteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (PuntajeInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

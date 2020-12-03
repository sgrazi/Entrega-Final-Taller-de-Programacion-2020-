package test;

import excepciones.PlataformaRepetidaException;
import excepciones.TicketsAgotadosParaFuncionException;
import excepciones.EspectaculoAgregadoYaExisteExcepcion;
import excepciones.EspectaculoEnPaqueteErrorException;
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.UsuarioNoExisteException;
import excepciones.CanjeInvalidoException;
import excepciones.CategoriaConNombreVacioException;
import excepciones.CategoriaRepetidaException;
import excepciones.PaqueteNoExisteException;
import excepciones.PaqueteRepetidoException;
import excepciones.PlataformaNoExisteException;
import excepciones.EspectaculoNoExistenteException;
import excepciones.EspectadorYaRegistradoException;
import excepciones.NombreFuncionRepetidoException;

import logica.IControladorEspectaculo;
import logica.IControladorUsuario;
import logica.ManejadorUsuario;
import logica.Fabrica;
import logica.Funcion;
import logica.ManejadorPlataforma;
import logica.Plataforma;
import logica.Espectaculo;
import logica.ManejadorEspectaculo;
import logica.ManejadorPaquete;
import logica.ManejadorCategoria;
import logica.Espectador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import datatypes.DtHora;
import datatypes.DtPaquete;
import datatypes.DtPlataforma;

class ControladorEspectaculoTest {

	private static IControladorEspectaculo ctrlE;
	private static IControladorUsuario ctrlU;
	private static ManejadorUsuario manejadorU;

	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		ctrlE = fabrica.getIControladorEspectaculo();
		ctrlU = fabrica.getIControladorUsuario();

		manejadorU = ManejadorUsuario.getInstance();

	}

	@Test
	void testAltaPlataformaOk() {
		ManejadorPlataforma mPlataforma = ManejadorPlataforma.getInstance();
		try {
			ctrlE.altaPlataforma("PlataformaOK", "test.ok", "PruebaOK");
			Plataforma plat = mPlataforma.getPlataforma("PlataformaOK");
			
			ManejadorPlataforma manejP = ManejadorPlataforma.getInstance();
			Plataforma[] pl = manejP.getPlataformas();
			assertNotEquals(null, plat);
			assertEquals("PlataformaOK", plat.getNombre());
			assertEquals("PruebaOK", plat.getDescripcion());
			assertEquals("test.ok", plat.getUrl());

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testAltaPlataformaRepetida() {
		try {
			String[] aa = ctrlE.listarNombresPlataformas();
			ctrlE.altaPlataforma("PlataformaRep", "test.rep", "PruebaRep");

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		}

		assertThrows(PlataformaRepetidaException.class, () -> {
			ctrlE.altaPlataforma("PlataformaRep", "test.rep", "PruebaRep");
		});
	}

	@Test
	void testGetCostoEspectaculo() {
		try {
			ctrlE.altaPlataforma("Plataforma CostoEspectaculo", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCatq");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCatq");
			ctrlU.confirmarAlta(true, "artista CostoEspectaculo", "nom", "ape", "email CostoEspectaculo", date,
					"descripcion", "biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("Plataforma CostoEspectaculo", "artista CostoEspectaculo", "Espectaculo Costo",
					"desc", "url", 13, 10, 0, 30, date, categoria, "img",0,"","");

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testAltaEspectaculoOk() {
		ManejadorEspectaculo mEspectaculo = ManejadorEspectaculo.getInstance();
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculo", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat1");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat1");
			ctrlU.confirmarAlta(true, "artistaEspectaculo", "nom", "ape", "email", date, "descripcion", "biografia",
					"url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculo", "artistaEspectaculo", "EspectaculoOK", "desc", "url", 0, 10,
					0, 30, date, categoria, "img",0,"","");
			Espectaculo esp = mEspectaculo.getEspectaculo("EspectaculoOK");
			assertNotEquals(null, esp);
			assertEquals("PlataformaEspectaculo", esp.getPlataforma());
			assertEquals("artistaEspectaculo", esp.getOrganizador());
			assertEquals("EspectaculoOK", esp.getNombre());
			assertEquals("desc", esp.getDescripcion());
			assertEquals("url", esp.getURL());
			assertEquals(0, esp.getCosto());
			assertEquals(10, esp.getCantMaxEspectadores());
			assertEquals(0, esp.getCantMinEspectadores());
			assertEquals(30, esp.getDuracion());
			assertEquals(date, esp.getFechaDeRegistro());

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testAltaEspectaculoDtOk() {
		ManejadorEspectaculo mEspectaculo = ManejadorEspectaculo.getInstance();
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoDT", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat1DT");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat1DT");
			ctrlU.confirmarAlta(true, "artistaEspectaculoDT", "nom", "ape", "emailDT", date, "descripcion", "biografia",
					"url", "contra", "img");
			DtEspectaculo dtEsp = new DtEspectaculo("lalalelel", "", "", 0, 0, 0, 0, date,categoria,"");
			dtEsp.setArtista("artistaEspectaculoDT");
			dtEsp.setPlataforma("PlataformaEspectaculoDT");
			ctrlE.altaEspectaculo(dtEsp);
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaEspectaculoSettersOk() {
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoSetters", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlU.confirmarAlta(true, "artistaEspectaculoSetters", "nom", "ape", "email2", date, "descripcion",
					"biografia", "url", "contra", "img");
			Espectaculo esp = new Espectaculo();
			esp.setPlataforma("PlataformaEspectaculoSetters");
			esp.setOrganizador("artistaEspectaculoSetters");
			esp.setNombre("EspectaculoSetters");
			esp.setDescripcion("desc");
			esp.setURL("url");
			esp.setCosto(0);
			esp.setCantMaxEspectadores(10);
			esp.setCantMinEspectadores(0);
			esp.setDuracion(30);
			esp.setFechaDeRegistro(date);
			assertNotEquals(null, esp);
			assertEquals("PlataformaEspectaculoSetters", esp.getPlataforma());
			assertEquals("artistaEspectaculoSetters", esp.getOrganizador());
			assertEquals("EspectaculoSetters", esp.getNombre());
			assertEquals("desc", esp.getDescripcion());
			assertEquals("url", esp.getURL());
			assertEquals(0, esp.getCosto());
			assertEquals(10, esp.getCantMaxEspectadores());
			assertEquals(0, esp.getCantMinEspectadores());
			assertEquals(30, esp.getDuracion());
			assertEquals(date, esp.getFechaDeRegistro());

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		}

	}

	@Test
	void testObtenerAceptados() {
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculovvv", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat5");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat5");
			ctrlU.confirmarAlta(true, "artistaEspectaculo3", "nom", "ape", "email3", date, "descripcion",
					"biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculovvv", "artistaEspectaculo3", "EspectaculoOK3", "desc",
					"url", 0, 10, 0, 30, date, categoria, "img",0,"","");
			ctrlE.aceptarEspectaculo("EspectaculoOK3");
			Set<String> set = ctrlE.obtenerEspectaculosAceptados();
			Set<DtEspectaculo> setdt = ctrlE.obtenerEspectaculosAceptadosDt();
			Set<DtEspectaculo> setE = ctrlE.obtenerEspectaculosAceptadosPorCategoria("nombreCat5");
			Set<DtEspectaculo> setE2 = ctrlE.obtenerEspectaculosAceptadosPorPlataforma("PlataformaEspectaculovvv");
			DtPlataforma dtt = ctrlE.obtenerPlataformaDt("PlataformaEspectaculovvv");
			Set<String> hola = ctrlE.obtenerPaquetesEspectaculo("EspectaculoOK3");
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testAltaEspectaculoRepetido() {
		Date date = new Date();
		try {
			ctrlE.altaCategoria("nombreCat2");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat2");
			ctrlE.altaPlataforma("PlataformaEspectaculoRep", "test.rep", "PruebaRep");
			ctrlU.confirmarAlta(true, "artistaEspectaculo Rep", "nomRep", "ape", "artistaEspectaculo Rep", date,
					"descripcion", "biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoRep", "artistaEspectaculo Rep", "EspectaculoRep", "desc", "url",
					0, 10, 0, 30, date, categoria, "img",0,"","");
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(EspectaculoAgregadoYaExisteExcepcion.class, () -> {
			ctrlE.altaCategoria("nombreCat3");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat3");
			ctrlE.altaEspectaculo("PlataformaEspectaculoRep", "artistaEspectaculo", "EspectaculoRep", "desc", "url", 0,
					10, 0, 30, date, categoria, "img",0,"","");
		});
	}

	// a
	@Test
	void testListarPlataformasOk() {
		try {
			ctrlE.altaPlataforma("PlataformaTestLista0", "test.ok", "PruebaOK");
			ctrlE.altaPlataforma("PlataformaTestLista1", "test.ok", "PruebaOK");
			Set<String> set = ctrlE.listarPlataformasSet();
			assertEquals(set.contains("PlataformaTestLista0"), true);
			assertEquals(set.contains("PlataformaTestLista1"), true);
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSeleccionarPlataforma() {
		try {
			ctrlE.altaPlataforma("PlataformaSeleccionar", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat4");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat4");
			ctrlU.confirmarAlta(true, "artistaEspectaculoSeleccionar", "nom", "ape", "emailSeleccionar", date,
					"descripcion", "biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaSeleccionar", "artistaEspectaculoSeleccionar", "EspectaculoSeleccionar",
					"desc", "url", 0, 10, 0, 30, date, categoria, "img",0,"","");

			Set<String> espectaculos = ctrlE.seleccionarPlataforma("PlataformaSeleccionar");
			Set<String> espectaculos2 = ctrlE.obtenerEspectaculos("PlataformaSeleccionar");

			assertEquals(espectaculos.equals(espectaculos2), true);
			assertEquals(espectaculos.contains("EspectaculoSeleccionar"), true);

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EspectaculoAgregadoYaExisteExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testListarPaquetesOk() {
		try {
			Date inicio = new Date();
			Date fin = new Date();

			ctrlE.altaPaquete(new DtPaquete("PaqueteListando1", "Paquete de prueba1.", inicio, fin, 0.1f, 0));
			ctrlE.altaPaquete(new DtPaquete("PaqueteListando2", "Paquete de prueba2.", inicio, fin, 0.1f, 0));

			Set<String> pqts = ctrlE.listarPaquetes();
			assertTrue(pqts.contains("PaqueteListando1"));
			assertTrue(pqts.contains("PaqueteListando2"));
			
			ManejadorPaquete manejP = ManejadorPaquete.getInstance();
			Set<DtPaquete> dts = manejP.getDataPaquetes();
		} catch (PaqueteNoExisteException | PaqueteRepetidoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testObtenerPaqueteOk() {
		try {
			Date inicio = new Date();
			Date fin = new Date();
			ctrlE.altaPaquete(new DtPaquete("PaqueteTest", "Paquete de prueba.", inicio, fin, 0.2f, 0));
			DtPaquete paq = ctrlE.obtenerPaquete("PaqueteTest");

			ctrlE.altaPlataforma("PlataformaSeleccionar123", "test.ok", "PruebaOK");
			ctrlE.altaCategoria("nombreCat41");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat4");
			ctrlU.confirmarAlta(true, "artistaEspectaculoSeleccionar123", "nom", "ape", "emailSeleccionar124", fin,
					"descripcion", "biografia", "url", "contra", "img");
			ctrlU.confirmarAlta(false, "eeleccionar123", "nom", "ape", "emailSel", fin,
					"descripcion", "biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaSeleccionar123", "artistaEspectaculoSeleccionar123", "espPaquete123",
					"desc", "url", 0, 10, 0, 30, fin, categoria, "img",0,"","");
			ctrlU.comprarPaquete("eeleccionar123", "PaqueteTest");
			assertEquals("PaqueteTest", paq.getNombre());
			assertEquals("Paquete de prueba.", paq.getDescripcion());
			assertEquals(inicio, paq.getInicio());
			assertEquals(inicio, paq.getFin());
			assertTrue(paq.getEspectaculos() != null && paq.getEspectaculos().length == 0);

		} catch (PaqueteNoExisteException | PaqueteRepetidoException e) {
			fail(e.getMessage());
		} catch (PlataformaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testObtenerPaqueteFail() {
		assertThrows(PaqueteNoExisteException.class, () -> {
			ctrlE.obtenerPaquete("Este Paquete no existe");
		});
	}

	@Test
	void testAltaPaqueteFail() {
		Date fecha = new Date();
		try {
			ctrlE.altaPaquete(new DtPaquete("Paquete Repetido", "Paquete que falla.", fecha, fecha, 0.1f, 0));
		} catch (PaqueteRepetidoException e) {
			fail(e.getMessage());
		}

		assertThrows(PaqueteRepetidoException.class, () -> {
			ctrlE.altaPaquete(new DtPaquete("Paquete Repetido", "Paquete que falla.", fecha, fecha, 0.1f, 0));
		});
	}

	@Test
	void testCanjeHabilitado() {
		Date fechaNac = new Date();
		try {
			ctrlU.confirmarAlta(false, "remy", "remyNombre", "lh", "reman", fechaNac, "hola", "aaa", ".com", "contra", "img");
			assertEquals(ctrlE.canjeHabilitado("remy"), false);

		} catch (UsuarioAgregarDatosInvalidosException | UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testConfirmarAltaRegistroAFuncion() {
		Date fechaNac = new Date();
		DtHora hora = new DtHora(15, 30);
		String imagen = "";
		Set<String> artistas = new HashSet<String>();
		ManejadorCategoria manejC = ManejadorCategoria.getInstance();
		try {
			ctrlE.altaCategoria("nombreCat55");
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> categoria = manejC.getCategorias();
		try {
			ctrlE.altaPlataforma("sp", "sp.com", "");
		} catch (PlataformaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ctrlU.confirmarAlta(true, "rem", "remy", "lh", "remon", fechaNac, "descc", "", "mm", "contra", "img");
			ctrlU.confirmarAlta(false, "anto", "nella", "ma", "iaia", fechaNac, "desccc", "", "mmo", "contra", "img");
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ctrlE.altaEspectaculo("sp", "rem", "cine", "desc", ".com2", 1000, 500, 5, 60, fechaNac, categoria, "img",0,"","");
		} catch (EspectaculoAgregadoYaExisteExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ctrlE.confirmarAltaFuncion("cine", "func", fechaNac, hora, artistas, fechaNac, imagen);
			ctrlE.confirmarAltaFuncion("cine", "func1", fechaNac, hora, artistas, fechaNac, imagen);
			ctrlE.confirmarAltaFuncion("cine", "func2", fechaNac, hora, artistas, fechaNac, imagen);
			ctrlE.confirmarAltaFuncion("cine", "func3", fechaNac, hora, artistas, fechaNac, imagen);
		} catch (NombreFuncionRepetidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EspectaculoNoExistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ctrlE.confirmarAltaRegistroAFuncion("cine", "anto", "func", fechaNac, false, "", "", "");
			ctrlE.confirmarAltaRegistroAFuncion("cine", "anto", "func1", fechaNac, false, "", "", "");
			ctrlE.confirmarAltaRegistroAFuncion("cine", "anto", "func2", fechaNac, false, "", "", "");
			ctrlE.confirmarAltaRegistroAFuncion("cine", "anto", "func3", fechaNac, true, "func", "func1", "func2");
		} catch (EspectadorYaRegistradoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TicketsAgotadosParaFuncionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CanjeInvalidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EspectaculoNoExistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
		Espectador esp = manejU.getEspectador("anto");
		assertEquals(esp.estaRegistrado("func"), true);
		
	}

	@Test
	void testListarEspectadores() {
		Date date = new Date();
		try {
			ctrlU.confirmarAlta(false, "EspectadorAListaraaaa", "nom", "ape", "emailEspectador11111111", date, "", "", "",
					"contra", "img");
			Set<String> esp = ctrlE.listarEspectadores();
			Set<String> espNick = ctrlE.listarEspectadoresPorNick();
			assertEquals(esp.contains("emailEspectador11111111"), true);
			assertEquals(espNick.contains("EspectadorAListaraaaa"), true);
		} catch (UsuarioAgregarDatosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testAltaFuncionOk() {
		ManejadorEspectaculo mEspectaculo = ManejadorEspectaculo.getInstance();
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoFuncion", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat6");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat6");
			ctrlU.confirmarAlta(true, "artistaEspectaculoFuncion", "nom", "ape", "emailFuncion", date, "descripcion",
					"biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoFuncion", "artistaEspectaculoFuncion", "EspectaculoFuncion",
					"desc", "url", 0, 10, 0, 30, date, categoria, "img",0,"","");
			Espectaculo espectaculo = mEspectaculo.getEspectaculo("EspectaculoOK");
			DtHora hora = new DtHora(3, 0);
			Set<String> artista = new HashSet<String>();
			artista.add("artistaEspectaculoFuncion");
			String imagen = "";
			ctrlE.confirmarAltaFuncion("EspectaculoFuncion", "FuncionOk", date, hora, artista, date, imagen);
			
			//Funcion fun = mEspectaculo.getFuncion(fun);
			//aca
			
			
			DtFuncion dtf = ctrlE.obtenerDatosFuncion("FuncionOk");
			assertNotEquals(null, dtf);
			assertEquals("FuncionOk", dtf.getNombre());
			assertEquals(date, dtf.getFecha());
			assertEquals(hora, dtf.getHora());
			assertEquals(date, dtf.getFechaDeRegistro());
			assertEquals(0, dtf.getCantidadRegistros());

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (NombreFuncionRepetidoException e) {
			fail(e.getMessage());
		} catch (EspectaculoNoExistenteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CategoriaConNombreVacioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PlataformaNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	void testAltaFuncionRepetida() {
		Date date = new Date();
		DtHora hora = new DtHora(3, 0);
		try {
			ctrlE.altaCategoria("nombreCat7");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat7");
			ctrlE.altaPlataforma("PlataformaEspectaculoFuncionRepetida", "test.ok", "PruebaOK");
			ctrlU.confirmarAlta(true, "artistaEspectaculoFuncionRepetida", "nomRep", "ape", "emailFuncionRepetida",
					date, "descripcion", "biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoFuncionRepetida", "artistaEspectaculoFuncionRepetida",
					"EspectaculoFuncionRepetida", "desc", "url", 0, 10, 0, 30, date, categoria, "img",0,"","");

			Set<String> artista = new HashSet<String>();
			artista.add("artistaEspectaculoFuncionRepetida");
			String imagen = "";
			ctrlE.confirmarAltaFuncion("EspectaculoFuncionRepetida", "LaFuncionRepetida", date, hora, artista, date,
					imagen);
			Set<String> funciones = ctrlE.obtenerFuncionesEspectaculo("EspectaculoFuncionRepetida");
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion | EspectaculoNoExistenteException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException | UsuarioAgregarYaExisteException
				| NombreFuncionRepetidoException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(NombreFuncionRepetidoException.class, () -> {
			Set<String> artista = new HashSet<String>();
			artista.add("artistaEspectaculoFuncionRepetida");
			ctrlE.confirmarAltaFuncion("EspectaculoFuncionRepetida", "LaFuncionRepetida", date, hora, artista, date,
					"");
		});

	}

	@Test
	void testAltaFuncionEspectaculoErr() {
		Date date = new Date();
		DtHora hora = new DtHora(3, 0);
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoFuncion EspErr", "test.ok", "Prueba EspErr");
			ctrlU.confirmarAlta(true, "artistaEspectaculoFuncion  EspErr", "nomRep EspErr", "ape",
					"emailFuncion EspErr", date, "descripcion", "biografia", "url", "contra", "img");
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException | UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		}

		assertThrows(EspectaculoNoExistenteException.class, () -> {
			Set<String> artista = new HashSet<String>();
			artista.add("artistaEspectaculoFuncion  EspErr");
			ctrlE.confirmarAltaFuncion("EspectaculoFuncion EspErr", "LaFuncion Que no Existe", date, hora, artista,
					date, "");
		});
	}

	@Test
	void testGetEspectaculoDt() {
		try {
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat8");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat8");
			ctrlE.altaPlataforma("PlataformaEspectaculoTestDt", "test.ok", "PruebaOK");
			ctrlU.confirmarAlta(true, "artistaEspectaculoTestDt", "nom", "ape", "emailTestDt", date, "descripcion",
					"biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoTestDt", "artistaEspectaculoTestDt", "EspectaculoTestDt",
					"desc", "url", 0, 10, 0, 30, date, categoria, "img",0,"","");


			ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
			DtEspectaculo dt = manejE.getDtEspectaculo("EspectaculoTestDt");
			
			try {
				DtEspectaculo data;
				data = ctrlE.getEspectaculoDt("EspectaculoTestDt");

				assertEquals(data.getNombre(), "EspectaculoTestDt");
				assertEquals(data.getDescripcion(), "desc");
			} catch (EspectaculoNoExistenteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PlataformaNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Test
	void testSeleccionarEspectaculo() {
		ManejadorEspectaculo mEspectaculo = ManejadorEspectaculo.getInstance();
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoSelEsp", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat9");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat9");
			ctrlU.confirmarAlta(true, "artistaEspectaculoSelEsp", "nom", "ape", "emailSelEsp", date, "descripcion",
					"biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoSelEsp", "artistaEspectaculoSelEsp", "EspectaculoSelEsp",
					"desc", "url", 0, 10, 0, 30, date, categoria, "img",0,"","");
			Espectaculo espectaculo = mEspectaculo.getEspectaculo("EspectaculoSelEsp");
			DtHora hora = new DtHora(3, 0);
			Set<String> artista = new HashSet<String>();
			artista.add("artistaEspectaculoSelEsp");
			ctrlE.confirmarAltaFuncion("EspectaculoSelEsp", "FuncionSelEsp", date, hora, artista, date, "");

			Set<DtFuncion> funcs = ctrlE.seleccionarEspectaculo("EspectaculoSelEsp");
			assertNotEquals(null, funcs);

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (NombreFuncionRepetidoException e) {
			fail(e.getMessage());
		} catch (EspectaculoNoExistenteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CategoriaConNombreVacioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PlataformaNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Test
	void testListarFuncionesYObtenerFuncionesString() {
		ManejadorEspectaculo mEspectaculo = ManejadorEspectaculo.getInstance();
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoListarFunciones", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat0");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat0");
			ctrlU.confirmarAlta(true, "artistaEspectaculoListarFunciones", "nom", "ape", "emailListarFunciones", date,
					"descripcion", "biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoListarFunciones", "artistaEspectaculoListarFunciones",
					"EspectaculoListarFunciones", "desc", "url", 0, 10, 0, 30, date, categoria, "img",0,"","");
			DtHora hora = new DtHora(3, 0);
			Set<String> artista = new HashSet<String>();
			artista.add("artistaEspectaculoListarFunciones");
			ctrlE.confirmarAltaFuncion("EspectaculoListarFunciones", "FuncionListarFunciones", date, hora, artista,
					date, "");
			Set<DtFuncion> dts1 = ctrlE.getDtFunciones();
			Set<String> dtFunciones = ctrlE.obtenerDtFuncionesAString("EspectaculoListarFunciones");
			Set<String> funciones = ctrlE.listarFunciones("EspectaculoListarFunciones");

			ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
			Set<DtFuncion> dts = manejE.obtenerFunciones();
			
			assertEquals(dtFunciones.isEmpty(), false);
			assertEquals(funciones.isEmpty(), false);
		} catch (PlataformaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EspectaculoAgregadoYaExisteExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NombreFuncionRepetidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EspectaculoNoExistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void testSeleccionarEspectadorYFuncionFail() {
		manejadorU.eliminarUsuarios();

		assertThrows(UsuarioNoExisteException.class, () -> {
			ctrlE.seleccionarEspectadorYFuncion("OtroNick");
		});
	}
	
	@Test
	void testGenerarRegistrosAdmin() {
		String clave = ctrlU.generarLinkAdmin();
		boolean nada = ctrlU.accederRegistrosAdmin(clave);		
	}

	@Test
	void testAceptarRechazarEspectaculo() {
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoAA", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat1AA");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat1AA");
			ctrlU.confirmarAlta(true, "artistaEspectaculo22", "nom", "ape", "email22", date, "descripcion", "biografia",
					"url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoAA", "artistaEspectaculo22", "EspectaculoOK22", "desc", "url",
					0, 10, 0, 30, date, categoria, "img",0,"","");
			ctrlE.altaEspectaculo("PlataformaEspectaculoAA", "artistaEspectaculo22", "EspectaculoOK23", "desc", "url",
					0, 10, 0, 30, date, categoria, "img",0,"","");
			ctrlE.aceptarEspectaculo("EspectaculoOK22");
			ctrlE.rechazarEspectaculo("EspectaculoOK23");
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testEspectaculosIngresados() {
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculoAAa", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat1AAA");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat1AAA");
			ctrlU.confirmarAlta(true, "artistaEspectaculo221", "nom", "ape", "email221", date, "descripcion",
					"biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoAAa", "artistaEspectaculo221", "EspectaculoOK221", "desc",
					"url", 0, 10, 0, 30, date, categoria, "img",0,"","");
			ctrlE.altaEspectaculo("PlataformaEspectaculoAAa", "artistaEspectaculo221", "EspectaculoOK231", "desc",
					"url", 0, 10, 0, 30, date, categoria, "img",0,"","");
			Set<String> ingresados = ctrlE.obtenerEspectaculosIngresados();
			assertEquals(ingresados.contains("EspectaculoOK221"), true);
			assertEquals(ingresados.contains("EspectaculoOK231"), true);
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testSorteoDePremios() {
		try {
			ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
			ManejadorUsuario manejU = ManejadorUsuario.getInstance();
			
			ctrlE.altaPlataforma("PlataformaEspectaculoSorteo", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCatSorteo");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCatSorteo");
			ctrlU.confirmarAlta(true, "artistaSorteo", "nom", "ape", "emailSorteo", date, "descripcion",
					"biografia", "url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculoSorteo", "artistaSorteo", "EspectaculoOKSorteo", "desc",
					"url", 0, 10, 0, 30, date, categoria, "img",1,"","");
			Set<String> artista = new HashSet<String>();
			DtHora hora = new DtHora();
			ctrlE.confirmarAltaFuncion("EspectaculoOKSorteo", "LaFuncionSorteo", date, hora, artista, date,
					"imagen");
			Espectaculo espeee = manejE.getEspectaculo("EspectaculoOKSorteo");
			String toString = espeee.toString();
			Funcion func = espeee.getFuncion("LaFuncionSorteo");
			ctrlU.confirmarAlta(false, "espectSorteo", "nella", "ma", "mailmailmail", date, "desccc", "", "mmo", "contra", "img");
			ctrlE.confirmarAltaRegistroAFuncion("EspectaculoOKSorteo", "espectSorteo", "LaFuncionSorteo", date, false, "", "", "");
			ctrlE.sortearPremios(func);
			ctrlU.confirmarAlta(false, "espectSorteo2", "nella", "ma", "mailmailmail2", date, "desccc", "", "mmo", "contra", "img");
			ctrlE.confirmarAltaRegistroAFuncion("EspectaculoOKSorteo", "espectSorteo2", "LaFuncionSorteo", date, false, "", "", "");
			ctrlE.sortearPremios(func);
			Espectador espectador = manejU.getEspectador("espectSorteo");
			
			String[] ganadores = new String[1];
			ganadores[0] = "espectSorteo2";
			ctrlE.sorteoArreglado(func.getNombre(), ganadores, new Date());
			
			//para mi, verificar que hay algo ahi dentro
			//System.out.println(espectador.getPremios().size());
		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NombreFuncionRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectadorYaRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TicketsAgotadosParaFuncionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CanjeInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	void testTiempoFuncion() {
		Date fechaNac = new Date();
		DtHora hora = new DtHora(15, 30);
		String imagen = "";
		Set<String> artistas = new HashSet<String>();
		ManejadorCategoria manejC = ManejadorCategoria.getInstance();
		try {
			ctrlE.altaCategoria("nombreCatp");
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> categoria = manejC.getCategorias();
		try {
			ctrlE.altaPlataforma("spp", "sp.com", "");
		} catch (PlataformaRepetidaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ctrlU.confirmarAlta(true, "remp", "remyp", "lhp", "remonp", fechaNac, "desccp", "", "mm", "contra", "img");
		} catch (UsuarioAgregarDatosInvalidosException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioAgregarYaExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ctrlE.altaEspectaculo("spp", "remp", "cinep", "descp", ".com2p", 1000, 500, 5, 60, fechaNac, categoria, "img",0,"","");
		} catch (EspectaculoAgregadoYaExisteExcepcion e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ctrlE.confirmarAltaFuncion("cinep", "señal", fechaNac, hora, artistas, fechaNac, imagen);
		} catch (NombreFuncionRepetidoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EspectaculoNoExistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	@Test
	void testFinalizarEspectaculo() {
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculo99", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat199");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat199");
			ctrlU.confirmarAlta(true, "artistaEspectaculo99", "nom", "ape", "email99", date, "descripcion", "biografia",
					"url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculo99", "artistaEspectaculo99", "lalalelel2", "desc", "url", 0, 10,
					0, 30, date, categoria, "img",0,"","");
			ctrlE.aceptarEspectaculo("lalalelel2");
			ctrlE.finalizarEspectaculo("lalalelel2","email99",date);

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testEspectaculosParaPaquete() {
		ManejadorEspectaculo mEspectaculo = ManejadorEspectaculo.getInstance();
		try {
			ctrlE.altaPlataforma("PlataformaEspectaculo999", "test.ok", "PruebaOK");
			Date date = new Date();
			ctrlE.altaCategoria("nombreCat1999");
			Set<String> categoria = new HashSet<String>();
			categoria.add("nombreCat1999");
			ctrlU.confirmarAlta(true, "artistaEspectaculo999", "nom", "ape", "email999", date, "descripcion", "biografia",
					"url", "contra", "img");
			ctrlE.altaEspectaculo("PlataformaEspectaculo999", "artistaEspectaculo999", "lalalelel29", "desc", "url", 0, 10,
					0, 30, date, categoria, "img",0,"","");
			ctrlE.aceptarEspectaculo("lalalelel29");
			ctrlE.altaPaquete(new DtPaquete("PaqueteListando19", "Paquete de prueba19.",date,date, 0.1f, 0));
			Set<DtEspectaculo> setdt = ctrlE.espectaculosParaPaqueteEnPlataforma("PaqueteListando19", "PlataformaEspectaculo999");
			
			Set<DtPaquete> dtsP = ctrlE.getPaquetes();
			ctrlE.agregarEspectaculoAPaquete("lalalelel29", "PaqueteListando19");

		} catch (PlataformaRepetidaException e) {
			fail(e.getMessage());
		} catch (EspectaculoAgregadoYaExisteExcepcion e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarDatosInvalidosException e) {
			fail(e.getMessage());
		} catch (UsuarioAgregarYaExisteException e) {
			fail(e.getMessage());
		} catch (CategoriaRepetidaException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			fail(e1.getMessage());
		} catch (CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaqueteRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoEnPaqueteErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaqueteNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
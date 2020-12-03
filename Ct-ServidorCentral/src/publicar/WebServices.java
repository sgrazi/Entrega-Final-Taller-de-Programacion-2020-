
package publicar;

/**
 * @author efviodo
 *
 */

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import datatypes.DtHora;
import excepciones.CanjeInvalidoException;
import excepciones.EspectaculoNoExistenteException;
import excepciones.EspectadorYaRegistradoException;
import excepciones.NombreFuncionRepetidoException;
import excepciones.TicketsAgotadosParaFuncionException;
import logica.Artista;
import logica.ControladorEspectaculo;
import logica.ControladorUsuario;
import logica.Espectaculo;
import logica.Espectador;
import logica.ManejadorEspectaculo;
import datatypes.ArrayString;
import datatypes.DtPaquete;
import datatypes.DtPlataforma;
import excepciones.PaqueteNoExisteException;
import excepciones.PaqueteRepetidoException;
import datatypes.ColDtsPerfil;
import datatypes.DtColCategorias;
import datatypes.DtColPlataformas;
import datatypes.DtUsuario;
import datatypes.ListString;
import datatypes.SetString;
import excepciones.EspectaculoAgregadoYaExisteExcepcion;
import excepciones.EspectaculoEnPaqueteErrorException;
import excepciones.PlataformaNoExisteException;
import excepciones.PuntajeInvalidoException;
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.UsuarioNoEsEspectadorException;
import excepciones.UsuarioNoExisteException;
import logica.Compra;
import logica.Configuracion;
import logica.Fabrica;
import logica.Funcion;
import logica.IControladorEspectaculo;
import logica.IControladorUsuario;
import logica.ManejadorCategoria;
import logica.ManejadorPlataforma;
import logica.ManejadorUsuario;
import logica.Paquete;
import logica.Premio;
import logica.Usuario;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class WebServices {

	private Endpoint endpoint = null;
	// CONTROLADORES
	private IControladorEspectaculo iE; //NOPMD
	private IControladorUsuario iU; //NOPMD

	// Constructor
	public WebServices() {
		// TOMO CONTROLADORES DE LA FABRICA
		Fabrica fabrica = Fabrica.getInstance();
		iE = fabrica.getIControladorEspectaculo();
		iU = fabrica.getIControladorUsuario();
	}

	// Operaciones las cuales quiero publicar

	@WebMethod(exclude = true)
	public void publicar(Configuracion config) {
		//endpoint = Endpoint.publish("http://localhost:9129/webservices", this);
		
		String url = config.getWs();
		
		endpoint = Endpoint.publish(url, this);
	
	}
	
	 @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
        
    @WebMethod
    public ArrayString getEspectaculosAceptadosArtista(String nickname) {
    	ControladorUsuario cUsr = new ControladorUsuario();
    	String[] aceptados = cUsr.obtenerEspectaculosAceptadosArtista(nickname);
    	ArrayString resultado = new ArrayString(aceptados.length);
    	resultado.setStrings(aceptados);
    	return resultado;
    }
    
    @WebMethod
    public SetString getArtistasInvitadosMenos(String nick) {
    	ControladorUsuario cUsr = new ControladorUsuario();
    	Set<String> artistas = cUsr.obtenerArtistas();
 		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
 		Artista art = manejU.getArtista(nick);
 		artistas.remove(art.getCorreoElectronico());
 		SetString resultado = new SetString();
    	resultado.setStrings(artistas);
    	return resultado;
    }

    
    @WebMethod
    public DtFuncion getDtFuncion(String nombreFuncion) throws EspectaculoNoExistenteException {
    	DtFuncion data = iE.obtenerDatosFuncion(nombreFuncion);
    	
    	return data;
    }
    
    @WebMethod
    public DtEspectaculo getDtEspectaculo(String nombreEsp) {
    	ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
    	return manejE.getDtEspectaculo(nombreEsp);
    }
    
    @WebMethod
    public ListString getArtistasFuncion(String nombreFuncion) {
    	ControladorEspectaculo cEsp = new ControladorEspectaculo();
    	List<String> artistasInv = cEsp.obtenerArtistasFuncion(nombreFuncion);
    	ListString resultado = new ListString();
    	resultado.setStrings(artistasInv);
    	return resultado;
    }
    
    @WebMethod
    public SetString getRegistrosNoCanjeadosUsuario(String nickname) {
    	ControladorUsuario cUsr = new ControladorUsuario();
    	Set<String> registros = cUsr.getRegistrosNoCanjeadosUsuario(nickname);
    	SetString resultado = new SetString();
    	resultado.setStrings(registros);
    	return resultado;
    }
    
    @WebMethod
    public DtUsuario getDtUsuarioPorNickName(String nickname) {
    	ManejadorUsuario manejU = ManejadorUsuario.getInstance();
    	return manejU.obtenerDtUsuarioPorNickName(nickname);    	
    }
    
    @WebMethod
    public void confirmarAltaFuncion(String esp, String NombreFun, String Fecha, DtHora Hora, SetString artSet, String ImagenFuncion) throws NombreFuncionRepetidoException, EspectaculoNoExistenteException {
    	Fabrica fab = Fabrica.getInstance();
    	IControladorEspectaculo cEsp = fab.getIControladorEspectaculo();
    	Set<String> artistas = artSet.getStrings();
    	
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
	    	Date fechaFuncion;
			fechaFuncion = format.parse(Fecha);
			Date FechaActual = java.util.Calendar.getInstance().getTime();
	    	cEsp.confirmarAltaFuncion(esp, NombreFun, fechaFuncion, Hora, artistas, FechaActual, ImagenFuncion);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @WebMethod
    public void confirmarAltaRegistroAFuncion(String nombreEspectaculo, String nickEspectador, String nombreFuncion, boolean quiereCanjear, String reg1, String reg2, String reg3)
			throws EspectadorYaRegistradoException, TicketsAgotadosParaFuncionException, CanjeInvalidoException,
			EspectaculoNoExistenteException {
    	ControladorEspectaculo cEsp = new ControladorEspectaculo();
		Date FechaActual = java.util.Calendar.getInstance().getTime();
		cEsp.confirmarAltaRegistroAFuncion(nombreEspectaculo, nickEspectador, nombreFuncion, FechaActual , quiereCanjear, reg1, reg2, reg3 );
		
    	
    }
    
    @WebMethod
    public ListString sortearPremios(DtFuncion fun) {
    	ControladorEspectaculo cEsp = new ControladorEspectaculo();
    	ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
    	Funcion funcion = manejE.getFuncion(fun.getNombre());
    	ListString ganadores = new ListString();
    	ganadores.setStrings(cEsp.sortearPremios(funcion));
    	return ganadores;
    }
   

	@WebMethod
	public void altaEspectaculo(DtEspectaculo dtEsp)
			throws EspectaculoAgregadoYaExisteExcepcion, UsuarioNoExisteException, PlataformaNoExisteException {

		iE.altaEspectaculo(dtEsp);

	}

	@WebMethod
	public DtUsuario login(String usuario, String password) throws UsuarioNoExisteException {

		DtUsuario dtUsu = null;
		try {

			iU.inicioDeSesion(usuario, password);

			ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
			Usuario usu = manUsu.obtenerUsuario(usuario);

			if (usu == null) {
				// NO ES UN EMAIL VALIDO => TRATO DE BUSCAR POR EL NICK E INTENTO DE NUEVO

				usu = manUsu.obtenerUsuarioPorNickName(usuario);
				if (usu == null) {
					throw new UsuarioNoExisteException("No se encontro el usuario");
				}
			}

			if (usu instanceof Artista) {
				dtUsu = ((Artista) usu).getDt();
			} else {
				dtUsu = ((Espectador) usu).getDt();
			}

		} catch (UsuarioNoExisteException e) {
			throw new UsuarioNoExisteException(e.getMessage());
		}

		return dtUsu;

	}

	@WebMethod
	public DtColPlataformas obtenerPlataformas() {
		ManejadorPlataforma manPla = ManejadorPlataforma.getInstance();
		// String[] plataformas =
		// manPla.getNombresPlataformas().stream().toArray(String[]::new);

		Set<String> lista = manPla.getNombresPlataformas();

		DtColPlataformas plataformas = new DtColPlataformas();
		plataformas.setPlataformas(lista);

		return plataformas;
	}

	@WebMethod
	public DtColCategorias obtenerCategorias() {
		ManejadorCategoria manCat = ManejadorCategoria.getInstance();
		// String[] categorias = manCat.getCategorias().stream().toArray(String[]::new);

		Set<String> lista = manCat.getCategorias();

		DtColCategorias categorias = new DtColCategorias();
		categorias.setCategorias(lista);

		return categorias;
	}

	public void seguirAUnUsuarioWS(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteException {

		IControladorUsuario contrU = Fabrica.getInstance().getIControladorUsuario();
		contrU.seguirAUnUsuario(nickSeguidor, nickSeguido);

	}

	@WebMethod
	public void dejarDeSeguirAUnUsuarioWS(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteException {

		IControladorUsuario contrU = Fabrica.getInstance().getIControladorUsuario();
		contrU.dejarDeSeguirAUnUsuario(nickSeguidor, nickSeguido);
	}

	@WebMethod
	public void confirmarAltaWS(boolean esArtista, String nick, String nom, String ape, String email, Date fecNac,
			String descripcion, String biografia, String url, String contrasena, String img)
			throws UsuarioAgregarDatosInvalidosException, UsuarioAgregarYaExisteException, ParseException {

		// para probar soap.ui, pero en realidad el servlet es el que va a mandar el
		// request al soap y lo va a mandar con el Date
		// Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fecNac);

		IControladorUsuario contrU = Fabrica.getInstance().getIControladorUsuario();
		contrU.confirmarAlta(esArtista, nick, nom, ape, email, fecNac, descripcion, biografia, url, contrasena, img);
    }
    
    
    @WebMethod
    public DtUsuario obtenerUsuarioPorNickNameWS(String nickUsuario) throws UsuarioNoExisteException {
    	
    	ManejadorUsuario manUsuario = ManejadorUsuario.getInstance();
    	Usuario usuario = manUsuario.obtenerUsuarioPorNickName(nickUsuario);
    	
    	if (usuario == null) throw new UsuarioNoExisteException("El usuario " + nickUsuario + " no existe");
    	
    	if (usuario instanceof Artista) {
    		return ((Artista) usuario).getDt();
    	} else {
    		return ((Espectador) usuario).getDt();
    	}
    }
    
    @WebMethod
    public void setearDatosBasicosModificadosWS(String nick, String nombre, String apellido, Date fecha, String imagen) {
    	ManejadorUsuario manUsuario = ManejadorUsuario.getInstance();
    	Usuario usuario = manUsuario.obtenerUsuarioPorNickName(nick);
    	
    	usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setfechaDeNacimiento(fecha);
		usuario.setImagen(imagen);

	}

	@WebMethod
	public void setearDatosArtistaModificadosWS(String nick, String descripcion, String bio, String url)
			throws UsuarioNoExisteException {
		ManejadorUsuario manUsuario = ManejadorUsuario.getInstance();
		Usuario usuario = manUsuario.obtenerUsuarioPorNickName(nick);

		if (!(usuario instanceof Artista))
			throw new UsuarioNoExisteException("El usuario no es un artista");

		((Artista) usuario).setDescripcion(descripcion);
		((Artista) usuario).setBiografia(bio);
		((Artista) usuario).setSitioWeb(url);
    	
    }
    
    @WebMethod
    public void setearContrasenaNuevaWS(String nick, String passNueva) {
    	ManejadorUsuario manUsuario = ManejadorUsuario.getInstance();
    	Usuario usuario = manUsuario.obtenerUsuarioPorNickName(nick);
    	
    	usuario.setContrasena(passNueva);
    	
    }
    
    @WebMethod
    public ColDtsPerfil obtenerColsPerfilWS(String nickUsuario) {
    	ManejadorUsuario manUsuario = ManejadorUsuario.getInstance();
    	Usuario usuario = manUsuario.obtenerUsuarioPorNickName(nickUsuario);
    	
    	Map<String, Espectaculo> espectaculos = null;
    	Funcion[] funciones = null;
    	Set<Paquete> paquetes = null;
    	Set<Espectaculo> favoritos = null;
    	Map<String, Premio> premiosAsignados = null;
    	
    	if (usuario instanceof Artista) {									
			
    		espectaculos = ((Artista) usuario).getEspectaculos();
    		
		} else {

			funciones = ((Espectador) usuario).obtenerFunciones();
    		
			paquetes = new HashSet<Paquete>();
    		Set<Compra> compras = ((Espectador) usuario).getCompras();
    		for(Compra c: compras) {
    			paquetes.add(c.getPaquete());
    		}
    		
    		favoritos = ((Espectador) usuario).getFavoritos();
    		premiosAsignados = ((Espectador) usuario).getPremios();
    		/*for(Map.Entry<String, Premio> p: premiosAsignados.entrySet()) {
    			System.out.println(p.getValue().getNombreEspectaculo());
    		}*/
    		
		}
    	
    	ColDtsPerfil col = new ColDtsPerfil(usuario.getSeguidores(), usuario.getSeguidos(), espectaculos, funciones, paquetes, favoritos, premiosAsignados);
    	
    	return col;
    }
    
    @WebMethod
    public DtEspectaculo obtenerEspectaculo(String nom) throws EspectaculoNoExistenteException {
    	return iE.getEspectaculoDt(nom);
    }
     
	@WebMethod
	public String getAlgo() {
		return "hola";
	}

	@WebMethod
	public DtPaquete obtenerPaquete(String nombre) throws PaqueteNoExisteException {
		IControladorEspectaculo ctrlE = Fabrica.getInstance().getIControladorEspectaculo();
		DtPaquete paquete = ctrlE.obtenerPaquete(nombre);

		return paquete;
	}

	@WebMethod
	public ColDtPaquete obtenerPaquetes() {
		IControladorEspectaculo ctrlE = Fabrica.getInstance().getIControladorEspectaculo();
		Set<DtPaquete> paquetes = ctrlE.getPaquetes();
		ColDtPaquete result = new ColDtPaquete(paquetes);
		return result;
	}

	@WebMethod
	public String[] listarPaquetes() throws PaqueteNoExisteException {
		IControladorEspectaculo ctrlE = Fabrica.getInstance().getIControladorEspectaculo();
		Set<String> paquetes = ctrlE.listarPaquetes();
		return (String[]) paquetes.toArray(new String[paquetes.size()]);
	}

	@WebMethod
	public void altaPaquete(DtPaquete data) throws PaqueteRepetidoException {
		IControladorEspectaculo ctrlE = Fabrica.getInstance().getIControladorEspectaculo();
		ctrlE.altaPaquete(data);
	}

	@WebMethod
	public void comprarPaquete(String nickEspectador, String nombrePaquete) {
		IControladorUsuario ctrlU = Fabrica.getInstance().getIControladorUsuario();
		ctrlU.comprarPaquete(nickEspectador, nombrePaquete);
	}

	@WebMethod
	public String[] listarPlataformas() {
		IControladorEspectaculo ctrlE = Fabrica.getInstance().getIControladorEspectaculo();
		Set<String> plataformas = ctrlE.listarPlataformasSet();
		return (String[]) plataformas.toArray(new String[plataformas.size()]);
	}
		
	@WebMethod
	public ColDtEspectaculos obtenerEspectaculos() {
		Set<DtEspectaculo> espectaculos = iE.obtenerEspectaculosAceptadosDt();
		
		ColDtEspectaculos result = new ColDtEspectaculos(espectaculos);
		
		return result;
	}
	
	@WebMethod
	public DtPlataforma obtenerPlataforma(String nombre) {
		DtPlataforma plat = iE.obtenerPlataformaDt(nombre);
		//System.out.println(plat.getNombre());
		return plat;
	}
	
	@WebMethod
	public ColDtEspectaculos obtenerEspectaculosPorCategoria(String cat) {
		Set<DtEspectaculo> espectaculos = iE.obtenerEspectaculosAceptadosPorCategoria(cat);
		
		ColDtEspectaculos result = new ColDtEspectaculos(espectaculos);
		
		return result;
	}
	
	@WebMethod
	public ColDtUsuarios obtenerUsuarios() throws UsuarioNoExisteException {
		DtUsuario[] users = iU.obtenerUsuarios();
		
		ColDtUsuarios result = new ColDtUsuarios(users);
		
		return result;
	}
	
	@WebMethod
	public ColDtEspectaculos obtenerEspectaculosBusqueda(String clave) {
		Set<DtEspectaculo> espectaculos = iE.obtenerEspectaculosAceptadosDt();
		Set<DtEspectaculo> resE = new HashSet<DtEspectaculo>();
		// Buscar Espectaculos
		for (DtEspectaculo esp : espectaculos) {
			String nomE = esp.getNombre().toLowerCase();
			String descE = esp.getDescripcion().toLowerCase();

			if (nomE.contains(clave.toLowerCase()) || descE.contains(clave.toLowerCase())) {
				resE.add(esp);
			}
		}
		
		ColDtEspectaculos result = new ColDtEspectaculos(resE);
		
		return result;
	}
	
	@WebMethod
	public ColDtFuncion getFunciones() {
		Set<DtFuncion> funciones = iE.getDtFunciones();		
		ColDtFuncion result = new ColDtFuncion(funciones);
		
		return result;
	}
	
	@WebMethod
	public ColDtPaquete obtenerPaquetesBusqueda(String clave) {
		Set<DtPaquete> paquetes = iE.getPaquetes();
		Set<DtPaquete> resP = new HashSet<DtPaquete>();
		// Buscar Espectaculos
		for (DtPaquete esp : paquetes) {
			String nomP = esp.getNombre().toLowerCase();
			String descP = esp.getDescripcion().toLowerCase();

			if (nomP.contains(clave.toLowerCase()) || descP.contains(clave.toLowerCase())) {
				resP.add(esp);
			}
		}
		
		ColDtPaquete result = new ColDtPaquete(resP);
		
		return result;
	}
	
	@WebMethod
	public boolean espectaculoEsFavoritoWS(String nickname, String nombreEspectaculo) throws EspectaculoNoExistenteException, UsuarioNoEsEspectadorException {
		
		ManejadorUsuario manU = ManejadorUsuario.getInstance();
		Usuario usuario = manU.obtenerUsuarioPorNickName(nickname);
		
		ManejadorEspectaculo manE = ManejadorEspectaculo.getInstance();
		Espectaculo espectac = manE.getEspectaculo(nombreEspectaculo);
		
		if (espectac == null) throw new EspectaculoNoExistenteException("No existe el espectaculo: "+ nombreEspectaculo);
		
		
		if (usuario instanceof Espectador) {
			
			Espectador esp = (Espectador) usuario;
			return esp.esFavorito(espectac);
		} else throw new UsuarioNoEsEspectadorException("El usuario no es un espectador");
	}
	
	
	@WebMethod
	public void marcarComoFavorito(String nick, String esp) throws UsuarioNoExisteException, UsuarioNoEsEspectadorException, EspectaculoNoExistenteException {
		Fabrica fab = Fabrica.getInstance();
		IControladorUsuario contrU = fab.getIControladorUsuario();
		contrU.marcarEspectaculoComoFavorito(nick, esp);
	}
	
	@WebMethod
	public void desmarcarComoFavorito(String nick, String esp) throws UsuarioNoExisteException, UsuarioNoEsEspectadorException, EspectaculoNoExistenteException {
		Fabrica fab = Fabrica.getInstance();
		IControladorUsuario contrU = fab.getIControladorUsuario();
		contrU.desmarcarEspectaculoComoFavorito(nick, esp);
	}
	


	@WebMethod
	public ColDtEspectaculos espectaculosParaPaqueteEnPlataforma(String paquete, String plataforma) throws EspectaculoNoExistenteException {
		Set<DtEspectaculo> espectaculos = iE.espectaculosParaPaqueteEnPlataforma(paquete, plataforma);
		return new ColDtEspectaculos(espectaculos);
	}

	@WebMethod
	public void agregarEspectaculoAPaquete(String espectaculo, String paquete)
			throws EspectaculoEnPaqueteErrorException, EspectaculoNoExistenteException, PaqueteNoExisteException {
		iE.agregarEspectaculoAPaquete(espectaculo, paquete);
	}
	
	@WebMethod
	public void valorarEspectaculo(String nickEspectador, String espectaculo, int puntaje)
			throws PuntajeInvalidoException, UsuarioNoExisteException, EspectaculoNoExistenteException {
		iU.valorarEspectaculo(nickEspectador, espectaculo, puntaje);
	}

	@WebMethod
	public void finalizarEspectaculo(String espe, String art) throws EspectaculoNoExistenteException, UsuarioNoExisteException {
		Date fecha = new Date();
		
		iE.finalizarEspectaculo(espe, art, fecha);
	}
	
	@WebMethod
	public boolean accederRegistrosAdmin(String linkId) {
		return iU.accederRegistrosAdmin(linkId);
	}
}

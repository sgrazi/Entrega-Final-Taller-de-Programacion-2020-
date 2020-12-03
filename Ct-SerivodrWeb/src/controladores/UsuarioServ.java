package controladores;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import publicar.DtPremio;
import modelo.Imagen;
import publicar.ColDtsPerfil;
import publicar.DtArtista;
import publicar.DtEspectaculo;
import publicar.DtUsuario;
import publicar.EspectaculoNoExistenteException_Exception;
import publicar.ParseException_Exception;
import publicar.PuntajeInvalidoException_Exception;
import publicar.UsuarioAgregarDatosInvalidosException_Exception;
import publicar.UsuarioAgregarYaExisteException_Exception;
import publicar.UsuarioNoEsEspectadorException_Exception;
import publicar.UsuarioNoExisteException_Exception;
import publicar.WebServices;
import publicar.WebServicesService;

/**
 * Servlet implementation class UsuarioServ
 */
@WebServlet("/UsuarioServ")
@MultipartConfig
public class UsuarioServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
	public static final DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	private XMLGregorianCalendar procesarfecha(Date fecha) {
		GregorianCalendar gcfecha = new GregorianCalendar();
		gcfecha.setTime(fecha);
		return new XMLGregorianCalendarImpl(gcfecha);
	}

	private void procesarValorar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String espectaculo = req.getParameter("espectaculo");
		String espectador = req.getParameter("nickEspectador");
		int puntaje = Integer.parseInt("0" + req.getParameter("puntaje"));

		try {
			port.valorarEspectaculo(espectador, espectaculo, puntaje);
			resp.sendRedirect(req.getContextPath() + "/espectaculo?value=" + espectaculo);

		} catch (EspectaculoNoExistenteException_Exception | PuntajeInvalidoException_Exception
				| UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.sendRedirect(req.getContextPath() + "/home");
		}
	}

	private void procesarFormularioAltaUsuario(HttpServletRequest req, HttpServletResponse resp)
			throws UsuarioAgregarDatosInvalidosException_Exception, UsuarioAgregarYaExisteException_Exception,
			ParseException, IOException, ServletException {

		// Fabrica f = Fabrica.getInstance();
		// IControladorUsuario cu = f.getIControladorUsuario();
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String nickAlta = req.getParameter("nicknameAlta");
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String email = req.getParameter("email");
		String fn = req.getParameter("fecha");
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fn);
		String descripcion = req.getParameter("descripcion");
		String bio = req.getParameter("biografia");
		String url = req.getParameter("url");

		String contrasena = req.getParameter("contrasena");
		String confirmarContrasena = req.getParameter("confirmarContrasena");

		String imagen = "";

		Imagen img = new Imagen();

		// req.setAttribute("errores", "No se pudo generar la imagen en el servidor.");

		if (!contrasena.equals(confirmarContrasena)) {
			req.setAttribute("errores", "La contraseña no fue confirmada correctamente");
			req.getRequestDispatcher("/WEB-INF/usuario/altaUsuario.jsp").forward(req, resp);
		} else {

			try {

				img.cargarDesdeRequestInput(req, "imagen");
				img.saveToFile();

				imagen = img.getLink();

				if (req.getParameter("tipo-usuario").equals("artista")) {
					// cu.confirmarAlta(true, nickAlta, nombre, apellido, email, fecha, descripcion,
					// bio, url, contrasena, imagen);

					port.confirmarAltaWS(true, nickAlta, nombre, apellido, email, procesarfecha(fecha), descripcion,
							bio, url, contrasena, imagen);
				} else {
					// cu.confirmarAlta(false, nickAlta, nombre, apellido, email, fecha, "", "", "",
					// contrasena, imagen);
					port.confirmarAltaWS(false, nickAlta, nombre, apellido, email, procesarfecha(fecha), "", "", "",
							contrasena, imagen);
				}

				resp.sendRedirect("./login");
			} catch (UsuarioAgregarYaExisteException_Exception e) {
				// TODO: handle exception
				req.setAttribute("errores", "El usuario ya existe");

				req.getRequestDispatcher("/WEB-INF/usuario/altaUsuario.jsp").forward(req, resp);

			} catch (ParseException_Exception | UsuarioAgregarDatosInvalidosException_Exception ex) {
				req.setAttribute("errores", "Datos invalidos");

				req.getRequestDispatcher("/WEB-INF/usuario/altaUsuario.jsp").forward(req, resp);
			} catch (IOException es) {
				req.setAttribute("errores", "No se pudo generar la imagen en el servidor.");

				req.getRequestDispatcher("/WEB-INF/usuario/altaUsuario.jsp").forward(req, resp);
			}
			
			
		
		}
    }
    

	// ALTA PRONTO

	 private void procesarFormularioModificar(HttpServletRequest req, HttpServletResponse resp) throws UsuarioNoExisteException_Exception, ParseException, ServletException, IOException {
    	
    	WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
    	
    	String nomModif = req.getParameter("nombreModificar");
    	
    	
    	
    	//Usuario usuario = Login.getUsuarioLogueadoU(req);
		DtUsuario dtusuario = Login.getUsuarioLogueado(req);
		
		String passAnt = req.getParameter("contrasenaAnterior");
		String passNueva1 = req.getParameter("nuevaContrasena1");
		String passNueva2 = req.getParameter("nuevaContrasena2");
		
		//boolean cambioCorrecto = passAnt.equals(usuario.getContrasena()) && !passNueva1.equals("") && passNueva1.equals(passNueva2);
		boolean cambioCorrecto = passAnt.equals(dtusuario.getContrasena()) && !passNueva1.equals("") && passNueva1.equals(passNueva2);
		
		if (req.getParameter("c1") != null && req.getParameter("c1").equals("on") && !cambioCorrecto) {
			
				req.setAttribute("errores", "Cambio de contraseña incorrecto");
				req.getRequestDispatcher("/WEB-INF/usuario/modificarDatos.jsp").
				forward(req, resp);
			} else {
		
		String apellido = req.getParameter("apellido");
		String fn = req.getParameter("fecha");
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fn);
		String imagen = req.getParameter("imagen");
		/*
		usuario.setNombre(nomModif);
		usuario.setApellido(apellido);
		usuario.setfechaDeNacimiento(fecha);
		usuario.setImagen(imagen);
		*/
		port.setearDatosBasicosModificadosWS(dtusuario.getNickname(), nomModif, apellido, procesarfecha(fecha), imagen);
		
		if (req.getParameter("c1") != null && req.getParameter("c1").equals("on") && cambioCorrecto) {
			//usuario.setContrasena(passNueva1);
			port.setearContrasenaNuevaWS(dtusuario.getNickname(), passNueva1);
		}
		
		if (dtusuario instanceof DtArtista) {									//usuario instanceof Artista
			String descripcion = req.getParameter("descripcion");
    		String bio = req.getParameter("biografia");
    		String url = req.getParameter("url");
    		 /*
    		((Artista) usuario).setDescripcion(descripcion);
    		((Artista) usuario).setBiografia(bio);
    		((Artista) usuario).setSitioWeb(url);
			*/
    		try {
				port.setearDatosArtistaModificadosWS(dtusuario.getNickname(), descripcion, bio, url);
			} catch (UsuarioNoExisteException_Exception e) {
				req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
				forward(req, resp);
			}
		}
		
		resp.sendRedirect("./perfilUsuario?nickPerfil="+ dtusuario.getNickname()); //lo mismo pero con usuario.getNickName()
		}
    }

	private void procesarVisitarPerfil(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
    	String nickPerfil = req.getParameter("nickPerfil");
    	
    	   //------- WS --------
		 DtUsuario dtusr;
		try {
			dtusr = port.obtenerUsuarioPorNickNameWS(nickPerfil);
		
		 req.setAttribute("usuarioPerfil", dtusr);
		 
		 DtUsuario dtulogeado;

		dtulogeado = Login.getUsuarioLogueado(req);
		
		 req.setAttribute("usuarioLogeado", dtulogeado);
		 if (dtulogeado != null) {
			 ColDtsPerfil colsLog = port.obtenerColsPerfilWS(dtulogeado.getNickname());
			 req.setAttribute("seguidosLogeado", colsLog.getDtSeguidos());
		 }
		 
		 
		 ColDtsPerfil cols = port.obtenerColsPerfilWS(nickPerfil);
		  
		  req.setAttribute("seguidores", cols.getDtSeguidores());
		 req.setAttribute("seguidos", cols.getDtSeguidos());
		  
		 if (dtusr instanceof DtArtista) {
		 req.setAttribute("espectaculos", cols.getDtEspectaculos());
		 
		 List<DtEspectaculo> colOpt = new ArrayList<DtEspectaculo>();
		 for (DtEspectaculo dtesp: cols.getDtEspectaculos()) {
			 if (dtesp.getEstado().equals("ACEPTADO")) {
				 colOpt.add(dtesp);
			 }
		 }
		 
		 req.setAttribute("espectaculosAceptados", colOpt);
		  
		 } else {
			 
		 	 
		 ArrayList<DtPremio> colP = filtradoFechaP(cols.getPremios());	 
			 
		 req.setAttribute("premios", colP);
		  
		 req.setAttribute("funciones", cols.getDtFunciones());
		 
		 req.setAttribute("paquetes", cols.getDtPaquetes());
		 
		 req.setAttribute("favoritos", cols.getFavoritos());
		 
		 }
		
		req.getRequestDispatcher("/WEB-INF/usuario/perfilUsuario.jsp").
				forward(req, resp);
		} catch (UsuarioNoExisteException_Exception e) {
			System.out.println("aca da error porque no existe");
			req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
			forward(req, resp);
		}
    }
	
	public ArrayList<DtPremio> filtradoFechaP(List<DtPremio> colP) {

		ArrayList<DtPremio> aux = new ArrayList<DtPremio>(colP);
		ArrayList<DtPremio> res = new ArrayList<DtPremio>();

		int iter = 0;
		while (!aux.isEmpty()) {
			DtPremio iter2 = aux.get(iter);

			if (aux.size() > 1) {
				for (int i = 0; i < aux.size(); i++) {
					int valor = iter2.getFechaSorteo().compare(aux.get(i).getFechaSorteo());
					if ((valor > 0))
						iter = i;
				}
			}

			res.add(aux.get(iter));
			aux.remove(iter);
			iter = 0;
		}

		return res;
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException, ParseException, UsuarioAgregarDatosInvalidosException_Exception, UsuarioAgregarYaExisteException_Exception, UsuarioNoExisteException_Exception {
		
    	WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
    	
    	//Fabrica f = Fabrica.getInstance();
		//IControladorUsuario cu = f.getIControladorUsuario();
    	String nickAlta = req.getParameter("nicknameAlta");		
    	
    	if (nickAlta != null) {									//con esto s� que el req viene del alta
    		
    		procesarFormularioAltaUsuario(req, resp);
    	}
    	
    	String nomModif = req.getParameter("nombreModificar");	
    	
    	if (nomModif != null) {									//con esto s� que el req viene del modificar
    		
    		procesarFormularioModificar(req, resp);
    	}
    	
    	
    	String nickPerfil = req.getParameter("nickPerfil");
    	
    	if (nickPerfil != null) {									//con esto s� que estoy visitando un perfil
    		
    		procesarVisitarPerfil(req, resp);
    	}
    	
    	//String uri = req.getHeader("referer");
    	String espFav = URLDecoder.decode(req.getParameter("espFav"), "UTF-8");				//con esto s� que estoy en fav
		
    	
    	if (espFav != null) {
    		
    		String nickFav = req.getParameter("usr");
    		
    		try {
				port.marcarComoFavorito(nickFav, espFav);
			} catch (UsuarioNoExisteException_Exception | EspectaculoNoExistenteException_Exception | UsuarioNoEsEspectadorException_Exception e) {
				req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
				forward(req, resp);
			}
    		
    		espFav = espFav.replace("E","&Eacute;");
    		espFav = espFav.replace("%C9","&Eacute;");
    		resp.sendRedirect("./espectaculo?value="+ URLEncoder.encode(espFav, "UTF-8"));
    		//resp.sendRedirect(uri);
    	}
    	
    	String espUnFav = URLDecoder.decode(req.getParameter("espUnFav"), "UTF-8");				//con esto s� que estoy en Unfav
		
    	
    	if (espUnFav != null) {
    		
    		String nickUnFav = req.getParameter("usr");
    		
    		try {
				port.desmarcarComoFavorito(nickUnFav, espUnFav);
			} catch (UsuarioNoExisteException_Exception | EspectaculoNoExistenteException_Exception | UsuarioNoEsEspectadorException_Exception e) {
				req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
				forward(req, resp);
			}
    		espFav = espFav.replace("E","&Eacute;");
    		espFav = espFav.replace("%C9","&Eacute;");
    		resp.sendRedirect("./espectaculo?value=" + espUnFav);
    		//resp.sendRedirect(uri);

    	}
    	
    	
    	String usrFollow = req.getParameter("usrFollow");
    	
    	if (usrFollow != null) {									//con esto s� que estoy en follow
    		
    		String nickSeguidor = req.getParameter("usrlog");
    		
    		try {
				port.seguirAUnUsuarioWS(nickSeguidor, usrFollow);
			} catch (UsuarioNoExisteException_Exception e) {
				req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
				forward(req, resp);
			}
    		
    		//cu.seguirAUnUsuario(req.getParameter("usrlog"), usrFollow);
    		
    		resp.sendRedirect("./perfilUsuario?nickPerfil="+ usrFollow);
    	}
    	
    	String usrUnfollow = req.getParameter("usrUnfollow");
    	
    	if (usrUnfollow != null) {									//con esto s� que estoy en unfollow
    		
    		String seguidor = req.getParameter("usrlog");
    		try {
				port.dejarDeSeguirAUnUsuarioWS(seguidor, usrUnfollow);
			} catch (UsuarioNoExisteException_Exception e) {
				req.getRequestDispatcher("/WEB-INF/errorPages/404.jsp").
				forward(req, resp);
			}
    		 
    		//cu.dejarDeSeguirAUnUsuario(req.getParameter("usrlog"), usrUnfollow);
    		
    		resp.sendRedirect("./perfilUsuario?nickPerfil="+ usrUnfollow);
    	}
    	
    	String action = req.getParameter("action");
    	
    	if ((action != null) && (action.equals("editar"))) {											//con esto se que entro a modificar el perfil
    		req.getRequestDispatcher("/WEB-INF/usuario/modificarDatos.jsp").
			forward(req, resp);
    	}
    	
    	if ((action != null) && (action.equals("registrarse"))) {											//con esto se que entro a alta
    		req.getRequestDispatcher("/WEB-INF/usuario/altaUsuario.jsp").
			forward(req, resp);
    	}

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (path.equals("/valorarEsp"))
			procesarValorar(request, response);
		else
			try {
				processRequest(request, response);
			} catch (ServletException | IOException | ParseException | UsuarioAgregarDatosInvalidosException_Exception
					| UsuarioAgregarYaExisteException_Exception | UsuarioNoExisteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | ParseException | UsuarioAgregarDatosInvalidosException_Exception
				| UsuarioAgregarYaExisteException_Exception | UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

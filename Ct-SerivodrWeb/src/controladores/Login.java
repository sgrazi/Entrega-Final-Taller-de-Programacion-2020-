package controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.EstadoSesion;
import modelo.Sesion;
import publicar.UsuarioNoExisteException_Exception;
import publicar.WebServices;
import publicar.WebServicesService;
import publicar.DtUsuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Devuelve el usuario logueado
	 * @param request
	 * @return
	 * @throws UsuarioNoExisteException 
	 * @throws UsuarioNoEncontrado 
	 */
	static public DtUsuario getUsuarioLogueado(HttpServletRequest request) {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();
		Sesion sesion = new Sesion(request);

		DtUsuario dt = sesion.getUsuarioLogeadoDt();
		if (dt != null)
			dt = port.getDtUsuarioPorNickName(dt.getNickname());

		/*
		ControladorUsuario cu = new ControladorUsuario();
		DtUsuario dt = cu.obtenerUsuario((String) request.getSession().getAttribute("usuario_logueado")); //cuando se setea usuario_logueado???
		*/
		return dt;
	}
	
	//ESTE METODO YA NO PUEDE DEVOLVER EL USUARIO. USEN EL DE ARRIBA
	/*
	static public Usuario getUsuarioLogueadoU(HttpServletRequest request) throws UsuarioNoExisteException { //originalmente tenia throws usuarioNoEncontrado
		Sesion s = new Sesion(request);
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Usuario u =  mu.obtenerUsuarioPorNickName(s.getUsuarioLogeadoDt().getNickName());
		return u;
	}
	*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Sesion sesion = new Sesion(request);
		
		if (sesion.usuarioLogeado()) {
			response.sendRedirect(request.getContextPath() + "/home");
		} else {
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.processRequest(request, response);
		
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// OBTENGO DATOS DE SESION
		
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String recordarme = request.getParameter("recordarme");
		
		iniciarSesion(request,response, usuario,password,recordarme);

	}
	
	static void iniciarSesion(HttpServletRequest request, HttpServletResponse response, String usuario, String password, String recordarme) throws IOException {
		Sesion s = new Sesion(request);
		
		try {
			
			WebServicesService service = new WebServicesService();
			WebServices port = service.getWebServicesPort();
			
			publicar.DtUsuario dtUsu = port.login(usuario, password);
			
			s.setEstado(EstadoSesion.LOGIN_CORRECTO);
			s.setUsuarioLogeado(dtUsu);
			
			if (recordarme != null) {
				Cookie ck = new Cookie("CoronaTicketsUser", usuario + "|" + password);
				ck.setMaxAge(60*60*96); //POR TRES DIAS NO MAS. ES PARA LA DEFENSA DE LA TAREA NO MAS.
				response.addCookie(ck);
			}
			
		} catch (UsuarioNoExisteException_Exception e) {
			s.setEstado(EstadoSesion.LOGIN_INCORRECTO);
			s.setUsuarioLogeado(null);
		}
		
		// REDIRIGIMOS AL HOME PARA QUE SE ENCARGUE DE REDIRIGIR A DONDE CORRESPONDE
		response.sendRedirect(request.getContextPath() + "/home");
	}
}

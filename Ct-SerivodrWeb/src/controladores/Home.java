package controladores;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.WebServices;
import publicar.WebServicesService;
import publicar.DtEspectaculo;
import publicar.DtEspectador;
import publicar.DtPaquete;
import publicar.DtUsuario;
import modelo.*;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private WebServicesService service;
	private WebServices port;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
        
        this.service = new WebServicesService();
    	this.port = service.getWebServicesPort();
    }
    
    private String getPath(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length());
	}
    	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = getPath(request);
		
		if (path.equals("/consultaRegistros")) {
			String linkId = request.getParameter("linkId");

			if (linkId == null || linkId.isEmpty()) {
				
				response.sendError(404);
			} else if (this.port.accederRegistrosAdmin(linkId)) {
				Queue<String[]> registros = Filtro.cola;
				request.setAttribute("registros", registros);
				request.getRequestDispatcher("/WEB-INF/home/consultaRegistros.jsp").forward(request, response);
			} else  {
				response.sendError(404);
			}

		} else {
			this.processRequest(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Sesion s = new Sesion(request);
		
		if (s.getEstado() == EstadoSesion.LOGIN_INCORRECTO) {
			request.setAttribute("errores", "Usuario y/o contraseña incorrectos");
			
			//response.sendRedirect(request.getContextPath() + "/login");
			// REDIRIGIMOS AL HOME PARA QUE SE ENCARGUE DE REDIRIGIR A DONDE CORRESPONDE
			request.getRequestDispatcher("/WEB-INF/login/login.jsp").forward(request, response);		
			
		} else {
			
			if ( (!s.usuarioLogeado()) && s.hayCookie()) {
				//SI HAY COOKIE CON USER Y PASS LO TIRAMOS A LOGEAR DE NUEVO
				Login.iniciarSesion(request, response, s.getCookieUser(), s.getCookiePassword(), "SI");
				
			} else {
				
				WebServicesService service = new WebServicesService();
				WebServices port = service.getWebServicesPort();
	
				List<DtEspectaculo> espectaculos = port.obtenerEspectaculos().getEspectaculos();
				request.setAttribute("espectaculos", espectaculos);
				
				List<DtPaquete> paquetes = port.obtenerPaquetes().getPaquetes();
				request.setAttribute("paquetes", paquetes);
				
				DtUsuario usuario = Login.getUsuarioLogueado(request);
				request.setAttribute("usuarioLog", usuario);
				
				boolean esEspectador = (usuario instanceof DtEspectador);
				request.setAttribute("esEspectador", esEspectador);
				if(esEspectador) {
				
					List<DtEspectaculo> colFav = port.obtenerColsPerfilWS(usuario.getNickname()).getFavoritos();
					Set<String> favoritosNombres = new HashSet<String>();
					
					for (DtEspectaculo esp : colFav) {
						favoritosNombres.add(esp.getNombre());
					}
				
					request.setAttribute("favNombres", favoritosNombres);
				}
				
				
				request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
			}
			
		}
		
	}
	
}

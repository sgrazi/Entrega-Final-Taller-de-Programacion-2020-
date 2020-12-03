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

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Sesion s = new Sesion(request);
		
		s.setEstado(EstadoSesion.INVITADO);
		s.setUsuarioLogeado(null);
		
		//DESTRUYO COOKIE
		Cookie ck = new Cookie("CoronaTicketsUser","");//deleting value of cookie  
		ck.setMaxAge(0);//changing the maximum age to 0 seconds  
		response.addCookie(ck);//adding cookie in the response  
		
		// REDIRIGIMOS AL HOME PARA QUE SE ENCARGUE DE REDIRIGIR A DONDE CORRESPONDE
		response.sendRedirect(request.getContextPath() + "/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

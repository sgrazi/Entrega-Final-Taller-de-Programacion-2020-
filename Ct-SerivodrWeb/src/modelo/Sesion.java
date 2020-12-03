package modelo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicar.DtUsuario;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class Session
 */
@WebServlet("/Session")
public class Sesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private HttpServletRequest request;
	private Cookie cookie;
	private String cookieUser;
	private String cookiePass;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sesion(HttpServletRequest req) {
        super();
        
        this.request = req;
        
        HttpSession session = this.request.getSession();
        
        if (session.getAttribute("estado_sesion") == null) {
			session.setAttribute("estado_sesion", EstadoSesion.INVITADO);
			session.setAttribute("usuario_logueado", null);
			session.setAttribute("esActor", false);
		}
        
        this.setCookie("CoronaTicketsUser");
    }
    
    private void setCookie(String name) {
    	//VERIFICO COOKIE
    	this.cookie = null;
    	this.cookieUser = "";
    	this.cookiePass = "";
    	
    	Cookie ck[] = this.request.getCookies();
		
    	if (ck != null) {
			//BUSCO MI COOKIE
			for(int i=0;i < ck.length; i++){  
				  if (ck[i].getName().equals(name)) {
					  this.cookie = ck[i];
				  }
			}
		}
		
		if (this.cookie != null) {
			String dato = this.cookie.getValue();
			String[] datos = dato.split("\\|"); //0 = usuario; 1 = password
			
			this.cookieUser = datos[0];
			this.cookiePass = datos[1];
		}
		
    }
    
    public String getCookieUser() {
    	return this.cookieUser;
    }
    
    public String getCookiePassword() {
    	return this.cookiePass;
    }
    
    public boolean hayCookie() {
    	return !(this.cookieUser.isEmpty() && this.getCookiePassword().isEmpty());
    }
    
	public EstadoSesion getEstado() {
		HttpSession session = this.request.getSession();
		
		return (EstadoSesion)session.getAttribute("estado_sesion");
	}
	
	public void setEstado(EstadoSesion estado) {
		HttpSession session = this.request.getSession();
		
		session.setAttribute("estado_sesion", estado);
	}
	
	public void setUsuarioLogeado(DtUsuario usuario) {
		HttpSession session = this.request.getSession();
		
		session.setAttribute("usuario_logueado", usuario);
	}
	
	public DtUsuario getUsuarioLogeadoDt() {
		HttpSession session = this.request.getSession();
		
		return (DtUsuario)session.getAttribute("usuario_logueado");
	}
		
	public void redireccionarLogin(HttpServletResponse response) throws IOException, ServletException {
		
		switch(this.getEstado()){
		
			case LOGIN_INCORRECTO:
				this.request.getRequestDispatcher("/login.jsp").forward(this.request, response);
				break;
		}
		
	}
	
	public boolean usuarioLogeado() {
		return (this.getEstado() == EstadoSesion.LOGIN_CORRECTO);
		
	}

	
}

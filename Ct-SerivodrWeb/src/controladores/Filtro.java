package controladores;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Filtro
 */
@WebFilter(
		filterName = "/Filtro", 
		servletNames = { "Espectaculo", "Paquete", "Home", "Funcion", "ListarPorPlataformaCategoria", "UsuarioServ" },
		urlPatterns = { "/altaUsuario", "/login", "/" }
		)
public class Filtro implements Filter {
	public static final int MAX_ENTRADAS = 10000;
	public static Queue<String[]> cola;
	
	private final Set<String> rutasMovil = new HashSet<>(Arrays.asList(
			"/",
			"/home",
			"/buscar",
			"/espectaculo",
			"/fav", 
			"/unfav", 
			"/valorarEsp",
			"/consultaFuncion",
			"/listarFunciones",
			"/login"
		));
	
	private final Map<String,String> agentOS = new HashMap<String,String>();
	private final Map<String,String> agentBrowser = new HashMap<String,String>();

	/**
	 * Default constructor.
	 */
	public Filtro() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		boolean esMovil = httpRequest.getHeader("user-agent").toLowerCase().contains("mobile");

		if (esMovil) {
			String ruta = httpRequest.getServletPath();
			if (rutasMovil.contains(ruta)) {
				if (httpRequest != null)
					registrarAcceso(httpRequest);
				chain.doFilter(request, response);
			} else
				httpResponse.sendError(404);
		} else {
			if (httpRequest != null)
				registrarAcceso(httpRequest);
			chain.doFilter(request, response);
		}

	}

	private void registrarAcceso(HttpServletRequest request) {
		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (!path.equals("/consultaRegistros"))
			try {
				String met = request.getMethod().toLowerCase();

				if (met.equals("get")) {
					String ip = request.getRemoteAddr();
					String url = request.getRequestURL().toString();
					String agent = request.getHeader("user-agent").toLowerCase();

					String sistema = "Desconocido";
					String browser = "Desconocido";

					for (Entry<String, String> brw : agentBrowser.entrySet())
						if (agent.contains(brw.getKey()))
							browser = brw.getValue();

					for (Entry<String, String> sist : agentOS.entrySet())
						if (agent.contains(sist.getKey()))
							sistema = sist.getValue();

					String[] entrada = new String[] { ip, url, browser, sistema };
					if (cola.size() >= MAX_ENTRADAS)
						cola.poll();
					cola.add(entrada);

				}
			} catch (Exception e) {
				System.out.println("Error registrarAcceso: " + e.getMessage());
			}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		cola = new LinkedList<String[]>();

		this.agentOS.put("windows", "Windows");
		this.agentOS.put("mac", "Mac");
		this.agentOS.put("x11", "Linux");
		this.agentOS.put("android", "Android");
		this.agentOS.put("iphone", "IPhone");

		this.agentBrowser.put("msie", "IE");
		this.agentBrowser.put("opr", "Opera");
		this.agentBrowser.put("opera", "Opera");
		this.agentBrowser.put("chrome", "Chrome");
		this.agentBrowser.put("firefox", "Firefox");
	}
}

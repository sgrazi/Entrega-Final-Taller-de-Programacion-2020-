package controladores;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import publicar.DtEspectador;
import publicar.DtUsuario;
import publicar.EspectaculoEnPaqueteErrorException_Exception;
import publicar.EspectaculoNoExistenteException_Exception;
import modelo.Imagen;
import modelo.Sesion;
import publicar.PaqueteNoExisteException_Exception;
import publicar.PaqueteRepetidoException_Exception;
import publicar.WebServices;
import publicar.WebServicesService;

/**
 * Servlet implementation class Paquete
 */
@WebServlet("/Paquete")
@MultipartConfig
public class Paquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final DateFormat dateFormatEs = new SimpleDateFormat("dd/MM/yyyy");
	public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static String fechaMin;
	private List<String> errores;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Paquete() {
		super();
		errores = new ArrayList<String>();
		Date fecha = new Date();
		fechaMin = dateFormat.format(fecha);
	}

	private void formAlta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paquete/altaPaquete.jsp");
		dispatcher.forward(request, response);
	}

	private void consulta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String nombre = request.getParameter("nombre");

		if (nombre == null || nombre.isEmpty()) {
			Sesion s = new Sesion(request);
			List<publicar.DtPaquete> paquetes = port.obtenerPaquetes().getPaquetes();

			request.setAttribute("paquetes", paquetes);
			request.setAttribute("es_espectador", s.getUsuarioLogeadoDt() instanceof DtEspectador);
			request.getRequestDispatcher("/WEB-INF/paquete/listarPaquetes.jsp").forward(request, response);
		} else {
			try {
				publicar.DtPaquete paquete = port.obtenerPaquete(nombre);

				request.setAttribute("paquete", paquete);
			} catch (PaqueteNoExisteException_Exception e) {
				request.setAttribute("error", "No existe el paquete '" + nombre + "'");
			}
			request.getRequestDispatcher("/WEB-INF/paquete/consultaPaquete.jsp").forward(request, response);
		}
	}

	private void formCompra(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		List<publicar.DtPaquete> paquetes = port.obtenerPaquetes().getPaquetes();
		request.setAttribute("paquetes", paquetes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paquete/compraPaquete.jsp");
		dispatcher.forward(request, response);
	}

	private void formAgregarEspectaculo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// WIP
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		try {
			List<String> paquetes = port.listarPaquetes().getItem();
			List<String> plataformas = port.listarPlataformas().getItem();

			request.setAttribute("paquetes", paquetes);
			request.setAttribute("plataformas", plataformas);

		} catch (PaqueteNoExisteException_Exception e) {
			request.setAttribute("error_paquete", "No hay paquetes en el sistema");
		}
		request.getRequestDispatcher("/WEB-INF/paquete/agregarEspectaculo.jsp").forward(request, response);
	}

	private void formAgregarEspectaculoFinal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String paquete = request.getParameter("paquete");
		String plataforma = request.getParameter("plataforma");

		List<publicar.DtEspectaculo> espectaculos;
		try {
			espectaculos = port.espectaculosParaPaqueteEnPlataforma(paquete, plataforma).getEspectaculos();

			request.setAttribute("paquete", paquete);
			request.setAttribute("plataforma", plataforma);
			request.setAttribute("espectaculos", espectaculos);

			request.getRequestDispatcher("/WEB-INF/paquete/agregarEspectaculoFinal.jsp").forward(request, response);
		} catch (EspectaculoNoExistenteException_Exception e) {
			errores.add(e.getMessage());
			request.setAttribute("errores", errores);
			formAgregarEspectaculo(request, response);
		}

	}

	private String getPath(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		errores.clear();
		String ruta = getPath(request);
		switch (ruta) {
		case "/altaPaquete":
			formAlta(request, response);
			break;
		case "/paquete":
			consulta(request, response);
			break;
		case "/compraPaquete":
			formCompra(request, response);
			break;
		case "/agregarEspectaculoPaquete":
			formAgregarEspectaculo(request, response);
			break;
		default:
			response.getWriter().append("\n Y ahora? \n");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		errores.clear();

		String ruta = getPath(request);
		switch (ruta) {
		case "/altaPaquete":
			altaPaquete(request, response);
			break;
		case "/compraPaquete":
			compraPaquete(request, response);
			break;
		case "/agregarEspectaculoPaquete":
			String espectaculo = request.getParameter("espectaculo");
			if (espectaculo != null && !espectaculo.isEmpty())
				agregarEspectaculo(request, response);
			else
				formAgregarEspectaculoFinal(request, response);
			break;
		default:
			doGet(request, response);
			break;
		}

	}

	private void altaPaquete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		Map<String, String[]> params = request.getParameterMap();

		try {
			String nombre = params.get("nombre")[0];
			String descripcion = params.get("descripcion")[0];
			float descuento = Integer.parseInt("0" + params.get("descuento")[0]) * 0.01f;
			Date inicio = dateFormat.parse(params.get("inicio")[0]);

			Date fin = dateFormat.parse(params.get("fin")[0]);

			if (inicio.after(fin)) {
				errores.add("La fecha de inicio debe ser anterior a la de fin.");
				request.setAttribute("errores", errores);
				formAlta(request, response);
			} else {
				GregorianCalendar gcInicio = new GregorianCalendar();
				GregorianCalendar gcFin = new GregorianCalendar();
				gcInicio.setTime(inicio);
				gcFin.setTime(fin);

				publicar.DtPaquete paquete = new publicar.DtPaquete();
				paquete.setNombre(nombre);
				paquete.setDescripcion(descripcion);
				paquete.setInicio(new XMLGregorianCalendarImpl(gcInicio));
				paquete.setFin(new XMLGregorianCalendarImpl(gcFin));
				paquete.setDescuento(descuento);
				paquete.setCosto(0f);
				System.out.println("SE VIENE");

				if (request.getPart("imagen").getSize() > 0) {
					Imagen imagen = new Imagen();
					try {
						imagen.cargarDesdeRequestInput(request, "imagen");

						imagen.saveToFile();
						paquete.setImagen(imagen.getLink());
					} catch (IOException | ServletException e) {
						errores.add("Error al cargar imagen: " + e.getMessage());
					}
				} else
					paquete.setImagen("img/nodisponible.png");

				if (errores.isEmpty()) {
					port.altaPaquete(paquete);
					response.sendRedirect(request.getContextPath() + "/paquete?nombre=" + nombre);
				} else {
					request.setAttribute("errores", errores);
					formAlta(request, response);
				}
			}
		} catch (ParseException e) {
			errores.add("Error en formato de fechas");
			request.setAttribute("errores", errores);
			formAlta(request, response);
		} catch (PaqueteRepetidoException_Exception e) {
			errores.add(e.getMessage());
			request.setAttribute("errores", errores);
			formAlta(request, response);
		}
	}

	private void compraPaquete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String paquete = request.getParameter("paquete");

		DtUsuario espectador = (DtUsuario) request.getSession().getAttribute("usuario_logueado");

		boolean errPaquete = paquete == null || paquete.isEmpty();
		boolean errEspectador = !(espectador instanceof DtEspectador);

		if (!errEspectador && !errPaquete) {
			// CAMBIAR LA LOGICA! (Faltan excepciones y verificar)
			port.comprarPaquete(espectador.getNickname(), paquete);

			response.sendRedirect(request.getContextPath() + "/paquete?nombre=" + paquete);
		} else {
			if (errPaquete)
				errores.add("Debe seleccionar un paquete");
			if (errEspectador)
				errores.add("Debe estar logueado como espectador para realizar esta operación");
			request.setAttribute("errores", errores);
			formCompra(request, response);
		}
	}

	private void agregarEspectaculo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String paquete = request.getParameter("paquete");
		String espectaculo = request.getParameter("espectaculo");

		try {
			port.agregarEspectaculoAPaquete(espectaculo, paquete);
			response.sendRedirect(request.getContextPath() + "/paquete?nombre=" + paquete);
		} catch (EspectaculoEnPaqueteErrorException_Exception | EspectaculoNoExistenteException_Exception
				| PaqueteNoExisteException_Exception e) {
			errores.add(e.getMessage());
			request.setAttribute("errores", errores);
			formAgregarEspectaculoFinal(request, response);
		}

	}

}

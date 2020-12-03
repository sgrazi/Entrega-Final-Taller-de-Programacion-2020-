package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import modelo.Menu;
import publicar.DtEspectaculo;
import publicar.DtPaquete;
import publicar.DtPlataforma;
import publicar.DtUsuario;
import publicar.UsuarioNoExisteException_Exception;
import publicar.WebServices;
import publicar.WebServicesService;

/**
 * Servlet implementation class listar
 */
@WebServlet("/Categoria")
public class ListarPorPlataformaCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarPorPlataformaCategoria() {
		super();
	}

	private void perfilBuscado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("buscar");
		String ordenar = request.getParameter("ordenar");
		if (ordenar == null || !ordenar.equals("fecha"))
			ordenar = "alfabetico";

		request.setAttribute("nom", nombre);

		//IControladorEspectaculo ce = Fabrica.getInstance().getIControladorEspectaculo();
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();
		
		List<DtEspectaculo> colE = port.obtenerEspectaculosBusqueda(nombre).getEspectaculos();

		// Ordenar Espectaculos
		List<DtPaquete> colP = port.obtenerPaquetesBusqueda(nombre).getPaquetes();
		
		ArrayList<DtPaquete> alfaP;
		ArrayList<DtEspectaculo> espOrdenados;
		
		if (ordenar.equals("fecha")) {
			
			espOrdenados = filtradoFechaE(colE);
			alfaP = filtradoFechaP(colP);
			
		}
		else {
			alfaP = filtradoAlfabeticoP(colP);
			espOrdenados = filtradoAlfabeticoE(colE);
		
				
		}
			
		request.setAttribute("espectaculos", espOrdenados);
		request.setAttribute("orden", ordenar);
		request.setAttribute("paquetes", alfaP);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/buscar.jsp");
		dispatcher.forward(request, response);

	}

	public ArrayList<DtEspectaculo> filtradoAlfabeticoE(List<DtEspectaculo> colE) {

		ArrayList<DtEspectaculo> res = new ArrayList<DtEspectaculo>();
		Set<String> aux = new HashSet<String>();
		for (DtEspectaculo iter : colE) {
			aux.add(iter.getNombre());
		}

		ArrayList<String> sorted = new ArrayList<>(aux);
		Collections.sort(sorted);

		for (int i = 0; i < sorted.size(); i++) {
			for (DtEspectaculo iter : colE) {
				if (iter.getNombre().equals(sorted.get(i)))
					res.add(iter);
			}
		}

		return res;
	}

	public ArrayList<DtEspectaculo> filtradoFechaE(List<DtEspectaculo> colE) {

		ArrayList<DtEspectaculo> aux = new ArrayList<DtEspectaculo>(colE);
		ArrayList<DtEspectaculo> res = new ArrayList<DtEspectaculo>();

		int iter = 0;
		while (!aux.isEmpty()) {
			DtEspectaculo iter2 = aux.get(iter);

			if (aux.size() > 1) {
				for (int i = 0; i < aux.size(); i++) {
					int valor = iter2.getFechaDeRegistro().compare(aux.get(i).getFechaDeRegistro());
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

	public ArrayList<DtPaquete> filtradoAlfabeticoP(List<DtPaquete> colP) {

		ArrayList<DtPaquete> res = new ArrayList<DtPaquete>();
		Set<String> aux = new HashSet<String>();
		for (DtPaquete iter : colP) {
			aux.add(iter.getNombre());
		}

		ArrayList<String> sorted = new ArrayList<>(aux);
		Collections.sort(sorted);

		for (int i = 0; i < sorted.size(); i++) {
			for (DtPaquete iter : colP) {
				if (iter.getNombre().equals(sorted.get(i))) {
					res.add(iter);
				}
			}
		}

		return res;
	}
	
	public ArrayList<DtPaquete> filtradoFechaP(List<DtPaquete> colP) {

		ArrayList<DtPaquete> aux = new ArrayList<DtPaquete>(colP);
		ArrayList<DtPaquete> res = new ArrayList<DtPaquete>();

		int iter = 0;
		while (!aux.isEmpty()) {
			DtPaquete iter2 = aux.get(iter);

			if (aux.size() > 1) {
				for (int i = 0; i < aux.size(); i++) {
					int valor = iter2.getFin().compare(aux.get(i).getFin());
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


	private void perfilCategoria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//IControladorEspectaculo ce = Fabrica.getInstance().getIControladorEspectaculo();
		
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String nombre = request.getParameter("nombre");
		request.setAttribute("nom", nombre);
		List<DtEspectaculo> esps = port.obtenerEspectaculosPorCategoria(nombre).getEspectaculos();
		request.setAttribute("espectaculos", esps);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/listarPorCategoria.jsp");
		dispatcher.forward(request, response);

	}

	private void perfilPlataforma(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//IControladorEspectaculo ce = Fabrica.getInstance().getIControladorEspectaculo();
		
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();

		String nombre = request.getParameter("nombre");
		DtPlataforma plat = port.obtenerPlataforma(nombre);
		request.setAttribute("plataforma", plat);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/listarPorPlataforma.jsp");
		dispatcher.forward(request, response);

	}

	private String getPath(HttpServletRequest request) {
		return request.getRequestURI().substring(request.getContextPath().length());

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Menu.generarMenu(request);

		String ruta = request.getServletPath();

		switch (ruta) {
		case "/listarPorCategoria":
			perfilCategoria(request, response);
			break;
		case "/listarPorPlataforma":
			perfilPlataforma(request, response);
			break;
		case "/buscar":
			perfilBuscado(request, response);
			break;
		case "/listarUsuarios":
			listarUsuarios(request, response);
			break;

		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ruta = getPath(request);
		switch (ruta) {
		case "/listarPorCateogira":
			perfilCategoria(request, response);
			break;
		case "/listarPorPlataforma":
			perfilPlataforma(request, response);
			break;
		case "/buscar":
			perfilBuscado(request, response);
			break;
		case "/listarUsuarios":
			listarUsuarios(request, response);
			break;
		default:
			break;

		}

	}

	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//IControladorUsuario cu = Fabrica.getInstance().getIControladorUsuario();
		WebServicesService service = new WebServicesService();
		WebServices port = service.getWebServicesPort();
		
		List<DtUsuario> col;
		try {
			col = port.obtenerUsuarios().getUsuarios();
		} catch (UsuarioNoExisteException_Exception e) {
			// TODO Auto-generated catch block
			col = null;
		}
		request.setAttribute("usuarios", col);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/home/listarUsuarios.jsp");
		dispatcher.forward(request, response);

	}

}

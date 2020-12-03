package modelo;

import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import publicar.DtArtista;
import publicar.DtUsuario;
import publicar.WebServices;
import publicar.WebServicesService;

public class Menu {
	private static Menu instancia;

	private Map<String, subMenus> items;
	private WebServicesService service;
	private WebServices port;

	public Menu() {
		this.items = new HashMap<>();
		this.service = new WebServicesService();
		this.port = service.getWebServicesPort();
	}

	public void inicializar() {
		this.items.clear();
	}

	public void add(String nombre, subMenus sb) {
		this.items.put(nombre, sb);
	}

	public void add(String nombre, String nomSubMenu, String linkSubMenu) {

		subMenus sb = this.items.get(nombre);

		if (!this.items.containsKey(nombre)) {
			sb = new subMenus();
		}

		sb.add(nomSubMenu, linkSubMenu);
		this.items.put(nombre, sb);

	}

	public void delete(String nombre) {
		this.items.remove(nombre);
	}

	public subMenus getSubMenu(String nombre) {
		subMenus sb = this.items.get(nombre);

		return sb;
	}

	public List<String> getItems() {
		List<String> elementos = this.items.keySet().stream().collect(Collectors.toList());

		return elementos;
	}

	public void addPlataformas() {
		subMenus sMenus = new subMenus();
		List<String> plataformas = port.listarPlataformas().getItem();

		for (String pla : plataformas)
			sMenus.add(pla, "./listarPorPlataforma?nombre=" + pla);

		this.add("Plataformas", sMenus);
	}

	public void addCategorias() {
		subMenus sMenus = new subMenus();
		List<String> categorias = port.obtenerCategorias().getCategorias();

		for (String cat : categorias)
			sMenus.add(cat, "listarPorCategoria?nombre=" + cat);

		this.add("Categorias", sMenus);
	}

	public static void generarMenu(HttpServletRequest request) {
		Sesion sesion = new Sesion(request);

		DtUsuario usuario = sesion.getUsuarioLogeadoDt();
		if (usuario != null) {

		}

		instancia = new Menu();
		subMenus sMenus = new subMenus();

		sMenus.add("Usuarios", "./listarUsuarios");

		// DtArtista art = (DtArtista)usuario;
		if (usuario instanceof DtArtista) {
			sMenus.add("Alta Espectaculo", "./espectaculoAlta?action=ALTA");
			sMenus.add("Alta Funcion", "./altaFuncion");
			sMenus.add("Alta Paquete", "./altaPaquete");
			sMenus.add("Agregar Espectaculo a Paquete", "./agregarEspectaculoPaquete");
		}

		instancia.add("Administracion", sMenus);
		instancia.addPlataformas();
		instancia.addCategorias();

		request.setAttribute("menu", instancia);
	}

}

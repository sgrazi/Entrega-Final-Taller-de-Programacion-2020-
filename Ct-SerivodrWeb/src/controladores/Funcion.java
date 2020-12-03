package controladores;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.*;
import modelo.*;
import publicar.ArrayString;
import publicar.CanjeInvalidoException_Exception;
import publicar.DtArtista;
import publicar.DtEspectaculo;
import publicar.DtEspectador;
import publicar.DtFuncion;
import publicar.DtHora;
import publicar.EspectaculoNoExistenteException_Exception;
import publicar.EspectadorYaRegistradoException_Exception;
import publicar.ListString;
import publicar.NombreFuncionRepetidoException_Exception;
import publicar.SetString;
import publicar.TicketsAgotadosParaFuncionException_Exception;
import publicar.WebServices;
import publicar.WebServicesService;


@WebServlet("/Funcion")
@MultipartConfig
public class Funcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> errores;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Funcion() {
        super();
        errores = new ArrayList<String>();
    }
       	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Menu.generarMenu(request);
		
		WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
		
		
		if(request.getServletPath()=="/altaFuncion") {
			Sesion s = new Sesion(request);
			
	 		ArrayString aceptados = port.getEspectaculosAceptadosArtista(s.getUsuarioLogeadoDt().getNickname());  //1
	 		request.setAttribute("EspectaculosAceptados", aceptados.getStrings());

	 		SetString artistas = port.getArtistasInvitadosMenos(s.getUsuarioLogeadoDt().getNickname());
	 		request.setAttribute("artistas", artistas.getStrings());
	 		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/funcion/altaFuncion.jsp");
	        dispatcher.forward(request, response);
		}
		else if (request.getServletPath()=="/registroAFuncion") {
			String nombreFuncion = request.getParameter("nombreFuncion");
			request.setAttribute("nombreFuncion", nombreFuncion);
			DtFuncion dt = null;
			try {
				dt = port.getDtFuncion(nombreFuncion);
			} catch (EspectaculoNoExistenteException_Exception e) {
				e.printStackTrace();
			}
			
			String imagen = dt.getImagen();
			request.setAttribute("imagen", imagen);
			
			XMLGregorianCalendar fecha = dt.getFecha();
			Calendar calendar = fecha.toGregorianCalendar();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			formatter.setTimeZone(calendar.getTimeZone());
			String dateString = formatter.format(calendar.getTime());
			
			request.setAttribute("fecha", dateString);
			
			
			DtHora dtHora = dt.getHora();
			String horaString = Integer.toString(dtHora.getHoras());
			String minutoString = Integer.toString(dtHora.getMinutos());
			request.setAttribute("hora", horaString+":"+minutoString);
			
			ListString artistasInv = port.getArtistasFuncion(nombreFuncion);
			request.setAttribute("artistas", artistasInv.getStrings());
			
			int costo = dt.getCosto();
			request.setAttribute("costo", costo);
			
			//se cargan los dropdown de los registros
			Sesion s = new Sesion(request);
			SetString registros = port.getRegistrosNoCanjeadosUsuario(s.getUsuarioLogeadoDt().getNickname());
			request.setAttribute("RegistrosDelUsuario", registros.getStrings());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/funcion/registroAFuncion.jsp");
	        dispatcher.forward(request, response);
		} else if (request.getServletPath()=="/consultaFuncion") {
			Sesion s = new Sesion(request);
			
			DtFuncion dt = null;
			try {
				dt = port.getDtFuncion(request.getParameter("nombreFuncion"));
			} catch (EspectaculoNoExistenteException_Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			boolean logueado = s.usuarioLogeado();
			boolean registrado = false;
			boolean artistaOrganizador = false;
			boolean sorteada = true;
			boolean finalizada = false;
			GregorianCalendar actual = new GregorianCalendar();
			if(!dt.getFechaFinalizacion().toGregorianCalendar().after(actual)) {
				finalizada = true;				
			}
			if(logueado) {
				String nick = s.getUsuarioLogeadoDt().getNickname();
				try {
					DtEspectador e = (DtEspectador) port.getDtUsuarioPorNickName(nick);
					if(e.getFunciones().contains(request.getParameter("nombreFuncion")))
						registrado = true;
				} catch (ClassCastException e) {
					//el usuario era un artista, no se puede registrar
					registrado = true;
					DtArtista a = (DtArtista) port.getDtUsuarioPorNickName(nick);
					if(a.getNickname().equals(port.getDtEspectaculo(dt.getNombreEsp()).getArtista()) &&
							!dt.isSorteada() && finalizada) {//si el organizador esta logueado, no esta sorteada y la funcion esta finalizada
						sorteada = false;
						artistaOrganizador = true;
					}
				}
			}
			request.setAttribute("finalizada", finalizada);
			request.setAttribute("sorteada", sorteada);
			request.setAttribute("artistaOrganizador", artistaOrganizador);
			
			String imagen = dt.getImagen();
			request.setAttribute("imagen", imagen);
			
			boolean valor = logueado && !registrado;
			request.setAttribute("noRegistrado",valor);
			try {
				this.procesarConsulta(request, response);
			} catch (ServletException | IOException | EspectaculoNoExistenteException_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (request.getServletPath()=="/listarFunciones") {
			List<DtFuncion> funciones = port.getFunciones().getFuns();
			
			List<DtFuncion> ordenadas = new ArrayList<DtFuncion>(); 
			DtFuncion aux;
			for(int i=0; i< funciones.size();i++) {
				aux = funciones.get(i);
				int j;
				for(j=0; j<ordenadas.size();j++) {
					if(aux.getNombre().compareTo(ordenadas.get(j).getNombre())<0)
						break;
				}
				ordenadas.add(j, aux);
			}
			
			request.setAttribute("funciones",ordenadas);
			request.getRequestDispatcher("/WEB-INF/funcion/listarFunciones.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
		
		try {
			Menu.generarMenu(request);
			if(request.getServletPath()=="/altaFuncion") {
		 		this.procesarAlta(request, response);
		 		
			}
			else if (request.getServletPath()=="/registroAFuncion") {
				
				request.setAttribute("errores", errores);
				
				String nombreFuncion = request.getParameter("nombreFuncion");
				request.setAttribute("nombreFuncion", nombreFuncion);
				DtFuncion dt = port.getDtFuncion(nombreFuncion);
				
				
				String imagen = dt.getImagen();
				request.setAttribute("imagen", imagen);
				
				XMLGregorianCalendar fecha = dt.getFecha();
				Calendar calendar = fecha.toGregorianCalendar();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				formatter.setTimeZone(calendar.getTimeZone());
				String dateString = formatter.format(calendar.getTime());
				
				request.setAttribute("fecha", dateString);

				
				DtHora dtHora = dt.getHora();
				String horaString = Integer.toString(dtHora.getHoras());
				String minutoString = Integer.toString(dtHora.getMinutos());
				if(minutoString.length()<2)
					minutoString = minutoString + "0";
				request.setAttribute("hora", horaString+":"+minutoString);
				
				ListString artistasInv = port.getArtistasFuncion(nombreFuncion);
				request.setAttribute("artistas", artistasInv.getStrings());
				
				int costo = dt.getCosto();
				request.setAttribute("costo", costo);
				
				Sesion s = new Sesion(request);
				SetString registros = port.getRegistrosNoCanjeadosUsuario(s.getUsuarioLogeadoDt().getNickname());
				request.setAttribute("RegistrosDelUsuario", registros.getStrings());
				
				this.procesarRegistro(request, response);				
				
				
			} else if (request.getServletPath()=="/consultaFuncion") {
				Sesion s = new Sesion(request);
				DtFuncion dt = port.getDtFuncion(request.getParameter("nombreFuncion"));
				boolean logueado = s.usuarioLogeado();
				boolean registrado = false;
				boolean artistaOrganizador = false;
				boolean sorteada = true;
				boolean finalizada = false;
				GregorianCalendar actual = new GregorianCalendar();
				if(!dt.getFechaFinalizacion().toGregorianCalendar().after(actual)) {
					finalizada = true;				
				}
				if(logueado) {
					String nick = s.getUsuarioLogeadoDt().getNickname();
					try {
						DtEspectador e = (DtEspectador) port.getDtUsuarioPorNickName(nick);
						if(e.getFunciones().contains(request.getParameter("nombreFuncion")))
							registrado = true;
					} catch (ClassCastException e) {
						//el usuario era un artista, no se puede registrar
						registrado = true;
						DtArtista a = (DtArtista) port.getDtUsuarioPorNickName(nick);
						if(a.getNickname().equals(port.getDtEspectaculo(dt.getNombreEsp()).getArtista()) &&
								!dt.isSorteada() && finalizada) {//si el organizador esta logueado, no esta sorteada y la funcion esta finalizada
							sorteada = false;
							artistaOrganizador = true;
						}
					}
				}
				request.setAttribute("finalizada", finalizada);
				request.setAttribute("sorteada", sorteada);
				request.setAttribute("artistaOrganizador", artistaOrganizador);
				
				String imagen = dt.getImagen();
				request.setAttribute("imagen", imagen);
				
				boolean valor = logueado && !registrado;
				request.setAttribute("noRegistrado",valor);
				this.procesarConsulta(request, response);
			}
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoNoExistenteException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}
	
	protected void procesarAlta(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		
		errores.clear();
		WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
    	
		String Espectaculo = request.getParameter("espectaculo");
		String NombreFun = request.getParameter("nombre");
        
		DtHora Hora = new DtHora();
		
		//seccion para la fecha
		String StrFecha = request.getParameter("fecha");
		
		//seccion para la hora
		String HoraString = request.getParameter("hora");
		String[] HoraArray = HoraString.split ( ":" );
		Hora.setHoras(Integer.parseInt(HoraArray[0].trim()));
		Hora.setMinutos(Integer.parseInt(HoraArray[1].trim()));
		
		String[] artistas = request.getParameterValues("seleccionado");
		SetString artSet = new SetString();
		if(artistas != null) {

			for (String artista: artistas) {
				artSet.getStrings().add(artista);
			}	
		}
		
		String imagen = "";
		 
		Imagen img = new Imagen();
		img.cargarDesdeRequestInput(request, "inputGroupFile01");
		
		try {
			img.saveToFile();
			
			imagen = img.getLink();
		} catch (Exception e) {
			errores.add("No se pudo generar la imagen en el servidor.ACA "+ img.getName() + " " + e.getMessage());
		}
		
		String dir = "";
		boolean redirect = true;

		try {
			port.confirmarAltaFuncion(Espectaculo, NombreFun, StrFecha, Hora, artSet, imagen);
			dir = "./consultaFuncion?nombreFuncion="+NombreFun; //mando al home si hay exito
		} catch (EspectaculoNoExistenteException_Exception | NombreFuncionRepetidoException_Exception e) {
			// TODO Auto-generated catch block
			errores.add(e.getMessage());
			e.printStackTrace();
		}

		request.setAttribute("errores", errores);
		if(redirect)//si salio bien el alta
			response.sendRedirect(dir);
		else
			request.getRequestDispatcher("/WEB-INF/funcion/altaFuncion.jsp").forward(request, response);
	}
	
	protected void procesarConsulta(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, EspectaculoNoExistenteException_Exception {
		String nombreFuncion = request.getParameter("nombreFuncion");
		
		WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
    	
		if(request.getParameter("botonSorteo") != null) {
			DtFuncion fun = port.getDtFuncion(nombreFuncion);
			ListString ganadores = port.sortearPremios(fun);
			request.setAttribute("sorteado", "true");
			request.setAttribute("ganadores", ganadores.getStrings());
		}
		
		Sesion s = new Sesion(request);
		if(s.usuarioLogeado()) {
			request.setAttribute("logueado", "true");
		}
			
		DtFuncion dt = port.getDtFuncion(nombreFuncion);//1
		
		request.setAttribute("nombreFuncion", nombreFuncion);
		
		DtEspectaculo esp = port.getDtEspectaculo(dt.getNombreEsp());//1
		
		request.setAttribute("url", esp.getUrl());
		
		request.setAttribute("nombrePlataforma", esp.getPlataforma());
				
		XMLGregorianCalendar fecha = dt.getFecha();
		Calendar calendar = fecha.toGregorianCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		formatter.setTimeZone(calendar.getTimeZone());
		String dateString = formatter.format(calendar.getTime());
		
		request.setAttribute("fecha", dateString);
		
		DtHora dtHora = dt.getHora();
		String horaString = Integer.toString(dtHora.getHoras());
		String minutoString = Integer.toString(dtHora.getMinutos());
		if(minutoString.length()<2)
			minutoString = minutoString + "0";
		request.setAttribute("hora", horaString+":"+minutoString);
		
		ListString artistasInv = port.getArtistasFuncion(nombreFuncion);//1
		request.setAttribute("artistas", artistasInv.getStrings());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/funcion/consultaFuncion.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void procesarRegistro(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, EspectaculoNoExistenteException_Exception {

		WebServicesService service = new WebServicesService();
    	WebServices port = service.getWebServicesPort();
		Sesion s = new Sesion(request);
		DtFuncion dt = port.getDtFuncion(request.getParameter("nombreFuncion"));//1
		
		boolean canje = request.getParameter("c1") != null;//checkbox
		
		boolean redirect = true;
		
		try {
			port.confirmarAltaRegistroAFuncion(dt.getNombreEsp(), s.getUsuarioLogeadoDt().getNickname(), dt.getNombre() , canje, request.getParameter("registro1"), request.getParameter("registro2"), request.getParameter("registro3") );
		} catch (CanjeInvalidoException_Exception | EspectaculoNoExistenteException_Exception | EspectadorYaRegistradoException_Exception | TicketsAgotadosParaFuncionException_Exception e) {
			// TODO Auto-generated catch block
			errores.clear();
			errores.add(e.getMessage());
			request.setAttribute("errores", errores);
			redirect = false;
		}
		
		if(redirect){//si registro
			response.sendRedirect("./consultaFuncion?nombreFuncion="+dt.getNombre());
		}
		else {
			request.getRequestDispatcher("/WEB-INF/funcion/registroAFuncion.jsp").forward(request, response);
		}
	}
}

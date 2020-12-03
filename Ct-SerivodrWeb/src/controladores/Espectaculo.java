package controladores;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import java.util.*;

import modelo.Sesion;
import modelo.Imagen;
import publicar.*;


/**
 * Servlet implementation class espectaculo
 */
@WebServlet("/Espectaculo")
@MultipartConfig
public class Espectaculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> errores;
	 
	private WebServicesService service;
	private WebServices port;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Espectaculo() {
        super();
        
        errores = new ArrayList<String>();
        
        this.service = new WebServicesService();
    	this.port = service.getWebServicesPort();
    	
    }

    private XMLGregorianCalendar procesarfecha(Date fecha) {
    	GregorianCalendar gcfecha = new GregorianCalendar();
		gcfecha.setTime(fecha);
		return new XMLGregorianCalendarImpl(gcfecha);
    }
    
    private int[] resumenValoraciones( Map<String,Integer> valoraciones) {
    	int[] resultado = new int[5];
    	
		for (int i = 0; i < resultado.length; i++)
			resultado[i] = 0;

		for (int puntaje : valoraciones.values())
			resultado[puntaje - 1]++;

		return resultado;
    }
    
	private boolean espectadorTieneEspectaculo(DtEspectador espectador, DtEspectaculo espectaculo) {
		boolean res = false;

		for (DtFuncion func : espectaculo.getFunciones())
			if (espectador.getFunciones().contains(func.getNombre()))
				res = true;

		return res;
	}
    
	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		String uri = request.getHeader("referer");

		errores.clear();
		
		String action;
		try {
			action = request.getParameter("action").toUpperCase();
		} catch (Exception e) {
			action = "";
		}
		
		try {
			
			//SI NO VIENE UN ACTION ASUMIMOS QUE ES UNA CONSULTA
			switch(action) {
			  case "ALTA":
				  this.alta(request,response);
				  break;
			  default:
				  this.procesarConsulta(request, response);
			}
		
		} catch (ServletException | IOException | ParseException | UsuarioNoExisteException_Exception | EspectaculoNoExistenteException_Exception e) {
			// TODO Auto-generated catch block
			errores.add(e.getMessage());
			
			e.printStackTrace();	
			
			response.sendRedirect(uri);
		}
		 
	}

	/**
	 * @throws ServletException 
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		this.errores.clear();
				
		String action;
		try {
			action = request.getParameter("action").toUpperCase();
		} catch (Exception e) {
			action = "";
		}
		
		//SI NO VIENE UN ACTION ASUMIMOS QUE ES UNA CONSULTA
		try {
			switch(action) {
			  case "FINALIZAR":
				  
				  this.finalizar(request,response);
				  break;
			  default:
				  
				  this.procesarAlta(request, response);
			}	
		} catch (UsuarioNoExisteException_Exception | PlataformaNoExisteException_Exception | ParseException e) {
			request.setAttribute("errores", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/errorPages/500.jsp").forward(request, response);
		}
		
		
	}
	
	protected void limpiarRequest(HttpServletRequest request) {
		agregarPlataformas(request);
		agregarCategorias(request);
		
		//SETEO DATOS VACIOS POR SI SE PROCESA EL ALTA
		request.setAttribute("nombre", "");
		request.setAttribute("descripcion", "");
		request.setAttribute("url", "");
		request.setAttribute("minimo", 1);
		request.setAttribute("maximo", 1);
		request.setAttribute("costo", 0);
		request.setAttribute("duracion", 0);
		request.setAttribute("fecha", "");
		request.setAttribute("imagen", "");
		request.setAttribute("video", "");
		request.setAttribute("premio", "");
	}
	

	public void agregarPlataformas(HttpServletRequest request) {
		DtColPlataformas dtCol = this.port.obtenerPlataformas();
		
		List<String> plataformas = dtCol.getPlataformas();
		
		request.setAttribute("plataformas", plataformas);
	}
	
	public void agregarCategorias(HttpServletRequest request) {
		DtColCategorias dtCol = this.port.obtenerCategorias();
		
		List<String> categorias = dtCol.getCategorias();
		
		request.setAttribute("categorias", categorias);
	}
		
	protected void procesarConsulta(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, EspectaculoNoExistenteException_Exception {
		request.setCharacterEncoding("utf-8");

		String esp = request.getParameter("value");
		System.out.println(esp);
		this.errores.clear();
		
		if (esp == null) {
			this.errores.add("Falta parametro value");
			
			throw new ServletException("Falta parametro value");
		}
		
		if (esp == "") {
			this.errores.add("El nombre del espectaculo no es valido");
			
			throw new ServletException("Falta parametro value");
		}
		
		try {
    		DtEspectaculo espectaculo = this.port.obtenerEspectaculo(esp);
    		DtUsuario usuarioLog =  Login.getUsuarioLogueado(request);
    		
    		request.setAttribute("espectaculo", espectaculo);
    		request.setAttribute("tipo", "");
    		request.setAttribute("tipoNombre", "");
    		request.setAttribute("usuarioLog", usuarioLog);
    		
    		List<DtEspectaculo.Valoraciones.Entry> valsList = espectaculo.getValoraciones().getEntry();
    		Map<String,Integer> valoraciones = new HashMap<String, Integer>();

    		for (DtEspectaculo.Valoraciones.Entry val : valsList) 
				valoraciones.put(val.getKey(), val.getValue());

    		request.setAttribute("resumenVal", resumenValoraciones(valoraciones));
    		request.setAttribute("cantVals", valoraciones.size());
    		
    		boolean espect = false;
    		boolean puedeFinalizar = false;
    		boolean registrado = false;

    		if((usuarioLog instanceof DtEspectador)) {
				espect = true;

				registrado = espectadorTieneEspectaculo((DtEspectador) usuarioLog, espectaculo);

				String nickName = usuarioLog.getNickname();
				int valoracion = valoraciones.containsKey(nickName) ? valoraciones.get(nickName) : 0;
				request.setAttribute("valoracion", valoracion);

				request.setAttribute("esFavorito",
						port.espectaculoEsFavoritoWS(usuarioLog.getNickname(), espectaculo.getNombre()));
    		} else {
    			
    			boolean finalizar = ( (espectaculo.getEstado().equals("ACEPTADO")) || (espectaculo.getEstado().equals("APROBADO")) );
    			
    			if ((usuarioLog instanceof DtArtista) && finalizar) {
    				List<String> misEspectaculos = ((DtArtista)usuarioLog).getEspectaculos();
    				
    				puedeFinalizar = misEspectaculos.contains(espectaculo.getNombre());
    				
    			} 
    		}

    		request.setAttribute("registrado", registrado);
    		request.setAttribute("esEspectador", espect);
    		request.setAttribute("artistaPuedeFinalizar", puedeFinalizar);
    		
    		request.getRequestDispatcher("/WEB-INF/espectaculo/espectaculoConsulta.jsp").forward(request, response);
		} catch (EspectaculoNoExistenteException_Exception | UsuarioNoEsEspectadorException_Exception e) {
			this.errores.add(e.getMessage());
			
			request.setAttribute("errores", errores);
			
		}
		
	}

	private void controloSiEsArtista(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException, UsuarioNoExisteException_Exception {
		Sesion s = new Sesion(request);
		//SI NO ESTA LOGEADO SE REDIRECCIONA
		s.redireccionarLogin(response);
		
		DtUsuario usu = s.getUsuarioLogeadoDt();
		DtArtista artista = (DtArtista)usu;
		
		if (artista == null) {
			UsuarioNoExisteException faultBean = new UsuarioNoExisteException();
			faultBean.setMessage("Este usuario no es un artista");
						
			request.setAttribute("errores", "Este usuario no es un artista");
			throw new UsuarioNoExisteException_Exception("Este usuario no es un artista", faultBean);
		}
	}
	
	protected void finalizar(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException, UsuarioNoExisteException_Exception {
		String esp = request.getParameter("espectaculo");
		
		this.controloSiEsArtista(request, response);
		
		DtUsuario usu = Login.getUsuarioLogueado(request);
		
		try {
			this.port.finalizarEspectaculo(esp, usu.getCorreoElectronico());
			
			response.sendRedirect("./espectaculo?value=" + esp);
//			request.getRequestDispatcher("/WEB-INF/espectaculo/espectaculoConsulta.jsp?value=" + esp).forward(request, response);
		} catch (EspectaculoNoExistenteException_Exception e) {
			request.setAttribute("errores", e.getMessage());
			
			request.getRequestDispatcher("/WEB-INF/errorPages/500.jsp").forward(request, response);
		}
		
	}
	
	protected void alta(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException, UsuarioNoExisteException_Exception {
		this.controloSiEsArtista(request, response);
				
		limpiarRequest(request);
		
		request.getRequestDispatcher("/WEB-INF/espectaculo/espectaculoAlta.jsp").forward(request, response);
	}
	
	protected void procesarAlta(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, ParseException, PlataformaNoExisteException_Exception, UsuarioNoExisteException_Exception {
		this.controloSiEsArtista(request, response);
		
		Sesion s = new Sesion(request);
		DtUsuario usu = s.getUsuarioLogeadoDt();
		DtArtista artista = (DtArtista)usu;
		
		//TOMO DATOS DEL ESPECTACULO		
		String plat = request.getParameter("plataforma");
		String nom = request.getParameter("nombre");
		String desc = request.getParameter("descripcion");
		String url = request.getParameter("url");
		
		String sMin = request.getParameter("minimo");
		int min = Integer.parseInt(sMin);
		String sMax = request.getParameter("maximo");
		int max = Integer.parseInt(sMax);
		
		String sCosto = request.getParameter("costo");
		int costo = Integer.parseInt(sCosto);
		String sDur = request.getParameter("duracion");
		int dur = Integer.parseInt(sDur);
			
		String sCantSorteos = request.getParameter("cantSorteos");
		
		int cantSorteos = 0;
		try {
			cantSorteos = Integer.parseInt(sCantSorteos);
		} catch (Exception e) {
		}
				
		String video = request.getParameter("video");
		String premio = request.getParameter("premio");
		
		List<String> catSel = new ArrayList<>();
		try {
			String[] categorias = request.getParameterValues("categorias");
			
			catSel = Arrays.asList(categorias);
			
			if (catSel.size() == 0) {
				errores.add("Debe seleccionar al menos una categoria");
			}
		} catch (Exception e) {
			errores.add("Ocurrio un error al cargar las categorias seleccionadas");
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
		
		if (min > max) {
			errores.add("La capacidad minima no puede ser mayor que la maxima");
		}
		
		ObjectFactory fabrica = new ObjectFactory();
		DtEspectaculo dtEspectaculo = fabrica.createDtEspectaculo();
		dtEspectaculo.setPlataforma(plat);
		dtEspectaculo.setNombre(nom);
		dtEspectaculo.setDescripcion(desc);
		dtEspectaculo.setUrl(url);
		dtEspectaculo.setCosto(costo);
		dtEspectaculo.setCantMaxEspectadores(max);
		dtEspectaculo.setCantMinEspectadores(min);
		dtEspectaculo.setDuracion(dur);
		dtEspectaculo.setImagen(imagen);
		dtEspectaculo.setArtista(artista.getNickname());
		dtEspectaculo.setCantSorteosPorFuncion(cantSorteos);
		dtEspectaculo.setSorteoDescripcion(premio);
		dtEspectaculo.setVideoUrl(video);
		
		Date fecha = new Date();
		dtEspectaculo.setFechaDeRegistro(procesarfecha(fecha));
		
		for (String cat : catSel) {
			dtEspectaculo.getCategorias().add(cat);
		}
		
		//SI NO HAY ERRORES MANDO EL ALTA
		if (errores.size() == 0) {
				    	
			try {
				
				this.port.altaEspectaculo(dtEspectaculo);
				
				limpiarRequest(request);
				
				request.setAttribute("mensajes", "Espectaculo dado de alta exitosamente");
				
			} catch (EspectaculoAgregadoYaExisteExcepcion_Exception | PlataformaNoExisteException_Exception | UsuarioNoExisteException_Exception e) {
				errores.add(e.getMessage());
			}
						
		}
		
		//SI DESPUES DE ENVIAR HAY ERRORES ENTONCES VUELVO A CHEQUEAR PARA DEJAR LOS DATOS VIEJOS
		if (errores.size() > 0) {
			//DEJO EL REQUEST CON LOS DATOS QUE VINIERON
			request.setAttribute("nombre", dtEspectaculo.getNombre());
			request.setAttribute("descripcion", dtEspectaculo.getDescripcion());
			request.setAttribute("plataforma", dtEspectaculo.getPlataforma());
			request.setAttribute("url", dtEspectaculo.getUrl());
			request.setAttribute("minimo", dtEspectaculo.getCantMinEspectadores());
			request.setAttribute("maximo", dtEspectaculo.getCantMaxEspectadores());
			request.setAttribute("costo", dtEspectaculo.getCosto());
			request.setAttribute("duracion", dtEspectaculo.getDuracion());
			request.setAttribute("imagen", dtEspectaculo.getImagen());
			request.setAttribute("video", dtEspectaculo.getVideoUrl());
			request.setAttribute("premio", dtEspectaculo.getSorteoDescripcion());
			request.setAttribute("cantSorteos", dtEspectaculo.getCantSorteosPorFuncion());
		}
		
		agregarPlataformas(request);
		agregarCategorias(request);
		request.setAttribute("errores", errores);
		
		request.getRequestDispatcher("/WEB-INF/espectaculo/espectaculoAlta.jsp").forward(request, response);
	}

}

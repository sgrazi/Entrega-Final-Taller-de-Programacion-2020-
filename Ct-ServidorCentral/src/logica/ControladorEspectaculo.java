package logica;

import java.time.LocalDate;
import java.util.Random;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import datatypes.DtHora;
import datatypes.DtPaquete;
import datatypes.DtPlataforma;
import excepciones.CanjeInvalidoException;
import excepciones.CategoriaConNombreVacioException;
import excepciones.CategoriaRepetidaException;
import excepciones.EspectaculoAgregadoYaExisteExcepcion;
import excepciones.EspectaculoEnPaqueteErrorException;
import excepciones.EspectaculoNoExistenteException;
import excepciones.EspectadorYaRegistradoException;
import excepciones.PaqueteNoExisteException;
import excepciones.PaqueteRepetidoException;
import excepciones.PlataformaNoExisteException;
import excepciones.NombreFuncionRepetidoException;
import excepciones.PlataformaRepetidaException;
import excepciones.TicketsAgotadosParaFuncionException;
import excepciones.UsuarioNoExisteException;

public class ControladorEspectaculo implements IControladorEspectaculo {

	public ControladorEspectaculo() {
	}

	private void lanzarExcepcionUsuarioNoExiste() throws UsuarioNoExisteException {
		throw new UsuarioNoExisteException("No existe el usuario");
	}

	public Set<String> obtenerCategorias() {
		ManejadorCategoria manejC = ManejadorCategoria.getInstance();

		Set<String> res = manejC.getCategorias();

		return res;
	}

	public void altaEspectaculo(DtEspectaculo dtEsp)
			throws EspectaculoAgregadoYaExisteExcepcion, UsuarioNoExisteException, PlataformaNoExisteException {
		altaEspectaculo(dtEsp.getPlataforma(), dtEsp.getArtista(), dtEsp.getNombre(), dtEsp.getDescripcion(),
				dtEsp.getURL(), dtEsp.getCosto(), dtEsp.getCantMaxEspectadores(), dtEsp.getCantMinEspectadores(),
				dtEsp.getDuracion(), dtEsp.getFechaDeRegistro(), dtEsp.getCategorias(), dtEsp.getImagen(),
				dtEsp.getCantSorteosPorFuncion(), dtEsp.getSorteoDescripcion(), dtEsp.getVideoUrl());

	}

	public void altaEspectaculo(String plat, String art, String nom, String desc, String url, int costo, int capMax,
			int capMin, int minut, Date fecha, Set<String> cate, String imagen, int cantSorteos, String sorteoPremio,
			String video)
			throws EspectaculoAgregadoYaExisteExcepcion, UsuarioNoExisteException, PlataformaNoExisteException {

		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		ManejadorPlataforma mPlataforma = ManejadorPlataforma.getInstance();
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
		ManejadorCategoria manejC = ManejadorCategoria.getInstance();

		Espectaculo esp = manejE.getEspectaculo(nom);

		if (esp != null) {
			throw new EspectaculoAgregadoYaExisteExcepcion("Ya existe el espectaculo " + nom);
		}

		Artista arti = (Artista) manejU.obtenerUsuarioPorNickName(art);
		if (arti == null) {
			throw new UsuarioNoExisteException("No se encontro el artista con email " + art);
		}

		Map<String, Categoria> categorias = new HashMap<String, Categoria>();
		if (cate != null) {

			for (String c : cate) {
				Categoria cat = manejC.getCategoria(c);

				categorias.put(c, cat);
			}

		}

		if (imagen.isEmpty()) {
			imagen = "img/nodisponible.png";
		}

		esp = new Espectaculo(plat, art, nom, desc, url, costo, capMax, capMin, minut, fecha, categorias, imagen,
				cantSorteos);
		esp.setSorteoDescripcion(sorteoPremio);
		
		String newVideo = "";
		/*
		String videoCodigo = "";
		
		String[] link = video.split("\\/");
		
		for (String s: link) {
			videoCodigo = s;
		}
		*/
		
		String videoCodigo = video.replace("https://www.youtube.com/","");
		videoCodigo = videoCodigo.replace("https://youtu.be/","");
		videoCodigo = videoCodigo.replace("watch?v=","");
		
		if (!videoCodigo.isEmpty()) {
			newVideo = "https://www.youtube.com/embed/" + videoCodigo;
		}
		
		esp.setVideoUrl(newVideo);
		
		manejE.addEspectaculo(esp);

		// mu.agregarEspectaculoArtista(art, e); //EL MANEJADOR NO SE ENCARGA DE ESTO Y
		// LE FALTA MANEJAR EL CASO DONDE NO EXISTA EL ARTISTA.
		arti.addEspectaculo(esp);

		mPlataforma.agregarEspectaculoPlataforma(plat, esp);

	}

	public void altaPlataforma(String nombre, String url, String desc) throws PlataformaRepetidaException {
		ManejadorPlataforma mPlataforma = ManejadorPlataforma.getInstance();

		Plataforma plat = mPlataforma.getPlataforma(nombre);
		if (plat != null)
			throw new PlataformaRepetidaException("Ya existe la plataforma " + nombre);

		plat = new Plataforma(nombre, url, desc);
		mPlataforma.addPlataforma(plat);
	}

	public Set<String> listarPlataformasSet() {
		ManejadorPlataforma manejP = ManejadorPlataforma.getInstance();
		return manejP.getNombresPlataformas();
	}

	public Set<String> seleccionarPlataforma(String nombrePlataforma) {
		ManejadorPlataforma manejP = ManejadorPlataforma.getInstance();
		Plataforma plat = manejP.getPlataforma(nombrePlataforma);
		Set<String> nombres = plat.getNombresEsp();
		return nombres;
	}

	public boolean nombreFuncionExistente(String nombreFuncion) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		return manejE.existeFuncion(nombreFuncion);

	}

	public void confirmarAltaFuncion(String nomEspectaculo, String nombreFuncion, Date fecha, DtHora hora,
			Set<String> artistasNick, Date fechaAlta, String imagen)
			throws NombreFuncionRepetidoException, EspectaculoNoExistenteException {
		if (nombreFuncionExistente(nombreFuncion))
			throw new NombreFuncionRepetidoException(
					"Ya existe una funcion con el nombre '" + nombreFuncion + "' en el sistema.");

		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo esp = manejE.getEspectaculo(nomEspectaculo);

		if (esp == null)
			throw new EspectaculoNoExistenteException(
					"No existe el espectaculo '" + nomEspectaculo + "' en el sistema.");

		if (imagen.isEmpty()) {
			imagen = "img/nodisponible.png";
		}
		Funcion fun = new Funcion(nombreFuncion, fecha, hora, artistasNick, fechaAlta, imagen, esp);
		esp.agregarFuncion(fun);
		manejE.agregarFuncion(fun);
	}

	public Set<DtFuncion> seleccionarEspectaculo(String nombreEsp) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo esp = manejE.getEspectaculo(nombreEsp);
		return esp.getDtFunciones();
	}

	public Set<String> listarEspectadores() {
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
		Set<String> espectadores = manejU.getEspectadores();
		return espectadores;
	}

	public Set<String> listarEspectadoresPorNick() {
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
		Set<String> espectadores = manejU.getEspectadoresPorNick();
		return espectadores;
	}

	public Set<String> seleccionarEspectadorYFuncion(String nickEsp) throws UsuarioNoExisteException {
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();

		Espectador esp = manejU.getEspectadorPorNick(nickEsp);

		if (esp == null) {
			lanzarExcepcionUsuarioNoExiste();
		}

		Set<String> lista = esp.getRegistrosNoCanjeados();

		return lista;
	}

	public DtFuncion obtenerDatosFuncion(String nombre) throws EspectaculoNoExistenteException {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
    	
		return manejE.obtenerDatosFuncion(nombre);
	}

	public boolean formanCanjeValido(String nickEspectador, String reg1, String reg2, String reg3) {
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
		Espectador esp = manejU.getEspectadorPorNick(nickEspectador);
		Set<String> regNoCanjeados = esp.getRegistrosNoCanjeados();
		boolean r1Pertenece = regNoCanjeados.contains(reg1);
		boolean r2Pertenece = regNoCanjeados.contains(reg2);
		boolean r3Pertenece = regNoCanjeados.contains(reg3);

		boolean hayIguales = reg1.equals(reg2) || reg1.equals(reg3) || reg2.equals(reg3);
		return r1Pertenece && r2Pertenece && r3Pertenece && !hayIguales;
	}

	public void confirmarAltaRegistroAFuncion(String nombreEspectaculo, String nickEspectador, String nombreFuncion,
			Date fecRegistro, boolean quiereCanjear, String reg1, String reg2, String reg3)
			throws EspectadorYaRegistradoException, TicketsAgotadosParaFuncionException, CanjeInvalidoException,
			EspectaculoNoExistenteException {

		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo espectaculo = manejE.getEspectaculo(nombreEspectaculo);

		if (espectaculo == null)
			throw new EspectaculoNoExistenteException("No existe el espectaculo de nombre: " + nombreEspectaculo);

		Funcion fun = espectaculo.getFuncion(nombreFuncion);
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
		Espectador espectador = manejU.getEspectadorPorNick(nickEspectador);
		boolean error = espectador.estaRegistrado(nombreFuncion);

		if (error)
			throw new EspectadorYaRegistradoException(
					"El espectador " + nickEspectador + " ya esta registrado a la funcion " + nombreFuncion + ".");

		if (!error)
			error = fun.getCantidadRegistros() >= espectaculo.getCantMaxEspectadores();

		if (error)
			throw new TicketsAgotadosParaFuncionException(
					"La funcion " + nombreFuncion + " ha alcanzado su capacidad maxima.");

		boolean canjeValido = this.formanCanjeValido(nickEspectador, reg1, reg2, reg3);

		if (quiereCanjear && !canjeValido)
			throw new CanjeInvalidoException("Canje invalido.");
		float costo;
		Registro reg;
		if (!error) {

			if (quiereCanjear && canjeValido) {
				costo = 0;
				reg = new Registro(fun, espectador, fecRegistro, costo, null);
				Registro registro1 = espectador.getRegistro(reg1);
				Registro registro2 = espectador.getRegistro(reg2);
				Registro registro3 = espectador.getRegistro(reg3);

				registro1.setRegistroHijo(reg);
				registro2.setRegistroHijo(reg);
				registro3.setRegistroHijo(reg);
			} else {
				costo = espectaculo.getCosto();
				reg = new Registro(fun, espectador, fecRegistro, costo, null);
			}
			fun.agregarRegistro(reg);
			fun.incrementarRegistro();
			espectador.addRegistro(reg);
		}
	}

	public boolean canjeHabilitado(String nickEspectador) {
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();
		Espectador esp = manejU.getEspectadorPorNick(nickEspectador);

		return esp.getRegistrosNoCanjeados().size() >= 3;
	}

	public String[] listarNombresPlataformas() {
		Set<String> set = this.listarPlataformasSet();
		return set.stream().toArray(String[]::new);
	}

	public Set<String> listarPaquetes() throws PaqueteNoExisteException {
		ManejadorPaquete mPaquete = ManejadorPaquete.getInstance();
		Set<String> pqts = mPaquete.getPaquetes();
		if (pqts.isEmpty())
			throw new PaqueteNoExisteException("No hay paquetes en el sistema");
		return pqts;
	}

	public DtPaquete obtenerPaquete(String nombre) throws PaqueteNoExisteException {
		ManejadorPaquete mPaquete = ManejadorPaquete.getInstance();
		Paquete paq = mPaquete.getPaquete(nombre);

		if (paq == null)
			throw new PaqueteNoExisteException("No existe el paquete " + nombre);

		return paq.getData();
	}

	public float getCostoEspectaculo(String nombreEsp) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo esp = manejE.getEspectaculo(nombreEsp);
		return esp.getCosto();
	}

	public Set<String> listarFunciones(String nombreEsp) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo esp = manejE.getEspectaculo(nombreEsp);
		return esp.getNombresFunciones();
	}

	public Set<String> obtenerDtFuncionesAString(String nombreEsp) {

		Set<String> resultado = new HashSet<String>();
		Set<DtFuncion> dts = this.seleccionarEspectaculo(nombreEsp);
		String dato;
		Date date;
		LocalDate localDate;
		Date date2;
		LocalDate localDate2;
		int day;
		int month;
		int year;

		int day2;
		int month2;
		int year2;

		int mmm;
		String min;
		for (DtFuncion temp : dts) {

			// PARA FECHA FUNCION
			date = temp.getFecha();
			localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			day = localDate.getDayOfMonth();
			month = localDate.getMonthValue();
			year = localDate.getYear();

			// PARA FECHA ALTA
			date2 = temp.getFechaDeRegistro();
			localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			day2 = localDate2.getDayOfMonth();
			month2 = localDate2.getMonthValue();
			year2 = localDate2.getYear();

			mmm = temp.getHora().getMinutos();
			min = "";
			if (mmm < 10) {
				min = "0" + mmm;
			} else
				min = "" + mmm;

			dato = "Nombre: " + temp.getNombre() + " Fecha funcion: " + day + "/" + month + "/" + year + "  "
					+ temp.getHora().getHoras() + ":" + min + " Fecha de alta: " + day2 + "/" + month2 + "/" + year2
					+ " Cant registros: " + temp.getCantidadRegistros();
			resultado.add(dato);
		}

		return resultado;
	}

	public Set<String> obtenerEspectaculos(String plat) throws PlataformaNoExisteException {

		ManejadorPlataforma manejP = ManejadorPlataforma.getInstance();

		Set<String> colEspectaculos = manejP.obtenerEspectaculosDePlataforma(plat);

		return colEspectaculos;
	};

	public Set<DtEspectaculo> obtenerEspectaculosAceptadosPorCategoria(String categoria) {

		ManejadorEspectaculo manEsp = ManejadorEspectaculo.getInstance();
		Set<Espectaculo> espectaculos = manEsp.getEspectaculosAceptados();

		Set<DtEspectaculo> resAceptados = new HashSet<>();

		for (Espectaculo esp : espectaculos) {

			Map<String, Categoria> categorias = esp.getCategorias();
			if (categorias.containsKey(categoria)) {

				DtEspectaculo data = esp.getDt();

				resAceptados.add(data);
			}

		}

		return resAceptados;
	};

	public Set<DtEspectaculo> obtenerEspectaculosAceptadosPorPlataforma(String plataforma)
			throws PlataformaNoExisteException {
		ManejadorEspectaculo manEsp = ManejadorEspectaculo.getInstance();

		ManejadorPlataforma manPla = ManejadorPlataforma.getInstance();
		Set<String> lEspectaculos = manPla.obtenerEspectaculosDePlataforma(plataforma);

		Set<DtEspectaculo> resAceptados = new HashSet<>();

		for (String esp : lEspectaculos) {
			Espectaculo espectaculo = manEsp.getEspectaculo(esp);

			if ((espectaculo != null) && (espectaculo.estaAceptado())) {
				DtEspectaculo data = espectaculo.getDt();

				resAceptados.add(data);
			}

		}

		return resAceptados;
	};

	public DtEspectaculo getEspectaculoDt(String esp) throws EspectaculoNoExistenteException {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo espectaculo = manejE.getEspectaculo(esp);

		if (espectaculo == null) {
			throw new EspectaculoNoExistenteException("No se encontro el espectaculo " + esp);
		}

		DtEspectaculo data = espectaculo.getDt();

		return data;
	};

	public Set<String> obtenerFuncionesEspectaculo(String espe) {

		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();

		Espectaculo esp = manejE.getEspectaculo(espe);

		return esp.getNombresFunciones();

	};

	public Set<String> obtenerPaquetesEspectaculo(String espe) {

		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();

		Espectaculo esp = manejE.getEspectaculo(espe);

		Map<String, Paquete> paqs = esp.getPaqueMap();

		Set<String> res = new HashSet<String>();

		for (Map.Entry<String, Paquete> pair : paqs.entrySet()) {
			res.add(pair.getKey());
		}

		return res;

	};

	public void altaPaquete(DtPaquete data) throws PaqueteRepetidoException {
		ManejadorPaquete mPaquete = ManejadorPaquete.getInstance();
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();

		if (mPaquete.getPaquete(data.getNombre()) != null)
			throw new PaqueteRepetidoException("Ya existe un paquete con el nombre " + data.getNombre());

		Paquete paq = new Paquete(data.getNombre(), data.getDescripcion(), data.getInicio(), data.getFin(),
				data.getDescuento());
		paq.setImagen(data.getImagen());

		String[] esps = data.getEspectaculos();

		for (int i = 0; i < esps.length; i++) {

			Espectaculo esp = manejE.getEspectaculo(esps[i]);
			if (esp != null) {
				paq.agregarEspectaculo(esp);
				esp.agregarPaquete(paq);
			}

		}

		mPaquete.addPaquete(paq);
	}

	public void altaCategoria(String nombre) throws CategoriaRepetidaException, CategoriaConNombreVacioException {
		ManejadorCategoria manCat = ManejadorCategoria.getInstance();
		if (manCat.getCategoria(nombre) != null)
			throw new CategoriaRepetidaException("Ya existe una categoría con el nombre " + nombre);
		if (nombre.equals(""))
			throw new CategoriaConNombreVacioException("El nombre no puede ser vacio");
		Categoria cat = new Categoria(nombre);
		manCat.addCategoria(cat);
	}

	public Set<String> obtenerEspectaculosIngresados() {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		return manejE.getEspectaculosIngresados();
	}

	public Set<String> obtenerEspectaculosAceptados() {

		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();

		return manejE.getNombresEspectaculosAceptados();

	};

	public void aceptarEspectaculo(String espe) throws EspectaculoNoExistenteException {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo esp = manejE.getEspectaculo(espe);

		if (esp == null) {
			throw new EspectaculoNoExistenteException("No existe el espectaculo con nombre " + espe);
		}

		esp.cambiarEstado(true);
	}

	public void rechazarEspectaculo(String espe) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		ManejadorPlataforma manejP = ManejadorPlataforma.getInstance();
		Espectaculo esp = manejE.getEspectaculo(espe);
		esp.cambiarEstado(false);
		manejE.moverAPapelera(espe);
		String plat = esp.getPlataforma();
		manejP.destruirEspectaculo(plat, espe);

	}

	public void finalizarEspectaculo(String espe, String art, Date cuando)
			throws EspectaculoNoExistenteException, UsuarioNoExisteException {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo esp = manejE.getEspectaculo(espe);

		if (esp == null) {
			throw new EspectaculoNoExistenteException("No se encontro el espectaculo");
		}

		ManejadorUsuario manUsu = ManejadorUsuario.getInstance();
		Usuario usu = manUsu.obtenerUsuario(art);

		if (usu == null) {
			throw new UsuarioNoExisteException("No se encontro el usuario");
		}

		boolean pertenece = ((Artista) usu).getEspectaculos().containsKey(esp.getNombre());
		if (!pertenece) {
			throw new EspectaculoNoExistenteException("El espectaculo seleccionado no pertenece al artista");
		}

		esp.finalizarEspectaculo(cuando);
		// algo de bases de datos seguramente
	}

	public DtPlataforma obtenerPlataformaDt(String nom) {
		DtPlataforma data;

		ManejadorPlataforma manejP = ManejadorPlataforma.getInstance();
		Plataforma plat = manejP.getPlataforma(nom);
		data = plat.getDt();

		return data;
	}

	public List<String> obtenerArtistasFuncion(String nombreFun) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		return manejE.obtenerArtistasFuncion(nombreFun);
	}

	public Set<DtEspectaculo> obtenerEspectaculosAceptadosDt() {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Set<Espectaculo> espectaculos = manejE.getEspectaculosAceptados();

		Set<DtEspectaculo> dts = new HashSet<DtEspectaculo>();

		for (Espectaculo esp : espectaculos) {
			dts.add(esp.getDt());
		}

		return dts;
	}

	public Set<DtPaquete> getPaquetes() {

		ManejadorPaquete manejP = ManejadorPaquete.getInstance();
		Set<DtPaquete> res = manejP.getDataPaquetes();
		return res;

	}

	public List<String> sortearPremios(Funcion fun) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		Espectaculo esp = manejE.getEspectaculo(fun.getNombreEspectaculo());
		Premio pre;
		Date date = new Date();
		List<Registro> registros = fun.getRegistros();
		Random rand = new Random(); // instance of random class
		Registro[] values = new Registro[registros.size()];
		registros.toArray(values);
		int indice;
		Registro randomR;

		List<String> ganadores = new ArrayList<String>();

		fun.realizarSorteo();// pone sorteado en true
		if (values.length <= esp.getCantSorteosPorFuncion()) {// si no hay suficientes registros premio para todos
			for (int i = 0; i < values.length; i++) {
				randomR = (Registro) values[i];
				Espectador espec = randomR.getEsp();
				pre = new Premio(date, espec, fun, esp.getSorteoDescripcion());
				espec.agregarPremio(pre);
				fun.agregarPremio(pre);
				ganadores.add(espec.getNickName());
			}
		} else { // sorteo posta
			for (int i = 0; i < esp.getCantSorteosPorFuncion(); i++) {
				indice = rand.nextInt(values.length);
				randomR = (Registro) values[indice];
				if (randomR == null) {
					i -= 1;
				} else {// una vez tengo el sorteado, lo saco del arreglo y le doy su premio
					values[indice] = null;
					Espectador espec = randomR.getEsp();

					pre = new Premio(date, espec, fun, esp.getSorteoDescripcion());
					espec.agregarPremio(pre);
					fun.agregarPremio(pre);
					ganadores.add(espec.getNickName());
				}
			}
		}
		return ganadores;
	}

	public Set<DtEspectaculo> espectaculosParaPaqueteEnPlataforma(String paquete, String plataforma)
			throws EspectaculoNoExistenteException {
		ManejadorPlataforma manejPl = ManejadorPlataforma.getInstance();
		Plataforma plat = manejPl.getPlataforma(plataforma);
		Set<Espectaculo> espectaculos = plat.getEspectaculosSet();
		Set<DtEspectaculo> resultado = new HashSet<DtEspectaculo>();

		// Aceptados que no estan en paquete
		for (Espectaculo espectaculo : espectaculos)
			if (espectaculo.estaAceptado() && !espectaculo.getPaqueMap().containsKey(paquete))
				resultado.add(espectaculo.getDt());

		if (resultado.isEmpty())
			throw new EspectaculoNoExistenteException(
					"No hay espectaculos disponibles para agregar a '" + paquete + "' en '" + plataforma + "'");

		return resultado;
	}

	public void agregarEspectaculoAPaquete(String espectaculo, String paquete)
			throws EspectaculoEnPaqueteErrorException, EspectaculoNoExistenteException, PaqueteNoExisteException {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		ManejadorPaquete manejP = ManejadorPaquete.getInstance();

		Espectaculo espect = manejE.getEspectaculo(espectaculo);
		Paquete paquet = manejP.getPaquete(paquete);

		if (paquete == null)
			throw new PaqueteNoExisteException("No se ha encontrado el paquete '" + paquete + "'");

		if (espect == null)
			throw new EspectaculoNoExistenteException("No se ha encontrado el espectáculo '" + espectaculo + "'");

		if (!espect.estaAceptado())
			throw new EspectaculoEnPaqueteErrorException("El espectáculo no está aceptado");

		if (espect.getPaqueMap().containsKey(paquete))
			throw new EspectaculoEnPaqueteErrorException("El espectáculo ya pertenece al paquete");

		espect.agregarPaquete(paquet);
		paquet.agregarEspectaculo(espect);

	}

	public void sorteoArreglado(String funcion, String[] ganadores, Date cuando) {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		ManejadorUsuario manejU = ManejadorUsuario.getInstance();

		Funcion fun = manejE.getFuncion(funcion);
		Premio pre;
		Espectador esp;
		Espectaculo espectaculoAux = manejE.getEspectaculo(fun.getNombreEspectaculo());

		for (String ganador : ganadores) {
			esp = manejU.getEspectador(ganador);
			pre = new Premio(cuando, esp, fun, espectaculoAux.getSorteoDescripcion());
			esp.agregarPremio(pre);
			fun.agregarPremio(pre);
		}

		fun.realizarSorteo();
	}

	public Set<DtFuncion> getDtFunciones() {
		ManejadorEspectaculo manejE = ManejadorEspectaculo.getInstance();
		return manejE.obtenerFunciones();
	}
}

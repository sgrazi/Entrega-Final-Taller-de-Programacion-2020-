package logica;

import excepciones.CanjeInvalidoException;
import excepciones.CategoriaConNombreVacioException;
import excepciones.CategoriaRepetidaException;
import excepciones.EspectadorYaRegistradoException;
import excepciones.NombreFuncionRepetidoException;
import excepciones.PaqueteNoExisteException;
import excepciones.PaqueteRepetidoException;
import excepciones.PlataformaNoExisteException;

import java.util.Date;
import java.util.List;
import java.util.Set;

import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import datatypes.DtHora;
import datatypes.DtPaquete;
import datatypes.DtPlataforma;
import excepciones.EspectaculoAgregadoYaExisteExcepcion;
import excepciones.EspectaculoEnPaqueteErrorException;
import excepciones.EspectaculoNoExistenteException;
import excepciones.PlataformaRepetidaException;
import excepciones.TicketsAgotadosParaFuncionException;
import excepciones.UsuarioNoExisteException;

public interface IControladorEspectaculo {

	public abstract void altaPlataforma(String nombre, String url, String descripcion)
			throws PlataformaRepetidaException;

	public abstract Set<String> obtenerCategorias();

	public abstract Set<String> seleccionarPlataforma(String nombrePlataforma);

	public abstract void confirmarAltaFuncion(String nomEspectaculo, String nombreFuncion, Date fecha, DtHora hora,
			Set<String> artistas, Date fechaAlta, String imagen)
			throws NombreFuncionRepetidoException, EspectaculoNoExistenteException;

	public abstract Set<DtFuncion> seleccionarEspectaculo(String nombreEsp);

	public abstract Set<String> listarEspectadores();
	
	public abstract Set<DtPaquete> getPaquetes();

	public abstract Set<String> seleccionarEspectadorYFuncion(String nomEsp) throws UsuarioNoExisteException;

	public abstract DtPlataforma obtenerPlataformaDt(String nom);

	public abstract DtFuncion obtenerDatosFuncion(String nombre) throws EspectaculoNoExistenteException;

	public abstract boolean formanCanjeValido(String nombreEspectador, String reg1, String reg2, String reg3);

	public abstract void confirmarAltaRegistroAFuncion(String nombreEspectaculo, String nombreEspectador,
			String nombreFuncion, Date fecRegistro, boolean quiereCanjear, String reg1, String reg2, String reg3)
			throws EspectadorYaRegistradoException, TicketsAgotadosParaFuncionException, CanjeInvalidoException,
			EspectaculoNoExistenteException;

	public abstract boolean canjeHabilitado(String nombreEspectador);

	public abstract String[] listarNombresPlataformas();

	public abstract float getCostoEspectaculo(String nombreEsp);

	public abstract boolean nombreFuncionExistente(String nombreFuncion);

	public abstract Set<String> listarFunciones(String nombreEsp);

	public abstract Set<String> obtenerDtFuncionesAString(String nombreEsp);

	public abstract Set<String> listarPlataformasSet();

	public abstract void altaEspectaculo(DtEspectaculo dtEsp) throws EspectaculoAgregadoYaExisteExcepcion, UsuarioNoExisteException, PlataformaNoExisteException;
	
	public abstract void altaEspectaculo(String plat, String art, String nom, String desc, String url, int costo, int capMax,
			int capMin, int minut, Date fecha, Set<String> cate, String imagen, int cantSorteos, String sorteoPremio, String video)
			throws EspectaculoAgregadoYaExisteExcepcion, UsuarioNoExisteException, PlataformaNoExisteException;

	public abstract Set<String> obtenerEspectaculos(String plat) throws PlataformaNoExisteException;

	public abstract DtEspectaculo getEspectaculoDt(String esp) throws EspectaculoNoExistenteException;

	public abstract Set<String> obtenerFuncionesEspectaculo(String esp);

	public abstract Set<String> obtenerPaquetesEspectaculo(String esp);

	public abstract Set<String> listarPaquetes() throws PaqueteNoExisteException;

	public abstract DtPaquete obtenerPaquete(String nombre) throws PaqueteNoExisteException;

	public abstract Set<String> listarEspectadoresPorNick();

	public abstract void altaPaquete(DtPaquete paq) throws PaqueteRepetidoException;

	public abstract Set<String> obtenerEspectaculosIngresados();

	public abstract Set<String> obtenerEspectaculosAceptados();

	public abstract void altaCategoria(String nombre) throws CategoriaRepetidaException, CategoriaConNombreVacioException;

	public abstract void aceptarEspectaculo(String esp) throws EspectaculoNoExistenteException;

	public abstract void rechazarEspectaculo(String esp);
	
	public abstract void finalizarEspectaculo(String esp, String art, Date cuando) throws EspectaculoNoExistenteException, UsuarioNoExisteException;

	public abstract List<String> obtenerArtistasFuncion(String esp);

	public abstract Set<DtEspectaculo> obtenerEspectaculosAceptadosPorPlataforma(String plataforma)
			throws PlataformaNoExisteException;

	public abstract Set<DtEspectaculo> obtenerEspectaculosAceptadosPorCategoria(String categoria);

	public abstract Set<DtEspectaculo> obtenerEspectaculosAceptadosDt();
	
	public abstract List<String> sortearPremios(Funcion fun);
	
	public abstract Set<DtEspectaculo> espectaculosParaPaqueteEnPlataforma(String paquete, String plataforma) throws EspectaculoNoExistenteException;
	
	public abstract void agregarEspectaculoAPaquete(String espectaculo, String paquete)
			throws EspectaculoEnPaqueteErrorException, EspectaculoNoExistenteException, PaqueteNoExisteException;
	
	public abstract void sorteoArreglado(String funcion, String[] ganadores, Date cuando);
	
	public abstract Set<DtFuncion> getDtFunciones();
}

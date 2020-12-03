package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import datatypes.*;
import excepciones.CanjeInvalidoException;
import excepciones.CategoriaConNombreVacioException;
import excepciones.CategoriaRepetidaException;
import excepciones.EspectaculoAgregadoYaExisteExcepcion;
import excepciones.EspectaculoNoExistenteException;
import excepciones.EspectadorYaRegistradoException;
import excepciones.NombreFuncionRepetidoException;
import excepciones.PaqueteRepetidoException;
import excepciones.PlataformaNoExisteException;
import excepciones.PlataformaRepetidaException;
import excepciones.PuntajeInvalidoException;
import excepciones.TicketsAgotadosParaFuncionException;
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.UsuarioNoEsEspectadorException;
import excepciones.UsuarioNoExisteException;
import logica.Configuracion;
import logica.Fabrica;
import logica.IControladorEspectaculo;
import logica.IControladorUsuario;
import publicar.WebServices;

import java.awt.event.ActionListener;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;

import java.util.Set;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Principal {

	private JFrame frame;
	
	private Configuracion config;
	private frmConfiguracion frmConfig;
	private static AltaUsuario altaUsuarioInternalFrame;
	private static ModificarUsuario modificarUsuarioInternalFrame;
	private static consultarUsuario consultarUsuarioDatos;
	private static seleccionarUsuarioModificar seleccionarUsuarioParaModificar;
	public static ConsultaUsuario seleccionarUsuario;
		
	private static ConsultarPaquete consultarPaqueteDatos;
		 
	private ConsultarPaquete conPaqInternalFrame;
	private AltaPlataforma altPlatInternalFrame;
	private static ConsultaDeFuncionAEspectaculo conFunInternalFrame;
	private static ConsultaDeFuncionAEspectaculo consultarFuncion;
	private AltaEspectaculo altEspectaculoFrame;
	private ConsultaEspectaculo conEspectaculoInternalFrame;
	private static ConsultaEspectaculo consultarEspectaculo;
		
	private RegistroAFuncionDeEspectaculo registroAFuncionInternalFrame;
	private AltaDeFuncionDeEspectaculo altaFuncionInternalFrame;
	private static EspectaculoSeleccionar espectaculoSeleccionar;
	private static FuncionSeleccionar funcionSeleccionar;
	
	//nuevos parte 2 lab
	private static AceptarEspectaculo aceptarEspectaculo;
	private AltaCategoria altCategoria;
	private JLabel lblMensajePrincipal;
	
	private boolean datosCargados;
	
	//CONTROLADORES
	private IControladorUsuario IU;
	private IControladorEspectaculo IE;
	
	//DATOS UTILES PARA CASOS DE PRUEBA
	//CATEGORIAS
	private String c1 = "Bandas Latinas";
	private String c2 = "Solistas";
	private String c3 = "Rock en Inglés";
	private String c4 = "Música Tropical";

	//DATOS UTILES ARTISTAS

	private String VP = "vpeople";
	private String DM = "dmode";
	private String CL = "clauper";
	private String BS = "bruceTheBoss";
	private String TN = "tripleNelson";
	private String LL = "la_ley";
	private String PI = "lospimpi";
	private String DY = "dyangounchained";
	private String AL = "alcides";

	//DATOS UTILES ESPECTACULOS

	private String E1 = "Los Village Volvieron";
	private String E2 = "Global Spirit";
	private String E3 = "Memphis Blues World";
	private String E4 = "Springsteen on Broadway";
	private String E5 = "Bien de Familia";
	private String E6 = "30 años";
	private String E7 = "Grandes Exitos 2020";
	private String E8 = "Llego a casa";
	private String E9 = "Nochebuena con Alcides y amigos";
	private String E10 = "Fin de Año con Alcides y amigos";

	//DATOS UTILES FUNCIONES

	private String F1 = "Los Village Volvieron - 1";
	private String F2 = "Los Village Volvieron - 2";
	private String F3 = "Los Village Volvieron - 3";
	private String F4 = "Global Spirit (I)";
	private String F5 = "Global Spirit (II)";
	private String F6 = "Global Spirit (III)";
	private String F7 = "Memphis Blues World - A";
	private String F8 = "Memphis Blues World - B";
	private String F9 = "Memphis Blues World - C";
	private String F10 = "Springsteen on Broadway - i";
	private String F11 = "Springsteen on Broadway - ii";
	private String F12 = "Springsteen on Broadway - iii";
	private String F13 = "Bien de Familia - A";
	private String F14 = "Bien de Familia - B";
	private String F15 = "Bien de Familia - C";
	private String F16 = "30 años - 1";
	private String F17 = "30 años - 2";
	private String F18 = "30 años - 3";
	private String F19 = "Grandes Éxitos 2020 - Dia";
	private String F20 = "Grandes Éxitos 2020 - Noche";
	private String F21 = "Llega a Casa - 1";
	private String F22 = "Llega a Casa - 2";
	

	//DATOS UTILES ESPECTADORES

	private String EL = "eleven11";
	private String CO = "costas";
	private String EW = "watson";   //EN EL EVA DICE WASTON PERO ME PARECE QUE SE EQUIVOCARON Y ES WATSON
	private String GH = "house";
	private String SP = "sergiop";
	private String AR = "chino";
	private String AP = "tonyp";
	private String ML = "lachiqui";
	private String CB = "cbochinche";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
					//EL WS SE CARGA AL FINAL DEL CONSTRUCTOR AHORA
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public Principal() {
		initialize();

		// TOMO CONTROLADORES DE LA FABRICA
        Fabrica fabrica = Fabrica.getInstance();
        IU = fabrica.getIControladorUsuario();
        IE = fabrica.getIControladorEspectaculo();
        
        datosCargados = false;
        
        aceptarEspectaculo = new AceptarEspectaculo(IE);
        aceptarEspectaculo.setLocation(33, 29);
        aceptarEspectaculo.setVisible(false);
        frame.getContentPane().add(aceptarEspectaculo);
        
        altCategoria = new AltaCategoria(IE);
        altCategoria.setLocation(33, 29);
        altCategoria.setVisible(false);
        frame.getContentPane().add(altCategoria);
        
        consultarFuncion = new ConsultaDeFuncionAEspectaculo(IE);
        consultarFuncion.setLocation(33, 29);
        consultarFuncion.setVisible(false);
                
        funcionSeleccionar = new FuncionSeleccionar();
        funcionSeleccionar.setLocation(33, 29);
        funcionSeleccionar.setVisible(false);
        
        espectaculoSeleccionar = new EspectaculoSeleccionar(IE);
        espectaculoSeleccionar.setLocation(33, 29);
        espectaculoSeleccionar.setVisible(false);
        
        altaUsuarioInternalFrame = new AltaUsuario(IU);
        altaUsuarioInternalFrame.setLocation(33, 29);
        altaUsuarioInternalFrame.setVisible(false);

        consultarUsuarioDatos = new consultarUsuario(IU);
        consultarUsuarioDatos.setLocation(33, 29);
        consultarUsuarioDatos.setVisible(false);
        
        seleccionarUsuarioParaModificar = new seleccionarUsuarioModificar(IU);
        seleccionarUsuarioParaModificar.setLocation(33, 29);
        seleccionarUsuarioParaModificar.setVisible(false);
        
        modificarUsuarioInternalFrame = new ModificarUsuario(IU);
        modificarUsuarioInternalFrame.setLocation(33, 29);
        modificarUsuarioInternalFrame.setVisible(false);
        
        consultarPaqueteDatos = new ConsultarPaquete(IE);
        consultarPaqueteDatos.setLocation(33, 29);
        consultarPaqueteDatos.setVisible(false);
                
        seleccionarUsuario = new ConsultaUsuario(IU);
        seleccionarUsuario.setLocation(33, 29);
        seleccionarUsuario.setVisible(false);
                        
		altPlatInternalFrame = new AltaPlataforma();
		altPlatInternalFrame.setVisible(false);
		
		conFunInternalFrame = new ConsultaDeFuncionAEspectaculo(IE);
		conFunInternalFrame.setVisible(false);
		
		altEspectaculoFrame = new AltaEspectaculo(IE, IU);
		altEspectaculoFrame.setLocation(33, 29);
		altEspectaculoFrame.setVisible(false);
		
		conEspectaculoInternalFrame = new ConsultaEspectaculo(IE);
		conEspectaculoInternalFrame.setLocation(33, 29);
		conEspectaculoInternalFrame.setVisible(false);
		
		consultarEspectaculo = new ConsultaEspectaculo(IE);
		consultarEspectaculo.setLocation(33, 29);
		consultarEspectaculo.setVisible(false);
				
		registroAFuncionInternalFrame = new RegistroAFuncionDeEspectaculo(IE);
		registroAFuncionInternalFrame.setLocation(33, 29);
		registroAFuncionInternalFrame.setVisible(false);
		
		altaFuncionInternalFrame = new AltaDeFuncionDeEspectaculo(IE, IU);
		altaFuncionInternalFrame.setLocation(33, 29);
		altaFuncionInternalFrame.setVisible(false);
		
		frmConfig = new frmConfiguracion();
		frmConfig.setLocation(33, 29);
		frmConfig.setVisible(false);
		
		conPaqInternalFrame = new ConsultarPaquete(IE);
		conPaqInternalFrame.setVisible(false);
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(altPlatInternalFrame);

		frame.getContentPane().add(frmConfig);
		frame.getContentPane().add(conPaqInternalFrame);
		frame.getContentPane().add(conFunInternalFrame);
		frame.getContentPane().add(altEspectaculoFrame);
		frame.getContentPane().add(conEspectaculoInternalFrame);
		frame.getContentPane().add(registroAFuncionInternalFrame);
		frame.getContentPane().add(altaFuncionInternalFrame);
		frame.getContentPane().add(altaUsuarioInternalFrame);
		frame.getContentPane().add(seleccionarUsuario);
		frame.getContentPane().add(espectaculoSeleccionar);
		frame.getContentPane().add(funcionSeleccionar);
		frame.getContentPane().add(consultarFuncion);
		frame.getContentPane().add(consultarEspectaculo);
		frame.getContentPane().add(consultarUsuarioDatos);
		frame.getContentPane().add(consultarPaqueteDatos);
		frame.getContentPane().add(seleccionarUsuarioParaModificar);
		frame.getContentPane().add(modificarUsuarioInternalFrame);
		
		lblMensajePrincipal = new JLabel("New label");
		lblMensajePrincipal.setBounds(10, 4, 547, 14);
		frame.getContentPane().add(lblMensajePrincipal);
		
		config = new Configuracion();
		WebServices pub = new WebServices();
		try {
			this.config.load();
			pub.publicar(this.config);
			
			this.lblMensajePrincipal.setText("");
		} catch (Exception e) {
			
			this.lblMensajePrincipal.setText("No es posible publicar el web service. La direccion " + config.getWs() + " esta en uso por otro publicador");
		}	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("CoronaTickets admin");
		frame.setBounds(100, 100, 800, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuInicio = new JMenu("Inicio");
		menuBar.add(menuInicio);

		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 frame.setVisible(false);
	             frame.dispose();
			}
		});
		
		JMenuItem mntmCargarDatosBasicos = new JMenuItem("Cargar Datos Basicos");
		mntmCargarDatosBasicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!datosCargados) {
					try {
						cargarDatosBasicos();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					datosCargados = true;
				}
			}
		});
		menuInicio.add(mntmCargarDatosBasicos);
		
		JSeparator separator_1 = new JSeparator();
		menuInicio.add(separator_1);
		
		JMenuItem menuConfiguracion = new JMenuItem("Configuracion");
		menuConfiguracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmConfig.cargoForm(config);
				frmConfig.setVisible(true);
			}
		});
		menuInicio.add(menuConfiguracion);
		
		JMenuItem menuGetLink = new JMenuItem("Generar Link Registros");
		menuGetLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String link = "/consultaRegistros?linkId=" + IU.generarLinkAdmin();
				
				JPanel cont = new JPanel();
				cont.add(new JLabel("Dirigirse a la siguiente ruta en CoronaTickets: "));
				cont.add(new JTextField(link));
				JOptionPane.showMessageDialog(frame, cont, "Acceder a Registros", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuInicio.add(menuGetLink);
		
		JSeparator separator = new JSeparator();
		menuInicio.add(separator);
		menuInicio.add(menuSalir);

		JMenu menuPaquetes = new JMenu("Paquetes");
		menuBar.add(menuPaquetes);

		JMenuItem menuConsPaq = new JMenuItem("Consultar Paquete");
		menuConsPaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mostrar Consulta de Paquete
				conPaqInternalFrame.cargarPaquetes();
			}
		});
		menuPaquetes.add(menuConsPaq);

		JMenu menuFunciones = new JMenu("Funciones");
		menuBar.add(menuFunciones);

		JMenuItem menuConsFun = new JMenuItem("Consultar Funcion en Espectaculo");
		menuConsFun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mostrar Consulta de Funcion en Espectaculo
				conFunInternalFrame.cargarPlataformas();
				conFunInternalFrame.setVisible(true);
			}
		});
		
		
		JMenuItem mntmAltaFuncionDe = new JMenuItem("Alta funcion de Espectaculo");
		mntmAltaFuncionDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para ver la lista de todas las plataformas,
                // cargando previamente la lista
				altaFuncionInternalFrame.cargarPlataformas();
				altaFuncionInternalFrame.setVisible(true);
			}
		});
		menuFunciones.add(mntmAltaFuncionDe);
		menuFunciones.add(menuConsFun);
		JMenu menuEspectaculo = new JMenu("Espectaculos");
		menuBar.add(menuEspectaculo);
		
		JMenuItem menuAltaEspectaculo = new JMenuItem("Alta Espectaculo");
		menuAltaEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Mostrar Alta Espectaculo
				altEspectaculoFrame.setVisible(true);
			}
		});
		
		menuEspectaculo.add(menuAltaEspectaculo);
		
		JMenuItem menuAceptarRechazar = new JMenuItem("Aceptar/Rechazar Espectaculos Ingresados");
		menuAceptarRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptarEspectaculo.llenarComboEspectaculo();
				aceptarEspectaculo.setVisible(true);
			}
		});

		menuEspectaculo.add(menuAceptarRechazar);
		
		JMenuItem menuConsultaEspectaculo = new JMenuItem("Consulta Espectaculo");
		menuEspectaculo.add(menuConsultaEspectaculo);
		menuConsultaEspectaculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Mostrar Consulta Espectaculo
				conEspectaculoInternalFrame.setVisible(true);
			}
		});
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mnNuevoUsuario = new JMenuItem("Nuevo");
		mnNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaUsuarioInternalFrame.setVisible(true);
			}
		});
		mnUsuarios.add(mnNuevoUsuario);
		
		JMenuItem mnConsultarUsuario = new JMenuItem("Consultar");
		mnConsultarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				seleccionarUsuario.setVisible(true);
				
			}
		});
		mnUsuarios.add(mnConsultarUsuario);
		
		JMenuItem mModificarUsuario = new JMenuItem("Modificar");
		mModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarUsuarioParaModificar.setVisible(true);
			}
		});
		mnUsuarios.add(mModificarUsuario);
		
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		JMenuItem mntmRegistroAFuncion = new JMenuItem("Registro a funcion de espectaculo");
		mntmRegistroAFuncion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para ver la lista de todas las plataformas,
                // cargando previamente la lista
				registroAFuncionInternalFrame.cargarPlataformas();
				registroAFuncionInternalFrame.cargarEspectadores();
				registroAFuncionInternalFrame.setVisible(true);
			}
		});
		mnRegistro.add(mntmRegistroAFuncion);
		
		JMenu menuCategoria = new JMenu("Categorias");
		menuBar.add(menuCategoria);
		
		JMenuItem mnAltaCat = new JMenuItem("Alta de Categoria");
		mnAltaCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Muestro el InternalFrame para ver la lista de todas las plataformas,
                // cargando previamente la lista
				altCategoria.setVisible(true);
			}
		});
		menuCategoria.add(mnAltaCat);
		
	}
	
	public static void ConsultarPaquete(String nom) {
		consultarPaqueteDatos.cargoVistaConsulta(nom);
		consultarPaqueteDatos.setLocation(seleccionarUsuario.getX()*22,seleccionarUsuario.getY() * 6);
		consultarPaqueteDatos.setVisible(true);
		
	}
	
	public static void ModificarUsuario(DtUsuario usuario) {
		modificarUsuarioInternalFrame.cargoFormulario(usuario);
		modificarUsuarioInternalFrame.setVisible(true);
	}
	
	public static void ConsultarUsuario(DtUsuario usuario) {
		consultarUsuarioDatos.cargoFormulario(usuario);
		consultarUsuarioDatos.setVisible(true);
		
		espectaculoSeleccionar.setVisible(false);
		funcionSeleccionar.setVisible(false);
		consultarFuncion.setVisible(false);
		consultarEspectaculo.setVisible(false);
		
		if (usuario instanceof DtArtista) {
			String[] lista = ((DtArtista) usuario).getEspectaculos();
			
			SeleccionarEspectaculo(lista);
		} else {
			String[] lista = ((DtEspectador) usuario).getFunciones();
			
			SeleccionarFuncion(lista);
		}
		
		funcionSeleccionar.setLocation(consultarUsuarioDatos.getX() * 17,consultarUsuarioDatos.getY());
		consultarFuncion.setLocation(consultarUsuarioDatos.getX() * 17,funcionSeleccionar.getY() * 10);
		
		espectaculoSeleccionar.setLocation(consultarUsuarioDatos.getX() * 17,consultarUsuarioDatos.getY());
	    consultarEspectaculo.setLocation(consultarUsuarioDatos.getX() * 17,espectaculoSeleccionar.getY() * 10);
	}
	
	public static void ConsultarFuncion(String funcion) throws EspectaculoNoExistenteException {
		consultarFuncion.setLocation(33, 29);
		consultarFuncion.cargoVistaConsulta(funcion);
		consultarFuncion.setVisible(true);
	}
	
	public static void ConsultarEspectaculo(String espectaculo) {
		consultarFuncion.setLocation(33, 29);
		consultarEspectaculo.cargoVistaConsulta(espectaculo);
		consultarEspectaculo.setVisible(true);
	}
	
	public static void cerrarTodasLasVentanasDeConsultarUsuario() {
		consultarUsuarioDatos.setVisible(false);
		seleccionarUsuario.setVisible(false);
		espectaculoSeleccionar.setVisible(false);
		funcionSeleccionar.setVisible(false);
		consultarFuncion.setVisible(false);
		consultarEspectaculo.setVisible(false);
	}
	
	public static void SeleccionarEspectaculo(String[] espectaculos) {
		espectaculoSeleccionar.setLocation(33, 29);
	    espectaculoSeleccionar.cargarEspectaculos(espectaculos);
	    espectaculoSeleccionar.setVisible(true);
	}
	
	public static void SeleccionarFuncion(String[] funciones) {
		funcionSeleccionar.setLocation(33, 29);
		funcionSeleccionar.cargarFunciones(funciones);
		funcionSeleccionar.setVisible(true);
	}
	
	private void cargarDatosBasicos() throws EspectaculoNoExistenteException, NombreFuncionRepetidoException {
		try {
			
			datosDePruebaCategorias();
			datosDePruebaUsuarios();
			datosDePruebaSeguirUsuarios();
			datosDePruebaPlataformas();
			datosDePruebaEspectaculos();
			datosDePruebaFunciones();
			datosDePruebaCajes();
			datosDePruebaPaquetes();
			datosDePruebaSorteos();
			datosDePruebaFavoritos();
			datosDePruebaEstadosEspectaculos();
			datosDePruebaValoraciones();
			
		} catch (CategoriaRepetidaException | CategoriaConNombreVacioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsuarioAgregarDatosInvalidosException | UsuarioAgregarYaExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlataformaRepetidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectaculoAgregadoYaExisteExcepcion | PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EspectadorYaRegistradoException | TicketsAgotadosParaFuncionException | CanjeInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PaqueteRepetidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PuntajeInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void datosDePruebaCategorias() throws CategoriaRepetidaException, CategoriaConNombreVacioException {
		//CARGAR CATEGORIAS
		IE.altaCategoria(c1);
		IE.altaCategoria(c2);
		IE.altaCategoria(c3);
		IE.altaCategoria(c4);
	}
	
	private void datosDePruebaUsuarios() throws UsuarioAgregarDatosInvalidosException, UsuarioAgregarYaExisteException {
		Calendar c = Calendar.getInstance();

		//CARGAR ESPECTADORES
		c.set(1971, 12 - 1, 31);
		IU.confirmarAlta(false, EL, "Eleven", "Ten", "eleven11@gmail.com", c.getTime(), "", "", "","lkj34df","");
		c.set(1983, 11 - 1, 15);
		IU.confirmarAlta(false, CO, "Gerardo", "Costas", "gcostas@gmail.com", c.getTime(), "", "", "","poke579","");
		c.set(1990, 4 - 1, 15);
		IU.confirmarAlta(false, EW, "Emma", "Watson", "e.watson@gmail.com", c.getTime(), "", "", "","mkji648","https://bit.ly/3jrashA");
		c.set(1959, 5 - 1, 15);
		IU.confirmarAlta(false, GH, "Gregory", "House", "greghouse@gmail.com", c.getTime(), "", "", "","fcku0123","https://bit.ly/3ng8YZE");
		c.set(1950, 1 - 1, 28);
		IU.confirmarAlta(false, SP, "Sergio", "Puglia", "puglia@alpanpan.com.uy", c.getTime(), "", "", "","vbmn4r","https://bit.ly/2EViUGV");
		c.set(1976, 3 - 1, 17);
		IU.confirmarAlta(false, AR, "Alvaro", "Recoba", "chino@trico.org.uy", c.getTime(), "", "", "","ncnl123","https://bit.ly/3cTJWuX");
		c.set(1955, 2 - 1, 14);
		IU.confirmarAlta(false, AP, "Antonio", "Pacheco", "eltony@manya.org.uy", c.getTime(), "", "", "","mny101","https://bit.ly/3cS2bkh");
		c.set(1927, 2 - 1, 23);
		IU.confirmarAlta(false, ML, "Mirtha", "Legrand", "lachiqui@hotmail.com.ar", c.getTime(), "", "", "","1o1vbm","");
		c.set(1937, 5 - 1, 8);
		IU.confirmarAlta(false, CB, "Cacho", "Bochinche", "cbochinche@vera.com.uy", c.getTime(), "", "", "","ultraton01","");
		
		//CARGAR ARTISTAS
	
		String des = "Village People es una innovadora formacion musical de estilo disco de finales de los años 70. Fue famosa tanto por sus peculiares disfraces, como por sus canciones pegadizas, con letras sugerentes y llenas de dobles sentidos.";
		String bio = "Grupo americano del disco creado por Jacques Morali y Henry Belolo en 1977. Segun Marjorie Burgess, todo comenzo cuando Morali fue a un bar gay de Nueva York una noche y noto al bailarin Felipe Rose vestido como un nativo americano.";
		String web = "www.officialvillagepeople.com\r\n";
		
		//USUARIO DE PRUEBA MIO
		c.set(1977, 1 - 1, 1);
		IU.confirmarAlta(true, "ro", "ro", "ro", "ro", c.getTime(), des, bio, web,"ro","https://bit.ly/36uctpI");
		//FIN USUARIO DE PRUEBA
		c.set(1977, 1 - 1, 1);
		IU.confirmarAlta(true, VP, "Village", "People", "vpeople@tuta.io", c.getTime(), des, bio, web,"asdfg456","https://bit.ly/36uctpI");
		
		des = "Depeche Mode es un grupo ingles de música electrónica formado en Basildon, Essex, en 1980 por Vicent Clarke y Andrew John Fletcher, a los que se unieron Martin Lee Gore y poco despues David Gahan. Actualmente se le considera como grupo de música alternativa.";
		bio = "";
		web = "www.depechemode.com";
		
		c.set(1980, 6 - 1, 14);
		IU.confirmarAlta(true, DM, "Depeche", "Mode", "dmode@tuta.io", c.getTime(), des, bio, web,"123rtgfdv","https://bit.ly/2GB7vME");
		
		des = "Cynthia Ann Stephanie Lauper, conocida simplemente como Cyndi Lauper, es una cantartistaa, actriz y empresaria estadounidense. Despues de participar en el grupo musical, Blue Angel, en 1983 firmo con Portrait Records (filial de Epic Records) y lanzo su exitoso album debut She's So Unusual a finales de ese mismo año. Siguio lanzando una serie de albumes en los que encontro una inmensa popularidad, superando los limites de contenido de las letras de sus canciones.";
		bio = "Cynthia Ann Stephanie Lauper (Brooklyn, Nueva York; 22 de junio de 1953). ";
		web = "cyndilauper.com";
		
		c.set(1953, 6 - 1, 22);
		IU.confirmarAlta(true, CL, "Cyndi", "Lauper", "clauper@hotmail.com", c.getTime(), des, bio, web,"poiuy086","https://bit.ly/34zFWvV");
					
		des = "Bruce Frederick Joseph Springsteen (Long Branch, Nueva Jersey, 23 de septiembre de 1949), mas conocido como Bruce Springsteen, es un cantante, musico y compositor estadounidense.";
		bio = "";
		web = "brucespringsteen.net";
		
		c.set(1949, 9 - 1, 23);
		IU.confirmarAlta(true, BS, "Bruce", "Springsteen", "bruceTheBoss@gmail.com", c.getTime(), des, bio, web,"GTO468","https://bit.ly/34hFwde");
		
		des = "La Triple Nelson es un grupo de rock uruguayo formado en enero de 1998 e integrado inicialmente por Christian Cary (guitarra y voz), Fernando \"Paco\" Pintos (bajo y coros) y Ruben Otonello (actualmente su nuevo baterista es Rafael Ugo).";
		bio = "";
		web = "www.latriplenelson.uy";
		
		c.set(1988, 1 - 1, 1);
		IU.confirmarAlta(true, TN, "La Triple", "Nelson", "tripleNelson@tuta.io", c.getTime(), des, bio, web,"HGF135","https://bit.ly/2Geps4a");
		
		des = "La Ley fue una banda chilena de rock formada en 1987 por iniciativa del tecladista y guitarrista. En un principio, La Ley tenia la aspiracion de ser un grupo de música tecno. Este disco resulta ser un éxito de ventas y reciben una invitacion al Festival Internacional de Vinia del Mar de febrero de 1994.";
		bio = "";
		web = "www.lasleyesdenewton.com";
		
		c.set(1987, 2 - 1, 14);
		IU.confirmarAlta(true, LL, "La", "Ley", "la_ley@tuta.io", c.getTime(), des, bio, web,"lkj65D","https://bit.ly/33oXxqQ");
		
		des = "Pimpinela es un duo musical argentino compuesto por los hermanos Lucia Galan y Joaquin Galan. Pimpinela ha editado veinticuatro discos";
		bio = "";
		web = "www.pimpinela.net";
		
		c.set(1981, 8 - 1, 13);
		IU.confirmarAlta(true, PI, "Pimpinela", "Pimpinela", "lospimpi@gmail.com", c.getTime(), des, bio, web,"jhvf395","https://bit.ly/30t4tRI");
		
		des = "Jose Gomez Romero, conocido artísticamente como Dyango es un cantante español de música romantica.";
		bio = "";
		web = "";
		
		c.set(1940, 3 - 1, 5);
		IU.confirmarAlta(true, DY, "Dyango", "Ango", "dyangounchained@gmail.com", c.getTime(), des, bio, web,"ijngr024","https://bit.ly/3jwA8JS");
		
		des = "Su carrera comienza en 1976 cuando forma la banda Los Playeros junto a su hermano Victor. Al poco tiempo se mudan a San Luis donde comienzan a hacerse conocidos en la escena musical. Su éxito a nivel nacional llega a comienzos de los años 1990 cuando desembarca en Buenos Aires y graba el éxito \"Violeta\", originalmente compuesta e interpretada en 1985 por el musico brasilenio Luiz Caldas bajo el titulo <Fricote>.";
		bio = "";
		web = "";
		
		c.set(1952, 7 - 1, 17);
		IU.confirmarAlta(true, AL, "Alcides", "Violeta", "alcides@tuta.io", c.getTime(), des, bio, web,"987mnbgh","https://bit.ly/3nnpAiu");
	}
	
	private void datosDePruebaSeguirUsuarios() throws UsuarioNoExisteException {
		//SEGUIR USUARIOS
		IU.seguirAUnUsuario(VP,BS);
		IU.seguirAUnUsuario(DM,BS);
		IU.seguirAUnUsuario(CL,VP);
		IU.seguirAUnUsuario(CL,DM);
		IU.seguirAUnUsuario(CL,DY);
		IU.seguirAUnUsuario(BS,VP);
		IU.seguirAUnUsuario(BS,DM);
		IU.seguirAUnUsuario(BS,CL);
		IU.seguirAUnUsuario(BS,GH);
		IU.seguirAUnUsuario(TN,CL);
		IU.seguirAUnUsuario(TN,LL);
		IU.seguirAUnUsuario(TN,EW);
		IU.seguirAUnUsuario(LL,DM);
		IU.seguirAUnUsuario(LL,PI);
		IU.seguirAUnUsuario(LL,EW);
		IU.seguirAUnUsuario(PI,DM);
		IU.seguirAUnUsuario(PI,DY);
		IU.seguirAUnUsuario(PI,AL);
		IU.seguirAUnUsuario(DY,TN);
		IU.seguirAUnUsuario(DY,PI);
		IU.seguirAUnUsuario(AL,PI);
		IU.seguirAUnUsuario(AL,SP);
		IU.seguirAUnUsuario(EL,PI);
		IU.seguirAUnUsuario(EL,DY);
		IU.seguirAUnUsuario(EL,EW);
		IU.seguirAUnUsuario(EL,AR);
		IU.seguirAUnUsuario(EL,AP);
		IU.seguirAUnUsuario(CO,VP);
		IU.seguirAUnUsuario(CO,DM);
		IU.seguirAUnUsuario(CO,CL);
		IU.seguirAUnUsuario(CO,BS);
		IU.seguirAUnUsuario(CO,TN);
		IU.seguirAUnUsuario(CO,LL);
		IU.seguirAUnUsuario(CO,PI);
		IU.seguirAUnUsuario(CO,DY);
		IU.seguirAUnUsuario(CO,AL);
		IU.seguirAUnUsuario(EW,DM);
		IU.seguirAUnUsuario(EW,CL);
		IU.seguirAUnUsuario(EW,BS);
		IU.seguirAUnUsuario(EW,GH);
		IU.seguirAUnUsuario(GH,BS);
		IU.seguirAUnUsuario(GH,LL);
		IU.seguirAUnUsuario(GH,DY);
		IU.seguirAUnUsuario(SP,VP);
		IU.seguirAUnUsuario(SP,LL);
		IU.seguirAUnUsuario(SP,PI);
		IU.seguirAUnUsuario(SP,AR);
		IU.seguirAUnUsuario(SP,AP);
		IU.seguirAUnUsuario(SP,ML);
		IU.seguirAUnUsuario(AR,AL);
		IU.seguirAUnUsuario(AR,SP);
		IU.seguirAUnUsuario(AP,AL);
		IU.seguirAUnUsuario(AR,SP);
		IU.seguirAUnUsuario(ML,PI);
		IU.seguirAUnUsuario(ML,AL);
		IU.seguirAUnUsuario(CB,LL);
		IU.seguirAUnUsuario(CB,PI);
		IU.seguirAUnUsuario(CB,AL);
		IU.seguirAUnUsuario(CB,AR);
		IU.seguirAUnUsuario(CB,AP);
		IU.seguirAUnUsuario(CB,ML);
	}
	
	private void datosDePruebaPlataformas() throws PlataformaRepetidaException {
		//CARGAR PLATAFORMAS
		
		IE.altaPlataforma("Instagram Live", "https://www.instagram.com/liveoficial", "Funcionalidad de la red social Instagram, con la que los usuarios pueden transmitir videos en vivo.");
		IE.altaPlataforma("Facebook Watch", "https://www.facebook.com/watch/", "Servicio de video bajo demanda operado por Facebook");
		IE.altaPlataforma("Twitter Live", "https://twitter.com/", "Aplicación de Twitter para la transmisión de video en directo (streaming).");
		IE.altaPlataforma("Youtube", "https://www.youtube.com/", "Sitio web de origen estadounidense dedicado a compartir videos. ");
	} 
	
	private void datosDePruebaEspectaculos() throws EspectaculoAgregadoYaExisteExcepcion, UsuarioNoExisteException, PlataformaNoExisteException, EspectaculoNoExistenteException {
		Calendar c = Calendar.getInstance();
		
		Set<String> aux = new HashSet<String>();
		String imagen = "";
		
		aux.add(c3);
		c.set(2020, 03, 31);
		IE.altaEspectaculo("Instagram Live", VP, E1, "Espectáculo de retorno de los Village People.", "https://ww.instagram.com/realvillagepeople/",550, 800, 1, 90, c.getTime(), aux, imagen, 2, "Meet & greet (encuentro) virtual con integrantes de Village People y unaccesorio de indumentaria de la banda que será elegido por el ganador, como ser el penacho de plumas del jefe indio (sujeto a disponibilidad). Info: https://bit.ly/sorteovp", "https://youtu.be/N8FxU1nmLWg");
		
		
		aux.clear();
		aux.add(c3);
		c.set(2020, 04, 20);
		IE.altaEspectaculo("Facebook Watch", DM, E2, "Espectáculo donde se presenta el Álbum Spirit.", "https://esla.facebook.com/depechemode/", 750, 1300, 1, 120, c.getTime(), aux, imagen, 3, "Box Set multimedia \"Depeche Mode: SPIRITS in the Forest\", que  sigue a la banda en su Global Spirit Tour 2017/2018, que vio a Depeche Mode tocar para más de 3 millones de fanáticos en 115 shows en todo el mundo. El Box Set contiene 2 CDs y 2 DVDs o 2 CDs y 1 Blu-ray (a elección). Info: https://bit.ly/sorteodm", "https://youtu.be/2qxcr6T9pNM");
		
		
		aux.clear();
		aux.add(c2);
		c.set(2020, 05, 30);
		IE.altaEspectaculo("Twitter Live", CL, E3, "Espectáculo promoviendo Álbum Memphis Blues.", "https://twitter.com/cyndilauper", 800, 1000, 1, 110, c.getTime(), aux, imagen, 2, "Meet & greet (encuentro) virtual con la legendaria cantante e ícono del Pop, que inspiró a tantas otras cantante femeninas como Madonna y Lady Gaga (aunque ellas jamás lo admitirían).\r\n", "https://youtu.be/ivHp3_gYXIc");
		
		
		aux.clear();
		aux.add(c3);
		c.set(2020, 06, 07);
		IE.altaEspectaculo("Youtube", BS, E4, "Springsteen tocando guitarra o piano y relatando anécdotas recogidas en su autobiografía de 2016, Born to Run.", "https://www.youtube.com/BruceSpringsteen", 980, 1500, 1, 100, c.getTime(), aux, imagen, 2, "Album completo \"Springsteen On Broadway\" en formato MP3 o CD (a elección). Info: https://bit.ly/sorteobs ", "https://youtu.be/M1xDzgob1JI");
		
		
		aux.clear();
		aux.add(c1);
		c.set(2020, 07, 8);
		IE.altaEspectaculo("Twitter Live", PI, E5, "El dúo estará presentando sus más sonados éxitos y también nuevas canciones .", "https://twitter.com/PimpinelaNet", 500, 500, 1, 150, c.getTime(), aux, imagen, 1, "¿Es cierto que son hermanos? ¿La voz de Lucía puede romper una copa de cristal? ¿Joaquín quiere dejar Pimpinela y ser el vocalista de una banda de heavy metal? Todas estas preguntas y muchas más podrás hacérselas a tus ídolos en un encuentro on-line exclusivo para los ganadores de este sorteo", "https://youtu.be/dPSlBRg0HeA");
		
		
		aux.clear();
		aux.add(c4);
		c.set(2020, 07, 31);
		IE.altaEspectaculo("Twitter Live", AL, E6, "Espectáculo conmemorando los 30 años de Violeta.", "https://twitter.com/alcides_shows", 450, 150, 3, 80, c.getTime(), aux, imagen, 3, "Entrada en platea VIP para el primer show presencial que realice Alcides a partir de enero de 2021 (una vez que el artista haya recibido la vacuna contra el SARS-COV-2), más 1 litro de Fernet de marca a confirmar", "https://youtu.be/65Pu6WP0bag");
		
		
		aux.clear();
		aux.add(c2);
		c.set(2020, 01, 9);
		IE.altaEspectaculo("Youtube", DY, E7, "Espectculo de gira con los temas de siempre.", "https://www.youtube.com/c/dyangooficial", 550, 4, 3, 120, c.getTime(), aux, imagen, 2, "Album completo \"Y Ahora Que\" para descargar en formato FLAC (24 bits, 44.1 kHz). Info: https://bit.ly/sorteody", "https://youtu.be/NxFeibjFt3k");
		
		
		aux.clear();
		aux.add(c1);
		c.set(2020, 05, 20);
		IE.altaEspectaculo("Instagram Live", TN, E8, "Llega a Casa Primer Espectaculo con transmision por streaming.", "https://www.instagram.colatriplenelson/", 400, 1500, 100, 100, c.getTime(), aux, imagen, 2, "Entrada doble para espectáculo \"Mi Bien\" a realizarse en el Auditorio Nacional del SODRE.", "https://youtu.be/m7r3YIFRI3k");
		
		
		aux.clear();
		aux.add(c4);
		c.set(2020, 11, 25);
		IE.altaEspectaculo("Twitter Live", AL, E9, "Esta nochebuena, festejamos con Alcides y grandes invitados", "https://twitter.com/alcides_shows", 600, 3, 1, 60, c.getTime(), aux, imagen, 0, "", "https://youtu.be/65Pu6WP0bag");
		
		aux.clear();
		aux.add(c4);
		c.set(2020, 11, 25);
		IE.altaEspectaculo("Twitter Live", AL, E10, "Este fin de año, festejamos con Alcides y grandes invitados", "https://twitter.com/alcides_shows", 700, 3, 1, 60, c.getTime(), aux, imagen, 0, "", "https://youtu.be/65Pu6WP0bag");
		
	}
	
	private void datosDePruebaFunciones() throws NombreFuncionRepetidoException, EspectaculoNoExistenteException {
		//CARGAR FUNCIONES
		Calendar cFechaF = new GregorianCalendar(2020,1,1);
		Calendar cFechaA = new GregorianCalendar(2020,1,1);
		Set<String> artistas = new HashSet<String>();
		DtHora hora;
	
		//F1
		hora = new DtHora();
		artistas.add(DM);
		artistas.add(CL);
		cFechaF.set(2020, 3, 15);
		hora.setValores(15, 30);
		cFechaA.set(2020, 2, 31);
		IE.confirmarAltaFuncion(E1, F1, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F2
		hora = new DtHora();
		artistas.clear();
		artistas.add(DM);
		artistas.add(CL);
		cFechaF.set(2020, 4, 01);
		hora.setValores(17, 00);
		cFechaA.set(2020, 2, 31);
		IE.confirmarAltaFuncion(E1, F2, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F3
		hora = new DtHora();
		artistas.clear();
		artistas.add(BS);
		artistas.add(CL);
		cFechaF.set(2020, 5, 01);
		hora.setValores(18, 00);
		cFechaA.set(2020, 2, 31);
		IE.confirmarAltaFuncion(E1, F3, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F4
		hora = new DtHora();
		artistas.clear();
		artistas.add(VP);
		cFechaF.set(2020, 5, 10);
		hora.setValores(19, 00);
		cFechaA.set(2020, 3, 20);
		IE.confirmarAltaFuncion(E2, F4, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F5
		hora = new DtHora();
		artistas.clear();
		artistas.add(BS);
		artistas.add(CL);
		cFechaF.set(2020, 6, 10);
		hora.setValores(20, 00);
		cFechaA.set(2020, 3, 20);
		IE.confirmarAltaFuncion(E2, F5, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F6
		hora = new DtHora();
		artistas.clear();
		artistas.add(PI);
		cFechaF.set(2020, 7, 10);
		hora.setValores(17, 45);
		cFechaA.set(2020, 3, 20);
		IE.confirmarAltaFuncion(E2, F6, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F7
		hora = new DtHora();
		artistas.clear();
		artistas.add(BS);
		cFechaF.set(2020, 7, 15);
		hora.setValores(16, 30);
		cFechaA.set(2020, 4, 30);
		IE.confirmarAltaFuncion(E3, F7, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F8
		hora = new DtHora();
		artistas.clear();
		artistas.add(BS);
		artistas.add(DM);
		cFechaF.set(2020, 7, 31);
		hora.setValores(19, 30);
		cFechaA.set(2020, 4, 30);
		IE.confirmarAltaFuncion(E3, F8, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F9
		hora = new DtHora();
		artistas.clear();
		artistas.add(PI);
		artistas.add(BS);
		cFechaF.set(2020, 8, 30);
		hora.setValores(20, 00);
		cFechaA.set(2020, 4, 30);
		IE.confirmarAltaFuncion(E3, F9, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F10
		hora = new DtHora();
		artistas.clear();
		artistas.add(DM);
		artistas.add(TN);
		cFechaF.set(2020, 8, 01);
		hora.setValores(19, 30);
		cFechaA.set(2020, 5, 07);
		IE.confirmarAltaFuncion(E4, F10, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F11
		hora = new DtHora();
		artistas.clear();
		artistas.add(TN);
		artistas.add(LL);
		cFechaF.set(2020, 8, 30);
		hora.setValores(17, 00);
		cFechaA.set(2020, 5, 07);
		IE.confirmarAltaFuncion(E4, F11, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F12
		hora = new DtHora();
		artistas.clear();
		artistas.add(LL);
		cFechaF.set(2020, 9, 15);
		hora.setValores(20, 00);
		cFechaA.set(2020, 5, 07);
		IE.confirmarAltaFuncion(E4, F12, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F13
		hora = new DtHora();
		artistas.clear();
		artistas.add(AL);
		cFechaF.set(2020, 8, 25);
		hora.setValores(19, 00);
		cFechaA.set(2020, 6, 8);
		IE.confirmarAltaFuncion(E5, F13, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F14
		hora = new DtHora();
		artistas.clear();
		artistas.add(TN);
		cFechaF.set(2020, 9, 25);
		hora.setValores(18, 30);
		cFechaA.set(2020, 6, 8);
		IE.confirmarAltaFuncion(E5, F14, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F15
		hora = new DtHora();
		artistas.clear();
		cFechaF.set(2020, 10, 25);
		hora.setValores(17, 45);
		cFechaA.set(2020, 6, 8);
		IE.confirmarAltaFuncion(E5, F15, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F16
		hora = new DtHora();
		artistas.clear();
		artistas.add(DY);
		cFechaF.set(2020, 8, 01);
		hora.setValores(21, 00);
		cFechaA.set(2020, 6, 31);
		IE.confirmarAltaFuncion(E6, F16, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");

		//F17
		hora = new DtHora();
		artistas.clear();
		artistas.add(PI);
		artistas.add(DY);
		cFechaF.set(2020, 9, 01);
		hora.setValores(21, 00);
		cFechaA.set(2020, 6, 31);
		IE.confirmarAltaFuncion(E6, F17, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F18
		hora = new DtHora();
		artistas.clear();
		cFechaF.set(2020, 10, 15);
		hora.setValores(21, 00);
		cFechaA.set(2020, 6, 31);
		IE.confirmarAltaFuncion(E6, F18, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F19
		hora = new DtHora();
		artistas.clear();
		artistas.add(PI);
		cFechaF.set(2020, 11, 19);
		hora.setValores(17, 00);
		cFechaA.set(2020, 10, 25);
		IE.confirmarAltaFuncion(E7, F19, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F20
		hora = new DtHora();
		artistas.clear();
		artistas.add(PI);
		cFechaF.set(2020, 11, 19);
		hora.setValores(21, 00);
		cFechaA.set(2020, 10, 24);
		IE.confirmarAltaFuncion(E7, F20, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
		
		//F21
		hora = new DtHora();
		artistas.clear();
		cFechaF.set(2020, 11, 18);
		hora.setValores(21, 30);
		cFechaA.set(2020, 10, 24);
		IE.confirmarAltaFuncion(E8, F21, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
				
		//F22
		hora = new DtHora();
		artistas.clear();
		cFechaF.set(2020, 11, 19);
		hora.setValores(21, 30);
		cFechaA.set(2020, 10, 24);
		IE.confirmarAltaFuncion(E8, F22, cFechaF.getTime(), hora, artistas, cFechaA.getTime(),"");
	}
	
	private void datosDePruebaCajes() throws EspectadorYaRegistradoException, TicketsAgotadosParaFuncionException, CanjeInvalidoException, EspectaculoNoExistenteException {
		Calendar fecRegistro = new GregorianCalendar(2020,1,1);
		Calendar cFechaA = new GregorianCalendar(2020,1,1);
		boolean quiereCanjear;
		String r1;
		String r2;
		String r3;
	
		//R1
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 9);
		IE.confirmarAltaRegistroAFuncion(E1, CO, F1, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R2
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 10);
		IE.confirmarAltaRegistroAFuncion(E1, SP, F1, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R3
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 12);
		IE.confirmarAltaRegistroAFuncion(E1, AR, F1, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R4
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 15);
		IE.confirmarAltaRegistroAFuncion(E1, AR, F2, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R5
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 20);
		IE.confirmarAltaRegistroAFuncion(E1, AP, F2, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R6
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 25);
		IE.confirmarAltaRegistroAFuncion(E1, CO, F2, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R7
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 28);
		IE.confirmarAltaRegistroAFuncion(E1, ML, F2, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R8
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 3, 16);
		IE.confirmarAltaRegistroAFuncion(E1, CB, F3, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R9
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 4, 15);
		IE.confirmarAltaRegistroAFuncion(E1, CO, F3, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R10
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 4, 20);
		IE.confirmarAltaRegistroAFuncion(E1, ML, F3, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R11
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 4, 05);
		IE.confirmarAltaRegistroAFuncion(E2, EL, F4, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R12
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 4, 10);
		IE.confirmarAltaRegistroAFuncion(E2, EW, F4, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R13
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 4, 15);
		IE.confirmarAltaRegistroAFuncion(E2, SP, F4, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R14
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 4, 20);
		IE.confirmarAltaRegistroAFuncion(E2, AP, F4, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R15
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 5, 8);
		IE.confirmarAltaRegistroAFuncion(E2, GH, F5, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R16
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 5, 13);
		IE.confirmarAltaRegistroAFuncion(E2, EW, F5, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R17
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 5, 25);
		IE.confirmarAltaRegistroAFuncion(E2, ML, F5, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R18
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 6, 05);
		IE.confirmarAltaRegistroAFuncion(E2, CB, F6, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R19
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 6, 11);
		IE.confirmarAltaRegistroAFuncion(E2, SP, F6, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R20
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 6, 18);
		IE.confirmarAltaRegistroAFuncion(E2, AR, F6, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R21
		quiereCanjear = true;
		r1 = F2;
		r2 = F3;
		r3 = F5;
		cFechaA.set(2020, 6, 19);
		IE.confirmarAltaRegistroAFuncion(E3, ML, F7, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R22
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 17);
		IE.confirmarAltaRegistroAFuncion(E3, EL, F8, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R23
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 20);
		IE.confirmarAltaRegistroAFuncion(E3, GH, F8, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R24
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 23);
		IE.confirmarAltaRegistroAFuncion(E3, AR, F8, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R25
		quiereCanjear = true;
		r1 = F1;
		r2 = F2;
		r3 = F3;
		cFechaA.set(2020, 7, 15);
		IE.confirmarAltaRegistroAFuncion(E3, CO, F9, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R26
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 26);
		IE.confirmarAltaRegistroAFuncion(E3, EW, F9, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R27
		quiereCanjear = true;
		r1 = F1;
		r2 = F2;
		r3 = F6;
		cFechaA.set(2020, 6, 19);
		IE.confirmarAltaRegistroAFuncion(E4, AR, F10, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R28
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 16);
		IE.confirmarAltaRegistroAFuncion(E4, AP, F10, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R29
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 24);
		IE.confirmarAltaRegistroAFuncion(E4, ML, F10, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R30
		quiereCanjear = true;
		r1 = F1;
		r2 = F4;
		r3 = F6;
		cFechaA.set(2020, 7, 01);
		IE.confirmarAltaRegistroAFuncion(E4, SP, F11, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R31
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 30);
		IE.confirmarAltaRegistroAFuncion(E4, GH, F11, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R32
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 16);
		IE.confirmarAltaRegistroAFuncion(E4, EL, F12, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R33
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 16);
		IE.confirmarAltaRegistroAFuncion(E4, CO, F12, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R34
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 8, 01);
		IE.confirmarAltaRegistroAFuncion(E4, EW, F12, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R35
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 8, 05);
		IE.confirmarAltaRegistroAFuncion(E4, SP, F12, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R36
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 16);
		IE.confirmarAltaRegistroAFuncion(E5, GH, F13, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R37
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 8, 03);
		IE.confirmarAltaRegistroAFuncion(E5, CB, F13, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R38
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 16);
		IE.confirmarAltaRegistroAFuncion(E5, EL, F14, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R39
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 8, 06);
		IE.confirmarAltaRegistroAFuncion(E5, CB, F14, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R40
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 8, 01);
		IE.confirmarAltaRegistroAFuncion(E5, CO, F15, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R41
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 16);
		IE.confirmarAltaRegistroAFuncion(E6, SP, F16, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R42
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 20);
		IE.confirmarAltaRegistroAFuncion(E6, EL, F16, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R43
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 8, 31);
		IE.confirmarAltaRegistroAFuncion(E6, AP, F16, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R44
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 16);
		IE.confirmarAltaRegistroAFuncion(E6, AR, F17, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R45
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 7, 20);
		IE.confirmarAltaRegistroAFuncion(E6, AP, F17, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);

		
		//R46
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 8, 02);
		IE.confirmarAltaRegistroAFuncion(E6, CO, F17, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);
		
		//R47
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 10, 26);
		IE.confirmarAltaRegistroAFuncion(E7, CB, F19, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);
		
		//R48
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 10, 27);
		IE.confirmarAltaRegistroAFuncion(E7, CO, F19, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);
		
		//R49
		quiereCanjear = false;
		r1 = "";
		r2 = "";
		r3 = "";
		cFechaA.set(2020, 10, 28);
		IE.confirmarAltaRegistroAFuncion(E7, ML, F19, fecRegistro.getTime(), quiereCanjear, r1, r2, r3);
	}
	
	private void datosDePruebaEstadosEspectaculos() throws UsuarioNoExisteException{
		Calendar c = Calendar.getInstance();
		
		try {
			IE.aceptarEspectaculo(E1);
			IE.aceptarEspectaculo(E2);
			c.set(2020, 10, 25);
			IE.finalizarEspectaculo(E3, "clauper@hotmail.com",c.getTime());
			IE.aceptarEspectaculo(E4);
			IE.aceptarEspectaculo(E5);
			c.set(2020, 10, 26);
			IE.finalizarEspectaculo(E6, "alcides@tuta.io",c.getTime());
			IE.aceptarEspectaculo(E7);
			IE.aceptarEspectaculo(E8);
			IE.rechazarEspectaculo(E10);
		} catch (EspectaculoNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void datosDePruebaPaquetes() throws PaqueteRepetidoException {
		Calendar c = Calendar.getInstance();
		
		c.set(2020, 4, 1);	// 01/05/2020 
		Date inicio = c.getTime();
		c.set(2020, 6, 31);	// 31/07/2020
		String [] esp = new String[2];
		esp[0] = E1;
		esp[1] = E2;
		Date fin = c.getTime();
		DtPaquete p1 = new DtPaquete("Paquete de Bandas", "Paquete de bandas musicales.", inicio, fin, 0.2f, 0, esp);	// 01/05/2020 - 31/07/2020 | Alta 30/04/2020
		p1.setImagen("https://periodicoveraz.com/wp-content/uploads/2018/07/2-696x450.jpg");
		
		String[] esp1 = new String[2];
		esp1[0] = E3;
		esp1[1] = E4;
		c.set(2020, 7, 1);	// 01/08/2020
		inicio = c.getTime();
		c.set(2020, 8, 30);	// 30/09/2020
		fin = c.getTime();
		DtPaquete p2 = new DtPaquete("Paquete Solistas", "Paquete de solistas.", inicio, fin, 0.3f, 0, esp1);				// 01/08/2020 - 30/09/2020 | Alta 15/07/2020
		p2.setImagen("https://www.chismestoday.com/wp-content/uploads/2020/02/FotoJet-2020-02-08T171629.608-800x533.jpg");
		
		String[] esp2 = new String[2];
		esp2[0] = E5;
		esp2[1] = E6;
		c.set(2020, 7, 15);	// 15/08/2020
		inicio = c.getTime();
		c.set(2020, 10, 25);// 25/11/2020
		fin = c.getTime();		
		DtPaquete p3 = new DtPaquete("Paquete Latino", "Paquete de espectáculos latinos.", inicio, fin, 0.15f, 0, esp2);// 15/08/2020 - 15/11/2020| Alta 01/08/2020
		p3.setImagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbQ_jrjLEAgeUu4-FcE-wKOSyyt3-c8Na_Bg&usqp=CAU");
		
		
		String[] esp3 = new String[2];
		esp3[0] = E7;
		esp3[1] = E8;
		c.set(2020, 10, 01);
		inicio = c.getTime();
		c.set(2020, 11, 23);
		fin = c.getTime();		
		DtPaquete p4 = new DtPaquete("La Triple Dyango", "Para los rockeros romanticos.", inicio, fin, 0.1f, 0, esp3);// 15/08/2020 - 15/11/2020| Alta 01/08/2020
		p4.setImagen("https://thumbs.dreamstime.com/z/guitarra-el%C3%A9ctrica-6903761.jpg");
		
		
		
		IE.altaPaquete(p1);
		IE.altaPaquete(p2);
		IE.altaPaquete(p3);
		IE.altaPaquete(p4);
		
		IU.comprarPaquete(AP,p1.getNombre());
		IU.comprarPaquete(ML,p1.getNombre());
		IU.comprarPaquete(CO,p2.getNombre());
		IU.comprarPaquete(EL,p2.getNombre());
		IU.comprarPaquete(EW,p3.getNombre());
		IU.comprarPaquete(ML,p4.getNombre());
		IU.comprarPaquete(CB,p4.getNombre());
	}
	
	private void datosDePruebaSorteos() {
		Calendar c = Calendar.getInstance();
		
		c.set(2020, 7, 17);
		String[] ganadoresS1 = new String[1];
		ganadoresS1[0] = ML;
		IE.sorteoArreglado(F7,ganadoresS1,c.getTime());
		
		c.set(2020, 8, 01);
		String[] ganadoresS2 = new String[2];
		ganadoresS2[0] = EL;
		ganadoresS2[1] = GH;
		IE.sorteoArreglado(F8,ganadoresS2,c.getTime());
		
		c.set(2020, 8, 31);
		String[] ganadoresS3 = new String[2];
		ganadoresS3[0] = CO;
		ganadoresS3[1] = EW;
		IE.sorteoArreglado(F9,ganadoresS3,c.getTime());
		
		c.set(2020, 8, 30);
		String[] ganadoresS4 = new String[3];
		ganadoresS4[0] = SP;
		ganadoresS4[1] = EL;
		ganadoresS4[2] = AP;
		IE.sorteoArreglado(F16,ganadoresS4,c.getTime());
		
		c.set(2020, 8, 30);
		String[] ganadoresS5 = new String[3];
		ganadoresS5[0] = AR;
		ganadoresS5[1] = AP;
		ganadoresS5[2] = CO;
		IE.sorteoArreglado(F17,ganadoresS5,c.getTime());
	}
	
	private void datosDePruebaFavoritos() {
		
		try {
			IU.marcarEspectaculoComoFavorito(EL, E2);
			IU.marcarEspectaculoComoFavorito(EL, E6);
			IU.marcarEspectaculoComoFavorito(CO, E1);
			IU.marcarEspectaculoComoFavorito(CO, E2);
			IU.marcarEspectaculoComoFavorito(CO, E3);
			IU.marcarEspectaculoComoFavorito(EW, E4);
			IU.marcarEspectaculoComoFavorito(GH, E3);
			IU.marcarEspectaculoComoFavorito(GH, E4);
			IU.marcarEspectaculoComoFavorito(SP, E4);
			IU.marcarEspectaculoComoFavorito(SP, E6);
			IU.marcarEspectaculoComoFavorito(AR, E1);
			IU.marcarEspectaculoComoFavorito(AR, E2);
			IU.marcarEspectaculoComoFavorito(AR, E6);
			IU.marcarEspectaculoComoFavorito(AR, E5);
			IU.marcarEspectaculoComoFavorito(ML, E1);
			IU.marcarEspectaculoComoFavorito(CB, E2);
			
		} catch (UsuarioNoExisteException | UsuarioNoEsEspectadorException | EspectaculoNoExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void datosDePruebaValoraciones() throws PuntajeInvalidoException, UsuarioNoExisteException, EspectaculoNoExistenteException {
		IU.valorarEspectaculo(AR, E1, 5);
		IU.valorarEspectaculo(AP, E1, 2);
		IU.valorarEspectaculo(CO, E1, 3);
		IU.valorarEspectaculo(ML,E1,4);
		IU.valorarEspectaculo(EL,E2,4);
		IU.valorarEspectaculo(EW,E2,1);
		IU.valorarEspectaculo(SP,E2,2);
		IU.valorarEspectaculo(AP,E2,2);
		IU.valorarEspectaculo(CB,E2,5);
		IU.valorarEspectaculo(AR,E2,5);
		IU.valorarEspectaculo(EL,E3,2);
		IU.valorarEspectaculo(GH,E3,4);
		IU.valorarEspectaculo(AR,E3,2);
		IU.valorarEspectaculo(SP,E4,3);
		IU.valorarEspectaculo(GH,E4,4);
		IU.valorarEspectaculo(EL,E4,2);
		IU.valorarEspectaculo(CO,E4,1);
		IU.valorarEspectaculo(EW,E4,5);
		IU.valorarEspectaculo(GH,E5,1);
		IU.valorarEspectaculo(CB,E5,4);
		IU.valorarEspectaculo(AR,E6,5);
		IU.valorarEspectaculo(AP,E6,3);
		IU.valorarEspectaculo(CO,E6,2);
	}
}

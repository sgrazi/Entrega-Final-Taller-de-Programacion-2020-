package presentacion;

import javax.swing.JFrame;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import logica.IControladorEspectaculo;
import java.util.Set;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import datatypes.DtEspectaculo;
import excepciones.EspectaculoNoExistenteException;
import excepciones.PlataformaNoExisteException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConsultaEspectaculo extends JInternalFrame {
	
	private IControladorEspectaculo IEspectaculo;
	
	private JComboBox<String> cboPlataformas;
	private JComboBox<String> cboEspectaculos;
	private JLabel lblEspectaculos;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JTextField txtURL;
	private JTextField txtCosto;
	private JLabel lmlCapacidad;
	private JLabel lblDuracion;
	private JLabel lblFecha;
	private JTextField txtDuracion;
	private JTextField txtMax;
	private JTextField txtMin;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblCosto;
	private JLabel lblMin;
	private JLabel lblMax;
	private JDateChooser dateFecha;
	private JLabel lblFunciones;
	private JComboBox<String> cboFunciones;
	private JLabel lblPaquetes;
	private JComboBox<String> cboPaquetes;
	private JLabel lblNewLabel;
	private JComboBox<String> cboCategorias;
	private JTextField txtEstado;
	
	
	public ConsultaEspectaculo(IControladorEspectaculo ie) {
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarPlataformas();
				
			}
		});
	
		this.IEspectaculo = ie;
        setIconifiable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
        setTitle("Consulta Espectaculo");
		setBounds(10, 10, 643, 433);
		
		JLabel lblPlataformas = new JLabel("Plataformas:");
		lblPlataformas.setBounds(12, 28, 73, 16);
		
		lblEspectaculos = new JLabel("Espectaculos:");
		lblEspectaculos.setBounds(228, 28, 78, 16);
		lblEspectaculos.setVisible(false);
		
		
		//COMBO PLATAFORMAS
		cboPlataformas = new JComboBox<String>();
		cboPlataformas.setBounds(97, 25, 121, 22);
		
		//OBTENGO LAS PLATAFORMAS //LAS PLATAFORMAS SE ESTAN CARGANDO EN EL SHOW DEL FORMULARIO
		//Set<String> colPlataformas = this.IEspectaculo.listarPlataformasSet();
				
		//ITEM INICIAL 
//		cboPlataformas.addItem("Seleccionar");
//				
//		//CARGO COMBOBOX PLATAFORMAS
//		String plataformas[] = new String[colPlataformas.size()];
//		colPlataformas.toArray(plataformas);
//		for(String plat : colPlataformas) {
//			cboPlataformas.addItem(plat);
//		}
		
		// ACCION COMBO PLATAFORMAS
		cboPlataformas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				limpiarDatosAlSeleccionarPlataforma();
				
				if (cboPlataformas.getSelectedIndex() > 0) {
					llenarComoboEspectaculo();
				}
				
			}
		});
		
		
		//SE CREA COMBO DE ESPECTACULOS
		cboEspectaculos = new JComboBox<String>();
		cboEspectaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatosAlSeleccionarEspectaculo();
				
				if (cboEspectaculos.getSelectedIndex() > -1) {
					cargoContenidoEspectaculo((String)cboEspectaculos.getSelectedItem());
				}
			}
		});
		
		cboEspectaculos.setBounds(305, 25, 307, 22);
		
		//VISIBILIDAD FALSE
		cboEspectaculos.setVisible(false);
		
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 68, 50, 16);
		
		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(12, 97, 70, 16);
		
		JLabel lblURL = new JLabel("URL:");
		lblURL.setBounds(12, 137, 27, 16);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(97, 65, 269, 22);
		txtNombre.setColumns(10);
		txtNombre.setEditable(false);
		
		txtDescripcion = new JTextField();
		txtDescripcion.setBounds(97, 94, 269, 22);
		txtDescripcion.setEditable(false);
		txtDescripcion.setColumns(10);
		
		txtURL = new JTextField();
		txtURL.setBounds(97, 134, 269, 22);
		txtURL.setEditable(false);
		txtURL.setColumns(10);
		
		lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(12, 172, 37, 16);
		
		lblMin = new JLabel("Min:");
		lblMin.setBounds(97, 207, 25, 16);
		
		lblMax = new JLabel("Max:");
		lblMax.setBounds(203, 207, 28, 16);
		
		txtCosto = new JTextField();
		txtCosto.setBounds(97, 169, 69, 22);
		txtCosto.setColumns(10);
		txtCosto.setEditable(false);
		
		lmlCapacidad = new JLabel("Capacidad:");
		lmlCapacidad.setBounds(12, 207, 64, 16);
		
		lblDuracion = new JLabel("Duracion:");
		lblDuracion.setBounds(12, 242, 55, 16);
		
		lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(12, 274, 39, 16);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(97, 239, 68, 22);
		txtDuracion.setEditable(false);
		txtDuracion.setColumns(10);
		
		txtMax = new JTextField();
		txtMax.setBounds(235, 204, 32, 22);
		txtMax.setColumns(10);
		txtMax.setEditable(false);
		
		txtMin = new JTextField();
		txtMin.setBounds(134, 204, 32, 22);
		txtMin.setColumns(10);
		txtMin.setEditable(false);
		
		dateFecha = new JDateChooser();
		dateFecha.setBounds(97, 268, 111, 22);
		
		lblFunciones = new JLabel("Funciones:");
		lblFunciones.setBounds(380, 68, 62, 16);
		
		cboFunciones = new JComboBox<String>();
		cboFunciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboFunciones.getSelectedIndex() > 0) {
					try {
						mostrarFuncion();
					} catch (EspectaculoNoExistenteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		cboFunciones.setBounds(378, 94, 234, 22);
		
		lblPaquetes = new JLabel("Paquetes:");
		lblPaquetes.setBounds(380, 123, 57, 16);
		
		cboPaquetes = new JComboBox<String>();
		cboPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cboPaquetes.getSelectedIndex() > 0) {
					Principal.ConsultarPaquete((String) cboPaquetes.getSelectedItem());
				}
			}
		});
		
		cboPaquetes.setBounds(378, 149, 234, 22);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(517, 359, 95, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(lblDuracion);
		getContentPane().add(lblPlataformas);
		getContentPane().add(lblDescripcion);
		getContentPane().add(lblURL);
		getContentPane().add(lmlCapacidad);
		getContentPane().add(lblNombre);
		getContentPane().add(dateFecha);
		getContentPane().add(txtCosto);
		getContentPane().add(txtDuracion);
		getContentPane().add(lblMin);
		getContentPane().add(txtMin);
		getContentPane().add(lblMax);
		getContentPane().add(txtMax);
		getContentPane().add(txtURL);
		getContentPane().add(txtDescripcion);
		getContentPane().add(txtNombre);
		getContentPane().add(cboPlataformas);
		getContentPane().add(lblCosto);
		getContentPane().add(lblEspectaculos);
		getContentPane().add(btnCancelar);
		getContentPane().add(cboEspectaculos);
		getContentPane().add(lblFunciones);
		getContentPane().add(lblPaquetes);
		getContentPane().add(cboPaquetes);
		getContentPane().add(cboFunciones);
		getContentPane().add(lblFecha);
		
		lblNewLabel = new JLabel("Categorias:");
		lblNewLabel.setBounds(380, 208, 62, 14);
		getContentPane().add(lblNewLabel);
		
		cboCategorias = new JComboBox<String>();
		cboCategorias.setBounds(380, 233, 232, 20);
		getContentPane().add(cboCategorias);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setColumns(10);
		txtEstado.setBounds(97, 303, 170, 22);
		getContentPane().add(txtEstado);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(12, 306, 73, 16);
		getContentPane().add(lblEstado);
	}
	
	protected void mostrarFuncion() throws EspectaculoNoExistenteException {
		// TODO Auto-generated method stub
		String fun = (String) this.cboFunciones.getSelectedItem();
		Principal.ConsultarFuncion(fun);
		
	}

	private void cargarPlataformas() {
		// TODO Auto-generated method stub
		
		//this.cboFunciones.removeAllItems();
		
		//OBTENGO PLATAFORMAS
		Set<String> colPlataformas = this.IEspectaculo.listarPlataformasSet();
				
		//ITEM INICIAL
		cboPlataformas.addItem("Seleccionar");
				
		//CARGO COMBOBOX PLATAFORMAS
		String plataformas[] = new String[colPlataformas.size()];
		colPlataformas.toArray(plataformas);
		for(String plat : colPlataformas) {
			cboPlataformas.addItem(plat);
		}
				
		
	}

	protected void llenarComoboEspectaculo() {
				
		//REMUEVO TODOS LOS ITEMS DEL COMOBOX ESPECTACULOS PARA QUE CUANDO SELECCIONE OTRA PLATAFORMA SE BORREN LOS ESPECTACULOS DE LA PLATAFORMA ANTERIOR
		this.cboEspectaculos.removeAllItems();
				
		//OBTENGO LA PLATAFORMA
		String plat = (String) this.cboPlataformas.getSelectedItem();
				
		//OBTENGO LOS ESPECTACULOS DE LA PLATAFORMA SELECCIONADA
		Set<String> colEspectaculos;
		try {
			colEspectaculos = this.IEspectaculo.obtenerEspectaculos(plat);
		
				
			// SI ES VACIA
			if (colEspectaculos.isEmpty()) {
				JOptionPane.showMessageDialog(this, "La plataforma no posee espectaculos", "Consulta Espectaculos",
						JOptionPane.ERROR_MESSAGE);
			}
			else { //SI NO ES VACIA
								
					//CARGO COMBOBOX ESPECTACULOS
					String espectaculos[] = new String[colEspectaculos.size()];
					colEspectaculos.toArray(espectaculos);
					for(String esp : colEspectaculos) {
						cboEspectaculos.addItem(esp);
					}
				
					
					//PONGO VISIBLE LAS COMPONENTES DE ESPECTACULOS
					this.lblEspectaculos.setVisible(true);
					this.cboEspectaculos.setVisible(true);	
				}
		} catch (PlataformaNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void limpiarFormulario() {
		this.cboPlataformas.removeAllItems();
		this.limpiarDatosAlSeleccionarPlataforma();
		
		cboEspectaculos.removeAllItems();
		this.limpiarDatosAlSeleccionarEspectaculo();
		
		setVisible(false);
	}
	
	private void limpiarDatosAlSeleccionarPlataforma() {
		this.cboEspectaculos.removeAllItems();
		this.limpiarDatosAlSeleccionarEspectaculo();
	}
	
	private void limpiarDatosAlSeleccionarEspectaculo() {
		
		txtDescripcion.setText("");
		txtNombre.setText("");
		txtURL.setText("");
		txtCosto.setText("");
		txtMax.setText("");
		txtMin.setText("");
		txtDuracion.setText("");
		dateFecha.setDate(null);
		txtEstado.setText("");
		
		cboCategorias.removeAllItems();
		cboFunciones.removeAllItems();
		cboPaquetes.removeAllItems();
		cboFunciones.setVisible(true);
		lblFunciones.setVisible(true);
		cboPaquetes.setVisible(true);
		lblPaquetes.setVisible(true);
	}
	
	private void cargoContenidoEspectaculo(String esp) {
		//OBTENGO DTESPECTACULO SELECCIONADO
		
		DtEspectaculo e;
		try {
			e = this.IEspectaculo.getEspectaculoDt(esp);
			
			
			//RELLENO LOS CAMPOS CON EL DT OBTENIDO
			this.txtNombre.setText(e.getNombre());
			this.txtDescripcion.setText(e.getDescripcion());
			this.txtCosto.setText(Integer.toString((int) e.getCosto()));
			this.txtDuracion.setText(Integer.toString(e.getDuracion()));
			this.txtMax.setText(Integer.toString(e.getCantMaxEspectadores()));
			this.txtMin.setText(Integer.toString(e.getCantMinEspectadores()));
			this.txtURL.setText(e.getURL());
			this.dateFecha.setDate(e.getFechaDeRegistro());
					
			for(String c : e.getCategorias()) {
				this.cboCategorias.addItem(c);
			}
			
			this.txtEstado.setText(e.getEstado());
		} catch (EspectaculoNoExistenteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Set<String> colFunciones = this.IEspectaculo.obtenerFuncionesEspectaculo(esp);
		if (colFunciones.isEmpty()) {
					
			this.cboFunciones.setVisible(false);
			this.lblFunciones.setVisible(false);
					
		}else {
				
				this.cboFunciones.addItem("Seleccionar");
			
				String funciones[] = new String[colFunciones.size()];
				colFunciones.toArray(funciones);
				for(String fun : colFunciones) {
					this.cboFunciones.addItem(fun);
				}
					
		}
				
		Set<String> colPaquetes = this.IEspectaculo.obtenerPaquetesEspectaculo(esp);
		if (colPaquetes.isEmpty()) {
					
				this.cboPaquetes.setVisible(false);
				this.lblPaquetes.setVisible(false);
					
		}else {
				
				this.cboPaquetes.addItem("Seleccionar");
				
				String paquetes[] = new String[colPaquetes.size()];
				colPaquetes.toArray(paquetes);
				for(String pac : colPaquetes) {
					this.cboPaquetes.addItem(pac);
				}
					
		}
		
	}
	
	public void cargoVistaConsulta(String esp) {
		//BORRO EVENTOS DEL SHOW XQ ME JODE LA VISTA DE LA CONSULTA
		ComponentListener[] eventos = this.getComponentListeners();
		this.removeComponentListener(eventos[0]);
		
		cboPlataformas.setVisible(false);
		cboEspectaculos.removeAllItems();
		
		lblEspectaculos.setVisible(true);
		cboEspectaculos.setVisible(true);
		
		cboEspectaculos.addItem(esp);
		
		cargoContenidoEspectaculo(esp);
	}
}

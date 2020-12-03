package presentacion;


import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JSeparator;

import logica.IControladorEspectaculo;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import datatypes.DtFuncion;
import excepciones.EspectaculoNoExistenteException;

@SuppressWarnings("serial")
public class ConsultaDeFuncionAEspectaculo extends JInternalFrame {
	

	private IControladorEspectaculo controladorEspectaculo;
	
	private JComboBox comboPlataformas;
	private JComboBox comboFunciones;
	private JComboBox comboEspectaculos;
	private JEditorPane datosFuncion;
	private DefaultComboBoxModel modelPlataformas;
	private DefaultComboBoxModel modelEspectaculos;
	private DefaultComboBoxModel modelFunciones;
	
	public ConsultaDeFuncionAEspectaculo(IControladorEspectaculo ice) {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				limpiarFormulario();
			}
		});
		
		// Se inicializa con el controlador de usuarios
		controladorEspectaculo = ice;

		setClosable(true);
		setBounds(100, 100, 449, 348);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Consulta de Funcion de Espectaculo");
		getContentPane().setLayout(null);

		
		//creo la estructura que crea el combobox de plataformas 		
		Vector plataformas=new Vector();
	    modelPlataformas = new DefaultComboBoxModel(plataformas);
		
		//creo la estructura que crea el combobox de espectaculos
		Vector espectaculos=new Vector();//duda, puedo usar estos mismos vectores para almacenar las listas de espectaculos en vez de hacer un arreglo? - stefano
	    modelEspectaculos = new DefaultComboBoxModel(espectaculos);

		//creo la estructura que crea el combobox de funciones
	    Vector funciones=new Vector();  
	    modelFunciones = new DefaultComboBoxModel(funciones);
				
	    //creo el combobox a partir de la lista de plataformas
	    comboPlataformas = new JComboBox(modelPlataformas);

	    comboPlataformas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//agrego la lista de espectaculos de la plataforma seleccionada al jcombobox
				modelEspectaculos.removeAllElements();
				modelFunciones.removeAllElements();
				datosFuncion.setText(" ");
				Set<String> setEspectaculos = controladorEspectaculo.seleccionarPlataforma(comboPlataformas.getSelectedItem().toString());//(seleccionar plataforma devuelve los espectaculos)
				String[] arrEspectaculos = new String[setEspectaculos.size()];
				setEspectaculos.toArray(arrEspectaculos);
				for(int i = 0; i < arrEspectaculos.length; i++) {
					modelEspectaculos.addElement(arrEspectaculos[i]);
				}
			}
		});
	    
		comboPlataformas.setBounds(152, 11, 247, 20);
		getContentPane().add(comboPlataformas);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 42, 414, 2);
		getContentPane().add(separator);
		
		JLabel Plataforma = new JLabel("Seleccionar Plataforma");
		Plataforma.setBounds(22, 16, 120, 14);
		getContentPane().add(Plataforma);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 88, 414, 2);
		getContentPane().add(separator_1);
		
		//creo el combobox a partir de la lista de espectaculos
		comboEspectaculos = new JComboBox(modelEspectaculos);
		comboEspectaculos.addActionListener(new ActionListener() {//agrego la lista de funciones del espectaculos seleccionado al jcombobox
			public void actionPerformed(ActionEvent e) {
				modelFunciones.removeAllElements();
				datosFuncion.setText(" ");
				
				if(comboEspectaculos.getSelectedItem()!=null) {
					Set<String> setFunciones = controladorEspectaculo.listarFunciones(comboEspectaculos.getSelectedItem().toString());
					String[] arrFunciones = new String[setFunciones.size()];
					setFunciones.toArray(arrFunciones);
					for(int i = 0; i < arrFunciones.length; i++) {
						modelFunciones.addElement(arrFunciones[i]);
					}
				}
			}
		});
		comboEspectaculos.setBounds(152, 58, 247, 20);
		getContentPane().add(comboEspectaculos);
		
		JLabel Espectaculo = new JLabel("Seleccionar Espectaculo");
		Espectaculo.setBounds(22, 62, 120, 14);
		getContentPane().add(Espectaculo);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 134, 414, 2);
		getContentPane().add(separator_1_1);
		

		JLabel Funcion = new JLabel("Seleccionar Funcion");
		Funcion.setBounds(22, 108, 120, 14);
		getContentPane().add(Funcion);
		
		datosFuncion = new JEditorPane();
		datosFuncion.setEditable(false);
		datosFuncion.setBounds(10, 147, 414, 126);
		getContentPane().add(datosFuncion);
		
		//creo el combobox a partir de la lista de funciones
		comboFunciones = new JComboBox(modelFunciones);
		comboFunciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//pido el dt de la funcion elegida y lo paso a un texto
				if(comboFunciones.getSelectedItem()!=null) {
					try {
						cargarFuncion(comboFunciones.getSelectedItem().toString());
					} catch (EspectaculoNoExistenteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		comboFunciones.setBounds(152, 105, 247, 20);
		getContentPane().add(comboFunciones);
		
		JButton Salir = new JButton("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiarFormulario();
			}
		});
		Salir.setBounds(334, 284, 89, 23);
		getContentPane().add(Salir);
		
	}
	
	public void cargarPlataformas() {
		
		DefaultComboBoxModel<String> modelPlataforma;
        modelPlataforma = new DefaultComboBoxModel<String>();
        
		Set<String> plataformasSet = controladorEspectaculo.listarPlataformasSet();
	    String[] arrPlataformas = new String[plataformasSet.size()];
	    plataformasSet.toArray(arrPlataformas);
	    modelPlataforma.addElement("");
		for(int i = 0; i < arrPlataformas.length; i++) {
			modelPlataforma.addElement(arrPlataformas[i]);
		}
		comboPlataformas.setModel(modelPlataforma);
	}
	
	public void cargarFuncion(String funcion) throws EspectaculoNoExistenteException {
		DtFuncion dt = controladorEspectaculo.obtenerDatosFuncion(funcion);
		int m = dt.getHora().getMinutos();
		String min = "";
		if (m < 10) { min = "0" + m; } else min = "" + m;
		SimpleDateFormat d = new SimpleDateFormat("dd MMM yyyy ");
		String texto = "Nombre: " + dt.getNombre() +"\nFecha creacion: " + d.format(dt.getFechaDeRegistro())+ dt.getHora().getHoras()+ ":"+ min + "\nFecha de la funcion: " + d.format(dt.getFecha());
		
		datosFuncion.setText(texto);
	}
	
	public void cargoVistaConsulta(String funcion) throws EspectaculoNoExistenteException {
		comboPlataformas.setEnabled(false);
		comboEspectaculos.setEnabled(false);
		
		comboPlataformas.removeAllItems();
		comboEspectaculos.removeAllItems();
		comboFunciones.removeAllItems();
		
		comboFunciones.addItem(funcion);
		comboFunciones.setSelectedIndex(0);
	
		cargarFuncion(funcion);
	}
	
	private void limpiarFormulario() {
		//LA VISTA EXTERNA DE CONSULTA PUEDE CAMBIAR ALGUNOS COMPONENTES, LOS DEJO COMO ESTABAN 
		comboPlataformas.setEnabled(true);
		comboEspectaculos.setEnabled(true);
		setLocation(33, 29);
		
		modelPlataformas.removeAllElements();
		modelEspectaculos.removeAllElements();
		modelFunciones.removeAllElements();
		datosFuncion.setText(" ");	
	}
	
}

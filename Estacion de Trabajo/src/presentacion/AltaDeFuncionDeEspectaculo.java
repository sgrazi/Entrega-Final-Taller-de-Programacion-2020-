package presentacion;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import excepciones.EspectaculoNoExistenteException;
import excepciones.NombreFuncionRepetidoException;
import logica.IControladorEspectaculo;
import logica.IControladorUsuario;

import com.toedter.calendar.JDateChooser;

import datatypes.DtHora;

import javax.swing.JSpinner;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AltaDeFuncionDeEspectaculo extends JInternalFrame {
	private JTextField textField_NombreFuncion;
	
	private IControladorEspectaculo controlEsp;
	private IControladorUsuario controlUsuario;
	
	private JComboBox<String> comboBox_Plataforma;
	private JComboBox<String> comboBox_Espectaculos;
	private JDateChooser dateChooser_FechaFuncion;
	private JSpinner spinner_HoraInicio;
	private JSpinner spinner_MinutosInicio;
	private JDateChooser dateChooser_FechaDeAltaFuncion;
	private JComboBox<String> comboBox_Artistas;
	private JButton btnAgregar;
	private JLabel lblAgregados;
	int i;
	private JComboBox<String> comboBox_Artistas_1;
	private JButton btnAgregar_1;
	
	private Set<String> artistasSet = new HashSet<String>();
	private JTextField textField_Imagen;
	/**
	 * Create the frame.
	 */
	public AltaDeFuncionDeEspectaculo(IControladorEspectaculo IE, IControladorUsuario IU) {
		
		controlEsp = IE;
		controlUsuario = IU;
		i = 0;
		//SIN EVENTOS
		
		setTitle("Alta de Funcion de Espectaculo");
		setBounds(100, 100, 720, 546);
		getContentPane().setLayout(null);
		
		JLabel lblElijaElEspectaculo = new JLabel("Elija el espectaculo:");
		lblElijaElEspectaculo.setBounds(10, 11, 180, 23);
		getContentPane().add(lblElijaElEspectaculo);
		
		JLabel lblPlataforma = new JLabel("Plataforma:");
		lblPlataforma.setBounds(10, 56, 104, 23);
		getContentPane().add(lblPlataforma);
		
		JLabel lblNombreFuncion = new JLabel("Nombre:");
		lblNombreFuncion.setBounds(10, 166, 104, 23);
		getContentPane().add(lblNombreFuncion);
		
		textField_NombreFuncion = new JTextField();
		textField_NombreFuncion.setColumns(10);
		textField_NombreFuncion.setBounds(159, 167, 387, 20);
		getContentPane().add(textField_NombreFuncion);
		
		JLabel lblDatosBasicosDe = new JLabel("Datos basicos de la funcion:");
		lblDatosBasicosDe.setBounds(10, 132, 180, 23);
		getContentPane().add(lblDatosBasicosDe);
		
		JLabel lblFechaFuncion = new JLabel("Fecha:");
		lblFechaFuncion.setBounds(10, 270, 104, 23);
		getContentPane().add(lblFechaFuncion);
		
		Date date = new Date();
		
		dateChooser_FechaFuncion = new JDateChooser(date);
		dateChooser_FechaFuncion.setBounds(159, 273, 160, 20);
		getContentPane().add(dateChooser_FechaFuncion);
		
		JLabel lblHoraInicio = new JLabel("Hora de inicio:");
		lblHoraInicio.setBounds(10, 320, 104, 23);
		getContentPane().add(lblHoraInicio);
		
		spinner_HoraInicio = new JSpinner();
		spinner_HoraInicio.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner_HoraInicio.setBounds(159, 323, 54, 20);
		getContentPane().add(spinner_HoraInicio);
		
		spinner_MinutosInicio = new JSpinner();
		spinner_MinutosInicio.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_MinutosInicio.setBounds(233, 323, 47, 20);
		getContentPane().add(spinner_MinutosInicio);
		
		JLabel lblHs = new JLabel("Hs");
		lblHs.setBounds(290, 322, 28, 19);
		getContentPane().add(lblHs);
		
		JLabel lblDosPuntos = new JLabel(":");
		lblDosPuntos.setBounds(221, 320, 13, 23);
		getContentPane().add(lblDosPuntos);
		
		JLabel lblFechaDeAltaFuncion = new JLabel("Fecha de alta:");
		lblFechaDeAltaFuncion.setBounds(10, 410, 104, 23);
		getContentPane().add(lblFechaDeAltaFuncion);
		
		dateChooser_FechaDeAltaFuncion = new JDateChooser(date);
		dateChooser_FechaDeAltaFuncion.setBounds(159, 413, 160, 20);
		getContentPane().add(dateChooser_FechaDeAltaFuncion);
		
		
		JLabel lblEspectaculos = new JLabel("Espectaculos:");
		lblEspectaculos.setBounds(10, 90, 104, 23);
		getContentPane().add(lblEspectaculos);
		
		JLabel lblArtistasInvitados = new JLabel("Artistas invitados:");
		lblArtistasInvitados.setBounds(10, 368, 104, 23);
		getContentPane().add(lblArtistasInvitados);
		
		comboBox_Artistas = new JComboBox<String>();
		comboBox_Artistas.setBounds(159, 369, 209, 20);
		getContentPane().add(comboBox_Artistas);
		
		lblAgregados = new JLabel("Agregados:  0");
		lblAgregados.setBounds(498, 335, 97, 23);
		getContentPane().add(lblAgregados);
		
		//
		
		
		
		//SE ELIJE UNA PLATAFORMA Y SE ACTUALIZA EL COMBO DE ESPECTACULOS
		
		comboBox_Plataforma = new JComboBox<String>();
		comboBox_Plataforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultComboBoxModel<String> modelEspectaculo;
				String[] arrEspectaculos = controlEsp.seleccionarPlataforma(comboBox_Plataforma.getSelectedItem().toString()).stream().toArray(String[]::new);
		        modelEspectaculo = new DefaultComboBoxModel<String>(arrEspectaculos);
		        comboBox_Espectaculos.setModel(modelEspectaculo);
			}
		});
		comboBox_Plataforma.setBounds(159, 57, 209, 20);
		getContentPane().add(comboBox_Plataforma);
		
		
		//SE ELIJE UN ESPECTACULO Y SE ACTUALIZA EL COMBO DE ARTISTAS
		
		comboBox_Espectaculos = new JComboBox<String>();
		comboBox_Espectaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel<String> modelArtistas;
				
				//APARECEN LOS NICKNAMES DE LOS ARTISTAS
				String[] arrArtistas = controlUsuario.getArtistasNick().stream().toArray(String[]::new);
				modelArtistas = new DefaultComboBoxModel<String>(arrArtistas);
				comboBox_Artistas.setModel(modelArtistas);
				
				DefaultComboBoxModel<String> modelArtistas2;
				
				//APARECEN LOS NICKNAMES DE LOS ARTISTAS
				String[] arrArtistas2 = new String[0];
				modelArtistas2 = new DefaultComboBoxModel<String>(arrArtistas2);
				comboBox_Artistas_1.setModel(modelArtistas2);
				
				btnAgregar.setEnabled(comboBox_Artistas.getSelectedIndex() != -1);
			}
		});
		comboBox_Espectaculos.setBounds(159, 91, 209, 20);
		getContentPane().add(comboBox_Espectaculos);
		
		
		//SE AGREGAN ARTISTAS
		
		btnAgregar = new JButton("->");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_Artistas.getSelectedIndex() == -1) btnAgregar.setEnabled(false); else btnAgregar.setEnabled(true);
				if (comboBox_Artistas.getSelectedIndex() != -1 && !artistasSet.contains(comboBox_Artistas.getSelectedItem().toString()) ) {
					artistasSet.add(comboBox_Artistas.getSelectedItem().toString());
					//comboBox_Artistas.remove(comboBox_Artistas.getSelectedIndex());
					i = i+1;
					lblAgregados.setText("Agregados:  " + i);
					comboBox_Artistas_1.addItem(comboBox_Artistas.getSelectedItem().toString());
					comboBox_Artistas.removeItem(comboBox_Artistas.getSelectedItem());
				}
				
				if (comboBox_Artistas_1.getSelectedIndex() != -1) btnAgregar_1.setEnabled(true);
			}
		});
		btnAgregar.setEnabled(comboBox_Artistas.getSelectedIndex() != -1);
		btnAgregar.setBounds(386, 350, 47, 23);
		getContentPane().add(btnAgregar);
		
		//CONFIRMAR
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if (checkFormulario()) {
				try {
					cmdAltaFuncionActionPerformed(arg0);
				} catch (NombreFuncionRepetidoException | EspectaculoNoExistenteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				
				}
			}
		});
		btnAceptar.setBounds(454, 482, 89, 23);
		getContentPane().add(btnAceptar);
		
		//CANCELAR
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(585, 482, 89, 23);
		getContentPane().add(btnCancelar);
		
		btnAgregar_1 = new JButton("<-");
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox_Artistas_1.getSelectedIndex() == -1) btnAgregar_1.setEnabled(false); else btnAgregar_1.setEnabled(true);
				if (comboBox_Artistas.getSelectedIndex() != -1) btnAgregar.setEnabled(true); else btnAgregar.setEnabled(false);
				if (comboBox_Artistas_1.getSelectedIndex() != -1 && artistasSet.contains(comboBox_Artistas_1.getSelectedItem().toString()) ) {
					artistasSet.remove(comboBox_Artistas_1.getSelectedItem().toString());
					//comboBox_Artistas.remove(comboBox_Artistas.getSelectedIndex());
					i = i-1;
					lblAgregados.setText("Agregados:  " + i);
					
					comboBox_Artistas.addItem(comboBox_Artistas_1.getSelectedItem().toString());
					comboBox_Artistas_1.removeItem(comboBox_Artistas_1.getSelectedItem());
					
				}
				
				if (comboBox_Artistas.getSelectedIndex() != -1) btnAgregar.setEnabled(true);
			}
		});
		btnAgregar_1.setEnabled(false);
		btnAgregar_1.setBounds(386, 384, 47, 23);
		getContentPane().add(btnAgregar_1);
		
		comboBox_Artistas_1 = new JComboBox<String>();
		comboBox_Artistas_1.setBounds(454, 369, 209, 20);
		getContentPane().add(comboBox_Artistas_1);
		
		JLabel lblImagen = new JLabel("Imagen:");
		lblImagen.setBounds(10, 222, 104, 23);
		getContentPane().add(lblImagen);
		
		textField_Imagen = new JTextField();
		textField_Imagen.setColumns(10);
		textField_Imagen.setBounds(159, 223, 387, 20);
		getContentPane().add(textField_Imagen);
		
		
	}
	
	
	//FUNCIONES FUERA DEL CONSTRUCTOR
	
	// ESTO SE EJECUTA CUANDO EN EL PRINCIPAL SE SELECCIONA LA OPCION DE ALTA DE FUNCION
	public void cargarPlataformas() {
        DefaultComboBoxModel<String> modelPlataforma;
        modelPlataforma = new DefaultComboBoxModel<String>(controlEsp.listarNombresPlataformas());
        comboBox_Plataforma.setModel(modelPlataforma);
    }
	
	
	protected void cmdAltaFuncionActionPerformed(ActionEvent arg0) throws NombreFuncionRepetidoException, EspectaculoNoExistenteException {

		try {
				String nombreEspectaculo = comboBox_Espectaculos.getSelectedItem().toString();
				String nombreFuncion = textField_NombreFuncion.getText();
				String imagen = textField_Imagen.getText();
				Date fecRegistro = dateChooser_FechaFuncion.getDate();
				
				Date fechaAlta = dateChooser_FechaDeAltaFuncion.getDate();
				
				DtHora horaRegistro = new DtHora((int) spinner_HoraInicio.getValue(), (int) spinner_MinutosInicio.getValue());
				
				
				controlEsp.confirmarAltaFuncion(nombreEspectaculo, nombreFuncion, fecRegistro, horaRegistro, artistasSet, fechaAlta, imagen);
			
				JOptionPane.showMessageDialog(this, "La funcion se ha dado de alta", "Alta de funcion",
						JOptionPane.INFORMATION_MESSAGE);
				
				limpiarFormulario();
				setVisible(false);
			
			}catch(NombreFuncionRepetidoException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Alta de funcion", JOptionPane.ERROR_MESSAGE);
				textField_NombreFuncion.setText("");
				}
		
	  }
	
	
	private boolean checkFormulario() {
		
		boolean plataformaValida = comboBox_Plataforma.getSelectedIndex() != -1;
		boolean espectaculoValido = comboBox_Espectaculos.getSelectedIndex() != -1;
		boolean funcionValida = textField_NombreFuncion.getText() != "";

		
		if (!plataformaValida) {
			JOptionPane.showMessageDialog(this, "Plataforma invalida", "Alta Funcion",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		else {
			if(!espectaculoValido || !funcionValida) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta Funcion",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;	
			}
		}
		
		return true;
		
	}
	

	private void limpiarFormulario() {
		i = 0;
		//comboBox_Plataforma.removeAllItems();
		comboBox_Espectaculos.removeAllItems();
		comboBox_Artistas.removeAllItems();
		textField_NombreFuncion.setText("");
		spinner_HoraInicio.setValue(0);
		spinner_MinutosInicio.setValue(0);
		//spinner_Agregados.setValue(0);
		lblAgregados.setText("Agregados:  " + i);
		artistasSet.clear();
		
	}
}

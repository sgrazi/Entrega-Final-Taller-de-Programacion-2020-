package presentacion;


import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import excepciones.*;
import logica.IControladorEspectaculo;


import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.Color;


@SuppressWarnings("serial")
public class RegistroAFuncionDeEspectaculo extends JInternalFrame {
	
	private IControladorEspectaculo controlEsp;
	private JComboBox<String> comboBox_Plataforma;
	private JComboBox<String> comboBox_Espectaculos;
	private JComboBox<String> comboBox_Funcion;
	private JComboBox<String> comboBox_Espectador;
	private JLabel lblPrecio;
	private JCheckBox chckbxCanjear;
	private JComboBox<String> comboBox_r1;
	private JComboBox<String> comboBox_r2;
	private JComboBox<String> comboBox_r3;
	private JButton btnCanjear;
	private JLabel lblcanjeInvalido;
	private JDateChooser dateChooser_FechaDeAltaFuncion;
	private boolean canjeConfirmado;
	private JComboBox<String> comboBox_Funcion_1;
	private JButton btnCancelar_1;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public RegistroAFuncionDeEspectaculo(IControladorEspectaculo ice) {
		
		controlEsp = ice;
		
		
		
		//SIN ACCIONES
		Date date = new Date();
		
		setTitle("Registro a funcion de espectaculo");
		setBounds(100, 100, 722, 572);
		getContentPane().setLayout(null);
		
		//
		
		//SE CANJEAN LOS REGISTROS POR APARIENCIA PERO SE REGISTRA EN EL CONFIRMAR
		
		btnCanjear = new JButton("Canjear");
		btnCanjear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (chckbxCanjear.isSelected() && controlEsp.formanCanjeValido(comboBox_Espectador.getSelectedItem().toString(),
							comboBox_r1.getSelectedItem().toString(), comboBox_r2.getSelectedItem().toString(), comboBox_r3.getSelectedItem().toString())) {
						lblPrecio.setText("0");
						chckbxCanjear.setEnabled(false);
						comboBox_r1.setEnabled(false);
						comboBox_r2.setEnabled(false);
						comboBox_r3.setEnabled(false);
						btnCanjear.setEnabled(false);
						lblcanjeInvalido.setVisible(false);
						canjeConfirmado = true;
					} else {
						lblcanjeInvalido.setVisible(true);
					}
			}
		});
		
		btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (canjeConfirmado) {
				if (comboBox_Espectaculos.getSelectedIndex() != -1) {
					lblPrecio.setText(String.valueOf(controlEsp.getCostoEspectaculo(comboBox_Espectaculos.getSelectedItem().toString())));
				} else lblPrecio.setText("");
				
				if (comboBox_Espectador.getSelectedIndex() != -1 && controlEsp.canjeHabilitado(comboBox_Espectador.getSelectedItem().toString())) {
					chckbxCanjear.setSelected(false);
					comboBox_r1.setEnabled(false);
					comboBox_r2.setEnabled(false);
					comboBox_r3.setEnabled(false);
					chckbxCanjear.setEnabled(true);
					}
				canjeConfirmado = false;
				}
			}
		});
		btnCancelar_1.setBounds(607, 443, 89, 23);
		getContentPane().add(btnCancelar_1);
		
		comboBox_Funcion = new JComboBox<String>();
		comboBox_Funcion.setBounds(159, 269, 209, 20);
		getContentPane().add(comboBox_Funcion);
		dateChooser_FechaDeAltaFuncion = new JDateChooser(date);
		dateChooser_FechaDeAltaFuncion.setBounds(159, 395, 160, 20);
		getContentPane().add(dateChooser_FechaDeAltaFuncion);
		
		JLabel lblIngreseLosDatos = new JLabel("Ingrese los datos del registro:");
		lblIngreseLosDatos.setBounds(10, 234, 180, 23);
		getContentPane().add(lblIngreseLosDatos);
		
		JLabel lblFechaDeRegistro = new JLabel("Fecha de registro:");
		lblFechaDeRegistro.setBounds(10, 395, 104, 23);
		getContentPane().add(lblFechaDeRegistro);
		
		JLabel lblFuncion = new JLabel("Funcion:");
		lblFuncion.setBounds(10, 268, 104, 23);
		getContentPane().add(lblFuncion);
		
		JLabel lblEspectador = new JLabel("Espectador:");
		lblEspectador.setBounds(10, 322, 104, 23);
		getContentPane().add(lblEspectador);
		
		//SE SELECCIONA UN ESPECTADOR Y LOS REGISTROS APARECEN
		
		comboBox_Espectador = new JComboBox<String>();
		comboBox_Espectador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//HAGO UNA ESPECIE DE CANCELAR AL CAMBIAR DE ESPECTADOR
				if (comboBox_Espectaculos.getSelectedIndex() != -1) {
					lblPrecio.setText(String.valueOf(controlEsp.getCostoEspectaculo(comboBox_Espectaculos.getSelectedItem().toString())));
				}
					
					reiniciarCanje();
				
				
				
					if (comboBox_Espectador.getSelectedIndex() != -1 && controlEsp.canjeHabilitado(comboBox_Espectador.getSelectedItem().toString())) {
						chckbxCanjear.setEnabled(true);
						try {
							DefaultComboBoxModel<String> modelRegistro1 , modelRegistro2, modelRegistro3;
							String[] arrRegistrosNoCanjeados;
					
							arrRegistrosNoCanjeados = controlEsp.seleccionarEspectadorYFuncion(comboBox_Espectador.getSelectedItem().toString()).stream().toArray(String[]::new);
						
							modelRegistro1 = new DefaultComboBoxModel<String>(arrRegistrosNoCanjeados);
							modelRegistro2 = new DefaultComboBoxModel<String>(arrRegistrosNoCanjeados);
							modelRegistro3 = new DefaultComboBoxModel<String>(arrRegistrosNoCanjeados);
							comboBox_r1.setModel(modelRegistro1);
							comboBox_r2.setModel(modelRegistro2);
							comboBox_r3.setModel(modelRegistro3);
					
						} catch (UsuarioNoExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						comboBox_r1.removeAllItems();
						comboBox_r2.removeAllItems();
						comboBox_r3.removeAllItems();
					}
				
			}
		});
		comboBox_Espectador.setBounds(159, 323, 209, 20);
		getContentPane().add(comboBox_Espectador);
		btnCanjear.setEnabled(false);
		btnCanjear.setBounds(521, 443, 89, 23);
		getContentPane().add(btnCanjear);
		
		JLabel lblElijaElEspectaculo = new JLabel("Elija el espectaculo:");
		lblElijaElEspectaculo.setBounds(10, 11, 180, 23);
		getContentPane().add(lblElijaElEspectaculo);
		
		JLabel lblPlataforma = new JLabel("Plataforma:");
		lblPlataforma.setBounds(10, 56, 104, 23);
		getContentPane().add(lblPlataforma);
		
		JLabel lblEspectaculos = new JLabel("Espectaculos:");
		lblEspectaculos.setBounds(10, 90, 104, 23);
		getContentPane().add(lblEspectaculos);
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(159, 146, 537, 1);
		getContentPane().add(separator);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(423, 214, 71, 1);
		getContentPane().add(separator_1_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1.setBounds(423, 214, 1, 242);
		getContentPane().add(separator_1_1_1);
		
		JSeparator separator_1_1_2 = new JSeparator();
		separator_1_1_2.setBounds(630, 214, 66, 1);
		getContentPane().add(separator_1_1_2);
		
		JSeparator separator_1_1_3 = new JSeparator();
		separator_1_1_3.setBounds(423, 455, 273, 1);
		getContentPane().add(separator_1_1_3);
		
		JLabel lblRegistro = new JLabel("Registro 1:");
		lblRegistro.setBounds(433, 228, 104, 23);
		getContentPane().add(lblRegistro);
		
		JLabel lblRegistro_2 = new JLabel("Registro 2:");
		lblRegistro_2.setBounds(433, 294, 104, 23);
		getContentPane().add(lblRegistro_2);
		
		JLabel lblRegistro_3 = new JLabel("Registro 3:");
		lblRegistro_3.setBounds(434, 364, 104, 23);
		getContentPane().add(lblRegistro_3);
		
		comboBox_r1 = new JComboBox<String>();
		comboBox_r1.setEnabled(false);
		comboBox_r1.setBounds(434, 252, 209, 20);
		getContentPane().add(comboBox_r1);
		
		comboBox_r2 = new JComboBox<String>();
		comboBox_r2.setEnabled(false);
		comboBox_r2.setBounds(434, 328, 209, 20);
		getContentPane().add(comboBox_r2);
		
		comboBox_r3 = new JComboBox<String>();
		comboBox_r3.setEnabled(false);
		comboBox_r3.setBounds(434, 398, 209, 20);
		getContentPane().add(comboBox_r3);
		
		JSeparator separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1_1_1.setBounds(695, 214, 1, 242);
		getContentPane().add(separator_1_1_1_1);
		
		JLabel lblCosto = new JLabel("Costo:");
		lblCosto.setBounds(10, 455, 44, 23);
		getContentPane().add(lblCosto);
		
		lblPrecio = new JLabel("");
		lblPrecio.setBounds(86, 455, 55, 23);
		getContentPane().add(lblPrecio);
		
		JLabel lblPesos = new JLabel("$");
		lblPesos.setBounds(64, 455, 38, 23);
		getContentPane().add(lblPesos);
		
		lblcanjeInvalido = new JLabel("*Canje invalido*");
		lblcanjeInvalido.setVisible(false);
		lblcanjeInvalido.setForeground(Color.RED);
		lblcanjeInvalido.setBounds(434, 429, 148, 23);
		getContentPane().add(lblcanjeInvalido);
		
		canjeConfirmado = false;
		
		
		//SE SELECCIONA UNA PLATAFORMA Y SE ACTUALIZA EL COMBO ESPECTACULO
		
		comboBox_Plataforma = new JComboBox<String>();
		comboBox_Plataforma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox_Funcion.removeAllItems();
				comboBox_Funcion_1.removeAllItems();
				
				
				DefaultComboBoxModel<String> modelEspectaculo;
				String[] arrEspectaculos = controlEsp.seleccionarPlataforma(comboBox_Plataforma.getSelectedItem().toString()).stream().toArray(String[]::new);
		        modelEspectaculo = new DefaultComboBoxModel<String>(arrEspectaculos);
		        comboBox_Espectaculos.setModel(modelEspectaculo);

			}
		});
		comboBox_Plataforma.setBounds(159, 57, 209, 20);
		getContentPane().add(comboBox_Plataforma);
		
		//SE SELECCIONA UN ESPECTACULO Y SE ACTUALIZA EL COMBO FUNCION Y ESPECTADOR
		
		comboBox_Espectaculos = new JComboBox<String>();
		comboBox_Espectaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//PARA EL COMBOBOX DE DATOS FUNCION
				
				DefaultComboBoxModel<String> modelFuncionDatos;
				String[] arrFuncionesDatos;
				if (comboBox_Espectaculos.getSelectedIndex() != -1) {
				arrFuncionesDatos = controlEsp.obtenerDtFuncionesAString(comboBox_Espectaculos.getSelectedItem().toString()).stream().toArray(String[]::new);
				} else arrFuncionesDatos = new String[0];
				modelFuncionDatos = new DefaultComboBoxModel<String>(arrFuncionesDatos);
				comboBox_Funcion_1.setModel(modelFuncionDatos);
				
				//PARA EL COMBOBOX DE FUNCION
				
				DefaultComboBoxModel<String> modelFuncion;
				String[] arrFunciones;
				if (comboBox_Espectaculos.getSelectedIndex() != -1) {
				arrFunciones = controlEsp.listarFunciones(comboBox_Espectaculos.getSelectedItem().toString()).stream().toArray(String[]::new);
				} else arrFunciones = new String[0];
				modelFuncion = new DefaultComboBoxModel<String>(arrFunciones);
				comboBox_Funcion.setModel(modelFuncion);
				
				//PARA EL COMBOBOX DE ESPECTADOR
				
				
				
				//PARA EL COSTO
				if (comboBox_Espectaculos.getSelectedIndex() != -1 && !canjeConfirmado) {
				lblPrecio.setText(String.valueOf(controlEsp.getCostoEspectaculo(comboBox_Espectaculos.getSelectedItem().toString())));
				}
				
			}
		});
		comboBox_Espectaculos.setBounds(159, 91, 209, 20);
		getContentPane().add(comboBox_Espectaculos);
		
		//CONFIRMAR
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFormulario()) {
					try {
						cmdRegistroAFuncionActionPerformed(arg0);
						
					} catch (TicketsAgotadosParaFuncionException | EspectadorYaRegistradoException | CanjeInvalidoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (EspectaculoNoExistenteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

		btnAceptar.setBounds(390, 508, 89, 23);
		getContentPane().add(btnAceptar);
		
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
                setVisible(false);
			}
		});
		btnCancelar.setBounds(521, 508, 89, 23);
		getContentPane().add(btnCancelar);
		
		chckbxCanjear = new JCheckBox("Canjear Registro");
		chckbxCanjear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxCanjear.isSelected()) {
					comboBox_r1.setEnabled(true);
					comboBox_r2.setEnabled(true);
					comboBox_r3.setEnabled(true);
					btnCanjear.setEnabled(true);
				} else {
					comboBox_r1.setEnabled(false);
					comboBox_r2.setEnabled(false);
					comboBox_r3.setEnabled(false);
					btnCanjear.setEnabled(false);
				}
			}
		});
		chckbxCanjear.setEnabled(false);
		chckbxCanjear.setBounds(508, 198, 135, 23);
		getContentPane().add(chckbxCanjear);
		
		JLabel lblFuncionDatos = new JLabel("Datos de funciones:");
		lblFuncionDatos.setBounds(10, 135, 131, 23);
		getContentPane().add(lblFuncionDatos);
		
		comboBox_Funcion_1 = new JComboBox<String>();
		comboBox_Funcion_1.setBounds(20, 171, 644, 20);
		getContentPane().add(comboBox_Funcion_1);

	}
	
	
	//FUNCIONES POR FUERA DEL CONSTRUCTOR
	
	public void cargarPlataformas() {
        DefaultComboBoxModel<String> modelPlataforma;
        modelPlataforma = new DefaultComboBoxModel<String>(controlEsp.listarNombresPlataformas());
        comboBox_Plataforma.setModel(modelPlataforma);
    }
	
	public void cargarEspectadores() {
		DefaultComboBoxModel<String> modelEspectador;
		String[] arrEspectadores = controlEsp.listarEspectadoresPorNick().stream().toArray(String[]::new);
		modelEspectador = new DefaultComboBoxModel<String>(arrEspectadores);
		comboBox_Espectador.setModel(modelEspectador);
    }
	
	
	
	
	protected void cmdRegistroAFuncionActionPerformed(ActionEvent arg0) throws EspectadorYaRegistradoException, TicketsAgotadosParaFuncionException, CanjeInvalidoException, EspectaculoNoExistenteException {
		try {
			
			String nombreEspectaculo = comboBox_Espectaculos.getSelectedItem().toString();
			String nickEspectador = comboBox_Espectador.getSelectedItem().toString();
			String nombreFuncion = comboBox_Funcion.getSelectedItem().toString();
			Date fecRegistro = dateChooser_FechaDeAltaFuncion.getDate();
			String r1;
			String r2;
			String r3;
			if (canjeConfirmado) {
				r1 = comboBox_r1.getSelectedItem().toString();
				r2 = comboBox_r2.getSelectedItem().toString();
				r3 = comboBox_r3.getSelectedItem().toString();
			} else {
				r1 = "";
				r2 = "";
				r3 = "";
			}
			
			controlEsp.confirmarAltaRegistroAFuncion(nombreEspectaculo, nickEspectador, nombreFuncion, fecRegistro, canjeConfirmado, r1, r2, r3);
			JOptionPane.showMessageDialog(this, "Registro exitoso", "Registro a funcion", JOptionPane.INFORMATION_MESSAGE);
			limpiarFormulario();
			setVisible(false);
			
		} catch (EspectaculoNoExistenteException | TicketsAgotadosParaFuncionException | EspectadorYaRegistradoException | CanjeInvalidoException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(), "Registro a funcion", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private boolean checkFormulario() {

		boolean valido = (comboBox_Plataforma.getSelectedIndex() != -1) && (comboBox_Espectaculos.getSelectedIndex() != -1) &&
				(comboBox_Funcion.getSelectedIndex() != -1) && (comboBox_Espectador.getSelectedIndex() != -1);
		
		if (!valido) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Registro a funcion",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
		return true;
		
	}
	
	private void reiniciarCanje() {
		chckbxCanjear.setSelected(false);
		chckbxCanjear.setEnabled(false);
		btnCanjear.setEnabled(false);
		comboBox_r1.setEnabled(false);
		comboBox_r2.setEnabled(false);
		comboBox_r3.setEnabled(false);
		canjeConfirmado = false;
	}
	
	private void limpiarFormulario() {
		
		comboBox_r1.setEnabled(false);
		comboBox_r2.setEnabled(false);
		comboBox_r3.setEnabled(false);
		btnCanjear.setEnabled(false);
		
		
		chckbxCanjear.setEnabled(false);
		chckbxCanjear.setSelected(false);
		lblcanjeInvalido.setVisible(false);
		lblPrecio.setText("");
		
		
		comboBox_r1.removeAllItems();
		comboBox_r2.removeAllItems();
		comboBox_r3.removeAllItems();
		comboBox_Funcion.removeAllItems();
		comboBox_Funcion_1.removeAllItems();
		comboBox_Espectaculos.removeAllItems();
		comboBox_Espectador.removeAllItems();
		
	}
}

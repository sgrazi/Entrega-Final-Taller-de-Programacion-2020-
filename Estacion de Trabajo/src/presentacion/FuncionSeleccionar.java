package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import excepciones.EspectaculoNoExistenteException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FuncionSeleccionar extends JInternalFrame {
	private JComboBox<String> cboFunciones;
	
	/**
	 * Create the frame.
	 */
	public FuncionSeleccionar() {
		setTitle("Seleccionar");
		setClosable(true);
		setBounds(100, 100, 413, 125);
		getContentPane().setLayout(null);
		
		JLabel lblFuncion = new JLabel("Funcion");
		lblFuncion.setBounds(12, 16, 76, 16);
		getContentPane().add(lblFuncion);
		
		cboFunciones = new JComboBox<String>();
		cboFunciones.setBounds(64, 16, 321, 22);
		getContentPane().add(cboFunciones);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					consultarFuncion(e);
				} catch (EspectaculoNoExistenteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(203, 50, 87, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(298, 50, 87, 25);
		getContentPane().add(btnCancelar);

	}

	public void cargarFunciones(String[] espectaculos) {
		DefaultComboBoxModel<String> model;
        try {
        	
            model = new DefaultComboBoxModel<String>(espectaculos);
            this.cboFunciones.setModel(model);
            
        } catch (Exception e) {
        	//NO SE MUESTRA NINGUN ELEMENTO
        }
	}
	
	public void consultarFuncion(ActionEvent e) throws EspectaculoNoExistenteException {
		
		if (cboFunciones.getSelectedIndex() != -1) {
			String funcion = (String) cboFunciones.getSelectedItem(); 
			
			Principal.ConsultarFuncion(funcion);	
		}
		
	}

}

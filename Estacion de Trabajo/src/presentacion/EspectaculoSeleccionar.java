package presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import logica.IControladorEspectaculo;

import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EspectaculoSeleccionar extends JInternalFrame {

	private JComboBox<String> cboEspectaculos;
	
	/**
	 * Create the frame.
	 */
	public EspectaculoSeleccionar(IControladorEspectaculo IE) {
		setTitle("Seleccionar");
		setClosable(true);
		setBounds(100, 100, 445, 129);
		getContentPane().setLayout(null);
		
		cboEspectaculos = new JComboBox<String>();
		cboEspectaculos.setBounds(87, 13, 321, 22);
		getContentPane().add(cboEspectaculos);
		
		JLabel lblEspectaculo = new JLabel("Espectaculo");
		lblEspectaculo.setBounds(12, 16, 76, 16);
		getContentPane().add(lblEspectaculo);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboEspectaculos.getSelectedIndex() != -1) {
					String espectaculo = (String) cboEspectaculos.getSelectedItem(); 
					
					Principal.ConsultarEspectaculo(espectaculo);	
				}
			}
		});
		btnAceptar.setBounds(226, 47, 87, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(321, 47, 87, 25);
		getContentPane().add(btnCancelar);

	}
	
	public void cargarEspectaculos(String[] espectaculos) {
		DefaultComboBoxModel<String> model;
        try {
        	
            model = new DefaultComboBoxModel<String>(espectaculos);
            this.cboEspectaculos.setModel(model);
            
        } catch (Exception e) {
        	//NO SE MUESTRA NINGUN ELEMENTO
        }
	}
}

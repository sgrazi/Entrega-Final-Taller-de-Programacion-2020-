package presentacion;


import javax.swing.JInternalFrame;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import datatypes.*;
import excepciones.UsuarioNoExisteException;
import logica.IControladorUsuario;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class ConsultaUsuario extends JInternalFrame {
	
	private IControladorUsuario IUsuario;
	
	public JComboBox<DtUsuario> cboUsuarios;
	public JButton btnAceptar; 
	
	/**
	 * Create the frame.
	 */
	public ConsultaUsuario(IControladorUsuario iu) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarUsuarios();
			}
		});
		//INICIALIZO EL CONTROLADOR DE USUARIO
		IUsuario = iu;
				
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
                cerrarVentana();
			}
		});
		
		setClosable(true);
				
		setTitle("Consultar usuario");
		setBounds(100, 100, 417, 125);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(12, 16, 62, 16);
		getContentPane().add(lblNewLabel);
		
		cboUsuarios = new JComboBox<DtUsuario>();
		cboUsuarios.setBounds(67, 13, 321, 22);
		getContentPane().add(cboUsuarios);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(206, 48, 87, 25);
		
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (cboUsuarios.getSelectedIndex() == -1) {
						throw new UsuarioNoExisteException("Debe seleccionar un usuario");
					}
					
					cargarDatosDeUsuario(e);
					
				} catch (UsuarioNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btnCancelar.setBounds(301, 48, 87, 25);
		getContentPane().add(btnCancelar);

	}
	
	public void cargarUsuarios() {
		cboUsuarios.removeAllItems();
		
        DefaultComboBoxModel<DtUsuario> model;
        try {
            model = new DefaultComboBoxModel<DtUsuario>(IUsuario.obtenerUsuarios());
            cboUsuarios.setModel(model);
        } catch (UsuarioNoExisteException e) {
        	//NO SE MUESTRA NINGUN ELEMENTO
        }

    }
	
	public String getUsuarioSeleccionado() {
		return cboUsuarios.getSelectedItem().toString();
	}
	
	private void cargarDatosDeUsuario(ActionEvent arg0) throws UsuarioNoExisteException{
		String email = getUsuarioSeleccionado();
		
		try {
			DtUsuario usuario = this.IUsuario.obtenerUsuario(email);
			
			Principal.ConsultarUsuario(usuario);
			
			this.setVisible(false);
			
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Consultar usuario", JOptionPane.ERROR_MESSAGE);
		} 
		
	}
	
	private void cerrarVentana() {
		setVisible(false);
	    Principal.cerrarTodasLasVentanasDeConsultarUsuario();
	}
    
    
}

package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import datatypes.DtUsuario;
import excepciones.UsuarioNoExisteException;
import logica.IControladorUsuario;

public class seleccionarUsuarioModificar extends ConsultaUsuario {

	private IControladorUsuario IUsuario;
	
	public seleccionarUsuarioModificar(IControladorUsuario iu) {
		super(iu);
				
		IUsuario = iu;
		setTitle("Modificar usuario");
		
		ActionListener[] actions = this.btnAceptar.getActionListeners();
		this.btnAceptar.removeActionListener(actions[0]);
		
		//AGREGO ACCION AL BOTON ACEPTAR
		this.btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = getUsuarioSeleccionado();
				
				try {
					
					modificarDatosDeUsuario(email);
					
				} catch (UsuarioNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
	}
	
	private void modificarDatosDeUsuario(String email) throws UsuarioNoExisteException {
		
		try {
			DtUsuario usuario = this.IUsuario.obtenerUsuario(email);
			
			Principal.ModificarUsuario(usuario);
			
			this.setVisible(false);
			
		} catch (UsuarioNoExisteException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Modificar usuario", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}

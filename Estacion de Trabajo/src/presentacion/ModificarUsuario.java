package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import excepciones.FechaInvalidaException;
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.UsuarioNoExisteException;
import logica.IControladorUsuario;

public class ModificarUsuario extends AltaUsuario{

	private IControladorUsuario IUsuario;
	
	public ModificarUsuario(IControladorUsuario iu) {
		super(iu);
		
		IUsuario = iu;

		setTitle("Modificar usuario");
		vistaModificar();
		
		//BORRO LA ACCION DEL BOTON ACEPTAR Y GENERO LA DE MODIFICAR
		ActionListener[] actions = this.btnAceptar.getActionListeners();
		this.btnAceptar.removeActionListener(actions[0]);
		
		//AGREGO ACCION AL BOTON ACEPTAR
		this.btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					modificarDatosDeUsuario();
					
				} catch (UsuarioAgregarDatosInvalidosException | UsuarioNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
	}

	
	protected void modificarDatosDeUsuario() throws UsuarioAgregarDatosInvalidosException, UsuarioNoExisteException {
		//VERIFICO CAMPOS OBLIGATORIOS
        if ( this.datosObligatoriosCorrectos() ) {

        	String email= this.txtEmail.getText();
            String nick = this.txtNickname.getText();
            String nom 	= this.txtNombre.getText();
            String ape 	= this.txtApellido.getText();
            String des	= this.txtDescripcion.getText();
            String web 	= this.txtSitioWeb.getText();
            String bio 	= this.txtBiografia.getText();
            String con 	= this.txtContrasena.getText();
            String img 	= this.txtImagen.getText();
            Date fn = null;
            
            Calendar cal = Calendar.getInstance();
			cal.setLenient(false);
			
            try {
            	
            	if (txtFecha.getDate() != null) {
	            	try {
						cal.setTime(txtFecha.getDate());
						cal.getTime();
						
						fn = this.txtFecha.getDate();
					} catch (Exception e2) {
						// TODO: handle exception
						throw new FechaInvalidaException("Fecha invalida");
					}
            	}
            	
                this.IUsuario.confirmarModificacion(nick, nom, ape, email, fn, des, bio,web,con,img);

                // Mensaje de exito
                JOptionPane.showMessageDialog(this, "El Usuario se ha modificado con exito", "Modificar de usuario",
                        JOptionPane.INFORMATION_MESSAGE);
                
                setVisible(false);	
                
            } catch (UsuarioAgregarYaExisteException | FechaInvalidaException | UsuarioNoExisteException | UsuarioAgregarDatosInvalidosException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Modificar de usuario", JOptionPane.ERROR_MESSAGE);
            }
            
        }
		
	}
	
}

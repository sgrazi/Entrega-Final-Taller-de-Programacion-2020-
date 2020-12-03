package presentacion;


import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;

import datatypes.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//PAQUETES NUESTROS
import excepciones.UsuarioAgregarDatosInvalidosException;
import excepciones.UsuarioAgregarYaExisteException;
import excepciones.FechaInvalidaException;
import logica.IControladorUsuario;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.TextArea;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {
	
	private IControladorUsuario IUsuario;
		
	public JTextField txtNickname;
	public JTextField txtNombre;
	public JTextField txtApellido;
	public JTextField txtEmail;
	public JTextField txtSitioWeb;
	public JDateChooser txtFecha;
	private JPanel panelArtista;
	public JRadioButton rdArtista;
	public JRadioButton rdEspectador;
	
	private JPanel rdPanel;
	public TextArea txtDescripcion;
	public TextArea txtBiografia;
	public JTextField txtContrasena;
	private JTextField txtContrasena2;
	private JLabel lblNewLabel_1_2_2;
	public JTextField txtImagen;
	public JButton btnAceptar;
	public JButton btnCancelar;
	
	public AltaUsuario(IControladorUsuario iu) {
				
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				limpiarFormulario();
			}
		});
		
		
		//INICIALIZO EL CONTROLADOR DE USUARIO
		IUsuario = iu;
				
		setResizable(false);
		setMaximizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Alta de usuario");
	    setClosable(true);
		setBounds(100, 100, 494, 506);
		getContentPane().setLayout(null);
		
		txtFecha = new JDateChooser();
		txtFecha.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		txtFecha.setBounds(128, 140, 135, 22);
		getContentPane().add(txtFecha);
		
		JLabel lblNewLabel = new JLabel("Nickname");
		lblNewLabel.setBounds(16, 53, 80, 16);
		getContentPane().add(lblNewLabel);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(128, 50, 135, 22);
		getContentPane().add(txtNickname);
		txtNickname.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(16, 83, 45, 16);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(128, 80, 135, 22);
		txtNombre.setColumns(10);
		getContentPane().add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(273, 83, 45, 16);
		getContentPane().add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(328, 80, 135, 22);
		txtApellido.setColumns(10);
		getContentPane().add(txtApellido);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail");
		lblNewLabel_1.setBounds(16, 113, 36, 16);
		getContentPane().add(lblNewLabel_1);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(128, 110, 335, 22);
		txtEmail.setColumns(10);
		getContentPane().add(txtEmail);
		
		JLabel lblNewLabel_1_1 = new JLabel("Fecha de nacimiento");
		lblNewLabel_1_1.setBounds(16, 140, 107, 22);
		getContentPane().add(lblNewLabel_1_1);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(281, 441, 87, 25);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					doyDeAltaUsuario(e);
					
				} catch (UsuarioAgregarDatosInvalidosException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FechaInvalidaException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			}
		});
		getContentPane().add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            setVisible(false);
			}
		});
		btnCancelar.setBounds(376, 441, 87, 25);
		getContentPane().add(btnCancelar);
		
		rdPanel = new JPanel();
		rdPanel.setBounds(102, 11, 177, 30);
		getContentPane().add(rdPanel);
		
		rdEspectador = new JRadioButton("Espectador");
		rdEspectador.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				verPanelArtista(!rdEspectador.isSelected());
			}
		});
		rdEspectador.setSelected(true);
		rdPanel.add(rdEspectador);
		
		rdArtista = new JRadioButton("Artista");
		rdArtista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				verPanelArtista(rdArtista.isSelected());
			}
		});
		rdPanel.add(rdArtista);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdArtista);
	    group.add(rdEspectador);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("Contraseña");
	    lblNewLabel_1_2.setBounds(16, 178, 92, 16);
	    getContentPane().add(lblNewLabel_1_2);
	    
	    txtContrasena = new JTextField();
	    txtContrasena.setColumns(10);
	    txtContrasena.setBounds(128, 173, 335, 22);
	    getContentPane().add(txtContrasena);
	    
	    JLabel lblNewLabel_1_2_1 = new JLabel("Repita contraseña");
	    lblNewLabel_1_2_1.setBounds(16, 208, 115, 16);
	    getContentPane().add(lblNewLabel_1_2_1);
	    
	    txtContrasena2 = new JTextField();
	    txtContrasena2.setColumns(10);
	    txtContrasena2.setBounds(128, 203, 335, 22);
	    getContentPane().add(txtContrasena2);
	    
	    lblNewLabel_1_2_2 = new JLabel("Imagen");
	    lblNewLabel_1_2_2.setBounds(16, 240, 115, 16);
	    getContentPane().add(lblNewLabel_1_2_2);
	    
	    panelArtista = new JPanel();
	    panelArtista.setBounds(0, 267, 482, 163);
	    getContentPane().add(panelArtista);
	    panelArtista.setLayout(null);
	    
	    JLabel lblNewLabel_1_1_1 = new JLabel("Descripcion");
	    lblNewLabel_1_1_1.setBounds(17, 7, 97, 16);
	    panelArtista.add(lblNewLabel_1_1_1);
	    
	    JLabel lblNewLabel_1_1_1_1 = new JLabel("Sitio web");
	    lblNewLabel_1_1_1_1.setBounds(17, 131, 97, 16);
	    panelArtista.add(lblNewLabel_1_1_1_1);
	    
	    JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Biografia");
	    lblNewLabel_1_1_1_1_1.setBounds(17, 67, 97, 16);
	    panelArtista.add(lblNewLabel_1_1_1_1_1);
	    
	    txtDescripcion = new TextArea("",0,0,txtDescripcion.SCROLLBARS_VERTICAL_ONLY);
	    txtDescripcion.setBounds(127, 6, 336, 55);
	    panelArtista.add(txtDescripcion);
	    
	    txtSitioWeb = new JTextField();
	    txtSitioWeb.setBounds(127, 128, 336, 22);
	    txtSitioWeb.setColumns(10);
	    panelArtista.add(txtSitioWeb);
	    
	    txtBiografia = new TextArea("",0,0,txtDescripcion.SCROLLBARS_VERTICAL_ONLY);
	    txtBiografia.setBounds(127, 67, 336, 55);
	    panelArtista.add(txtBiografia);
	    
	    //EL PANEL DE ARTISTA ESTA NO VISIBLE POR DEFECTO
	    panelArtista.setVisible(false);
	    
	    txtImagen = new JTextField();
	    txtImagen.setColumns(10);
	    txtImagen.setBounds(128, 234, 335, 22);
	    getContentPane().add(txtImagen);
	}

	public void limpiarFormulario() {
		/*
		FConsulta = false;
		
		setTitle("Alta de usuario");
		setLocation(33, 29);
		
		rdEspectador.setEnabled(true);
		rdEspectador.setSelected(true);
		rdArtista.setEnabled(true);
		*/
		
		rdEspectador.setSelected(true);
        txtNickname.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtFecha.cleanup();
        txtFecha.setDate(null);
        txtContrasena.setText("");
        txtImagen.setText("");
        txtDescripcion.setText("");
        txtBiografia.setText("");
        txtSitioWeb.setText("");
        
        //btnAceptar.setVisible(true);
        
    }
	
	public boolean datosObligatoriosCorrectos() {
		String email = this.txtEmail.getText();
        String nick = this.txtNickname.getText();
        String contrasena = this.txtContrasena.getText();
        
        if ( email.isEmpty() ) {
            JOptionPane.showMessageDialog(this, "El campo E-mail no puede ser vacio", "Alta de usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if ( nick.isEmpty() ) {
        	JOptionPane.showMessageDialog(this, "El campo  Nickname no puede ser vacio", "Alta de usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if ( contrasena.isEmpty() ) {
        	JOptionPane.showMessageDialog(this, "El campo contraseña no puede ser vacio", "Alta de usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if ( !contrasena.equals(this.txtContrasena2.getText()) ) {
        	JOptionPane.showMessageDialog(this, "La contraseña no coincide con el campo reingresar contraseña", "Alta de usuario",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
	}
		
	protected void doyDeAltaUsuario(ActionEvent arg0) throws UsuarioAgregarDatosInvalidosException, FechaInvalidaException {
		//VERIFICO CAMPOS OBLIGATORIOS
        if ( datosObligatoriosCorrectos() ) {

        	boolean esArtista = this.rdArtista.isSelected();
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
            	
                this.IUsuario.confirmarAlta(esArtista, nick, nom, ape, email, fn, des, bio,web,con,img);

                // Mensaje de exito
                JOptionPane.showMessageDialog(this, "El Usuario se ha creado con exito", "Alta de usuario",
                        JOptionPane.INFORMATION_MESSAGE);
                
                // Limpio el internal frame antes de cerrar la ventana
                limpiarFormulario();
                setVisible(false);	
                
            } catch (UsuarioAgregarYaExisteException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de usuario", JOptionPane.ERROR_MESSAGE);
            } catch (FechaInvalidaException e1) {
                JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta de usuario", JOptionPane.ERROR_MESSAGE);
            }
            
        }
		
	}
	
	public void cargoFormulario(DtUsuario usuario) {
			
		this.rdEspectador.setSelected(true);
		this.txtEmail.setText(usuario.getCorreoElectronico());
		this.txtNickname.setText(usuario.getNickName());
        this.txtNombre.setText(usuario.getNombre());
        this.txtApellido.setText(usuario.getApellido());
        this.txtContrasena.setText(usuario.getContrasena());
        this.txtContrasena2.setText(usuario.getContrasena());
        this.txtImagen.setText(usuario.getImagen());
        this.txtFecha.setDate(usuario.getFechaDeNacimiento());
        
		if (usuario instanceof DtArtista) {
			DtArtista a = ((DtArtista) usuario);
			
			this.rdArtista.setSelected(true);
            this.txtDescripcion.setText(a.getDescripcion());
            this.txtSitioWeb.setText(a.getUrl());
            this.txtBiografia.setText(a.getBiografia());

		}
		
	}
	
	public void cargoVistaConsulta(DtUsuario usuario) {
		cargoFormulario(usuario);
	}
	
	private void verPanelArtista(boolean estado) {
		if (this.panelArtista != null){
			this.panelArtista.setVisible(estado);
		}
	}
	
	public void vistaModificar() {
		
		this.rdEspectador.setEnabled(false);
		this.rdArtista.setEnabled(false);
		this.txtNickname.setEnabled(false);
		this.txtEmail.setEnabled(false);
	}
	
	public void vistaConsultar() {
		
		this.rdEspectador.setEnabled(false);
		this.rdArtista.setEnabled(false);
		this.btnAceptar.setEnabled(false);
	}

}

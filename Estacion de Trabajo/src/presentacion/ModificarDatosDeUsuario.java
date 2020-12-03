package presentacion;


import javax.swing.JInternalFrame;

import logica.IControladorUsuario;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import com.toedter.calendar.JDateChooser;

public class ModificarDatosDeUsuario extends JInternalFrame {

	private IControladorUsuario IUsuario;
	private JTextField NicknameABuscar;
	private JTextField NicknameNuevo;
	private JTextField NombreNuevo;
	private JTextField ApellidoNuevo;
	private JTextField MailNuevo;
	private JTextField DescNueva;
	private JTextField BiografiaNueva;
	private JTextField WebNueva;
	
	public ModificarDatosDeUsuario(IControladorUsuario iu) {

		IUsuario = iu;
		
		setTitle("Modificar Datos de Usuario");
		setIconifiable(true);
		setBounds(100, 100, 450, 410);
		getContentPane().setLayout(null);
		
		JLabel LabelNickname = new JLabel("Nickname");
		LabelNickname.setBounds(22, 16, 46, 14);
		getContentPane().add(LabelNickname);
		
		NicknameABuscar = new JTextField();
		NicknameABuscar.setBounds(84, 13, 215, 20);
		getContentPane().add(NicknameABuscar);
		NicknameABuscar.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 44, 414, 2);
		getContentPane().add(separator_1);
		
		JLabel LabelViejo = new JLabel("Datos Viejos");
		LabelViejo.setHorizontalAlignment(SwingConstants.LEFT);
		LabelViejo.setBounds(64, 57, 89, 14);
		getContentPane().add(LabelViejo);
		
		NicknameNuevo = new JTextField();
		NicknameNuevo.setColumns(10);
		NicknameNuevo.setBounds(257, 77, 153, 20);
		getContentPane().add(NicknameNuevo);
		
		JEditorPane NicknameViejo = new JEditorPane();
		NicknameViejo.setEditable(false);
		NicknameViejo.setBounds(22, 77, 153, 20);
		getContentPane().add(NicknameViejo);
		
		JLabel LabelNuevo = new JLabel("Datos Nuevos");
		LabelNuevo.setHorizontalAlignment(SwingConstants.LEFT);
		LabelNuevo.setBounds(300, 57, 89, 14);
		getContentPane().add(LabelNuevo);
		
		JLabel Nickname = new JLabel("Nickname");
		Nickname.setHorizontalAlignment(SwingConstants.CENTER);
		Nickname.setBounds(185, 80, 62, 14);
		getContentPane().add(Nickname);
		
		JEditorPane NombreViejo = new JEditorPane();
		NombreViejo.setEditable(false);
		NombreViejo.setBounds(22, 106, 153, 20);
		getContentPane().add(NombreViejo);
		
		JLabel Nombre = new JLabel("Nombre");
		Nombre.setHorizontalAlignment(SwingConstants.CENTER);
		Nombre.setBounds(185, 109, 62, 14);
		getContentPane().add(Nombre);
		
		NombreNuevo = new JTextField();
		NombreNuevo.setColumns(10);
		NombreNuevo.setBounds(257, 106, 153, 20);
		getContentPane().add(NombreNuevo);
		
		JEditorPane ApellidoViejo = new JEditorPane();
		ApellidoViejo.setEditable(false);
		ApellidoViejo.setBounds(22, 135, 153, 20);
		getContentPane().add(ApellidoViejo);
		
		JLabel Apellido = new JLabel("Apellido");
		Apellido.setHorizontalAlignment(SwingConstants.CENTER);
		Apellido.setBounds(185, 138, 62, 14);
		getContentPane().add(Apellido);
		
		ApellidoNuevo = new JTextField();
		ApellidoNuevo.setColumns(10);
		ApellidoNuevo.setBounds(257, 135, 153, 20);
		getContentPane().add(ApellidoNuevo);
		
		JEditorPane MailViejo = new JEditorPane();
		MailViejo.setEditable(false);
		MailViejo.setBounds(22, 164, 153, 20);
		getContentPane().add(MailViejo);
		
		JLabel Mail = new JLabel("Mail");
		Mail.setHorizontalAlignment(SwingConstants.CENTER);
		Mail.setBounds(185, 167, 62, 14);
		getContentPane().add(Mail);
		
		MailNuevo = new JTextField();
		MailNuevo.setColumns(10);
		MailNuevo.setBounds(257, 164, 153, 20);
		getContentPane().add(MailNuevo);
		
		JLabel FechaNacimiento = new JLabel("Fecha de Nacimiento");
		FechaNacimiento.setHorizontalAlignment(SwingConstants.CENTER);
		FechaNacimiento.setBounds(167, 196, 99, 14);
		getContentPane().add(FechaNacimiento);
		
		JEditorPane FechaNacVieja = new JEditorPane();
		FechaNacVieja.setEditable(false);
		FechaNacVieja.setBounds(22, 193, 135, 20);
		getContentPane().add(FechaNacVieja);
		
		JDateChooser dateChooser_FechaDeAltaFuncion = new JDateChooser();
		dateChooser_FechaDeAltaFuncion.setBounds(275, 193, 135, 20);
		getContentPane().add(dateChooser_FechaDeAltaFuncion);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 232, 414, 2);
		getContentPane().add(separator_1_1);
		
		JEditorPane DescVieja = new JEditorPane();
		DescVieja.setEditable(false);
		DescVieja.setBounds(22, 250, 153, 20);
		getContentPane().add(DescVieja);
		
		JLabel Descripcion = new JLabel("Descripcion");
		Descripcion.setHorizontalAlignment(SwingConstants.CENTER);
		Descripcion.setBounds(185, 254, 62, 14);
		getContentPane().add(Descripcion);
		
		DescNueva = new JTextField();
		DescNueva.setColumns(10);
		DescNueva.setBounds(257, 250, 153, 20);
		getContentPane().add(DescNueva);
		
		JEditorPane BiografiaVieja = new JEditorPane();
		BiografiaVieja.setEditable(false);
		BiografiaVieja.setBounds(22, 280, 153, 20);
		getContentPane().add(BiografiaVieja);
		
		JLabel Biografia = new JLabel("Biografia");
		Biografia.setHorizontalAlignment(SwingConstants.CENTER);
		Biografia.setBounds(185, 284, 62, 14);
		getContentPane().add(Biografia);
		
		BiografiaNueva = new JTextField();
		BiografiaNueva.setColumns(10);
		BiografiaNueva.setBounds(257, 280, 153, 20);
		getContentPane().add(BiografiaNueva);
		
		JEditorPane WebVieja = new JEditorPane();
		WebVieja.setEditable(false);
		WebVieja.setBounds(22, 311, 153, 20);
		getContentPane().add(WebVieja);
		
		JLabel SitioWeb = new JLabel("Sitio Web");
		SitioWeb.setHorizontalAlignment(SwingConstants.CENTER);
		SitioWeb.setBounds(185, 315, 62, 14);
		getContentPane().add(SitioWeb);
		
		WebNueva = new JTextField();
		WebNueva.setColumns(10);
		WebNueva.setBounds(257, 311, 153, 20);
		getContentPane().add(WebNueva);
		
		JButton Confirmar = new JButton("Confirmar");
		Confirmar.setBounds(321, 347, 89, 23);
		getContentPane().add(Confirmar);
		

		JButton Modificar = new JButton("Modificar");
		/*Modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DtUsuario datos = IUsuario.obtenerUsuario(NicknameABuscar.getText());
					
					NicknameViejo.setText(datos.getNickName());
					NombreViejo.setText(datos.getNombre());
					ApellidoViejo.setText(datos.getApellido());
					MailViejo.setText(datos.getCorreoElectronico());
					FechaNacVieja.setText(datos.getFechaDeNacimiento().toString());
					
					if(datos instanceof DtArtista) {
						
						DtArtista a = ((DtArtista) datos);
						
						DescVieja.setText(a.getDescripcion());
						BiografiaVieja.setText(a.getBiografia());
						WebVieja.setText(a.getUrl());
					}
					
				}
				catch(Exception e2) {
				}
			}
		});		*/
		Modificar.setBounds(321, 12, 89, 23);
		getContentPane().add(Modificar);
		
	}
}

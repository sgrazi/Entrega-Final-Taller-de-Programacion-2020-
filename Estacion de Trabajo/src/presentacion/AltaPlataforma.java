package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AltaPlataforma extends JInternalFrame {
	private JTextField tfNombre;
	private JTextField tfURL;
	/**
	 * Create the frame.
	 */
	public AltaPlataforma() {
		setResizable(false);
        setIconifiable(true);
        setMaximizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Plataforma");
		setBounds(10, 10, 423, 235);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 14, 94, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(114, 11, 283, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblURL = new JLabel("URL:");
		lblURL.setBounds(10, 39, 94, 14);
		getContentPane().add(lblURL);
		
		tfURL = new JTextField();
		tfURL.setBounds(114, 36, 283, 20);
		getContentPane().add(tfURL);
		tfURL.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setBounds(10, 72, 94, 14);
		getContentPane().add(lblDescripcion);
		
		JTextArea taDescripcion = new JTextArea();
		taDescripcion.setBounds(114, 67, 283, 70);
		getContentPane().add(taDescripcion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ocultar y limpiar campos
				setVisible(false);
				tfNombre.setText("");
				tfURL.setText("");
				taDescripcion.setText("");	
			}
		});
		btnCancelar.setBounds(307, 171, 90, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// validar y guardar
				
			}
		});
		btnAceptar.setBounds(207, 171, 90, 23);
		getContentPane().add(btnAceptar);

	}
}

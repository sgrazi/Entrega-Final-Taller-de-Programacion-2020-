package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import excepciones.CategoriaConNombreVacioException;
import excepciones.CategoriaRepetidaException;
import logica.IControladorEspectaculo;

public class AltaCategoria extends JInternalFrame {
	private JTextField tfNombre;
	private IControladorEspectaculo controlEsp;
	
	public AltaCategoria(IControladorEspectaculo IE) {
		controlEsp = IE;
		
		setResizable(false);
        setIconifiable(true);
        setMaximizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta de Categoria");
		setBounds(10, 10, 430, 118);
		getContentPane().setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 14, 94, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(114, 11, 283, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ocultar y limpiar campos
				setVisible(false);
				tfNombre.setText("");
			}
		});
		btnCancelar.setBounds(307, 56, 90, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				altaCat(e);	
			}
		});
		btnAceptar.setBounds(202, 56, 90, 23);
		getContentPane().add(btnAceptar);

	}
	
	protected void altaCat(ActionEvent arg0) {
		if(tfNombre.getText()!="") {
			try {
				try {
					this.controlEsp.altaCategoria(tfNombre.getText());
				} catch (CategoriaConNombreVacioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.tfNombre.setText("");
				JOptionPane.showMessageDialog(this, "La categoria se ha creado con exito", "Alta Categoria", JOptionPane.INFORMATION_MESSAGE);
			} catch (CategoriaRepetidaException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta Categoria", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

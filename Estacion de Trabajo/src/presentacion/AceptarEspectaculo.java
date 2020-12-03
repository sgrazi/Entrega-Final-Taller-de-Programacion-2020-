package presentacion;

import java.util.Set;

import javax.swing.JInternalFrame;

import logica.IControladorEspectaculo;
import javax.swing.JLabel;

import excepciones.EspectaculoNoExistenteException;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AceptarEspectaculo extends JInternalFrame {
	
	private IControladorEspectaculo IEspectaculo;
	private JLabel ingresados;
	private JComboBox<String> comboEspectaculos;
	private JButton Aceptar;
	private JButton Rechazar;

	public AceptarEspectaculo(IControladorEspectaculo ie) {
		setBounds(100, 100, 524, 148);
		this.IEspectaculo = ie;
		getContentPane().setLayout(null);
		
		setIconifiable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(false);
        setTitle("Aceptar/Rechazar Espectaculos Ingresados");
		
		ingresados = new JLabel("Espectaculos");
		ingresados.setBounds(20, 22, 122, 13);
		getContentPane().add(ingresados);
		
		comboEspectaculos = new JComboBox<String>();
		comboEspectaculos.setBounds(142, 18, 344, 21);
		getContentPane().add(comboEspectaculos);
		
		Aceptar = new JButton("Aceptar");
		Aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboEspectaculos.getSelectedItem()!="")
					try {
						IEspectaculo.aceptarEspectaculo(comboEspectaculos.getSelectedItem().toString());
					} catch (EspectaculoNoExistenteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				limpiarFormulario();
				llenarComboEspectaculo();
			}
		});
		Aceptar.setBounds(20, 71, 140, 21);
		getContentPane().add(Aceptar);
		
		Rechazar = new JButton("Rechazar");
		Rechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboEspectaculos.getSelectedItem()!="")
					IEspectaculo.rechazarEspectaculo(comboEspectaculos.getSelectedItem().toString());
				limpiarFormulario();
				llenarComboEspectaculo();
			}
		});
		Rechazar.setBounds(204, 71, 140, 21);
		getContentPane().add(Rechazar);
		
		JButton Salir = new JButton("Salir");
		Salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiarFormulario();
			}
		});
		Salir.setBounds(401, 71, 85, 21);
		getContentPane().add(Salir);
	}
	
	protected void llenarComboEspectaculo() {
		this.comboEspectaculos.removeAllItems();
		//OBTENGO ESPECTACULOS INGRESADOS
		Set<String> colEspectaculos = this.IEspectaculo.obtenerEspectaculosIngresados();
		//CARGO COMBOBOX ESPECTACULOS
		String espectaculos[] = new String[colEspectaculos.size()];
		colEspectaculos.toArray(espectaculos);
		comboEspectaculos.addItem("");
		for(String esp : colEspectaculos) {
			comboEspectaculos.addItem(esp);
		}
	}
	
	private void limpiarFormulario() {
		comboEspectaculos.removeAllItems();
	}
}

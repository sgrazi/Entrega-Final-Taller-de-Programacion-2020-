package presentacion;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import datatypes.DtPaquete;
import excepciones.PaqueteNoExisteException;
import logica.IControladorEspectaculo;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ConsultarPaquete extends JInternalFrame {
	private IControladorEspectaculo controlEsp;
	private JTextField txtNombre;
	private JTextField txtDesc;
	private JTextField txtDescuento;
	private JComboBox comboBox;
	private JComboBox<String> comboBox_1;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JButton btnNewButton;
	private JList<String> listCategorias;
	private DefaultListModel<String> categoriasModel;

	/**
	 * Create the frame.
	 */
	public ConsultarPaquete(IControladorEspectaculo ie) {
		controlEsp = ie;

		setResizable(false);
		setIconifiable(true);
		setMaximizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar un Paquete");
		setBounds(100, 100, 519, 365);
		getContentPane().setLayout(null);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		categoriasModel = new DefaultListModel<String>();

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String) comboBox.getSelectedItem();

				if (item != null && !item.isEmpty()) {
					try {
						DtPaquete paquete = controlEsp.obtenerPaquete(item);
						// Mostrar info del Paquete
						txtNombre.setText(paquete.getNombre());
						txtDesc.setText(paquete.getDescripcion());
						txtDescuento.setText(String.valueOf(Math.round(paquete.getDescuento() * 100)) + "%");

						String inicio = dateFormat.format(paquete.getInicio());
						String fin = dateFormat.format(paquete.getFin());
						txtFin.setText(fin);
						txtInicio.setText(inicio);

						// espectaculos:
						String[] espectaculos = paquete.getEspectaculos();
						comboBox_1.removeAllItems();
						comboBox_1.addItem("");
						for (String n : espectaculos)
							comboBox_1.addItem(n);

						// categorias:
						categoriasModel.removeAllElements();
						Set<String> categorias = paquete.getCategorias();
						for (String cat : categorias)
							categoriasModel.addElement(cat);

						listCategorias.setModel(categoriasModel);

					} catch (PaqueteNoExisteException e2) {
						JOptionPane.showMessageDialog(comboBox.getParent(), "Error al cargar el paquete",
								"Consulta de Paquete", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		comboBox.setBounds(102, 11, 297, 20);
		getContentPane().add(comboBox);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 42, 414, 2);
		getContentPane().add(separator);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setToolTipText("");
		txtNombre.setBounds(102, 55, 297, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(20, 58, 65, 14);
		getContentPane().add(lblNombre);

		JLabel lblDesc = new JLabel("Descripción:");
		lblDesc.setBounds(20, 83, 72, 14);
		getContentPane().add(lblDesc);

		txtDesc = new JTextField();
		txtDesc.setToolTipText("");
		txtDesc.setEditable(false);
		txtDesc.setColumns(10);
		txtDesc.setBounds(102, 80, 297, 20);
		getContentPane().add(txtDesc);

		JLabel lblDescuento = new JLabel("Descuento:");
		lblDescuento.setBounds(21, 138, 65, 14);
		getContentPane().add(lblDescuento);

		txtDescuento = new JTextField();
		txtDescuento.setToolTipText("");
		txtDescuento.setEditable(false);
		txtDescuento.setColumns(10);
		txtDescuento.setBounds(103, 135, 65, 20);
		getContentPane().add(txtDescuento);

		JLabel lblEspectaculos = new JLabel("Espectaculos:");
		lblEspectaculos.setBounds(21, 282, 76, 14);
		getContentPane().add(lblEspectaculos);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(31, 302, 317, 20);
		getContentPane().add(comboBox_1);

		btnNewButton = new JButton("Ver Espectaculo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String item = (String) comboBox_1.getSelectedItem();

				if (item != null && !item.isEmpty()) {
					Principal.ConsultarEspectaculo(item);
				}
			}
		});
		btnNewButton.setBounds(358, 301, 123, 23);
		getContentPane().add(btnNewButton);

		JLabel lblPaquete = new JLabel("Paquete:");
		lblPaquete.setBounds(20, 14, 65, 14);
		getContentPane().add(lblPaquete);

		JLabel lblInicio = new JLabel("Inicio:");
		lblInicio.setBounds(20, 109, 65, 14);
		getContentPane().add(lblInicio);

		txtInicio = new JTextField();
		txtInicio.setToolTipText("");
		txtInicio.setEditable(false);
		txtInicio.setColumns(10);
		txtInicio.setBounds(102, 106, 113, 20);
		getContentPane().add(txtInicio);

		txtFin = new JTextField();
		txtFin.setToolTipText("");
		txtFin.setEditable(false);
		txtFin.setColumns(10);
		txtFin.setBounds(282, 106, 117, 20);
		getContentPane().add(txtFin);

		JLabel lblFin = new JLabel("Fin:");
		lblFin.setBounds(234, 109, 38, 14);
		getContentPane().add(lblFin);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 188, 317, 78);
		getContentPane().add(scrollPane);

		listCategorias = new JList<String>();

		listCategorias.setEnabled(false);
		scrollPane.setViewportView(listCategorias);

		JLabel lblCategorias = new JLabel("Categorías:");
		lblCategorias.setBounds(20, 163, 88, 14);
		getContentPane().add(lblCategorias);

	}

	public void cargarPaquetes() {
		this.limpiarDatos();
		try {
			Set<String> pqts = controlEsp.listarPaquetes();
			comboBox.addItem(""); // Para que inicie 'des-seleccionado'
			for (String n : pqts)
				comboBox.addItem(n);

			this.setVisible(true);
		} catch (PaqueteNoExisteException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consulta de Paquete", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false); // por las dudas
		}
	}

	public void limpiarDatos() {
		this.comboBox.removeAllItems();
		this.comboBox_1.removeAllItems();
		this.txtDesc.setText("");
		this.txtNombre.setText("");
		this.txtFin.setText("");
		this.txtInicio.setText("");
		this.txtDescuento.setText("");
		categoriasModel.removeAllElements();
	}

	public void cargoVistaConsulta(String paquete) {
		DtPaquete p;
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			p = this.controlEsp.obtenerPaquete(paquete);
			this.txtNombre.setText(p.getNombre());
			this.txtDesc.setText(p.getDescripcion());
			// this.txtFin.setText(String.valueOf(p.getFin()));
			// this.txtInicio.setText(String.valueOf(p.getInicio()));
			// this.txtDescuento.setText(String.valueOf(p.getDescuento()));
			txtDescuento.setText(String.valueOf(Math.round(p.getDescuento() * 100)) + "%");

			String inicio = dateFormat.format(p.getInicio());
			String fin = dateFormat.format(p.getFin());
			txtFin.setText(fin);
			txtInicio.setText(inicio);

			for (String art : p.getEspectaculos()) {
				this.comboBox_1.addItem(art);// this.cboFunciones.addItem(pac);
			}

			this.btnNewButton.setVisible(false);

		} catch (PaqueteNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

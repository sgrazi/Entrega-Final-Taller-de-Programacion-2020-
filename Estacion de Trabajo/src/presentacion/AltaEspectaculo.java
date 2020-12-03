package presentacion;

import javax.swing.JInternalFrame;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import excepciones.EspectaculoAgregadoYaExisteExcepcion;
import excepciones.PlataformaNoExisteException;
import excepciones.UsuarioNoExisteException;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.TextArea;
import java.awt.event.ActionEvent;

import logica.IControladorEspectaculo;
import logica.IControladorUsuario;

import com.toedter.calendar.JDateChooser;

import datatypes.DtEspectaculo;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class AltaEspectaculo extends JInternalFrame {
	
	private IControladorEspectaculo IEspectaculo;
	private IControladorUsuario IUsuario;

	private JTextField txtNombre;
	private JTextField txtURL;
	private JComboBox<String> cboPlataformas;
	private JComboBox<String> cboArtistas;
	private JSpinner spMin;
	private JSpinner spCosto;
	private JSpinner spMax;
	private JDateChooser txtFecha;
	private TextArea txtDescripcion;
	private JSpinner spMinu;
	private JComboBox<String> cboCategorias;
	private JTextField txtFoto;
	private JComboBox<String> cboElegidas;
	private Set<String> categoriasSeleccionadas;
	
	public AltaEspectaculo(IControladorEspectaculo ie, IControladorUsuario iu) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				cargarPlataformas();
				cargarArtistas();
				cargarCategorias();
			}
		});
		
		this.IEspectaculo = ie;
		this.IUsuario = iu;
		
		setResizable(false);
        setIconifiable(true);
        setMaximizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Alta Espectaculo");
		setBounds(10, 10, 665, 456);
		
		JLabel lblPlataforma = new JLabel("Plataforma:");
		
		categoriasSeleccionadas = new HashSet<String>();
		
		//COMBOBOX PLATAFORMAS
		cboPlataformas = new JComboBox<String>();
		
		//COMOBO ARTISTAS
		cboArtistas = new JComboBox<String>();
		
		//BOTONES
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registroEspectaculo(e);
				} catch (PlataformaNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
			
		
		JButton btnCaneclar = new JButton("Cancelar");
		btnCaneclar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarFormulario();
			}
		});
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		txtNombre = new JTextField();
		
		txtNombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		
		JLabel lblCosto = new JLabel("Costo:");
		
		JLabel lblArtista = new JLabel("Artista:");
		
		spCosto = new JSpinner();
		spCosto.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		JLabel lblCapacidad = new JLabel("Capacidad:");
		
		spMin = new JSpinner();
		spMin.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		spMax = new JSpinner();
		spMax.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		JLabel lblMin = new JLabel("Min");
		
		JLabel lblMax = new JLabel("Max");
		
		JLabel lblDuracion = new JLabel("Duracion:");
		
		JLabel lblURL = new JLabel("URL:");
		
		txtURL = new JTextField();
		
		txtURL.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		
		txtFecha = new JDateChooser();
		
		txtDescripcion = new TextArea("",0,0,txtDescripcion.SCROLLBARS_VERTICAL_ONLY);
		getContentPane().setLayout(null);
		
		spMinu = new JSpinner();
		spMinu.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		JLabel lblNewLabel = new JLabel("Min");
		
		JLabel lblCategorias = new JLabel("Categorias:");
		
		cboCategorias = new JComboBox<String>();
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarCategoria();
			}
		});
		
		cboElegidas = new JComboBox<String>();
		
		JLabel lblNewLabel_1 = new JLabel("Elegidas:");
		
		JLabel lblNewLabel_2 = new JLabel("Imagen:");
		
		txtFoto = new JTextField();
		txtFoto.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblPlataforma)
							.addGap(13)
							.addComponent(cboPlataformas, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addGap(82)
							.addComponent(lblArtista)
							.addGap(18)
							.addComponent(cboArtistas, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNombre)
							.addGap(28)
							.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblURL)
							.addGap(46)
							.addComponent(txtURL, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(lblCosto)
							.addGap(18)
							.addComponent(spCosto, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(lblCapacidad)
									.addGap(10)
									.addComponent(lblMin)
									.addGap(10)
									.addComponent(spMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(16)
									.addComponent(lblMax)
									.addGap(10)
									.addComponent(spMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(lblDescripcion)
									.addGap(9)
									.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
							.addGap(111)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFecha)
									.addGap(18)
									.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDuracion)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(spMinu, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))))
					.addGap(102))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(439, Short.MAX_VALUE)
					.addComponent(btnAceptar)
					.addGap(18)
					.addComponent(btnCaneclar)
					.addGap(46))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(112)
					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(471, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCategorias)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(cboElegidas, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(cboCategorias, Alignment.LEADING, 0, 136, Short.MAX_VALUE))
					.addContainerGap(437, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPlataforma))
						.addComponent(cboPlataformas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblArtista))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(cboArtistas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNombre))
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(lblURL))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(txtURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCosto)
								.addComponent(spCosto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblCapacidad))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblMin))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(spMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblMax))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(spMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFecha))))
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDescripcion)
									.addGap(26))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addComponent(spMinu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(lblDuracion))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtFoto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategorias)
						.addComponent(cboCategorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboElegidas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1))
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCaneclar)
						.addComponent(btnAceptar))
					.addGap(35))
		);
		getContentPane().setLayout(groupLayout);
	}


	protected void agregarCategoria() {
		// TODO Auto-generated method stub
		String cat = (String) this.cboCategorias.getSelectedItem();
		this.cboElegidas.addItem(cat);
		this.cboCategorias.removeItem(cat);
		this.categoriasSeleccionadas.add(cat);
	}


	protected void cargarCategorias() {
		// TODO Auto-generated method stub
		
		this.cboCategorias.removeAllItems();
		Set<String> categorias = this.IEspectaculo.obtenerCategorias();
		
		String[] cate = new String[categorias.size()];
		
		categorias.toArray(cate);
			
		for(String c : categorias) {
				
			cboCategorias.addItem(c);
		}
		
	}


	protected void registroEspectaculo(ActionEvent e) throws PlataformaNoExisteException {
		
		//OBTENGO LOS DATOS
		String plat = (String) this.cboPlataformas.getSelectedItem();
		String art = (String) this.cboArtistas.getSelectedItem();
		String nom = this.txtNombre.getText();
		String desc = this.txtDescripcion.getText();
		String url = this.txtURL.getText();
		int costo = (int) this.spCosto.getValue();
		int capMax = (int) this.spMax.getValue();
		int capMin = (int) this.spMin.getValue();
		int minut = (int) this.spMinu.getValue();
		Date f = this.txtFecha.getDate();
		String imagen = this.txtFoto.getText();
		
		DtEspectaculo dtEsp = new DtEspectaculo(nom, desc, url,costo, capMax, capMin, minut, f, this.categoriasSeleccionadas, imagen);
		dtEsp.setPlataforma(plat);
		dtEsp.setArtista(art);
		dtEsp.setVideoUrl("");
		dtEsp.setCantSorteosPorFuncion(0);
		dtEsp.setSorteoDescripcion("");
				
		if (checkFormulario()) {
			
			try {
				
//				try {
//					
//					this.IEspectaculo.altaEspectaculo(dtEsp);
//					
//				} catch (UsuarioNoExisteException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
				this.IEspectaculo.altaEspectaculo(dtEsp);
				
				JOptionPane.showMessageDialog(this, "El Espectaculo se ha creado con exito", "Alta Espectaculo",
                        JOptionPane.INFORMATION_MESSAGE);
				
				limpiarFormulario();
				setVisible(false);
				
			}catch(EspectaculoAgregadoYaExisteExcepcion | UsuarioNoExisteException | PlataformaNoExisteException exp) {
				
				 JOptionPane.showMessageDialog(this, exp.getMessage(), "Alta Espectaculo", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		
	}
	
	private void limpiarFormulario() {
		
		this.txtDescripcion.setText("");
		this.txtNombre.setText("");
		this.txtURL.setText("");
		this.spCosto.setValue(1);
		this.spMax.setValue(1);
		this.spMin.setValue(0);
		this.spMinu.setValue(1);
		this.txtFecha.setDate(null);
		this.cboPlataformas.removeAllItems();
		this.cboArtistas.removeAllItems();
		this.cboCategorias.removeAllItems();
		this.cboElegidas.removeAllItems();
		setVisible(false);
		
		
	}
	
	private void cargarArtistas() {

		cboArtistas.removeAllItems();
		
		Set<String> colUsuarios = this.IUsuario.getArtistasNick();
		
		//ITEM INICIAL
		cboArtistas.addItem("Seleccionar");

		//CARGO COMOBOBOX ARTISTAS
		String[] artistas = new String[colUsuarios.size()];
		
		colUsuarios.toArray(artistas);
		
		for(String art : colUsuarios) {
			
			cboArtistas.addItem(art);
		}
			

    }
	
	private void cargarPlataformas() {
	
		//OBTENGO PLATAFORMAS
		Set<String> colPlataformas = this.IEspectaculo.listarPlataformasSet();
		
		//ITEM INICIAL
		cboPlataformas.addItem("Seleccionar");
		
		//CARGO COMBOBOX PLATAFORMAS
		String plataformas[] = new String[colPlataformas.size()];
		colPlataformas.toArray(plataformas);
		for(String plat : colPlataformas) {
			cboPlataformas.addItem(plat);
		}
		
	}

	
	private boolean checkFormulario() {
		
		String plat = (String) this.cboPlataformas.getSelectedItem();
		String art = (String) this.cboArtistas.getSelectedItem();
		String nom = this.txtNombre.getText();
		String desc = this.txtDescripcion.getText();
		String url = this.txtURL.getText();
		Date f = this.txtFecha.getDate();
		int min = (int) this.spMin.getValue();
		int max = (int) this.spMax.getValue();
		
		
		if (plat == "Seleccionar") {
			JOptionPane.showMessageDialog(this, "Debe elegir una Plataforma", "Alta Espectaculo",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		else {
			if(nom.isEmpty() || desc.isEmpty() || url.isEmpty()|| (f == null)) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacios", "Alta Espectaculoo",
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
				
			}
			else {
				if(art == "Seleccionar") {
					JOptionPane.showMessageDialog(this, "Debe elegir una Plataforma", "Alta Espectaculo",
		                    JOptionPane.ERROR_MESSAGE);
		            return false;
					
				}
				else {
					if(min > max) {
						JOptionPane.showMessageDialog(this, "La cantidad minima de espectadores no puede ser mayor que la maxima", "Alta Espectaculo",
			                    JOptionPane.ERROR_MESSAGE);
			            return false;
					}
					else {
						if(this.categoriasSeleccionadas.isEmpty()) {
							JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una categoria", "Alta Espectaculo",
				                    JOptionPane.ERROR_MESSAGE);
				            return false;
						}
					}
					
				}
			}
		}
		
		return true;
		
	}
}

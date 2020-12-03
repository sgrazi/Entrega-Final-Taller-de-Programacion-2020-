package presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import logica.Configuracion;
import publicar.WebServices;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import presentacion.Principal;

public class frmConfiguracion extends JInternalFrame {
	
	private JTextField txtProtocolo;
	private JTextField txtURL;
	private JTextField txtPuerto;
	private Configuracion configuracion;
	private JTextField txtPath;
	private JTextField txtEndpoint;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmConfiguracion frame = new frmConfiguracion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmConfiguracion() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				txtProtocolo.setFocusable(true);
			}
		});
		setTitle("Configuracion");
		setBounds(100, 100, 404, 209);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Protocolo");
		lblNewLabel.setBounds(10, 42, 65, 14);
		getContentPane().add(lblNewLabel);
		
		txtProtocolo = new JTextField();
		txtProtocolo.setBounds(75, 36, 86, 20);
		getContentPane().add(txtProtocolo);
		txtProtocolo.setColumns(10);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(10, 70, 46, 14);
		getContentPane().add(lblUrl);
		
		txtURL = new JTextField();
		txtURL.setColumns(10);
		txtURL.setBounds(75, 64, 290, 20);
		getContentPane().add(txtURL);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(171, 42, 46, 14);
		getContentPane().add(lblPuerto);
		
		txtPuerto = new JTextField();
		txtPuerto.setColumns(10);
		txtPuerto.setBounds(215, 36, 86, 20);
		getContentPane().add(txtPuerto);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarConfig();
			}
		});
		btnAceptar.setBounds(182, 143, 87, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(277, 143, 87, 25);
		getContentPane().add(btnCancelar);
		
		JLabel lblPathFile = new JLabel("Path File");
		lblPathFile.setBounds(10, 17, 65, 14);
		getContentPane().add(lblPathFile);
		
		txtPath = new JTextField();
		txtPath.setColumns(10);
		txtPath.setBounds(75, 11, 290, 20);
		getContentPane().add(txtPath);
		
		txtEndpoint = new JTextField();
		txtEndpoint.setColumns(10);
		txtEndpoint.setBounds(75, 91, 290, 20);
		getContentPane().add(txtEndpoint);
		
		JLabel lblEndPoint = new JLabel("End point");
		lblEndPoint.setBounds(10, 97, 65, 14);
		getContentPane().add(lblEndPoint);

	}
	
	public void cargoForm(Configuracion config) {
		this.configuracion = config;
		
		this.txtPath.setText(config.getPath());
		this.txtProtocolo.setText(config.getProtocolo());
		this.txtPuerto.setText(config.getPort());
		this.txtURL.setText(config.getUrl());
		this.txtEndpoint.setText(config.getEndpoint());
	}
	
	private void salvarConfig() {
		
		try {
			this.configuracion.setPath(txtPath.getText());
			this.configuracion.save(this.txtProtocolo.getText(), this.txtURL.getText(), this.txtPuerto.getText(), txtEndpoint.getText());
			
			JOptionPane.showMessageDialog(this, "Debe cerrar y abrir nuevamente la aplicacion para que los cambios tengan efecto", "Configuracion",
                    JOptionPane.WARNING_MESSAGE);
			
			setVisible(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, e.getMessage(), "Configuracion", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}

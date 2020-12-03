package presentacion;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import logica.IControladorUsuario;

public class consultarUsuario extends AltaUsuario{

	private IControladorUsuario IUsuario;
	
	public consultarUsuario(IControladorUsuario iu) {
		super(iu);
		
		IUsuario = iu;

		setTitle("Consulta usuario");
		vistaModificar();
		
		rdEspectador.setEnabled(false);
		rdArtista.setEnabled(false);
		btnAceptar.setVisible(false);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				Principal.cerrarTodasLasVentanasDeConsultarUsuario();
			}
		});
		
	}

}

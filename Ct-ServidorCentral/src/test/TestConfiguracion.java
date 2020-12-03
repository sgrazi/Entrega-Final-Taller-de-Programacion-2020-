package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import logica.Configuracion;

class TestConfiguracion {

	@Test
	void testConfiguracion() {
		Configuracion config = new Configuracion();
		// config.load(); //se hace solo con el creador
		assertEquals(config.getEndpoint(),"webservices");
		
		config.setPort("9129");
		assertEquals(config.getPort(), "9129");
		
		config.setProtocolo("http");
		assertEquals(config.getProtocolo(),"http");
		
		config.setUrl("localhost");
		assertEquals(config.getUrl(), "localhost");
		
		try {
			config.save("http", "localhost", "9128", "webservices");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(config.getEndpoint(),"webservices");
		assertEquals(config.getPort(), "9128");
		assertEquals(config.getProtocolo(),"http");
		assertEquals(config.getUrl(), "localhost");
		
		
		
	}

}

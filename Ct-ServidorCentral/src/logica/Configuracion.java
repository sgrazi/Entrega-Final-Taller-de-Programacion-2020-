package logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configuracion {
	
	private Properties propiedades;
	private String path;
	private static String name = ".coronaTickets";
	
	public Configuracion() {
		// TODO Auto-generated constructor stub
		
		this.propiedades = new Properties();
		this.path = System.getProperty("user.home") + "/";
		
		this.load();
		
	}
	
	public void load() {
		try (InputStream inStream = new FileInputStream(this.path + name)) {
			propiedades.load(inStream);
			
			inStream.close();
		}catch (IOException e) {
			//SETEO VALORES POR DEFECTO PORQUE NO PUDE CARGAR EL ARCHIVO
			propiedades.clear();
			
			propiedades.setProperty("protocolo", "http");
			propiedades.setProperty("url", "localhost");
			propiedades.setProperty("port", "9129");
			propiedades.setProperty("endpoint", "webservices");
		}
	}
	
	public String getWs() {
		return this.getProtocolo() + "://"+ this.getUrl() + ":" + this.getPort()  + "/"+ this.getEndpoint();
	}
	
	public String getWsdl() {
		return this.getWs() +"?wsdl";
	}
	
	public void setEndpoint(String value) {
		this.propiedades.setProperty("endpoint", value);
	}
	
	public String getEndpoint() {
		return this.propiedades.getProperty("endpoint");
	}
	
	public String getUrl() {
		return this.propiedades.getProperty("url");
	}
	
	public String getPort() {
		return this.propiedades.getProperty("port");
	}
	
	public String getProtocolo() {
		return this.propiedades.getProperty("protocolo");
	}
	
	public void setUrl(String value) {
		this.propiedades.setProperty("url", value);
	}
	
	public void setPort(String value) {
		this.propiedades.setProperty("port", value);
	}
	
	public void setProtocolo(String value) {
		this.propiedades.setProperty("protocolo", value);
	}
	
	public void setPath(String value) {
		this.path = value;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public void save(String protocolo, String url, String port, String endPoint) throws IOException {
		String antProtocolo = this.getProtocolo();
		String antUrl = this.getUrl();
		String antPort = this.getPort();
		String antEndpoint = this.getEndpoint();
		
		this.setProtocolo(protocolo);
		this.setPort(port);
		this.setUrl(url);
		this.setEndpoint(endPoint);
		
		try (OutputStream output = new FileOutputStream(this.path + name)) {

            //save properties to project root folder
            this.propiedades.store(output, null);
			
        } catch (IOException io) {
        	this.setProtocolo(antProtocolo);
        	this.setPort(antPort);
    		this.setUrl(antUrl);
    		this.setEndpoint(antEndpoint);
    		
            throw new IOException("No se pudo guardar la configuracion. Error " + io.getMessage());
        }	
	}
	
}

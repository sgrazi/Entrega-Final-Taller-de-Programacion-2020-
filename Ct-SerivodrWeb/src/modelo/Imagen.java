package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.text.SimpleDateFormat;

public class Imagen {
	
	private String defaultPath;
	private List<String> extensionesSoportadas;
	
	private String name;
	private String extension;
	private InputStream content;
	
	public Imagen() {
		// TODO Auto-generated constructor stub
		
		//SETEO LAS EXTENSIONES DE IMAGENES SOPORTADAS
		this.defaultPath = "img/";
		this.extensionesSoportadas = Arrays.asList("jpg", "JPG", "png", "PNG", "bmp", "BMP");
		this.name = "";
		this.extension = "";
		this.content = null;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getDefaultPath() {
		return defaultPath;
	}
	
	private void cargoYControloExtension(String name) throws IOException {
		//obtengo extension
	    this.extension = name.substring(name.lastIndexOf(".") + 1);
	    
	    //controlo extension
	    if (!this.extensionesSoportadas.contains(this.extension)) {
	    	throw new IOException("El archivo no es un tipo de imagen soportada.");
	    }
	}
	
	public void cargarDesdeRequestInput(HttpServletRequest request, String nameInput) throws IOException, ServletException {
		//AHORA CARGO LOS DATOS DEL ARCHIVO
		Part filePart = request.getPart(nameInput); //Retrieves <input type="file" name="nameInput">
		
		//obtengo nombre
	    this.name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    //obtengo imagen
	    this.content = filePart.getInputStream();
	    
	    if (!this.name.isEmpty()) {
	    	this.cargoYControloExtension(this.name);
	    }
	    	    
	    //PROVISORIO. HAY QUE BORRARLO CUANDO PASEMOS AL SERVIDOR CENTRAL 
	    //SETEO DIRECTORIO POR DEFECTO
  		//OBTENGO DIRECTORIO RAIZ
  		ServletContext context = request.getServletContext();
  		this.defaultPath = context.getRealPath("/");
  		
  		
  		//AHORA LO DEJO EN EL DIRECTORIO DE IMAGENES POR DEFECTO
  		this.defaultPath = this.defaultPath + "\\img\\";
	}
	
	public void cargarDesdeArchivo(String fileName) throws IOException {
		//AHORA CARGO LOS DATOS DEL ARCHIVO
		this.name = this.defaultPath + fileName;
		
		File f = new File(this.name);
        this.content = new FileInputStream(f);
        
        this.cargoYControloExtension(this.name);
	}
	
	public void saveToFile() throws IOException {
		if (!this.name.isEmpty()) {
			File uploads = new File(this.defaultPath);
			File file = new File(uploads, this.name);
		    
		    try (InputStream input = this.content) {
		        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		    }
		}
	    
	}
	
	public byte[] toByte() throws IOException {
	    byte[] byteArray = null;
	    
	    try {
	            byteArray = new byte[this.content.available()];
	            this.content.read(byteArray);
	    } catch (IOException e) {
	            throw new IOException("No se pudo generar la imagen como byte[] " + e.getMessage());
	    }
	    
	    return byteArray;

	}
	
	public String getLink() {
		if (this.name.isEmpty()) {
			return "";
		} else {
			return "./img/" + this.getName();
		}
	}
	
}

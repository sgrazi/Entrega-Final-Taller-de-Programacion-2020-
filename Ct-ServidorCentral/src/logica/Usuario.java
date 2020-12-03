package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correoElectronico;
	private Date fechaDeNacimiento;
	private String contrasena;
	private String imagen;
	
	//LAS CLAVES SON LOS NICKNAMES
	private Map<String, Usuario> seguidos; 
	private Map<String, Usuario> seguidores;
	
	public Usuario(String nick, String nom, String ape, String email, Date fecNac, String pass, String img) {
		seguidos = new HashMap<String, Usuario>();
		seguidores = new HashMap<String, Usuario>();
		
		this.setNickname(nick);
		this.setNombre(nom);
		this.setApellido(ape);
		this.setCorreoElectronico(email);
		this.setfechaDeNacimiento(fecNac);
		this.setContrasena(pass);
		this.setImagen(img);
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getNickName() {
        return this.nickname;
    }

    public String getCorreoElectronico() {
        return this.correoElectronico;
    }
    
    public Date getFechaDeNacimiento() {
        return this.fechaDeNacimiento;
    }
    
    public void setNickname(String value) {
        this.nickname = value;
    }

    public void setNombre(String value) {
        this.nombre = value;
    }
    
    public void setApellido(String value) {
        this.apellido = value;
    }
    
    public void setCorreoElectronico(String value) {
        this.correoElectronico = value;
    }
    
    public void setfechaDeNacimiento(Date value) {
        this.fechaDeNacimiento = value;
    }
    
    public void setSeguido(Usuario usu) {
    	seguidos.put(usu.getNickName(), usu);
    }
    
    public void setSeguidor(Usuario usu) {
    	seguidores.put(usu.getNickName(), usu);
    }
    
    public void borrarSeguido(String nickname) {
    	if (seguidos.containsKey(nickname)) {
    		seguidos.remove(nickname);
    	}
    }
    
    public void borrarSeguidor(String nickname) {
    	if (seguidores.containsKey(nickname)) {
    		seguidores.remove(nickname);
    	}
    }
    
    public  void clear() {
        setNickname("");
        setNombre("");
        setApellido("");
        setCorreoElectronico("");
        setfechaDeNacimiento(null);
        setImagen("");
        setContrasena("");
        seguidos.clear();
        seguidores.clear();
    }
    
    public String getContrasena() {
        return this.contrasena;
    }

    public String getImagen() {
        return this.imagen;
    }
    
    public Map<String, Usuario> getSeguidos() {
        return this.seguidos;
    }
    
    public Map<String, Usuario> getSeguidores() {
        return this.seguidores;
    }
    
    public void setImagen(String value) {
        this.imagen = value;
    }
    
    public void setContrasena(String value) {
        this.contrasena = value;
    }
    
    
}

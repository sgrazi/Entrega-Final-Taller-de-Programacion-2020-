package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import datatypes.DtUsuario;
import excepciones.UsuarioNoExisteException;

/**
 * Los usuarios se identifican por el correo electronico y el nickname que son
 * unicos. En nuestro caso usaremos el email para identificar a un usuario dado
 * dentro del mapa usuarioPorEmail y tendremos un mapa auxiliar que tendra el
 * par nickname-Email para poder obtener el email de un determinado nickname.
 * 
 * @author rdelavega
 *
 */

public class ManejadorUsuario {
	private static ManejadorUsuario instancia;

	private Map<String, Usuario> usuariosPorEmail;
	private Map<String, String> nicknamesEmail;

	public ManejadorUsuario() {
		usuariosPorEmail = new HashMap<String, Usuario>();
		nicknamesEmail = new HashMap<String, String>();
	}

	public static ManejadorUsuario getInstance() {
		if (instancia == null) {
			instancia = new ManejadorUsuario();
		}

		return instancia;
	}

	public void agregarUsuario(Usuario usu) {
		String email = usu.getCorreoElectronico();
		String nick = usu.getNickName();

		usuariosPorEmail.put(email, usu);
		nicknamesEmail.put(nick, email);
	}

	public Usuario obtenerUsuario(String email) {
		return (Usuario) usuariosPorEmail.get(email);
	}

	public Usuario obtenerUsuarioPorNickName(String nick) {
		String email = this.obtenerEmailPorNickName(nick);

		return this.obtenerUsuario(email);
	}

	public String obtenerEmailPorNickName(String nick) {
		return nicknamesEmail.get(nick);
	}

	public boolean existe(String email) {
		Usuario usu = this.obtenerUsuario(email);

		return usu != null;
	}

	public boolean existePorNickname(String nick) {
		Usuario usu = this.obtenerUsuarioPorNickName(nick);

		return usu != null;
	}

	public Usuario[] obtenerUsuarios() {
		if (this.usuariosPorEmail.isEmpty())
			// return null;
			return new Usuario[0];
		else {
			Collection<Usuario> usrs = this.usuariosPorEmail.values();

			Object[] obj = usrs.toArray();
			Usuario[] usuarios = new Usuario[obj.length];

			for (int i = 0; i < obj.length; i++) {
				usuarios[i] = (Usuario) obj[i];
			}

			return usuarios;
		}

	}

	public Set<String> getEspectadores() {
		Set<String> espectadores = new HashSet<String>();
		for (Map.Entry<String, Usuario> pair : this.usuariosPorEmail.entrySet()) {
			if (pair.getValue() instanceof Espectador) {
				espectadores.add(pair.getKey());
			}
		}
		return espectadores;
	}

	public Espectador getEspectadorPorNick(String nickEsp) {
		Espectador esp = (Espectador) this.obtenerUsuarioPorNickName(nickEsp);

		return esp;
	}

	public Espectador getEspectador(String nombreEsp) {
		String email = (String) this.nicknamesEmail.get(nombreEsp);
		Espectador esp = (Espectador) this.usuariosPorEmail.get(email);
		return esp;
	}

	public Artista getArtista(String nickArt) {
		String email = (String) this.nicknamesEmail.get(nickArt);
		return (Artista) this.usuariosPorEmail.get(email);
	}

	public Set<String> getArtistas() {
		Set<String> artistas = new HashSet<String>();

		for (Map.Entry<String, Usuario> pair : usuariosPorEmail.entrySet()) {
			if (pair.getValue() instanceof Artista) {
				artistas.add(pair.getKey());
			}
		}

		return artistas;
	}

	public Set<String> getArtistasNick() {
		Set<String> artistas = new HashSet<String>();

		for (Map.Entry<String, Usuario> pair : usuariosPorEmail.entrySet()) {
			if (pair.getValue() instanceof Artista) {
				artistas.add(pair.getValue().getNickName());
			}
		}

		return artistas;
	}

	public void agregarEspectaculoArtista(String art, Espectaculo esp) throws UsuarioNoExisteException {
		String email = (String) nicknamesEmail.get(art);
		Artista artista = (Artista) usuariosPorEmail.get(email);
		
		if (artista == null) {
			throw new UsuarioNoExisteException("El artista " + art + " no existe");
		}
		
		artista.addEspectaculo(esp);
	}

	public Set<String> getEspectadoresPorNick() {
		Set<String> espectadores = new HashSet<String>();
		for (Map.Entry<String, String> pair : this.nicknamesEmail.entrySet()) {
			if (this.obtenerUsuarioPorNickName(pair.getKey()) instanceof Espectador) {
				espectadores.add(pair.getKey());
			}
		}
		return espectadores;
	}

	public void eliminarUsuarios() {
		usuariosPorEmail.clear();
		nicknamesEmail.clear();
	}
	
	public DtUsuario obtenerDtUsuarioPorNickName(String nickname) {
		String email = this.obtenerEmailPorNickName(nickname);
		try{
			Espectador esp = (Espectador) obtenerUsuario(email);
			return esp.getDt();
		}catch(ClassCastException e ) {
			Artista art = (Artista) obtenerUsuario(email);
			return art.getDt();
		}
	}

}

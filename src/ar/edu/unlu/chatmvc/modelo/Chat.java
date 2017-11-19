package ar.edu.unlu.chatmvc.modelo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Chat extends ObservableRemoto implements IChat {
	private HashMap<Integer, Usuario> usuarios;
	private ArrayList<Mensaje> mensajes;
	
	public Chat() {
		this.usuarios = new HashMap<>();
		this.mensajes = new ArrayList<>();
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IChat#conectarUsuario(java.lang.String)
	 */
	@Override
	public Usuario conectarUsuario(String nombre) throws RemoteException {
		Usuario u = new Usuario(nombre);
		this.usuarios.put(u.getId(), u);
		this.notificarObservadores(Eventos.CAMBIO_LISTA_USUARIOS);
		this.enviarMensajeDelSistema("Usuario "+nombre+" conectado.");
		return u;
	}

	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IChat#desconectarUsuario(int)
	 */
	@Override
	public void desconectarUsuario(int usuarioId) throws RemoteException {
		Usuario usuario = this.usuarios.get(usuarioId);
		this.usuarios.remove(usuarioId);
		this.notificarObservadores(Eventos.CAMBIO_LISTA_USUARIOS);
		this.enviarMensajeDelSistema("El usuario "+usuario.getNombre()+" se desconectó.");
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IChat#getMensajes()
	 */
	@Override
	public Mensaje[] getMensajes() throws RemoteException {
		Mensaje[] mensajes = new Mensaje[this.mensajes.size()];
		return this.mensajes.toArray(mensajes);
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IChat#getUsuarios()
	 */
	@Override
	public Usuario[] getUsuarios() throws RemoteException {
		Usuario[] usuarios = new Usuario[this.usuarios.size()];
		return this.usuarios.values().toArray(usuarios);
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IChat#enviarMensaje(java.lang.String, ar.edu.unlu.chatmvc.modelo.IUsuario)
	 */
	@Override
	public void enviarMensaje(String mensaje, IUsuario usuario) throws RemoteException {
		this.mensajes.add(new Mensaje(mensaje, usuario));
		this.notificarObservadores(Eventos.NUEVO_MENSAJE);
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IChat#enviarMensajeDelSistema(java.lang.String)
	 */
	@Override
	public void enviarMensajeDelSistema(String mensaje) throws RemoteException {
		this.mensajes.add(new Mensaje(mensaje));
		this.notificarObservadores(Eventos.NUEVO_MENSAJE);
	}
	
	public void cerrar(IObservadorRemoto controlador, int usuarioId) throws RemoteException {
		this.removerObservador(controlador);
		this.desconectarUsuario(usuarioId);
	}
}

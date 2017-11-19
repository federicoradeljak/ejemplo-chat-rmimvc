package ar.edu.unlu.chatmvc.controlador;

import java.rmi.RemoteException;
import ar.edu.unlu.chatmvc.modelo.Eventos;
import ar.edu.unlu.chatmvc.modelo.IChat;
import ar.edu.unlu.chatmvc.modelo.IMensaje;
import ar.edu.unlu.chatmvc.modelo.IUsuario;
import ar.edu.unlu.chatmvc.vista.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class Controlador implements IControladorRemoto {
	
	private IChat modelo;
	private IVista vista;
	private IUsuario usuario;

	public <T extends IObservableRemoto> Controlador(T modelo) {
		try {
			this.setModeloRemoto(modelo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Controlador() {
		
	}
	
	public void setVista(IVista vista) {
		this.vista = vista;
	}
	
	public void enviarMensaje(String mensaje) {
		try {
			this.modelo.enviarMensaje(mensaje, this.usuario);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void enviarMensajeDelSistema(String mensaje) {
		try {
			this.modelo.enviarMensajeDelSistema(mensaje);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IMensaje[] getMensajes() {
		try {
			return this.modelo.getMensajes();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void conectarUsuario(String nombre) {
		try {
			this.usuario = (IUsuario) this.modelo.conectarUsuario(nombre);
			this.vista.mostrarListaUsuarios((IUsuario[]) this.modelo.getUsuarios()); 
			this.vista.mostrarChat((IMensaje[]) this.modelo.getMensajes());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void desconectarUsuario(int usuarioId) {
		try {
			this.modelo.desconectarUsuario(usuarioId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cerrarApp() {
		try {
			this.modelo.cerrar(this, this.usuario.getId());
			System.exit(0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actualizar(IObservableRemoto observable, Object arg0) throws RemoteException {
		if (arg0 instanceof Eventos) {
			switch ((Eventos) arg0) {
			case CAMBIO_LISTA_USUARIOS:
				this.vista.mostrarListaUsuarios((IUsuario[]) this.modelo.getUsuarios());
				break;
			case NUEVO_MENSAJE:
				this.vista.mostrarChat((IMensaje[]) this.modelo.getMensajes());
				break;
			default:
				break;
			
			}
		}
	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T arg0) throws RemoteException {
		this.modelo = (IChat) arg0;
	}
}

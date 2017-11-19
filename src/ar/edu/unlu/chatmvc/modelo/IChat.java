package ar.edu.unlu.chatmvc.modelo;

import java.rmi.Remote;
import java.rmi.RemoteException;

import ar.edu.unlu.mvcrmi.observer.IObservableRemoto;

public interface IChat extends IObservableRemoto {

	Usuario conectarUsuario(String nombre) throws RemoteException;

	void desconectarUsuario(int usuarioId) throws RemoteException;

	Mensaje[] getMensajes() throws RemoteException;

	Usuario[] getUsuarios() throws RemoteException;

	void enviarMensaje(String mensaje, IUsuario usuario) throws RemoteException;

	void enviarMensajeDelSistema(String mensaje) throws RemoteException;
	
	void cerrar(int usuarioId) throws RemoteException;

}
package ar.edu.unlu.chatmvc.cliente;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ar.edu.unlu.chatmvc.controlador.Controlador;
import ar.edu.unlu.chatmvc.vista.IVista;
import ar.edu.unlu.chatmvc.vista.grafica.VistaGrafica;
import ar.edu.unlu.mvcrmi.cliente.Cliente;

public class AppCliente {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		IVista vista = new VistaGrafica(controlador);
		Cliente c = new Cliente("127.0.0.1", 8888, "127.0.0.1", 9999);
		try {
			c.iniciar(controlador);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

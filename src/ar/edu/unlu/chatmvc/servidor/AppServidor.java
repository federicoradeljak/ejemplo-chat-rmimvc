package ar.edu.unlu.chatmvc.servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import ar.edu.unlu.chatmvc.modelo.Chat;
import ar.edu.unlu.mvcrmi.servidor.Servidor;

public class AppServidor {

	public static void main(String[] args) {
		Chat modelo = new Chat();
		Servidor servidor = new Servidor("192.168.0.16", 8888);
		try {
			servidor.iniciar(modelo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

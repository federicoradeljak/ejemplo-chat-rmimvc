package ar.edu.unlu.chatmvc.modelo;

import java.io.Serializable;

public class Usuario implements IUsuario, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7848877797693205738L;
	private static int ID = 0;
	private String nombre;
	private int id;
	
	public Usuario(String nombre) {
		this.nombre = nombre;
		this.id = Usuario.ID++;
	}
	
	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IUsuario#getNombre()
	 */
	@Override
	public String getNombre() {
		return this.nombre;
	}

	/* (non-Javadoc)
	 * @see ar.edu.unlu.chatmvc.modelo.IUsuario#getId()
	 */
	@Override
	public int getId() {
		return id;
	}
}

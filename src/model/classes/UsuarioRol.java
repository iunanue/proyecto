package model.classes;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "usuario_rol")
public class UsuarioRol implements Serializable {
	
	@Id
	private String username;
	
	@Id
	private String rol;
	
	public UsuarioRol(String username,String rol){
		this.username = username;
		this.rol = rol;
	}
	
	public UsuarioRol(){

	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}

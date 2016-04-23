package model.classes;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	private String username;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "password")
	private String password;

	public Usuario() {
	}

	public Usuario(String username, String mail, String password) {
		this.username = username;
		this.mail = mail;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

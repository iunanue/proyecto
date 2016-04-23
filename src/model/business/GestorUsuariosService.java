package model.business;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Usuario;
import model.data.Connect;

public class GestorUsuariosService {

	private static GestorUsuariosService gestorUsuariosService = null;
	
	public GestorUsuariosService(){
		
	}
	
	public static GestorUsuariosService getInstance() {
		if(gestorUsuariosService == null){
			gestorUsuariosService = new GestorUsuariosService();
		}
		return gestorUsuariosService;
	}
	
	public boolean existsUsuario(String username){
		return Connect.getIDao().existsUsuario(username);
	}
	
	public void addUsuario(String username,String mail,String password){
		Usuario usuario = new Usuario(username,mail,password);
		Connect.getIDao().addUsuario(usuario);
	}
	
	public void updateUsuario(String username,String mail,String password){
		Usuario usuario = new Usuario(username,mail,password);
		Connect.getIDao().updateUsuario(usuario);
	}
	
	public void deleteUsuario(String username,String mail,String password){
		Usuario usuario = new Usuario(username,mail,password);
		Connect.getIDao().deleteUsuario(usuario);
	}
	
	public List<Usuario> getUsers(){
		return Connect.getIDao().getUsers();
	}
		
	public Usuario getUsuario(String username){
		return Connect.getIDao().getUsuario(username);
	}
	
	public void forgotPassword(String username,String mail){
		String newPassword = UUID.randomUUID().toString();
		Usuario updatedUser = new Usuario(username,mail,newPassword);
		updateUsuario(username,mail,newPassword);
		sendEmail(updatedUser);		
	}
	
	private void sendEmail(Usuario usuario)
	{
		// Recipient's email ID needs to be mentioned.
	      String to = usuario.getMail();//change accordingly

	      // Sender's email ID needs to be mentioned
	      String from = "i.unanue.telleria@gmail.com";//change accordingly
	      final String username = "i.unanue.telleria";//change accordingly
	      final String password = "gqmznkbhtuklkpyr";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Nueva contraseña");

	         // Now set the actual message
	         message.setText("Hola " + usuario.getUsername() + ", la nueva contraseña generada es: " + usuario.getPassword());

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}

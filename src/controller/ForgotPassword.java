package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import classes.Usuario;
import model.Connect;
import model.Dao;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connect c = new Connect();
	Usuario usuario;
	String username;
	String mail;
	String mensaje = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		username = request.getParameter("username");
		mail = request.getParameter("mail");
		
		if (checkForm(request, response)) 
		{
			usuario = c.getIDao().getUsuario(username);
			if(usuario != null)
			{
				if(usuario.getMail().equals(mail))
				{
					System.out.println("CAMBIO CORRECTO");
					String newPassword = UUID.randomUUID().toString();
					Usuario updatedUser = new Usuario(username,mail,newPassword);
//					c.getDao().updateUsuario(updatedUser);
					sendEmail(newPassword);
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				}
				else
				{
					mensaje = "El mail escrito no corresponde con el del usuario";
					request.getSession().setAttribute("mensaje", mensaje);
					request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
				}
			}
			else
			{
				mensaje = "El usuario no existe en la Base de Datos";
				request.getSession().setAttribute("mensaje", mensaje);
				request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
			}
		} 
		else {
			request.getSession().setAttribute("mensaje", mensaje);
			request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
		}
	}

	private boolean checkForm(HttpServletRequest request, HttpServletResponse response) {
		if ((request.getParameter("username").equals("")) || (request.getParameter("mail").equals(""))) {
			mensaje = "Rellene todos los campos por favor.";
			return false;
		} else {
			return true;
		}
	}
	private void sendEmail(String newPassword)
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
	         message.setText("Hola " + usuario.getUsername() + ", la nueva contraseña generada es: " + newPassword);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}

// if (checkForm(request, response)) {
//
// dao.getRegister().addUser(request.getParameter("username"),
// request.getParameter("mail"),
// request.getParameter("password"));
// mensaje = null;
// request.getSession().setAttribute("mensaje", mensaje);
// request.getRequestDispatcher("login.jsp").forward(request, response);
// } else {
// request.getSession().setAttribute("mensaje", mensaje);
// request.getRequestDispatcher("register.jsp").forward(request, response);
// }

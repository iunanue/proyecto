package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Usuario;

public class Dao {
	private Connect connect;

	public Dao(Connect connect) {
		this.connect = connect;
	}

	public boolean addUser(String username, String mail, String password) {

		boolean added = false;
		try {
			Connection c = connect.getConnection();
			if (c != null) {

				String sql = "INSERT INTO usuario (username, mail, password) values (?, ?, ?)";

				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);

				statement.setString(1, username);
				statement.setString(2, mail);
				statement.setString(3, password);

				statement.executeUpdate();
				added = true;

				// statement.close();
				// statement = null;

				// agregar el rol

				sql = "INSERT INTO usuario_rol (username, rol) values (?, ?)";
				System.out.println(sql);
				statement = (PreparedStatement) c.prepareStatement(sql);

				statement.setString(1, username);
				statement.setString(2, "usuario");

				statement.executeUpdate();
				//
				// statement.close();
				// statement = null;

			}
			// c.close();
		} catch (SQLException e) {
			added = false;
			e.printStackTrace();
		}
		return added;

	}

	public Usuario getUser(String username) {

		Usuario usuario = null;

		Connection c;
		try {
			c = connect.getConnection();
			if (c != null) {

				String sql = "SELECT * FROM usuario WHERE username = ?";

				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
				statement.setString(1, username);

				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					usuario = new Usuario(rs.getString("username"), rs.getString("mail"), rs.getString("password"));
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
	public void updateUsuario(Usuario usuario) {

		Connection c;
		try {
			c = connect.getConnection();
			if (c != null) {

				String sql = "UPDATE usuario SET mail = '" + usuario.getMail()+ "', password = '" + usuario.getPassword()+ "' WHERE username = '" + usuario.getUsername()+"'";
				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
				System.out.println(sql);
				statement.executeUpdate(sql);

			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteUsuario(String username)
	{
		Connection c;
		try {
			c = connect.getConnection();
			if (c != null) {

				String sql = "DELETE FROM usuario_rol WHERE username = '"+username+"'";
				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
				System.out.println(sql);
				statement.executeUpdate(sql);
				
				sql = "DELETE FROM usuario WHERE username = '"+username+"'";
				statement = (PreparedStatement) c.prepareStatement(sql);
				System.out.println(sql);
				statement.executeUpdate(sql);
						

			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
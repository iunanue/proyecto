package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.ClaseGasto;
import classes.ClaseIngreso;
import classes.Cuenta;
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
	public ArrayList<Usuario> getUsers() {

		ArrayList<Usuario> listaUsers = new ArrayList<Usuario>();
		
		Usuario usuario = null;

		Connection c;
		try {
			c = connect.getConnection();
			if (c != null) {

				String sql = "SELECT * FROM usuario";

				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);

				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					usuario = new Usuario(rs.getString("username"), rs.getString("mail"), rs.getString("password"));
					listaUsers.add(usuario);
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsers;
	}
	public ArrayList<ClaseIngreso> getClaseIngreso() {

		ArrayList<ClaseIngreso> listaClaseIngreso = new ArrayList<ClaseIngreso>();
		
		ClaseIngreso claseingreso = null;

		Connection c;
		try {
			c = connect.getConnection();
			if (c != null) {

				String sql = "SELECT * FROM claseingreso";

				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);

				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					claseingreso = new ClaseIngreso(rs.getInt("id_claseIngreso"), rs.getString("descripcion"));
					listaClaseIngreso.add(claseingreso);
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaClaseIngreso;
	}
	public ArrayList<ClaseGasto> getClaseGasto() {

		ArrayList<ClaseGasto> listaClaseGasto = new ArrayList<ClaseGasto>();
		
		ClaseGasto clasegasto = null;

		Connection c;
		try {
			c = connect.getConnection();
			if (c != null) {

				String sql = "SELECT * FROM clasegasto";

				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);

				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					clasegasto = new ClaseGasto(rs.getInt("id_claseGasto"), rs.getString("descripcion"));
					listaClaseGasto.add(clasegasto);
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaClaseGasto;
	}
	public ArrayList<Cuenta> getCuentas() {

		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		
		Cuenta cuenta = null;

		Connection c;
		try {
			c = connect.getConnection();
			if (c != null) {

				String sql = "SELECT * FROM cuenta";

				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);

				ResultSet rs = statement.executeQuery();

				while (rs.next()) {
					cuenta = new Cuenta(rs.getInt("id_cuenta"), rs.getFloat("saldo"), rs.getString("descripcion"));
					listaCuentas.add(cuenta);
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCuentas;
	}

}
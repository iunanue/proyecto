package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;


import classes.ClaseGasto;
import classes.ClaseIngreso;
import classes.Cuenta;
import classes.Usuario;
import classes.UsuarioRol;

public class Dao {

	private Session currentSession;
	private Transaction currentTransaction;

	private Connect connect;

	public Dao(Connect connect) {
		this.connect = connect;
//		currentSession = openCurrentSession();
	}

	public Session openCurrentSession() {
	
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;

	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
	
///////////////////////////////////
	
	public void addUsuario(Usuario entity) {
		openCurrentSessionwithTransaction();
		getCurrentSession().save(entity);
		closeCurrentSessionwithTransaction();
		addUsuarioRol(entity.getUsername());
	}
	public void addUsuarioRol(String username) {
		UsuarioRol usuarioRol= new UsuarioRol(username,"usuario");
		openCurrentSessionwithTransaction();
		getCurrentSession().save(usuarioRol);
		closeCurrentSessionwithTransaction();
	}
	public Usuario getUsuario(String username) {
		openCurrentSessionwithTransaction();
		Usuario usuario = (Usuario) getCurrentSession().get(Usuario.class, username);
		closeCurrentSessionwithTransaction();
		return usuario;
	}
	public boolean existsUsuario(String username){
		boolean exists = false;
		if(getUsuario(username)!= null){
			exists = true;
		}
	return exists;
	}
	public void deleteUsuario(Usuario entity) {
		openCurrentSessionwithTransaction();
		getCurrentSession().delete(entity);
		closeCurrentSessionwithTransaction();
	}
	
	
	
	
///////////////////////////////////	
	


//	public void updateUsuario(Usuario usuario) {
//
//		Connection c;
//		try {
//			c = connect.getConnection();
//			if (c != null) {
//
//				String sql = "UPDATE usuario SET mail = '" + usuario.getMail() + "', password = '"
//						+ usuario.getPassword() + "' WHERE username = '" + usuario.getUsername() + "'";
//				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
//				System.out.println(sql);
//				statement.executeUpdate(sql);
//
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//

//	public ArrayList<Usuario> getUsers() {
//
//		ArrayList<Usuario> listaUsers = new ArrayList<Usuario>();
//
//		Usuario usuario = null;
//
//		Connection c;
//		try {
//			c = connect.getConnection();
//			if (c != null) {
//
//				String sql = "SELECT * FROM usuario";
//
//				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
//
//				ResultSet rs = statement.executeQuery();
//
//				while (rs.next()) {
//					usuario = new Usuario(rs.getString("username"), rs.getString("mail"), rs.getString("password"));
//					listaUsers.add(usuario);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listaUsers;
//	}
//
//	public ArrayList<ClaseIngreso> getClaseIngreso() {
//
//		ArrayList<ClaseIngreso> listaClaseIngreso = new ArrayList<ClaseIngreso>();
//
//		ClaseIngreso claseingreso = null;
//
//		Connection c;
//		try {
//			c = connect.getConnection();
//			if (c != null) {
//
//				String sql = "SELECT * FROM claseingreso";
//
//				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
//
//				ResultSet rs = statement.executeQuery();
//
//				while (rs.next()) {
//					claseingreso = new ClaseIngreso(rs.getInt("id_claseIngreso"), rs.getString("descripcion"));
//					listaClaseIngreso.add(claseingreso);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listaClaseIngreso;
//	}
//
//	public ArrayList<ClaseGasto> getClaseGasto() {
//
//		ArrayList<ClaseGasto> listaClaseGasto = new ArrayList<ClaseGasto>();
//
//		ClaseGasto clasegasto = null;
//
//		Connection c;
//		try {
//			c = connect.getConnection();
//			if (c != null) {
//
//				String sql = "SELECT * FROM clasegasto";
//
//				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
//
//				ResultSet rs = statement.executeQuery();
//
//				while (rs.next()) {
//					clasegasto = new ClaseGasto(rs.getInt("id_claseGasto"), rs.getString("descripcion"));
//					listaClaseGasto.add(clasegasto);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listaClaseGasto;
//	}
//
//	public ArrayList<Cuenta> getCuentas() {
//
//		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
//
//		Cuenta cuenta = null;
//
//		Connection c;
//		try {
//			c = connect.getConnection();
//			if (c != null) {
//
//				String sql = "SELECT * FROM cuenta";
//
//				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
//
//				ResultSet rs = statement.executeQuery();
//
//				while (rs.next()) {
//					cuenta = new Cuenta(rs.getInt("id_cuenta"), rs.getFloat("saldo"), rs.getString("descripcion"));
//					listaCuentas.add(cuenta);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listaCuentas;
//	}
//
//	public void addMovimiento(String tipo, Timestamp fecha, int id_clase, String username, int id_cuenta, float importe,
//			String descripcion) {
//
//		try {
//			Connection c = connect.getConnection();
//			if (c != null) {
//
//				String sql = "INSERT INTO movimiento (tipo, fecha, id_clase, username, id_cuenta, importe, descripcion) values (?, ?, ?, ?, ?, ?, ?)";
//
//				PreparedStatement statement = (PreparedStatement) c.prepareStatement(sql);
//
//				statement.setString(1, tipo);
//				statement.setTimestamp(2, fecha);
//				statement.setInt(3, id_clase);
//				statement.setString(4, username);
//				statement.setInt(5, id_cuenta);
//				statement.setFloat(6, importe);
//				statement.setString(7, descripcion);
//
//				statement.executeUpdate();
//
//			}
//			// c.close();
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//		}
//
//	}
}
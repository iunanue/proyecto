package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import classes.ClaseGasto;
import classes.ClaseIngreso;
import classes.Cuenta;
import classes.Movimiento;
import classes.Usuario;
import classes.UsuarioRol;

public class Dao {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	private Connect connect;

	public Dao(Connect connect) {
		this.connect = connect;

		sessionFactory = createSessionFactory();
		session = openSession();
	}

	private static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;

	}

	public Session openSession() {

		session = sessionFactory.openSession();
		return session;
	}

	public Session getSession() {
		return session;
	}

	public void closeSession() {
		session.close();
	}

	public void beginTransaction() {
		transaction = getSession().beginTransaction();
	}

	public void endTransaction() {
		transaction.commit();
	}

	////////////////////////////////////////////////////////////////////////////////

	public void addUsuario(Usuario entity) {
		beginTransaction();
		getSession().save(entity);
		endTransaction();
		addUsuarioRol(entity.getUsername());
	}

	public void addUsuarioRol(String username) {
		UsuarioRol usuarioRol = new UsuarioRol(username, "usuario");
		beginTransaction();
		getSession().save(usuarioRol);
		endTransaction();
	}

	public Usuario getUsuario(String username) {
		Usuario usuario = null;
		beginTransaction();
		usuario = (Usuario) getSession().get(Usuario.class, username);
		endTransaction();
		return usuario;
	}

	public boolean existsUsuario(String username) {
		boolean exists = false;
		if (getUsuario(username) != null) {
			exists = true;
		}
		return exists;
	}
	
	public void updateUsuario(Usuario entity){
		beginTransaction();
		getSession().update(entity);
		endTransaction();
	}

	public void deleteUsuario(Usuario entity) {
		deleteUsuarioRol(entity.getUsername());
		beginTransaction();
		getSession().delete(entity);
		endTransaction();
	}
	public void deleteUsuarioRol(String username) {
		UsuarioRol usuarioRol = new UsuarioRol(username, "usuario");
		beginTransaction();
		getSession().delete(usuarioRol);
		endTransaction();
	}
	public List<Usuario> getUsers(){
		Criteria criteria = getSession().createCriteria(Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuarios = criteria.list();
		return listaUsuarios;
	}
	
	public List<ClaseIngreso> getClaseIngreso(){
		Criteria criteria = getSession().createCriteria(ClaseIngreso.class);
		@SuppressWarnings("unchecked")
		List<ClaseIngreso> listaClaseIngreso = criteria.list();
		return listaClaseIngreso;
	}
	
	public List<ClaseGasto> getClaseGasto(){
		Criteria criteria = getSession().createCriteria(ClaseGasto.class);
		@SuppressWarnings("unchecked")
		List<ClaseGasto> listaClaseGasto = criteria.list();
		return listaClaseGasto;
	}
	
	public List<Cuenta> getCuentas(){
		Criteria criteria = getSession().createCriteria(Cuenta.class);
		@SuppressWarnings("unchecked")
		List<Cuenta> listaCuentas = criteria.list();
		return listaCuentas;
	}
	
	public void addMovimiento(Movimiento entity){
		beginTransaction();
		getSession().save(entity);
		endTransaction();
	}
	public List<Movimiento> getMovimientos(){
		Criteria criteria = getSession().createCriteria(Movimiento.class);
		@SuppressWarnings("unchecked")
		List<Movimiento> listaMovimientos = criteria.list();
		return listaMovimientos;
	}
	

}
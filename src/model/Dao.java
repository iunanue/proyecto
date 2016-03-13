package model;

import java.util.List;

import org.hibernate.Criteria;
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

public class Dao implements IDao{

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	private Connect connect;

	public Dao(Connect connect) {
		this.connect = connect;

		sessionFactory = createSessionFactory();
//		session = openSession();
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
		openSession();
		beginTransaction();
		getSession().save(entity);
		endTransaction();
		closeSession();
		addUsuarioRol(entity.getUsername());
	}

	public void addUsuarioRol(String username) {
		openSession();
		UsuarioRol usuarioRol = new UsuarioRol(username, "usuario");
		beginTransaction();
		getSession().save(usuarioRol);
		closeSession();
		endTransaction();
	}

	public Usuario getUsuario(String username) {
		openSession();
		Usuario usuario = null;
		beginTransaction();
		usuario = (Usuario) getSession().get(Usuario.class, username);
		endTransaction();
		closeSession();
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
		openSession();
		beginTransaction();
		getSession().update(entity);
		endTransaction();
		closeSession();
	}

	public void deleteUsuario(Usuario entity) {
		openSession();
		deleteUsuarioRol(entity.getUsername());
		beginTransaction();
		getSession().delete(entity);
		endTransaction();
		closeSession();
	}
	public void deleteUsuarioRol(String username) {
		openSession();
		UsuarioRol usuarioRol = new UsuarioRol(username, "usuario");
		beginTransaction();
		getSession().delete(usuarioRol);
		endTransaction();
		closeSession();
	}
	public List<Usuario> getUsers(){
		openSession();
		Criteria criteria = getSession().createCriteria(Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuarios = criteria.list();
		closeSession();
		return listaUsuarios;
	}
	
	public List<ClaseIngreso> getClaseIngreso(){
		openSession();
		Criteria criteria = getSession().createCriteria(ClaseIngreso.class);
		@SuppressWarnings("unchecked")
		List<ClaseIngreso> listaClaseIngreso = criteria.list();
		closeSession();
		return listaClaseIngreso;
	}
	
	public List<ClaseGasto> getClaseGasto(){
		openSession();
		Criteria criteria = getSession().createCriteria(ClaseGasto.class);
		@SuppressWarnings("unchecked")
		List<ClaseGasto> listaClaseGasto = criteria.list();
		closeSession();
		return listaClaseGasto;
	}
	
	public Cuenta getCuenta(int id_cuenta) {
		openSession();
		Cuenta cuenta = null;
		beginTransaction();
		cuenta = (Cuenta) getSession().get(Cuenta.class, id_cuenta);
		endTransaction();
		closeSession();
		return cuenta;
	}
	
	public void updateCuenta(Movimiento movimiento, String funcion){
		
		Cuenta cuenta = getCuenta(movimiento.getId_cuenta());
		openSession();
		float saldo = cuenta.getSaldo();
		if(funcion.equals("add")){
			if(movimiento.getTipo().equals("Ingreso")){
				saldo = saldo + movimiento.getImporte();
			}
			if(movimiento.getTipo().equals("Gasto")){
				saldo = saldo - movimiento.getImporte();
			}
		}
		if(funcion.equals("delete")){
			if(movimiento.getTipo().equals("Ingreso")){
				saldo = saldo - movimiento.getImporte();
			}
			if(movimiento.getTipo().equals("Gasto")){
				saldo = saldo + movimiento.getImporte();
			}
		}
		
		cuenta.setSaldo(saldo);
		beginTransaction();
		getSession().update(cuenta);
		endTransaction();
		closeSession();
	}
	
	public List<Cuenta> getCuentas(){
		openSession();
		Criteria criteria = getSession().createCriteria(Cuenta.class);
		@SuppressWarnings("unchecked")
		List<Cuenta> listaCuentas = criteria.list();
		closeSession();
		return listaCuentas;
	}
	
	public Movimiento getMovimiento(int id_movimiento) {
		openSession();
		Movimiento movimiento = null;
		beginTransaction();
		movimiento = (Movimiento) getSession().get(Movimiento.class, id_movimiento);
		endTransaction();
		closeSession();
		return movimiento;
	}
	
	public void addMovimiento(Movimiento entity){
		openSession();
		beginTransaction();
		getSession().save(entity);
		endTransaction();
		closeSession();
		updateCuenta(entity,"add");
	}
	
	public void deleteMovimiento(Movimiento entity){
		openSession();
		beginTransaction();
		getSession().delete(entity);
		endTransaction();
		closeSession();
		updateCuenta(entity,"delete");
	}
	
	public List<Movimiento> getMovimientos(){
		openSession();
		Criteria criteria = getSession().createCriteria(Movimiento.class);
		@SuppressWarnings("unchecked")
		List<Movimiento> listaMovimientos = criteria.list();
		closeSession();
		return listaMovimientos;
	}
	

}
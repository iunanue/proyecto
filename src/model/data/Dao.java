package model.data;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import model.classes.Alerta;
import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.classes.Usuario;
import model.classes.UsuarioRol;

import org.hibernate.SessionFactory;

public class Dao implements IDao {

	private SessionFactory sessionFactory;

	public Dao() {
		sessionFactory = createSessionFactory();
	}

	private static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;

	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void addUsuario(Usuario entity) {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();
		addUsuarioRol(entity.getUsername());

	}

	@Override
	public void addUsuarioRol(String username) {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		UsuarioRol usuarioRol = new UsuarioRol(username, "usuario");
		Transaction tx = session.beginTransaction();
		session.save(usuarioRol);
		tx.commit();
		session.close();
	}

	@Override
	public Usuario getUsuario(String username) {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Usuario usuario = null;
		Transaction tx = session.beginTransaction();

		usuario = (Usuario) session.get(Usuario.class, username);

		tx.commit();
		session.close();

		return usuario;
	}

	@Override
	public boolean existsUsuario(String username) {
		boolean exists = false;
		if (getUsuario(username) != null) {
			exists = true;
		}
		return exists;
	}

	@Override
	public void updateUsuario(Usuario entity) {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();

		session.update(entity);

		tx.commit();
		session.close();
	}

	@Override
	public void deleteUsuario(Usuario entity) {
		deleteUsuarioRol(entity.getUsername());
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}

	@Override
	public void deleteUsuarioRol(String username) {
		UsuarioRol usuarioRol = new UsuarioRol(username, "usuario");
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(usuarioRol);
		tx.commit();
		session.close();
	}

	@Override
	public List<Usuario> getUsers() {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Usuario.class);
		@SuppressWarnings("unchecked")
		List<Usuario> listaUsuarios = criteria.list();

		tx.commit();
		session.close();

		return listaUsuarios;
	}

	@Override
	public List<ClaseIngreso> getClaseIngreso() {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(ClaseIngreso.class);
		@SuppressWarnings("unchecked")
		List<ClaseIngreso> listaClaseIngreso = criteria.list();

		tx.commit();
		session.close();

		return listaClaseIngreso;
	}

	@Override
	public List<ClaseGasto> getClaseGasto() {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(ClaseGasto.class);
		@SuppressWarnings("unchecked")
		List<ClaseGasto> listaClaseGasto = criteria.list();

		tx.commit();
		session.close();

		return listaClaseGasto;
	}

	@Override
	public Cuenta getCuenta(int id_cuenta) {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Cuenta cuenta = null;
		Transaction tx = session.beginTransaction();

		cuenta = (Cuenta) session.get(Cuenta.class, id_cuenta);

		tx.commit();
		session.close();

		return cuenta;
	}

	@Override
	public void refreshCuenta(Movimiento movimiento, String funcion) {
		Cuenta cuenta = getCuenta(movimiento.getId_cuenta());
		float saldo = cuenta.getSaldo();
		if(funcion.equals("add")){
			if(movimiento.getTipo().equals("Ingreso")){
				saldo = saldo + movimiento.getImporte();
			}
			if(movimiento.getTipo().equals("Gasto")){
				saldo = saldo - movimiento.getImporte();
			}
		}
		if(funcion.equals("update")){
			saldo = saldo + movimiento.getImporte();
		}
		if(funcion.equals("delete")){
			if(movimiento.getTipo().equals("Ingreso")){
				saldo = saldo - movimiento.getImporte();
			}
			if(movimiento.getTipo().equals("Gasto")){
				saldo = saldo + movimiento.getImporte();
			}
		}
		System.out.println("Saldo:"+saldo);
		cuenta.setSaldo(saldo);
		
		
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();

		session.update(cuenta);

		tx.commit();
		session.close();
	}

	@Override
	public List<Cuenta> getCuentas() {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(Cuenta.class);
		@SuppressWarnings("unchecked")
		List<Cuenta> listaCuentas = criteria.list();

		tx.commit();
		session.close();

		return listaCuentas;
	}

	@Override
	public Movimiento getMovimiento(int id_movimiento) {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Movimiento movimiento = null;
		Transaction tx = session.beginTransaction();

		movimiento = (Movimiento) session.get(Movimiento.class, id_movimiento);

		tx.commit();
		session.close();

		return movimiento;
	}

	@Override
	public void addMovimiento(Movimiento entity) {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();
		refreshCuenta(entity,"add");

	}
	
	@Override
	public void updateMovimiento(Movimiento movimientoAntiguo, Movimiento movimientoActualizado) {
		
		System.out.println("Valor real: " + movimientoActualizado.getImporte());
		Movimiento movimientoAntiguoCopia = new Movimiento();
		movimientoAntiguoCopia.setId_cuenta(movimientoAntiguo.getId_cuenta());
		movimientoAntiguoCopia.setImporte(movimientoAntiguo.getImporte());
		
		Movimiento movimientoActualizadoCopia = new Movimiento();
		movimientoActualizadoCopia.setId_cuenta(movimientoActualizado.getId_cuenta());
		movimientoActualizadoCopia.setImporte(movimientoActualizado.getImporte());
		
		float diferencia = 0;
		if(movimientoAntiguo.getId_cuenta()==movimientoActualizado.getId_cuenta()){
			if((movimientoAntiguo.getTipo().equals("Ingreso"))&&(movimientoActualizado.getTipo().equals("Ingreso"))){
				diferencia = movimientoActualizado.getImporte()-movimientoAntiguo.getImporte();
				movimientoAntiguoCopia.setImporte(diferencia);
			}
			if((movimientoAntiguo.getTipo().equals("Gasto"))&&(movimientoActualizado.getTipo().equals("Gasto"))){
				diferencia = movimientoActualizado.getImporte()-movimientoAntiguo.getImporte();
				movimientoAntiguoCopia.setImporte(-diferencia);
			}
			if((movimientoAntiguo.getTipo().equals("Ingreso"))&&(movimientoActualizado.getTipo().equals("Gasto"))){
				diferencia = movimientoActualizado.getImporte()+movimientoAntiguo.getImporte();
				movimientoAntiguoCopia.setImporte(-diferencia);
			}
			if((movimientoAntiguo.getTipo().equals("Gasto"))&&(movimientoActualizado.getTipo().equals("Ingreso"))){
				diferencia = movimientoActualizado.getImporte()+movimientoAntiguo.getImporte();
				movimientoAntiguoCopia.setImporte(diferencia);
			}
			refreshCuenta(movimientoAntiguoCopia, "update");
		}
		else
		{
			if((movimientoAntiguo.getTipo().equals("Ingreso"))&&(movimientoActualizado.getTipo().equals("Ingreso"))){
				movimientoAntiguoCopia.setImporte(-movimientoAntiguo.getImporte());
				refreshCuenta(movimientoAntiguoCopia, "update");
				refreshCuenta(movimientoActualizadoCopia, "update");			
			}
			if((movimientoAntiguo.getTipo().equals("Gasto"))&&(movimientoActualizado.getTipo().equals("Gasto"))){
				movimientoAntiguoCopia.setImporte(movimientoAntiguo.getImporte());
				refreshCuenta(movimientoAntiguoCopia, "update");
				movimientoActualizadoCopia.setImporte(-movimientoActualizado.getImporte());
				refreshCuenta(movimientoActualizadoCopia, "update");
			}
			if((movimientoAntiguo.getTipo().equals("Ingreso"))&&(movimientoActualizado.getTipo().equals("Gasto"))){
				movimientoAntiguoCopia.setImporte(-movimientoAntiguo.getImporte());
				refreshCuenta(movimientoAntiguoCopia, "update");
				movimientoActualizadoCopia.setImporte(-movimientoActualizado.getImporte());
				refreshCuenta(movimientoActualizadoCopia, "update");
			}
			if((movimientoAntiguo.getTipo().equals("Gasto"))&&(movimientoActualizado.getTipo().equals("Ingreso"))){
				refreshCuenta(movimientoAntiguoCopia, "update");
				refreshCuenta(movimientoActualizadoCopia, "update");
			}
		}
		
		
		
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();

		System.out.println("Valor real2: " + movimientoActualizado.getImporte());
		session.update(movimientoActualizado);

		tx.commit();
		session.close();
		
	}

	@Override
	public void deleteMovimiento(Movimiento entity) {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
		refreshCuenta(entity,"delete");

	}

	@Override
	public List<Movimiento> getMovimientos() {
		SessionFactory sesion= getSessionFactory();
        Session session =sesion.openSession();
        Transaction tx=session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Movimiento.class);
		@SuppressWarnings("unchecked")
		List<Movimiento> listaMovimientos = criteria.list();
            
        tx.commit();
        session.close();
       
        return listaMovimientos;		
	}

	@Override
	public List<Movimiento> getGenerarConsultaMovimientos(boolean filtroFecha, boolean filtroTipo, boolean filtroClase,
			boolean filtroUsuario, boolean filtroCuenta, String tipo, Timestamp fechaInicio, Timestamp fechaFin,
			int id_clase, String username, int id_cuenta) {
		SessionFactory sesion= getSessionFactory();
        Session session =sesion.openSession();
        Transaction tx=session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Movimiento.class);
        if(filtroFecha == true){
        	criteria.add(Restrictions.ge("fecha", fechaInicio));
        	criteria.add(Restrictions.le("fecha", fechaFin));
        }
        if(filtroTipo == true){
        	criteria.add(Restrictions.eq("tipo", tipo));
        }
        if(filtroClase == true){
        	criteria.add(Restrictions.eq("id_clase", id_clase));
        }
        if(filtroUsuario == true){
        	criteria.add(Restrictions.eq("username", username));
        }
        if(filtroCuenta == true){
        	criteria.add(Restrictions.eq("id_cuenta", id_cuenta));
        }
		@SuppressWarnings("unchecked")
		List<Movimiento> listaMovimientos = criteria.list();
            
        tx.commit();
        session.close();
       
        return listaMovimientos;		
	}

	@Override
	public void addCuenta(Cuenta entity) {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();	
	}

	@Override
	public void updateCuenta(Cuenta entity) {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();

		session.update(entity);

		tx.commit();
		session.close();
		
	}

	@Override
	public void deleteCuenta(Cuenta entity) {
		List <Movimiento> listaMovimientos = getMovimientos();
		for(Movimiento movimiento : listaMovimientos){
			if(movimiento.getId_cuenta()==entity.getId_cuenta()){
				deleteMovimiento(movimiento);
			}
		}
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}

	@Override
	public List<Movimiento> getGenerarAnalisisEstandar(Timestamp fechaInicio, Timestamp fechaFin) {
		SessionFactory sesion= getSessionFactory();
        Session session =sesion.openSession();
        Transaction tx=session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Movimiento.class);
        
        	criteria.add(Restrictions.ge("fecha", fechaInicio));
        	criteria.add(Restrictions.le("fecha", fechaFin));
       
		@SuppressWarnings("unchecked")
		List<Movimiento> listaMovimientos = criteria.list();
            
        tx.commit();
        session.close();
       
        return listaMovimientos;	
	}

	@Override
	public void addAlerta(Alerta entity) {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();	
	}

	@Override
	public void updateAlerta(Alerta entity) {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = session.beginTransaction();

		session.saveOrUpdate(entity);

		tx.commit();
		session.close();
	}

	@Override
	public void deleteAlerta(Alerta entity) {
		SessionFactory factory = getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
		
	}

	@Override
	public Alerta getAlerta(String username) {
		SessionFactory sesion = getSessionFactory();
		Session session = sesion.openSession();
		Alerta alerta = null;
		Transaction tx = session.beginTransaction();

		alerta = (Alerta) session.get(Alerta.class, username);

		tx.commit();
		session.close();

		return alerta;
	}


}
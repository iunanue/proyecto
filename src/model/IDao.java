package model;

import java.sql.Timestamp;
import java.util.List;

import classes.ClaseGasto;
import classes.ClaseIngreso;
import classes.Cuenta;
import classes.Movimiento;
import classes.Usuario;

public interface IDao {

	public void addUsuario(Usuario entity);

	public void addUsuarioRol(String username);

	public Usuario getUsuario(String username);

	public boolean existsUsuario(String username);

	public void updateUsuario(Usuario entity);

	public void deleteUsuario(Usuario entity);

	public void deleteUsuarioRol(String username);

	public List<Usuario> getUsers();

	public List<ClaseIngreso> getClaseIngreso();

	public List<ClaseGasto> getClaseGasto();
	
	public void addCuenta(Cuenta entity);

	public Cuenta getCuenta(int id_cuenta);

	public void updateCuenta(Movimiento movimiento, String funcion);

	public List<Cuenta> getCuentas();

	public Movimiento getMovimiento(int id_movimiento);

	public void addMovimiento(Movimiento entity);
	
	public void updateMovimiento(Movimiento movimientoAntiguo, Movimiento movimientoActualizado);

	public void deleteMovimiento(Movimiento entity);

	public List<Movimiento> getMovimientos();
	
	public List<Movimiento> getGenerarConsultaMovimientos(boolean filtroFecha,boolean filtroTipo,boolean filtroClase,boolean filtroUsuario,boolean filtroCuenta,String tipo,Timestamp fechaInicio,Timestamp fechaFin,int id_clase,String username,int id_cuenta);
}

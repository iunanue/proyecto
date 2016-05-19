package model.data;

import java.sql.Timestamp;
import java.util.List;

import model.classes.Alerta;
import model.classes.ClaseGasto;
import model.classes.ClaseIngreso;
import model.classes.Cuenta;
import model.classes.Movimiento;
import model.classes.TipoMovimiento;
import model.classes.Usuario;

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
	
	public void refreshCuenta(Movimiento movimiento, String funcion);

	public void updateCuenta(Cuenta entity);
	
	public void deleteCuenta(Cuenta entity);

	public List<Cuenta> getCuentas();

	public Movimiento getMovimiento(int id_movimiento);

	public void addMovimiento(Movimiento entity);
	
	public void updateMovimiento(Movimiento movimientoAntiguo, Movimiento movimientoActualizado);

	public void deleteMovimiento(Movimiento entity);

	public List<Movimiento> getMovimientos();
	
	public List<Movimiento> getGenerarConsultaMovimientos(boolean filtroFecha,boolean filtroTipo,boolean filtroClase,boolean filtroUsuario,boolean filtroCuenta,int id_tipoMovimiento,Timestamp fechaInicio,Timestamp fechaFin,int id_clase,String username,int id_cuenta);

	public List<Movimiento> getGenerarAnalisisEstandar(Timestamp fechaInicio,Timestamp fechaFin);
	
	public List<TipoMovimiento> getTiposMovimiento();
	
	public void addAlerta(Alerta entity);
	
	public Alerta getAlerta(String username);
	
	public void updateAlerta(Alerta entity);
	
	public void deleteAlerta(Alerta entity);
	
	public List<Movimiento> getMovimientosUsernameFecha(String username,Timestamp fechaInicio,Timestamp fechaFin);
}

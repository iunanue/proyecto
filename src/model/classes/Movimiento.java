package model.classes;
import javax.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "movimiento")
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_movimiento;
	
	@Column(name = "id_tipoMovimiento")
	private int id_tipoMovimiento;//ingreso/gasto
	
	@Column(name = "fecha")
	private Timestamp fecha;
	
	@Column(name = "id_clase")
	private int id_clase;//dependiendo de si es ingreso/gasto, las clases de ingreso/gasto
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "id_cuenta")
	private int id_cuenta;
	
	@Column(name = "importe")
	private float importe;
	
	@Column(name = "descripcion")
	private String descripcion;

	public Movimiento(int id_movimiento,int id_tipoMovimiento,Timestamp fecha,int id_clase,String username,int id_cuenta,float importe,String descripcion)
	{
		this.id_movimiento = id_movimiento;
		this.id_tipoMovimiento = id_tipoMovimiento;
		this.fecha = fecha;
		this.id_clase = id_clase;
		this.username = username;
		this.id_cuenta = id_cuenta;
		this.importe = importe;
		this.descripcion = descripcion;
	}
	public Movimiento(int id_tipoMovimiento,Timestamp fecha,int id_clase,String username,int id_cuenta,float importe,String descripcion)
	{
		this.id_tipoMovimiento = id_tipoMovimiento;
		this.fecha = fecha;
		this.id_clase = id_clase;
		this.username = username;
		this.id_cuenta = id_cuenta;
		this.importe = importe;
		this.descripcion = descripcion;
	}
	public Movimiento()
	{
		
	}
	
	public int getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(int id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	public int getTipo() {
		return id_tipoMovimiento;
	}

	public void setTipo(int id_tipoMovimiento) {
		this.id_tipoMovimiento = id_tipoMovimiento;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public int getId_clase() {
		return id_clase;
	}

	public void setId_clase(int id_clase) {
		this.id_clase = id_clase;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
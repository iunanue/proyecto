package classes;

import java.util.Date;

public class Movimiento {
	
	private int id_movimiento;
	private String tipo;//ingreso/gasto
	private Date fecha;
	private int id_clase;//dependiendo de si es ingreso/gasto, las clases de ingreso/gasto
	private String username;
	private int id_cuenta;
	private float importe;
	private String descripcion;

	public Movimiento(int id_movimiento,String tipo,Date fecha,int id_clase,String username,int id_cuenta,float importe,String descripcion)
	{
		this.id_movimiento = id_movimiento;
		this.tipo = tipo;
		this.fecha = fecha;
		this.id_clase = id_clase;
		this.username = username;
		this.id_cuenta = id_cuenta;
		this.importe = importe;
		this.descripcion = descripcion;
	}

	public int getId_movimiento() {
		return id_movimiento;
	}

	public void setId_movimiento(int id_movimiento) {
		this.id_movimiento = id_movimiento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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

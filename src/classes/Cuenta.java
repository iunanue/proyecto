package classes;

public class Cuenta {
	private int id_cuenta;
	private float saldo;
	private String descripcion;
	public Cuenta(int id_cuenta,float saldo,String descripcion)
	{
		this.id_cuenta = id_cuenta;
		this.saldo = saldo;
		this.descripcion = descripcion;
	}
	public int getId_cuenta() {
		return id_cuenta;
	}
	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

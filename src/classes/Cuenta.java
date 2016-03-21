package classes;
import javax.persistence.*;

@Entity
@Table(name = "cuenta")
public class Cuenta {
	
	@Id
	private int id_cuenta;
	
	@Column(name = "saldo")
	private float saldo;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	public Cuenta(int id_cuenta,float saldo,String descripcion)
	{
		this.id_cuenta = id_cuenta;
		this.saldo = saldo;
		this.descripcion = descripcion;
	}
	
	public Cuenta(float saldo,String descripcion)
	{
		this.saldo = saldo;
		this.descripcion = descripcion;
	}
	
	public Cuenta()
	{
		
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

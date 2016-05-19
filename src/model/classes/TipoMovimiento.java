package model.classes;
import javax.persistence.*;

@Entity
@Table(name = "tipomovimiento")
public class TipoMovimiento {

	@Id
	private int id_tipoMovimiento;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	public TipoMovimiento(int id_tipoMovimiento,String descripcion)
	{
		this.id_tipoMovimiento = id_tipoMovimiento;
		this.descripcion = descripcion;
	}
	public TipoMovimiento()
	{
		
	}
	public int getId_tipoMovimiento() {
		return id_tipoMovimiento;
	}
	public void setId_tipoMovimiento(int id_tipoMovimiento) {
		this.id_tipoMovimiento = id_tipoMovimiento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	

}

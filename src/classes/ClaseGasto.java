package classes;
import javax.persistence.*;

@Entity
@Table(name = "clasegasto")
public class ClaseGasto {
	
	@Id
	private int id_claseGasto;
	
	@Column(name = "descripcion")
	private String descripcion;

	public ClaseGasto(int id_claseGasto,String descripcion)
	{
		this.id_claseGasto = id_claseGasto;
		this.descripcion = descripcion;
	}
	
	public ClaseGasto()
	{
		
	}

	public int getId_claseGasto() {
		return id_claseGasto;
	}

	public void setId_claseGasto(int id_claseGasto) {
		this.id_claseGasto = id_claseGasto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}

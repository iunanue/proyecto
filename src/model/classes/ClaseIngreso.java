package model.classes;
import javax.persistence.*;

@Entity
@Table(name = "claseingreso")
public class ClaseIngreso {

	@Id
	private int id_claseIngreso;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	public ClaseIngreso(int id_claseIngreso,String descripcion)
	{
		this.id_claseIngreso = id_claseIngreso;
		this.descripcion = descripcion;
	}
	public ClaseIngreso()
	{
		
	}

	public int getId_claseIngreso() {
		return id_claseIngreso;
	}

	public void setId_claseIngreso(int id_claseIngreso) {
		this.id_claseIngreso = id_claseIngreso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}

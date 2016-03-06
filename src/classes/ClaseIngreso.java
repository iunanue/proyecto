package classes;

public class ClaseIngreso {

	private int id_claseIngreso;
	private String descripcion;
	
	public ClaseIngreso(int id_claseIngreso,String descripcion)
	{
		this.id_claseIngreso = id_claseIngreso;
		this.descripcion = descripcion;
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

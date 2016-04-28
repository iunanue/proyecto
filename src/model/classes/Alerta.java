package model.classes;
import javax.persistence.*;

@Entity
@Table(name = "alerta")
public class Alerta {
	
	@Id
	private String username;
	
	@Column(name = "alimentacionMonth")
	private float alimentacionMonth;
	
	@Column(name = "estudiosMonth")
	private float estudiosMonth;
	
	@Column(name = "suministrosMonth")
	private float suministrosMonth;
	
	@Column(name = "mantenimientoMonth")
	private float mantenimientoMonth;
	
	@Column(name = "hipotecaAlquilerMonth")
	private float hipotecaAlquilerMonth;
	
	@Column(name = "segurosMonth")
	private float segurosMonth;
	
	@Column(name = "ocioMonth")
	private float ocioMonth;
	
	@Column(name = "otrosMonth")
	private float otrosMonth;
		
	@Column(name = "alimentacionYear")
	private float alimentacionYear;
	
	@Column(name = "estudiosYear")
	private float estudiosYear;
	
	@Column(name = "suministrosYear")
	private float suministrosYear;

	@Column(name = "mantenimientoYear")
	private float mantenimientoYear;
	
	@Column(name = "hipotecaAlquilerYear")
	private float hipotecaAlquilerYear;
	
	@Column(name = "segurosYear")
	private float segurosYear;
	
	@Column(name = "ocioYear")
	private float ocioYear;
	
	@Column(name = "otrosYear")
	private float otrosYear;
	
	public Alerta() {
	}
	
	public Alerta(String username, float alimentacionMonth, float estudiosMonth, float suministrosMonth,
			float mantenimientoMonth, float hipotecaAlquilerMonth, float segurosMonth, float ocioMonth,
			float otrosMonth, float alimentacionYear, float estudiosYear, float suministrosYear,
			float mantenimientoYear, float hipotecaAlquilerYear, float segurosYear, float ocioYear, float otrosYear) {
		this.username = username;
		this.alimentacionMonth = alimentacionMonth;
		this.estudiosMonth = estudiosMonth;
		this.suministrosMonth = suministrosMonth;
		this.mantenimientoMonth = mantenimientoMonth;
		this.hipotecaAlquilerMonth = hipotecaAlquilerMonth;
		this.segurosMonth = segurosMonth;
		this.ocioMonth = ocioMonth;
		this.otrosMonth = otrosMonth;
		this.alimentacionYear = alimentacionYear;
		this.estudiosYear = estudiosYear;
		this.suministrosYear = suministrosYear;
		this.mantenimientoYear = mantenimientoYear;
		this.hipotecaAlquilerYear = hipotecaAlquilerYear;
		this.segurosYear = segurosYear;
		this.ocioYear = ocioYear;
		this.otrosYear = otrosYear;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getAlimentacionMonth() {
		return alimentacionMonth;
	}

	public void setAlimentacionMonth(float alimentacionMonth) {
		this.alimentacionMonth = alimentacionMonth;
	}

	public float getEstudiosMonth() {
		return estudiosMonth;
	}

	public void setEstudiosMonth(float estudiosMonth) {
		this.estudiosMonth = estudiosMonth;
	}

	public float getSuministrosMonth() {
		return suministrosMonth;
	}

	public void setSuministrosMonth(float suministrosMonth) {
		this.suministrosMonth = suministrosMonth;
	}

	public float getMantenimientoMonth() {
		return mantenimientoMonth;
	}

	public void setMantenimientoMonth(float mantenimientoMonth) {
		this.mantenimientoMonth = mantenimientoMonth;
	}

	public float getHipotecaAlquilerMonth() {
		return hipotecaAlquilerMonth;
	}

	public void setHipotecaAlquilerMonth(float hipotecaAlquilerMonth) {
		this.hipotecaAlquilerMonth = hipotecaAlquilerMonth;
	}

	public float getSegurosMonth() {
		return segurosMonth;
	}

	public void setSegurosMonth(float segurosMonth) {
		this.segurosMonth = segurosMonth;
	}

	public float getOcioMonth() {
		return ocioMonth;
	}

	public void setOcioMonth(float ocioMonth) {
		this.ocioMonth = ocioMonth;
	}

	public float getOtrosMonth() {
		return otrosMonth;
	}

	public void setOtrosMonth(float otrosMonth) {
		this.otrosMonth = otrosMonth;
	}

	public float getAlimentacionYear() {
		return alimentacionYear;
	}

	public void setAlimentacionYear(float alimentacionYear) {
		this.alimentacionYear = alimentacionYear;
	}

	public float getEstudiosYear() {
		return estudiosYear;
	}

	public void setEstudiosYear(float estudiosYear) {
		this.estudiosYear = estudiosYear;
	}

	public float getSuministrosYear() {
		return suministrosYear;
	}

	public void setSuministrosYear(float suministrosYear) {
		this.suministrosYear = suministrosYear;
	}

	public float getMantenimientoYear() {
		return mantenimientoYear;
	}

	public void setMantenimientoYear(float mantenimientoYear) {
		this.mantenimientoYear = mantenimientoYear;
	}

	public float getHipotecaAlquilerYear() {
		return hipotecaAlquilerYear;
	}

	public void setHipotecaAlquilerYear(float hipotecaAlquilerYear) {
		this.hipotecaAlquilerYear = hipotecaAlquilerYear;
	}

	public float getSegurosYear() {
		return segurosYear;
	}

	public void setSegurosYear(float segurosYear) {
		this.segurosYear = segurosYear;
	}

	public float getOcioYear() {
		return ocioYear;
	}

	public void setOcioYear(float ocioYear) {
		this.ocioYear = ocioYear;
	}

	public float getOtrosYear() {
		return otrosYear;
	}

	public void setOtrosYear(float otrosYear) {
		this.otrosYear = otrosYear;
	}
}
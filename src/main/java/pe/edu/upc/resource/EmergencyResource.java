package pe.edu.upc.resource;

import lombok.Data;


@Data
public class EmergencyResource {

	private int id;
    private int heartRate;
    private String transtorno;
    private String fecha_ritmo;
    
    public int getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	public String getTranstorno() {
		return transtorno;
	}
	public void setTranstorno(String transtorno) {
		this.transtorno = transtorno;
	}
	public String getFecha_ritmo() {
		return fecha_ritmo;
	}
	public void setFecha_ritmo(String fecha_ritmo) {
		this.fecha_ritmo = fecha_ritmo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}

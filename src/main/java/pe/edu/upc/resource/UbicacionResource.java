package pe.edu.upc.resource;


import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class UbicacionResource {


	private String latitud;
    private String longitud;
   
    public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	private String fecha;
	
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}


	
}

package pe.edu.upc.resource;

import javax.persistence.Column;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class SaveUbicacionResource {
	
	   @NotNull
	    @Column(name = "latitud", nullable = false)
	    private String latitud;
	   @NotNull
	    private String fecha;
	    public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
		@NotNull
	    @Column(name = "longitud", nullable = false)
	    private String longitud;
	
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

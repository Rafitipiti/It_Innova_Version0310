package pe.edu.upc.resource;

import java.util.Date;

import javax.persistence.Column;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveRitmoCardiacoResource {
	
	

	@NotNull
    @Column(name = "ritmo_cardiaco", nullable = false)
    private int ritmoCardiaco;
    @NotNull
    @Column(name = "fecha",nullable = false)
    private Date fecha;


	



	public int getRitmoCardiaco() {
		return ritmoCardiaco;
	}


	public void setRitmoCardiaco(int ritmoCardiaco) {
		this.ritmoCardiaco = ritmoCardiaco;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

    
}

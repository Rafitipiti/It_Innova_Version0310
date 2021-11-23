package pe.edu.upc.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name ="ritmo_cardiaco")
public class RitmoCardiaco {
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id; 
    
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@NotNull
    @Column(name = "ritmo_cardiaco", nullable = false)
    private int ritmoCardiaco;

    @NotNull
    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "patient_id", nullable = false)
	private MovPatient patient;
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

	public MovPatient getPatient() {
		return patient;
	}

	public void setPatient(MovPatient patient) {
		this.patient = patient;
	}
    
}

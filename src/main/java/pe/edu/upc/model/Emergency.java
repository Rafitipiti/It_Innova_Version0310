package pe.edu.upc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@Entity
@Table(name ="emergency")
public class Emergency {

private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(name = "state", length = 15, nullable = false)
	private int state;

	@NotNull
	@Column(name = "heart_rate", nullable = false)
	private int heartRate;

	@NotNull
	@Column(name = "lenght", nullable = false)
	private String length;

	@NotNull
	@Column(name = "latitude", nullable = false)
	private String latitude;

	@Column(name = "fecha_ritmo", nullable = false)
	private String fecha_ritmo;

	@Column(name = "transtorno", nullable = false)
	private String Transtorno;
	@Column(name = "nombres", nullable = false)
	private String Nombres;
	@Column(name = "apellidos", nullable = false)
	private String Apellidos;
	@Column(name = "DNI", nullable = false)
	private String DNI;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getFecha_ritmo() {
		return fecha_ritmo;
	}
	public void setFecha_ritmo(String fecha_ritmo) {
		this.fecha_ritmo = fecha_ritmo;
	}
	public String getTranstorno() {
		return Transtorno;
	}
	public void setTranstorno(String transtorno) {
		Transtorno = transtorno;
	}
	public String getNombres() {
		return Nombres;
	}
	public void setNombres(String nombres) {
		Nombres = nombres;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	

	
	
}
package pe.edu.upc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table (name ="Paciente")
public class Paciente implements Serializable{
private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPaciente;
	@NotBlank(message="No puede estar en blanco")
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "nombrePaciente", length = 15, nullable = false)
	private String nombrePaciente;
	@NotBlank(message="No puede estar en blanco")
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "apellidoPaciente", length = 15, nullable = false)
	private String apellidoPaciente;
	@NotBlank(message="No puede estar en blanco")
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "tipoDocumento", length = 15, nullable = false)
	private String tipoDocumento;
	@NotBlank(message="No puede estar en blanco")
	@NotEmpty(message="No puede estar vacío")
	@Pattern(regexp="[0-9]{8,12}", message="DNI: Solo se permite 7 dígitos numéricos, Otros: Solo se permite 12 dígitos numéricos ")
	@Column(name = "nroDocumento", length = 12, nullable = false)
	private String nroDocumento;
	@NotBlank(message="No puede estar en blanco")
	@NotEmpty(message="No puede estar vacío")
	@Column(name = "celular", nullable = false)
	@Pattern(regexp="[0-9]{9}", message="Solo se permite 9 valores númericos ")
	private String nrocelular;
	
	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Paciente(int idPaciente, String nombrePaciente, String apellidoPaciente, String tipoDocumento, String nroDocumento, String nrocelular) {
		super();
		this.idPaciente = idPaciente;
		this.nombrePaciente = nombrePaciente;
		this.apellidoPaciente= apellidoPaciente;
		this.tipoDocumento=tipoDocumento;
		this.nroDocumento=nroDocumento;
		this.nrocelular=nrocelular;
		
	}
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getApellidoPaciente() {
		return apellidoPaciente;
	}
	public void setApellidoPaciente(String apellidoPaciente) {
		this.apellidoPaciente = apellidoPaciente;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getNrocelular() {
		return nrocelular;
	}
	public void setNrocelular(String nrocelular) {
		this.nrocelular = nrocelular;
	}

	
}

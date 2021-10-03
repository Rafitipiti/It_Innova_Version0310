package pe.edu.upc.resource;
import lombok.Data;
import java.util.Date;

@Data
public class RitmoCardiacoResource {

   
    private int ritmoCardiaco;
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

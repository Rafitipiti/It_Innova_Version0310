package pe.edu.upc.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class EmergencyResource {

    private int id;
    private int heartRate;
    private String transtorno;
    private String fecha_ritmo;

}

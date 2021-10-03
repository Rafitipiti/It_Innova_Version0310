package pe.edu.upc.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class EmergencyResource {

    private int id;
    private int state;
    private int heartRate;
    private String length;
    private String latitude;

}

package pe.edu.upc.resource;

import javax.validation.constraints.NotNull;

public class ChangeDataResource {
    @NotNull
    public int id;
    @NotNull
    public String name;
    @NotNull
    public String lastName;
    @NotNull
    public String email;
    @NotNull
    public String password;


    public int Get_id(){
        return id;
    }
    public String Get_name(){
        return name;
    }
    public String Get_lastName(){
        return lastName;
    }
    public String Get_email() {return email;}
    public String Get_password(){
        return password;
    }
}

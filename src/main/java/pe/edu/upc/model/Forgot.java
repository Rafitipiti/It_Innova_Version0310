package pe.edu.upc.model;

public class Forgot {
    public String token;
    public String password;
    public String email;
    public Boolean flag;
    
    public String Get_token(){
    	return token;
    }
    public String Get_password(){
    	return password;
    }
    public String Get_email() {
    	return email;
    }
    public Boolean Get_flag() {
        return flag;
    }
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
    
}
package pe.edu.upc.model;

public class ResponseTransfer {
	public String Id;
	public String name;
	public String lastName;
	public String email;
	public String password;
	public ResponseTransfer(String id_,String name_,String lastName_,String email_,String password_) {
		Id = id_;
		name = name_;
		lastName = lastName_;
		email = email_;
		password = password_;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
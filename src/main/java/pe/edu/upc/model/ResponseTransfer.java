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
}
package Model;

import Framework.FixedWidthField;

public class Cuenta {
	
	@FixedWidthField(width=25) String usuario;
	@FixedWidthField(width=15) String id;
	@FixedWidthField(width=15) String password;
	
	public Cuenta(){
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

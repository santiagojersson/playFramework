package Model;

import Framework.*;

/**
 * Created by root on 8/10/16.
 */
public class Usuario {


    @FixedWidthField(width=25) String nombre;
    @FixedWidthField(width=15) String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return nombre + " "+ password;
	}
    
    
}

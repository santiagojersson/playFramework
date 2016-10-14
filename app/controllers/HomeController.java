package controllers;

import java.io.IOException;
import java.util.List;

import Framework.AnotationReader;
import Model.Cuenta;
import Model.Usuario;
import Framework.MiniFramework;
import play.data.DynamicForm;
import play.mvc.*;
import play.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
	
	public String usuario;
	
	public String mensaje;
	
	
    public Result index() {
        return ok(views.html.login.render("Bienvenido"));
    }
    
    public Result renderReg(){
    	return ok(views.html.registro.render("Registro"));
    }
    
    public Result regisSuccess(){
    	return ok(views.html.regisSuccess.render(usuario));
    }
    public Result cuentaRender(){
    	return ok(views.html.cuenta.render(usuario));
    }
    public Result cuentaAddRender(){
    	return ok(views.html.cuentaAgregada.render(mensaje));
    }
    public Result searchRender(){
    	return ok(views.html.search.render(usuario));
    }
    public Result cuentaNotFoundRender(){
    	return ok(views.html.notfound.render(mensaje));
    }
    
    
    public Result reg() throws IOException{
    	
    	DynamicForm dynamicForm = play.data.Form.form().bindFromRequest();
    	Usuario user= new Usuario();
    	AnotationReader reader = new AnotationReader("/home/santiago/Documentos/PlayFrameworkProjects/pass/src/FrameworkAnotaciones/Archivos/Usuario.txt", Usuario.class);
    		
    	user.setNombre(dynamicForm.get("nombre"));
    	user.setPassword(dynamicForm.get("password"));
        Logger.info("UserName "+user.getNombre());
        Logger.info("Password "+user.getPassword());
        reader.EscribirConAnotaciones(user);
        this.usuario=user.getNombre();
        return regisSuccess();
    	
    }
    
    public Result log() throws IOException{
    	DynamicForm dynamicForm = play.data.Form.form().bindFromRequest();
    	AnotationReader reader = new AnotationReader("/home/santiago/Documentos/PlayFrameworkProjects/pass/src/FrameworkAnotaciones/Archivos/Usuario.txt", Usuario.class);
    	boolean respuesta=false;
    	List<Object> lista= reader.leerConAnotaciones();
    	for (Object user : lista) {
			Usuario usuario= (Usuario) user;
			Logger.info("User "+usuario.getNombre());
			if (usuario.getNombre().trim().equalsIgnoreCase(dynamicForm.get("nombre")) && usuario.getPassword().trim().equalsIgnoreCase(dynamicForm.get("password"))) {
				Logger.info("Password "+usuario.getNombre());
				this.usuario=usuario.getNombre();
				respuesta=true;
				break;				
			}
		}
    	if (respuesta) {
    		mensaje="Bienvenido "+usuario;
    		respuesta=false;
    		return cuentaRender();
		}else{
			mensaje="El usuario o contraseña no coinsiden";
			return cuentaNotFoundRender();
		}
    	
    	
    }
    
    public Result addCuenta(){
    	AnotationReader reader = new AnotationReader("/home/santiago/Documentos/PlayFrameworkProjects/pass/src/FrameworkAnotaciones/Archivos/Cuentas.txt", Cuenta.class);
    	DynamicForm dynamicForm = play.data.Form.form().bindFromRequest();
    	Cuenta cuenta= new Cuenta();
    	cuenta.setUsuario(usuario);
    	cuenta.setId(dynamicForm.get("cuenta"));
    	cuenta.setPassword(dynamicForm.get("password"));
    	reader.EscribirConAnotaciones(cuenta);
    	mensaje="Cuenta agregada con exito";
    	return cuentaAddRender();
    }
    
    
    public Result buscar(){
    	AnotationReader reader = new AnotationReader("/home/santiago/Documentos/PlayFrameworkProjects/pass/src/FrameworkAnotaciones/Archivos/Cuentas.txt", Cuenta.class);
    	DynamicForm dynamicForm = play.data.Form.form().bindFromRequest();
    	boolean respuesta=false;
    	String contrasena="";
    	List<Object> lista= reader.leerConAnotaciones();
    	for (Object cuenta : lista) {
			Cuenta c= (Cuenta) cuenta;
			if (c.getId().trim().equalsIgnoreCase(dynamicForm.get("cuenta"))) {
				contrasena=c.getPassword();
				respuesta=true;
				break;
			}
		}
    	if (respuesta) {
			mensaje="La contraseña es "+contrasena;
			return cuentaAddRender();
		}else{
			mensaje="La cuenta que ingreso no esta registrada";
			return cuentaNotFoundRender();
		}
    	
    }
    
    
    

}

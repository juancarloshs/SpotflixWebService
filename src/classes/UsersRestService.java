package classes;


import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


@Path ("UsersRestService")
public class UsersRestService {
	
	@GET
	@Path("usuarios")
    @Produces(MediaType.APPLICATION_JSON)
 public List<Login> getUsers()
 {
		List<Login> listUsers=new ArrayList<Login>();
		listUsers=readUsers();	
		return listUsers;
		
 }
//	@GET
//	@Path("/{usuario}/{contra}")
//    @Produces(MediaType.APPLICATION_JSON)
// public void SaveUser(@PathParam("usuario")String u,@PathParam("contra") String con)
// {
//		List<Login> listUsers=new ArrayList<Login>();
//		listUsers=readUsers();	
//		listUsers.add(new Login(u,con));
// }
	
	public static List<Login> readUsers()
	{
		List<Login> listaUsuarios=new ArrayList<Login>();
		try {
			Type tipoListaUsers = new TypeToken<List<Login>>(){}.getType(); 
			  Gson gson=new Gson(); 
			
			  listaUsuarios = gson.fromJson (new FileReader ("D:/Documents/Eclipse-Workspace/WebServicess/listausuarios.json"), tipoListaUsers);

			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		
		return listaUsuarios;
	}
	
	@GET
    @Path("{usuario}/{contrasena}")
	@Produces(MediaType.TEXT_PLAIN)
	public String  crearUsuario (@PathParam("usuario") String usuario, @PathParam("contrasena") String contraseña )
	 {
		String ok="ok";
		String JSON = null;
		List<Login> listaUsuarios;

		listaUsuarios=new ArrayList<Login>();
		try {
			Type tipoListaUsers = new TypeToken<List<Login>>(){}.getType(); 
			  Gson gson=new Gson(); 
			  	  
						
	 listaUsuarios = gson.fromJson (new FileReader ("D:/Documents/Eclipse-Workspace/WebServicess/listausuarios.json"), tipoListaUsers);
					  
					//  gson.fromJson (new FileReader ("C:/Users/yonom/eclipse-workspace/WebService/listausuarios.json"), tipoListaUsers);
			  
			  Login usuarionuevo = new Login(usuario,contraseña);
			  
			  listaUsuarios.add(usuarionuevo);
			  
			  JSON = gson.toJson(listaUsuarios);
			  
			  
//			  FileWriter fw=new FileWriter("C:/Users/yonom/eclipse-workspace/WebService/listausuarios.json");        
//	           fw.write(JSON);
			  
	           FileWriter fichero = null;
	           PrintWriter pw = null;
	          
	               fichero = new FileWriter("D:/Documents/Eclipse-Workspace/WebServicess/listausuarios.json");
	               pw = new PrintWriter(fichero);
	               pw.println(JSON);

	         
	              if (null != fichero)
	                 fichero.close();
	            
			  
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		return ok;
		
	 }
	
	
}

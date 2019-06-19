package classes;
import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {
	
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourcesClasses(resources);
		return resources;
	}
	
	
	private void addRestResourcesClasses(Set<Class<?>> resources) {
		resources.add(classes.FilmRestService.class);
		resources.add(classes.MusicRestService.class);
		resources.add(classes.UsersRestService.class);		
	}

}
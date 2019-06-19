package classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Path("MusicRestService/")
public class MusicRestService {

	private Album f;

	@GET
	@Path("albumlist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Album> getAlbum() {
		List<Album> listOfAlbum = new ArrayList<Album>();
		listOfAlbum = readAlbums();
		return listOfAlbum;
	}

	@GET
	@Path("filtro/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Album> getAlbumByGender(@PathParam("genero") String genero) {
		List<Album> listOfAlbum = new ArrayList<Album>();
		List<Album> listOfAlbumByGender = new ArrayList<Album>();
		listOfAlbum = getAlbum();

		for (Album f : listOfAlbum) {

			if (f.getGenero().equalsIgnoreCase(genero)) {

				listOfAlbumByGender.add(f);

			}

		}

		return listOfAlbumByGender;
	}

	@GET
	@Path("busqueda/{valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Album> FindAlbum(@PathParam("valor") String valor) {
		List<Album> listOfAlbum = new ArrayList<Album>();
		List<Album> listOfAlbumFound = new ArrayList<Album>();
		listOfAlbum = getAlbum();
		valor = valor.toUpperCase();

		for (Album f : listOfAlbum) {
			List<Cancion> listOfSongs = f.getListaCanciones();
			String titulo = f.getTitulo().toUpperCase();
			String artista = f.getArtista().toUpperCase();
			if (titulo.equals(valor) || artista.equals(valor) || titulo.contains(valor) || artista.contains(valor)) {

				listOfAlbumFound.add(f);

			}

			for (Cancion c : listOfSongs) {
				if (c.getNombreCancion().equalsIgnoreCase(valor)) {
					listOfAlbumFound.add(f);
				}
			}

		}

		return listOfAlbumFound;
	}

	public static List<Album> readAlbums() {
		List<Album> listaAlbum = new ArrayList<Album>();
		try {
			Type tipoListaAlbum = new TypeToken<List<Album>>() {
			}.getType();
			Type tipoCancion = new TypeToken<List<Cancion>>() {
			}.getType();

			Gson gson = new Gson();

			listaAlbum = gson.fromJson(new FileReader("D:/Documents/Eclipse-Workspace/WebServicess/listaAlbum.json"),
					tipoListaAlbum);

		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaAlbum;
	}
}

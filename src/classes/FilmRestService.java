package classes;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

@Path("FilmRestService/")
public class FilmRestService {

	@GET
	@Path("peliculas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> getFilms() {
		List<Pelicula> listOfFilms = new ArrayList<Pelicula>();
		listOfFilms = readFilms();

		return listOfFilms;

	}

	@GET
	@Path("filtro/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> getFilmsByGender(@PathParam("genero") String genero) {
		List<Pelicula> listOfFilms = new ArrayList<Pelicula>();
		List<Pelicula> listOfFilmsByGender = new ArrayList<Pelicula>();
		listOfFilms = readFilms();

		for (Pelicula f : listOfFilms) {
			if (f.getGenero().equalsIgnoreCase(genero)) {

				listOfFilmsByGender.add(f);

			}

		}

		return listOfFilmsByGender;
	}

	@GET
	@Path("busqueda/{valor}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pelicula> FindFilms(@PathParam("valor") String valor) {
		List<Pelicula> listOfFilms = new ArrayList<Pelicula>();
		List<Pelicula> listOfFilmsFound = new ArrayList<Pelicula>();
		listOfFilms = readFilms();
		valor = valor.toUpperCase();

		for (Pelicula f : listOfFilms) {
			String titulo = f.getTitulo().toUpperCase();
			String director = f.getDirector().toUpperCase();
			if (titulo.equals(valor) || director.equals(valor) || titulo.contains(valor) || director.contains(valor)) {

				listOfFilmsFound.add(f);

			}

		}

		return listOfFilmsFound;
	}
	
	public static List<Pelicula> readFilms() {
		List<Pelicula> listaPeliculas = new ArrayList<Pelicula>();
		try {
			Type tipoListaPeliculas = new TypeToken<List<Pelicula>>() {
			}.getType();
			Gson gson = new Gson();

			listaPeliculas = gson.fromJson(
					new FileReader("D:/Documents/Eclipse-Workspace/WebServicess/listaPeliculas.json"),
					tipoListaPeliculas);

		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPeliculas;
	}
}
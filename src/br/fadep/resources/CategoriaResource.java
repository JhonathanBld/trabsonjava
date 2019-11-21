package br.fadep.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.fadep.model.domain.Categoria;
import br.fadep.service.CategoriaService;

@Path("/Categorias")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class CategoriaResource {

	private CategoriaService service = new CategoriaService();

	@GET
	public List<Categoria> getCategorias() {

		return service.getCategorias();
	}

	@GET
	@Path("{CategoriaId}")
	public Categoria getCategoria(@PathParam("CategoriaId") long id) {
		return service.getCategoria(id);
	}

	@POST
	public Response save(Categoria Categoria) {
		Categoria = service.saveCategoria(Categoria);
		return Response.status(Status.CREATED).entity(Categoria).build();
	}

	@DELETE
	@Path("{CategoriaId}")
	public void delete(@PathParam("CategoriaId") long id) {
		service.deleteCategoria(id);
	}

}
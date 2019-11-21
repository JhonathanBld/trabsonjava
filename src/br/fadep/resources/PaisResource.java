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

import br.fadep.model.domain.Pais;
import br.fadep.service.PaisService;

@Path("/Paiss")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class PaisResource {

	private PaisService service = new PaisService();

	@GET
	public List<Pais> getPaiss() {

		return service.getPaiss();
	}

	@GET
	@Path("{PaisId}")
	public Pais getPais(@PathParam("PaisId") long id) {
		return service.getPais(id);
	}

	@POST
	public Response save(Pais Pais) {
		Pais = service.savePais(Pais);
		return Response.status(Status.CREATED).entity(Pais).build();
	}

	@DELETE
	@Path("{PaisId}")
	public void delete(@PathParam("PaisId") long id) {
		service.deletePais(id);
	}

}
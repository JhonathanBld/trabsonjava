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

import br.fadep.model.domain.Estado;
import br.fadep.service.EstadoService;

@Path("/Estados")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class EstadoResource {

	private EstadoService service = new EstadoService();

	@GET
	public List<Estado> getEstados() {

		return service.getEstados();
	}

	@GET
	@Path("{EstadoId}")
	public Estado getEstado(@PathParam("EstadoId") long id) {
		return service.getEstado(id);
	}

	@POST
	public Response save(Estado Estado) {
		Estado = service.saveEstado(Estado);
		return Response.status(Status.CREATED).entity(Estado).build();
	}

	@DELETE
	@Path("{EstadoId}")
	public void delete(@PathParam("EstadoId") long id) {
		service.deleteEstado(id);
	}

}
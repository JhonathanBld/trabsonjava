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

import br.fadep.model.domain.Contato;
import br.fadep.service.ContatoService;

@Path("/Contatos")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class ContatoResource {

	private ContatoService service = new ContatoService();

	@GET
	public List<Contato> getContatos() {

		return service.getContatos();
	}

	@GET
	@Path("{ContatoId}")
	public Contato getContato(@PathParam("ContatoId") long id) {
		return service.getContato(id);
	}

	@POST
	public Response save(Contato Contato) {
		Contato = service.saveContato(Contato);
		return Response.status(Status.CREATED).entity(Contato).build();
	}

	@DELETE
	@Path("{ContatoId}")
	public void delete(@PathParam("ContatoId") long id) {
		service.deleteContato(id);
	}

}
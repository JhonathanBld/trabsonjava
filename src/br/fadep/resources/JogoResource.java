package br.fadep.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.fadep.model.domain.Jogo;
import br.fadep.service.JogoService;

@Path("/Jogos")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class JogoResource {

	private JogoService service = new JogoService();

	@GET
	public List<Jogo> getJogos() {

		return service.getJogos();
	}

	@GET
	@Path("{JogoId}")
	public Jogo getJogo(@PathParam("JogoId") long id) {
		return service.getJogo(id);
	}

	@POST
	public Response save(Jogo Jogo) {
		Jogo = service.saveJogo(Jogo);
		return Response.status(Status.CREATED).entity(Jogo).build();
	}

	@PUT
	@Path("{JogoId}")
	public void update(@PathParam("JogoId") long id, Jogo Jogo) {
		Jogo.setId(id);
		service.updateJogo(Jogo);
	}

	@DELETE
	@Path("{JogoId}")
	public void delete(@PathParam("JogoId") long id) {
		service.deleteJogo(id);
	}

}
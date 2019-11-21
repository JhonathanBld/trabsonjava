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

import br.fadep.model.domain.Cidade;
import br.fadep.service.CidadeService;

@Path("/Cidades")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class CidadeResource {

	private CidadeService service = new CidadeService();

	@GET
	public List<Cidade> getCidades() {

		return service.getCidades();
	}

	@GET
	@Path("{CidadeId}")
	public Cidade getCidade(@PathParam("CidadeId") long id) {
		return service.getCidade(id);
	}

	@POST
	public Response save(Cidade Cidade) {
		Cidade = service.saveCidade(Cidade);
		return Response.status(Status.CREATED).entity(Cidade).build();
	}

	@PUT
	@Path("{CidadeId}")
	public void update(@PathParam("CidadeId") long id, Cidade Cidade) {
		Cidade.setId(id);
		service.updateCidade(Cidade);
	}

	@DELETE
	@Path("{CidadeId}")
	public void delete(@PathParam("CidadeId") long id) {
		service.deleteCidade(id);
	}

}
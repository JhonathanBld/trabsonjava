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

import br.fadep.model.domain.Pessoa;
import br.fadep.service.PessoaService;

@Path("/Pessoas")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class PessoaResource {

	private PessoaService service = new PessoaService();

	@GET
	public List<Pessoa> getPessoas() {

		return service.getPessoas();
	}

	@GET
	@Path("{PessoaId}")
	public Pessoa getPessoa(@PathParam("PessoaId") long id) {
		return service.getPessoa(id);
	}

	@POST
	public Response save(Pessoa Pessoa) {
		Pessoa = service.savePessoa(Pessoa);
		return Response.status(Status.CREATED).entity(Pessoa).build();
	}

	@DELETE
	@Path("{PessoaId}")
	public void delete(@PathParam("PessoaId") long id) {
		service.deletePessoa(id);
	}

}
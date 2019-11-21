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

import br.fadep.model.domain.Carrinho;
import br.fadep.service.CarrinhoService;

@Path("/Carrinhos")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class CarrinhoResource {

	private CarrinhoService service = new CarrinhoService();

	@GET
	public List<Carrinho> getCarrinhos() {

		return service.getCarrinhos();
	}

	@GET
	@Path("{CarrinhoId}")
	public Carrinho getCarrinho(@PathParam("CarrinhoId") long id) {
		return service.getCarrinho(id);
	}

	@POST
	public Response save(Carrinho Carrinho) {
		Carrinho = service.saveCarrinho(Carrinho);
		return Response.status(Status.CREATED).entity(Carrinho).build();
	}

	@PUT
	@Path("{CarrinhoId}")
	public void update(@PathParam("CarrinhoId") long id, Carrinho Carrinho) {
		Carrinho.setId(id);
		service.updateCarrinho(Carrinho);
	}

	@DELETE
	@Path("{CarrinhoId}")
	public void delete(@PathParam("CarrinhoId") long id) {
		service.deleteCarrinho(id);
	}

}
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

import br.fadep.model.domain.Venda;
import br.fadep.service.VendaService;

@Path("/Vendas")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class VendaResource {

	private VendaService service = new VendaService();

	@GET
	public List<Venda> getVendas() {

		return service.getVendas();
	}

	@GET
	@Path("{VendaId}")
	public Venda getVenda(@PathParam("VendaId") long id) {
		return service.getVenda(id);
	}

	@POST
	public Response save(Venda Venda) {
		Venda = service.saveVenda(Venda);
		return Response.status(Status.CREATED).entity(Venda).build();
	}

	@PUT
	@Path("{VendaId}")
	public void update(@PathParam("VendaId") long id, Venda Venda) {
		Venda.setId(id);
		service.updateVenda(Venda);
	}

	@DELETE
	@Path("{VendaId}")
	public void delete(@PathParam("VendaId") long id) {
		service.deleteVenda(id);
	}

}
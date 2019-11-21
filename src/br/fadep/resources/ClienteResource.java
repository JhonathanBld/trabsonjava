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

import br.fadep.model.domain.Cliente;
import br.fadep.service.ClienteService;

@Path("/Clientes")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class ClienteResource {

	private ClienteService service = new ClienteService();

	@GET
	public List<Cliente> getClientes() {

		return service.getClientes();
	}

	@GET
	@Path("{ClienteId}")
	public Cliente getCliente(@PathParam("ClienteId") long id) {
		return service.getCliente(id);
	}

	@POST
	public Response save(Cliente Cliente) {
		Cliente = service.saveCliente(Cliente);
		return Response.status(Status.CREATED).entity(Cliente).build();
	}

	@PUT
	@Path("{ClienteId}")
	public void update(@PathParam("ClienteId") long id, Cliente Cliente) {
		Cliente.setId(id);
		service.updateCliente(Cliente);
	}

	@DELETE
	@Path("{ClienteId}")
	public void delete(@PathParam("ClienteId") long id) {
		service.deleteCliente(id);
	}

}
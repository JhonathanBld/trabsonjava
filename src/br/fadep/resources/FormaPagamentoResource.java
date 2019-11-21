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

import br.fadep.model.domain.FormaPagamento;
import br.fadep.service.FormaPagamentoService;

@Path("/FormaPagamentos")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class FormaPagamentoResource {

	private FormaPagamentoService service = new FormaPagamentoService();

	@GET
	public List<FormaPagamento> getFormaPagamentos() {

		return service.getFormaPagamentos();
	}

	@GET
	@Path("{FormaPagamentoId}")
	public FormaPagamento getFormaPagamento(@PathParam("FormaPagamentoId") long id) {
		return service.getFormaPagamento(id);
	}

	@POST
	public Response save(FormaPagamento FormaPagamento) {
		FormaPagamento = service.saveFormaPagamento(FormaPagamento);
		return Response.status(Status.CREATED).entity(FormaPagamento).build();
	}

	@DELETE
	@Path("{FormaPagamentoId}")
	public void delete(@PathParam("FormaPagamentoId") long id) {
		service.deleteFormaPagamento(id);
	}

}
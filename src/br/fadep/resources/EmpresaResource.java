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

import br.fadep.model.domain.Empresa;
import br.fadep.service.EmpresaService;

@Path("/Empresas")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")

public class EmpresaResource {

	private EmpresaService service = new EmpresaService();

	@GET
	public List<Empresa> getEmpresas() {

		return service.getEmpresas();
	}

	@GET
	@Path("{EmpresaId}")
	public Empresa getEmpresa(@PathParam("EmpresaId") long id) {
		return service.getEmpresa(id);
	}

	@POST
	public Response save(Empresa Empresa) {
		Empresa = service.saveEmpresa(Empresa);
		return Response.status(Status.CREATED).entity(Empresa).build();
	}

	@DELETE
	@Path("{EmpresaId}")
	public void delete(@PathParam("EmpresaId") long id) {
		service.deleteEmpresa(id);
	}

}
package javars;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.trevis.beans.DataExample;
import it.trevis.dao.SomethingDao;

@Path("/hello")
public class HelloWorldRS {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	@EJB
	private SomethingDao dao;
	
	Jsonb jsonb = JsonbBuilder.create();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		logger.debug("Chiamata a getAll()..");
		List<DataExample> result = dao.read();
		return Response.ok(jsonb.toJson(result), MediaType.APPLICATION_JSON).build();
	}

//	@GET
//	@Path("/ciao")
//	@Produces("text/plain")
//	public Response getHello() {
//		String response = "I'm the microserv Hello!";
//		logger.debug("La risposta del server è : {}", response);
//		return Response.status(200).entity(response).build();
//	}
//	
//	@GET
//	@Path("/")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public Response get() {
//		String response = "I'm the microserv Hello!";
//		logger.debug("La risposta del server è : {}", response);
//		return Response.status(200).entity(response).build();
//	}
//	
//	@GET
//	@Path("/{id}")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public Response getWithParams() {
//		String response = "I'm the microserv Hello!";
//		logger.debug("La risposta del server è : {}", response);
//		return Response.status(200).entity(response).build();
//	}
//	
//	@POST
//	@Path("/")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public Response create(String body) {
//		//dao.save(da);
//		return null;
//	}
//	
//	@DELETE
//	@Path("/")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public Response deleteAll() {
//		return null;
//	}
//	
//	@DELETE
//	@Path("/{id}")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public Response delete() {
//		return null;
//	}
//	
//	@PUT
//	@Path("/{}")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public Response put() {
//		return null;
//	}
	
	@GET
	@Path("/in")
	@Produces("text/plain")
	public Response getInsert() {
		DataExample da = new DataExample();
		da.setSomething("Stringa inserita");
//		dao.save(da);
		logger.debug("Entry inserita : {}", da);
		return Response.status(200).entity(dao.save(da).toString()).build();
	}
}

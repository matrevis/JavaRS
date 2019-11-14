package javars;

import java.lang.invoke.MethodHandles;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.trevis.beans.DataExample;
import it.trevis.dao.SomethingDao;

@Path("/hello")
public class HelloWorldRS {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	
	@EJB
	private SomethingDao dao;

	@GET
	@Path("/")
	@Produces("text/plain")
	public Response getHello() {
		String response = "I'm the microserv Hello!";
		logger.debug("La risposta del server è : {}", response);
		return Response.status(200).entity(response).build();
	}
	
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

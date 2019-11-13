package javars;

//import java.lang.invoke.MethodHandles;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("hello")
public class HelloWorldRS {

//	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldRS.class);

	@GET
	@Produces("text/plain")
	public Response getHello() {
		String response = "I'm the microserv Hello!";
		logger.info("La risposta del server è : {}", response);
		return Response.status(200).entity(response).build();
	}
}

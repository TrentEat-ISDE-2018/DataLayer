package datalayer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestConnectionResource {
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHelloHtml() {
        return "<html> " + "<title>" + "Hello DataLayer" + "</title>"
                + "<body><h1>" + "Hello World Test in DataLayer" + "</body></h1>"
                + "</html> ";
    }
}

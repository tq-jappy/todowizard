package todowizard.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import todowizard.entity.Todo;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@Path("/api/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    private TodoService todoService;

    @GET
    public List<Todo> getTodos() {
        return todoService.findAll();
    }
}

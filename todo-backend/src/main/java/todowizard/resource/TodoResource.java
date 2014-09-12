package todowizard.resource;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import todowizard.entity.Todo;
import todowizard.service.TodoService;

import com.google.inject.Inject;

/**
 * ToDo リソース。実際の処理はサービスクラスに委譲し、複雑なロジックはここでは実装しない。
 * 
 * @author t_endo
 */
@Path("/api/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    private TodoService todoService;

    @GET
    public List<Todo> getTodos() {
        return todoService.findAll();
    }

    @GET
    @Path("{id}")
    public Todo getTodo(@PathParam("id") int id) {
        return todoService.findById(id);
    }

    @POST
    public Todo postTodo(@Valid Todo todo) {
        return todoService.create(todo);
    }

    @PUT
    @Path("/{id}")
    public Todo updateTodo(@PathParam("id") int id, @Valid Todo todo) {
        return todoService.update(todo);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTodo(@PathParam("id") int id) {
        todoService.delete(id);
    }
}

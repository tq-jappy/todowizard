package todowizard.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    private TodoService todoService;

    @GET
    public List<Todo> getTodos() {
        return todoService.findAll();
    }

    @POST
    public Todo postTodo(Todo todo) {
        todoService.create(todo);
        return todo;
    }

    @DELETE
    @Path("/{id}")
    public void deleteTodo(@PathParam("id") int id) {
        todoService.delete(id);
    }
}

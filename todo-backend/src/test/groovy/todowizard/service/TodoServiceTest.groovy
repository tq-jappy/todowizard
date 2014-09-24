package todowizard.service;

import static org.junit.Assert.*
import static org.mockito.Mockito.*

import org.junit.Before
import org.junit.Test

import todowizard.dao.TodoDao
import todowizard.entity.Todo

/**
 * @author t_endo
 *
 */
class TodoServiceTest {

    private final TodoDao todoDao = mock(TodoDao.class)

    private TodoService todoService = new TodoService(todoDao)

    @Before
    void setUp() {
        reset(todoDao)
    }

    @Test
    public void "findAllで一覧が取得できる"()  {
        def todo1 = Todo.builder().title("todo01").build()
        def todo2 = Todo.builder().title("todo02").build()
        when(todoDao.selectAll()).thenReturn([todo1, todo2])

        assert todoService.findAll() == [todo1, todo2]
    }

    @Test
    public void "createで新規にタスクを追加できる"() {
        def actual =  todoService.create(Todo.builder().title("new todo").build())

        assert actual.getTitle() == "new todo"
    }

    @Test
    public void "deleteでタスクを削除できる"() {
        def actual =  todoService.delete(1)

        assert actual == 1
    }
}

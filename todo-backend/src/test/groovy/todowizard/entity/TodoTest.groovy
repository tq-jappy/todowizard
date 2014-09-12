package todowizard.entity
import static org.junit.Assert.*

import org.junit.Rule
import org.junit.Test

import todowizard.test.ExpectedViolation


/**
 * 
 * @author t_endo
 */
class TodoTest {

    @Rule
    public ExpectedViolation<Todo> violaton = new ExpectedViolation<>()

    @Test
    public void "プロパティが全てセットされていればOK"() {
        Todo todo = Todo.builder().id(1).title("aaa").build()

        violaton.expect(todo).beValid()
    }

    @Test
    public void "titleがnullの場合はエラー"() {
        Todo todo = Todo.builder().id(1).build()

        violaton.expect(todo).hasErrorWith("title")
    }
}

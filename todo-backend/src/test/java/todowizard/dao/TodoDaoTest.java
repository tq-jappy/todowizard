package todowizard.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Rule;
import org.junit.Test;

import todowizard.entity.Todo;
import todowizard.test.Migration;

/**
 * 
 * @author t_endo
 */
public class TodoDaoTest {

    // @Rule
    // public final TestingDbResource dbResource = new TestingDbResource();

    // private final TodoDao dao = new TodoDaoImpl(dbResource.getConfig());

    @Rule
    public final Migration migration = new Migration("config-test.yml");

    @Test
    public void selectById() {
        TodoDao dao = new TodoDaoImpl(migration.getConfig());

        migration.getTransactionManager().required(() -> {
            List<Todo> actual = dao.selectAll();

            assertThat(actual, is(notNullValue()));
            assertThat(actual.size(), is(3));
        });
    }
}

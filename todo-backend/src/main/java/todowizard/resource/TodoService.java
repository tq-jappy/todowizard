package todowizard.resource;

import java.util.List;

import todowizard.core.guice.Transactional;
import todowizard.dao.TodoDao;
import todowizard.entity.Todo;

import com.google.inject.Inject;

/**
 * 
 * @author t_endo
 */
@Transactional
public class TodoService {

    @Inject
    private TodoDao dao;

    public List<Todo> findAll() {
        return dao.selectAll();
    }
}

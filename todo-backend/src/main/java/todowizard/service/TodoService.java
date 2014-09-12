package todowizard.service;

import java.util.List;

import todowizard.core.guice.DomaTransactional;
import todowizard.dao.TodoDao;
import todowizard.entity.Todo;

import com.google.inject.Inject;

/**
 * ToDo サービス。<br />
 * {@link DomaTransactional} アノテーションをつけているため、メソッドの境界で自動的に REQUIRE
 * トランザクションがインターセプトされる。
 * 
 * @author t_endo
 */
@DomaTransactional
public class TodoService {

    @Inject
    private TodoDao dao;

    public List<Todo> findAll() {
        return dao.selectAll();
    }

    public Todo findById(int id) {
        return dao.selectById(id);
    }

    public Todo create(Todo todo) {
        dao.insert(todo);
        return todo;
    }

    public Todo update(Todo todo) {
        dao.update(todo);
        return todo;
    }

    public int delete(int id) {
        dao.delete(Todo.builder().id(id).build());
        return 1;
    }
}

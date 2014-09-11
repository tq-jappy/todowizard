package todowizard.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import todowizard.core.guice.Transactional;
import todowizard.dao.TodoDao;
import todowizard.entity.Todo;

import com.google.inject.Inject;

/**
 * ToDo サービス。<br />
 * {@link Transactional} アノテーションをつけているため、メソッドの境界で自動的に REQUIRE
 * トランザクションがインターセプトされる。
 * 
 * @author t_endo
 */
@Slf4j
@Transactional
public class TodoService {

    @Inject
    private TodoDao dao;

    public List<Todo> findAll() {
        return dao.selectAll();
    }

    public int create(Todo todo) {
        return dao.insert(todo);
    }

    public int delete(int id) {
        dao.delete(Todo.builder().id(id).build());
        return 1;
    }
}

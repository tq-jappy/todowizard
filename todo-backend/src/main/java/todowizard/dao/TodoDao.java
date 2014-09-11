package todowizard.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import todowizard.core.doma.InjectConfig;
import todowizard.entity.Todo;

@Dao
@InjectConfig
public interface TodoDao {

    @Select
    List<Todo> selectAll();

    @Select
    Todo selectById(Integer id);

    @Insert
    int insert(Todo todo);

    @Update
    int update(Todo todo);

    @Delete
    int delete(Todo todo);
}

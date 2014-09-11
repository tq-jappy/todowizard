package todowizard.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Script;

import todowizard.core.doma.InjectConfig;

@Dao
@InjectConfig
public interface AppDao {

    @Script
    public void create();

    @Script
    public void drop();
}
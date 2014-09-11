package todowizard;

import todowizard.dao.TodoDao;
import todowizard.dao.TodoDaoImpl;
import todowizard.resource.TodoResource;

import com.google.inject.AbstractModule;

public class TodoModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure() {
        bind(TodoDao.class).to(TodoDaoImpl.class);

        TodoResource todoResource = new TodoResource();
        bind(TodoResource.class).toInstance(todoResource);

        // requestInjection(todoResource);
    }
}

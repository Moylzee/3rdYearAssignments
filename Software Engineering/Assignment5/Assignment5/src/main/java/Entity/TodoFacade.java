package Entity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author Brian Moyles
 */
@Stateless
public class TodoFacade extends AbstractFacade<Todo> {
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TodoFacade() {
        super(Todo.class);
    }
}
package session;
import entity.Artwork;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author Brian Moyles
 */
@Stateless
public class ArtworkFacade extends AbstractFacade<Artwork> {
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtworkFacade() {
        super(Artwork.class);
    }
}
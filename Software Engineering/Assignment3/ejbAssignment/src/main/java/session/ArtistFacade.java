package session;
import entity.Artist;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author Brian Moyles
 */
@Stateless
public class ArtistFacade extends AbstractFacade<Artist> {
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArtistFacade() {
        super(Artist.class);
    }
}
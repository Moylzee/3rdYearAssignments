import Entity.Todo;
import Entity.TodoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author Brian Moyles
 */
@Named("todoBeanList")
@RequestScoped
public class todoBeanList {

    @EJB
    private TodoFacade todoFacade;
    private List<Todo> todoList;
    
    @PostConstruct
    public void initialize() {
        todoList = todoFacade.findAll();
    }
    
    public List<Todo> getTodoList() {return todoList;}
}   
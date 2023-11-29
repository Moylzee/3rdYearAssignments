package bean;
import Entity.Todo;
import Entity.TodoFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("todoBean")
@RequestScoped
public class todoBean implements Serializable {
    
    private Todo todo;
    private Integer id;
    String category;
    String description;
    Integer priority; 
    
    @EJB
    private TodoFacade todoFacade;
    
    @PostConstruct
    public void initialize() {
        String todoP = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("todoid");
        if (todoP != null) {
            id = Integer.parseInt(todoP);
            todo=todoFacade.find(id);
            category=todo.getCategory();
            description=todo.getDescription();
            priority=todo.getPriority();
        } 
    }

    // Method to create an entry from the to-do list 
    // Returns the user to the index page after creation
    public String createTodo() {
        Todo t = new Todo();
        t.setCategory(category);
        t.setDescription(description);
        t.setPriority(priority);
        todoFacade.create(t); 
        return "Created";
    }
    
    // Method to update an entry from the to-do list 
    // Returns the user to the index page after update is confirmed
    public String update() {
        todo.setCategory(category);
        todo.setDescription(description);
        todo.setPriority(priority);
        todoFacade.edit(todo);
        return "Updated";
    }
    
    // Method to delete an entry from the to-do list 
    // Returns the user to the index page after deletion is confirmed
    public String delete() {
        todoFacade.remove(todo);
        return "Deleted";
    }
    
    // Getters and Setters
    public String getCategory() {return category;}
    public String getDescription() {return description;}
    public Integer getPriority() {return priority;}
    public Todo getTodo() {return todo;}
    
    public void setCategory(String category) {this.category = category;}
    public void setDescription(String description) {this.description = description;}
    public void setPriority(Integer priority) {this.priority = priority;} 
    public void setTodo(Todo todo) {this.todo = todo;}
}
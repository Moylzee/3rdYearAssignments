package Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Brian Moyles
 */
@Entity
@Table(name = "todo")
@NamedQueries(
{
   @NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t")
})
public class Todo implements Serializable {
        
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name="category")
    private String category;
    
    @Column(name="description")
    private String description;
    
    @Column(name="priority")
    private Integer priority;
    
    // Default Constructor
    public Todo() {}
    // Constructor to pass parameters to use
    public Todo(Integer id, String category, String description, Integer priority ) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.priority = priority;
    }
  
    // Getters and Setters
    public Integer getId() {return id;}
    public String getCategory() {return category;}
    public String getDescription() {return description;}
    public Integer getPriority() {return priority;}

    public void setId(Integer id) {this.id = id;}
    public void setCategory(String category) {this.category = category;}
    public void setDescription(String description) {this.description = description;}
    public void setPriority(Integer priority) {this.priority = priority;}   
}
package bean;

public class Todo {
    String subject;
    String task;
    
    public Todo(String subject, String task){
        setSubject(subject);
        setTask(task);
    }
    
    // Setter Methods    
    public void setSubject(String subject){
        this.subject = subject;
    }
    
    public void setTask(String task){
        this.task = task;
    }
    
    // Getter Methods      
    public String getSubject() {
        return this.subject;
    }
    
    public String getTask(){
        return this.task;
    }
}


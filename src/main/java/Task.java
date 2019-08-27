import java.util.*;

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description){

        System.out.println("Got it. I've added this task: ");
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusIcon(){

        return(isDone ? "\u2713" : "\u2718");
    }

    public String connect(){

        return "[" + getStatusIcon() + "] " + description ;
    }
}

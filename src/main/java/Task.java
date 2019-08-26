import java.util.*;

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description){

        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon(){

        return(isDone ? "\u2713" : "\u2718");
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

}

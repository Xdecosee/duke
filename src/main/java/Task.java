import java.io.IOException;

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {

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

    public String getStatusIcon() {

        return (isDone ? "\u2713" : "\u2718");
    }

    public String connect() {

        return "[" + getStatusIcon() + "] " + description;
    }

    public void addData(int id, String directory) throws IOException {
    }
}

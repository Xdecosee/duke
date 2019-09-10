import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {

    private String by;
    private String type;

    public Deadline(String description, String by) {
        super(description);
        this.type = "[D]";
        this.by = by;
    }

    @Override
    public String connect() {

        return type + super.connect() + " (by: " + by + ")";

    }

    /**
     * Add new record
     *
     * @param id of new record
     * @param directory  directory of database files
     * @throws IOException if database error
     */
    @Override
    public void addData(int id, String directory) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(directory + "\\DB.txt", true));

        boolean done = isDone();
        int doneval = done ? 1 : 0;

        bw.write(id + "|" + type + "|" + doneval + "|" + getDescription() + "|" + by);
        bw.flush();
        bw.newLine();
        bw.close();

    }
}

import java.io.*;

public class Todo extends Task {

    private String type;

    public Todo(String description) {
        super(description);
        this.type = "[T]";

    }


    @Override
    public String connect() {

        return type + super.connect();

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

        bw.write(id + "|" + type + "|" + doneval + "|" + getDescription());
        bw.flush();
        bw.newLine();
        bw.close();

    }
}

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{

    private String at;
    private String type;

    public Event(String description, String at) throws IOException {
        super(description);
        this.type = "[E]";
        this.at = at;

    }

    public String getType() {
        return type;
    }

    @Override
    public String connect(){

        return type + super.connect() + " (at: " + at+ ")";

    }

    @Override
    public void addData(int id) throws IOException {

        BufferedWriter bw = new BufferedWriter( new FileWriter("./src/main/data/DB.txt",true));

        boolean done = isDone();
        int doneval = done ? 1 : 0;

        bw.write(id + "|" + type + "|" + doneval + "|" + getDescription() + "|" + at);
        bw.flush();
        bw.newLine();
        bw.close();

    }
}

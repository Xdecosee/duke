import java.io.*;
import java.util.*;

public class Todo  extends Task{

    private String type;

    public Todo(String description) {
        super(description);
        this.type = "[T]";

    }


    @Override
    public String connect(){

        return type + super.connect();

    }

    @Override
    public void addData(int id) throws IOException {

        BufferedWriter bw = new BufferedWriter( new FileWriter("./src/main/data/DB.txt",true));

        boolean done = isDone();
        int doneval = done ? 1 : 0;

        bw.write(id + "|" + type + "|" + doneval + "|" + getDescription());
        bw.flush();
        bw.newLine();
        bw.close();

    }
}

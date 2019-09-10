import java.io.*;


public class Duke {

    /**
     * Starts a new instance of Duke app.
     *
     * @param directory directory where database files will be at
     */

    public static void run(String directory) {

        Ui newinstance = new Ui();
        newinstance.start();
        Storage dbhandler = new Storage(directory);
        DukeException errorhandler = new DukeException();

        try {

            TaskList listhandler = new TaskList(dbhandler.retrieveData());
            Parser cmdhandler = new Parser(listhandler, newinstance, errorhandler, dbhandler, directory);
            boolean exit = false;

            while (!exit) {
                String input = newinstance.readline();
                exit = cmdhandler.processcmd(input);
            }

        } catch (Exception e) {

            errorhandler.dbError();
            newinstance.close();
        }


    }

    public static void main(String[] args) throws IOException {

        Duke turnOn = new Duke();
        String dataDirectory = "C:\\duke\\src\\main\\data";
        turnOn.run(dataDirectory);

    }

}
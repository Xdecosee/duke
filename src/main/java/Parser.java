import java.util.ArrayList;


public class Parser {

    private TaskList tlist;
    private Ui newinstance;
    private DukeException errorhandler;
    private Storage dbhandler;
    private String directory;

    /**
     * Create an instance on Parser that make sense of user commands and
     * execute relevant operations by calling the relevant handlers
     *
     * @param newinstance handler for user interface
     * @param errorhandler handler for errors
     * @param dbhandler handler for reading and making changes in database
     * @param directory folder to get database files
     * @param tlist Access the copy of task lists
     */
    public Parser(TaskList tlist, Ui newinstance, DukeException errorhandler, Storage dbhandler, String directory) {
        this.tlist = tlist;
        this.newinstance = newinstance;
        this.errorhandler = errorhandler;
        this.dbhandler = dbhandler;
        this.directory = directory;
    }

    /**
     * Make sense of user command and execute relevant operations
     *
     * @param input unprocessed user input
     */
    public boolean processcmd(String input) {

        //Extract command from first word
        String[] breakdown = input.split("\\s+");
        String command = breakdown[0];
        errorhandler = new DukeException(command);

        //Read
        if (command.equals("list")) {
            newinstance.listtasks();
            tlist.printContents();
            return false;
        }
        //Create
        else if (command.equals("todo")) {

            Task t = tlist.addTodo(input, errorhandler, dbhandler);
            if (t != null) {

                newinstance.success(t, tlist.listsize());
            }
            return false;

        }

        //Create
        else if (command.equals("deadline")) {

            Task t = tlist.addDeadline(input, errorhandler, dbhandler);

            if (t != null) {
                newinstance.success(t, tlist.listsize());
            }
            return false;
        }
        //Create
        else if (command.equals("event")) {

            Task t = tlist.addEvent(input, errorhandler, dbhandler);
            if (t != null) {

                newinstance.success(t, tlist.listsize());
            }
            return false;
        }
        //Update
        else if (command.equals("done")) {

            int success = tlist.markDone(input, errorhandler, dbhandler);

            if (success != -1) {

                String status = tlist.getTasklist().get(success - 1).getStatusIcon();
                String desc = tlist.getTasklist().get(success - 1).getDescription();
                newinstance.markDone(status, desc);

            }

            return false;

        } else if (command.equals("delete")) {

            String success = tlist.deleteEntry(input, errorhandler, dbhandler);

            if (success != null) {

                newinstance.deleteEntry(success, tlist.listsize());

            }

            return false;

        } else if (command.equals("find")) {

            ArrayList<Integer> found = tlist.searchEntry(input, errorhandler, dbhandler);
            if (found != null) {

                newinstance.searchEntry(found, tlist.getTasklist());
            }
            return false;

        } else if (command.equals("bye")) {

            newinstance.close();
            return true;

        } else {

            errorhandler.wrongCommand();
            return false;
        }

    }
}

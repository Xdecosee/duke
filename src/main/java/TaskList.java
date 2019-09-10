import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TaskList {


    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public ArrayList<Task> getTasklist() {
        return tasklist;
    }

    public int listsize() {

        return tasklist.size();

    }

    public void printContents() {

        ArrayList<Task> dataCopy = tasklist;

        for (Task p : dataCopy) {
            int index = dataCopy.indexOf(p) + 1;
            System.out.println(index + "." + p.connect());
        }
    }

    public Task addTodo(String input, DukeException errorhandler, Storage dbhandler) {

        try {

            String desc = input.substring(5);

            if (desc.isBlank()) {
                errorhandler.emptyDesc();
                return null;
            }

            Task newtodo = new Todo(desc);
            newtodo.addData(tasklist.size() + 1, dbhandler.getDirectory());
            tasklist.add(newtodo);

            return newtodo;

        } catch (Exception e) {


            errorhandler.emptyDesc();
            return null;

        }

    }

    public String dateformat(String toprocess) throws ParseException {

        SimpleDateFormat inputdt = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat saveddt = new SimpleDateFormat("dd MMMM yyyy , hh:mm a");
        Date date = inputdt.parse(toprocess);
        toprocess = saveddt.format(date);
        return toprocess;

    }

    public Task addDeadline(String input, DukeException errorhandler, Storage dbhandler) {


        try {

            String[] deadbreak = input.split("\\s+/by\\s+");
            String desc = deadbreak[0].substring(9);
            String by = deadbreak[1];

            if (desc.isBlank() && by.isBlank()) {

                errorhandler.emptyDesc();
                return null;

            }

            by = dateformat(by);
            Task newdeadline = new Deadline(desc, by);
            newdeadline.addData(tasklist.size() + 1, dbhandler.getDirectory());
            tasklist.add(newdeadline);

            return newdeadline;

        } catch (Exception e) {

            errorhandler.wrongFormat();
            return null;

        }


    }


    public Task addEvent(String input, DukeException errorhandler, Storage dbhandler) {

        try {

            String[] eventbreak = input.split("\\s+/at\\s+");
            String desc = eventbreak[0].substring(6);
            String at = eventbreak[1];


            if (desc.isBlank() && at.isBlank()) {

                errorhandler.emptyDesc();
                return null;
            }

            at = dateformat(at);
            Task newevent = new Event(desc, at);
            newevent.addData(tasklist.size() + 1, dbhandler.getDirectory());
            tasklist.add(newevent);

            return newevent;

        } catch (Exception e) {

            errorhandler.wrongFormat();
            return null;

        }

    }

    public Integer markDone(String input, DukeException errorhandler, Storage dbhandler) {

        try {

            String complete = input.substring(5);
            Integer index = Integer.valueOf(complete);

            try {

                tasklist.get(index - 1).setDone(true);
                dbhandler.updateRecord(index);
                return index;

            } catch (Exception e) {

                errorhandler.outOfBounds();
                return -1;
            }

        } catch (Exception e) {

            errorhandler.wrongFormat();
            return -1;

        }

    }

    public String deleteEntry(String input, DukeException errorhandler, Storage dbhandler) {

        try {
            String complete = input.substring(7);
            Integer index = Integer.valueOf(complete);

            try {

                Task todelete = tasklist.get(index - 1);
                String output = todelete.connect();
                tasklist.remove(index - 1);
                dbhandler.deleteRecord(index);
                return output;


            } catch (Exception e) {

                errorhandler.outOfBounds();
                return null;
            }


        } catch (Exception e) {

            errorhandler.wrongFormat();
            return null;

        }

    }

    public ArrayList<Integer> searchEntry(String input, DukeException errorhandler, Storage dbhandler) {

        try {
            String term = input.substring(5);

            if (term.isBlank()) {

                errorhandler.wrongFormat();
                return null;
            }

            ArrayList<Integer> found = dbhandler.search(term);
            return found;

        } catch (Exception e) {

            errorhandler.wrongFormat();
            return null;
        }

    }
}

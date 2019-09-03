import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Duke {

    public static void start() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");

    }

    public static ArrayList<Task> retrieveData() throws IOException {

        ArrayList<Task> dataCopy = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader("./src/main/data/DB.txt"));
        String record;

        //While Loop
        while ((record = br.readLine()) != null) {

            //Retrieve from txt file line by line
            StringTokenizer st = new StringTokenizer(record, "|");
            String id = st.nextToken();
            String type = st.nextToken();


            if (type.equals("[T]")) {

                int done = Integer.parseInt(st.nextToken());
                Task todo = new Todo(st.nextToken());
                boolean doneUpdate = (done == 1);
                todo.setDone(doneUpdate);
                dataCopy.add(todo);


            } else if (type.equals("[D]")) {

                int done = Integer.parseInt(st.nextToken());
                Task dl = new Deadline(st.nextToken(), st.nextToken());
                boolean doneUpdate = (done == 1);
                dl.setDone(doneUpdate);
                dataCopy.add(dl);


            } else if (type.equals("[E]")) {

                int done = Integer.parseInt(st.nextToken());
                Task event = new Event(st.nextToken(), st.nextToken());
                boolean doneUpdate = (done == 1);
                event.setDone(doneUpdate);
                dataCopy.add(event);

            }

        }

        br.close();
        return dataCopy;

    }

    public static void updateRecord(int id) throws IOException {

        File db = new File("./src/main/data/DB.txt");
        File tempDB = new File("./src/main/data/tempdb.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        String record;

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, "|");
            int savedid = Integer.parseInt(st.nextToken());

            if (savedid == id) {

                String first = id + "|" + st.nextToken() + "|" + 1;
                String oldbool = st.nextToken();
                while (st.hasMoreTokens()) {

                    first = first + "|" + st.nextToken();
                }
                bw.write(first);

            } else {

                bw.write(record);
            }
            bw.flush();
            bw.newLine();

        }

        bw.close();
        br.close();

        db.delete();
        tempDB.renameTo(new File("./src/main/data/DB.txt"));

    }

    public static void deleteRecord(int id) throws IOException {

        File db = new File("./src/main/data/DB.txt");
        File tempDB = new File("./src/main/data/tempdb.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        String record;
        int index = 1;

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, "|");
            int savedid = Integer.parseInt(st.nextToken());

            if (savedid == id) {

                continue;

            }

            String change = Integer.toString(index);

            while (st.hasMoreTokens()) {

                change = change + "|" + st.nextToken();
            }

            bw.write(change);
            bw.flush();
            bw.newLine();
            index += 1;

        }


        bw.close();
        br.close();

        db.delete();
        tempDB.renameTo(new File("./src/main/data/DB.txt"));

    }

    public static ArrayList<Integer> search(String term) throws IOException {

        File db = new File("./src/main/data/DB.txt");
        ArrayList<Integer> found = new ArrayList<Integer>();

        BufferedReader br = new BufferedReader(new FileReader(db));

        String record;

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, "|");
            int savedid = Integer.parseInt(st.nextToken());
            String type = st.nextToken();
            String oldbool = st.nextToken();
            while (st.hasMoreTokens()) {

                if(st.nextToken().contains(term)){
                    found.add(savedid);
                    break;
                }
            }

        }

        br.close();
        return found;

    }


    public static void main(String[] args) throws IOException {

        //Init Set Up
        start();
        ArrayList<Task> dataCopy = retrieveData();
        Scanner echotext = new Scanner(System.in);
        DukeException error = new DukeException();
        String input = echotext.nextLine();
        String command;
        SimpleDateFormat inputdt = new SimpleDateFormat("dd/MM/yyyy HHmm");
        SimpleDateFormat saveddt = new SimpleDateFormat("dd MMMM yyyy , hh:mm a");

        while (!input.equals("bye")) {

            //Extract command from first word
            String[] breakdown = input.split("\\s+");
            command = breakdown[0];


            //Read
            if (command.equals("list")) {

                System.out.println("Here are the tasks in your list:");

                for (Task p : dataCopy) {
                    int index = dataCopy.indexOf(p) + 1;
                    System.out.println(index + "." + p.connect());
                }

            }
            //Create
            else if (command.equals("todo")) {


                try {

                    String desc = input.substring(5);

                    if (desc.isBlank()) {

                        error.emptyDesc(command);
                        input = echotext.nextLine();
                        continue;
                    }
                    Task newtodo = new Todo(desc);
                    newtodo.addData(dataCopy.size() + 1);
                    dataCopy.add(newtodo);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newtodo.connect());
                    System.out.println("Now you have " + dataCopy.size() + " tasks in the list.");

                } catch (Exception e) {

                    error.emptyDesc(command);

                }

            }

            //Create
            else if (command.equals("deadline")) {

                try {

                    String[] deadbreak = input.split("\\s+/by\\s+");
                    String desc = deadbreak[0].substring(9);
                    String by = deadbreak[1];

                    if (desc.isBlank() && by.isBlank()) {

                        error.emptyDesc(command);
                        input = echotext.nextLine();
                        continue;
                    }


                    Date date = inputdt.parse(by);
                    by = saveddt.format(date);

                    Task newdeadline = new Deadline(desc, by);
                    newdeadline.addData(dataCopy.size() + 1);
                    dataCopy.add(newdeadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newdeadline.connect());
                    System.out.println("Now you have " + dataCopy.size() + " tasks in the list.");

                } catch (Exception e) {

                    error.wrongFormat(command);

                }


            }
            //Create
            else if (command.equals("event")) {

                try {

                    String[] eventbreak = input.split("\\s+/at\\s+");
                    String desc = eventbreak[0].substring(6);
                    String at = eventbreak[1];


                    if (desc.isBlank() && at.isBlank()) {

                        error.emptyDesc(command);
                        input = echotext.nextLine();
                        continue;
                    }

                    Date date = inputdt.parse(at);
                    at = saveddt.format(date);

                    Task newevent = new Event(desc, at);
                    newevent.addData(dataCopy.size() + 1);
                    dataCopy.add(newevent);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newevent.connect());
                    System.out.println("Now you have " + dataCopy.size() + " tasks in the list.");

                } catch (Exception e) {

                    error.wrongFormat(command);

                }

            }
            //Update
            else if (command.equals("done")) {


                try {
                    String complete = input.substring(5);
                    Integer index = Integer.valueOf(complete);

                    try {

                        dataCopy.get(index - 1).setDone(true);
                        updateRecord(index);

                    } catch (Exception e) {

                        error.outOfBounds();
                        input = echotext.nextLine();
                        continue;

                    }

                    String done = dataCopy.get(index - 1).getStatusIcon();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(" [" + done + "] " + dataCopy.get(index - 1).getDescription());

                } catch (Exception e) {

                    error.wrongFormat(command);

                }

            } else if (command.equals("delete")) {

                try {
                    String complete = input.substring(7);
                    Integer index = Integer.valueOf(complete);

                    try {

                        Task todelete = dataCopy.get(index - 1);
                        String output = todelete.connect();
                        dataCopy.remove(index - 1);
                        deleteRecord(index);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(output);
                        System.out.println("Now you have " + dataCopy.size() + " tasks in the list.");

                    } catch (Exception e) {

                        error.outOfBounds();
                        input = echotext.nextLine();
                        continue;
                    }


                } catch (Exception e) {

                    error.wrongFormat(command);

                }


            } else if (command.equals("find")) {

                try {
                    String term = input.substring(5);

                    if (term.isBlank()) {

                        error.wrongFormat(command);
                        input = echotext.nextLine();
                        continue;
                    }

                    ArrayList<Integer> found = search(term);

                    System.out.println(" Here are the matching tasks in your list:");

                    if (found.size() != 0){

                        for (Integer i : found) {

                            Task toprint = dataCopy.get(i - 1);
                            String output = i + "." + toprint.connect();
                            System.out.println(output);

                        }

                    }


                } catch (Exception e) {

                    error.wrongFormat(command);

                }

            } else {

                error.wrongCommand();
            }


            input = echotext.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
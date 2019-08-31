import java.util.*;

public class Duke {

    public static void main(String[] args) {

        //Start
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner echotext = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        DukeException error = new DukeException();

        //Init Input
        ArrayList<Task> inputlist = new ArrayList<Task>();
        String input = echotext.nextLine();
        String command;

        while (!input.equals("bye")) {

            //Extract command from first word
            String[] breakdown = input.split("\\s+");
            command = breakdown[0];


            if (command.equals("todo")) {


                try {

                    String desc = input.substring(5);

                    if (desc.isBlank()) {

                        error.emptyDesc(command);
                        input = echotext.nextLine();
                        continue;
                    }
                    Task newtodo = new Todo(desc);
                    inputlist.add(newtodo);
                    System.out.println(newtodo.connect());
                    System.out.println("Now you have " + inputlist.size() + " tasks in the list.");

                } catch (Exception e) {

                    error.emptyDesc(command);

                }

            } else if (command.equals("list")) {

                System.out.println("Here are the tasks in your list:");

                for (Task p : inputlist) {

                    int index = inputlist.indexOf(p) + 1;
                    System.out.println(index + p.connect());
                }

            } else if (command.equals("deadline")) {

                try {

                    String[] deadbreak = input.split("\\s+/by\\s+");
                    String desc = deadbreak[0];
                    String by = deadbreak[1];

                    if (desc.isBlank() && by.isBlank()) {

                        error.emptyDesc(command);
                        input = echotext.nextLine();
                        continue;
                    }

                    Task newdeadline = new Deadline(desc, by);
                    inputlist.add(newdeadline);
                    System.out.println(newdeadline.connect());
                    System.out.println("Now you have " + inputlist.size() + " tasks in the list.");

                } catch (Exception e) {

                    error.wrongFormat(command);

                }


            } else if (command.equals("event")) {

                try {

                    String[] eventbreak = input.split("\\s+/at\\s+");
                    String desc = eventbreak[0];
                    String at = eventbreak[1];


                    if (desc.isBlank() && at.isBlank()) {

                        error.emptyDesc(command);
                        input = echotext.nextLine();
                        continue;
                    }

                    Task newdeadline = new Deadline(desc, at);
                    inputlist.add(newdeadline);
                    System.out.println(newdeadline.connect());
                    System.out.println("Now you have " + inputlist.size() + " tasks in the list.");

                } catch (Exception e) {

                    error.wrongFormat(command);

                }

            } else if (command.equals("done")) {


                try {
                    String complete = input.substring(5);
                    Integer index = Integer.valueOf(complete);

                    try {

                        inputlist.get(index - 1).setDone(true);

                    } catch (Exception e) {

                        error.outOfBounds();
                        input = echotext.nextLine();
                        continue;

                    }

                    String done = inputlist.get(index - 1).getStatusIcon();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(" [" + done + "] " + inputlist.get(index - 1).getDescription());

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
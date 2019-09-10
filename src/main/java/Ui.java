import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner echotext;

    public Ui() {
        this.echotext = new Scanner(System.in);
    }

    public String readline() {

        return echotext.nextLine();

    }

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

    public static void close() {

        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void listtasks() {

        System.out.println("Here are the tasks in your list:");

    }

    public static void success(Task t, int size) {

        System.out.println("Got it. I've added this task: ");
        System.out.println(t.connect());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void markDone(String done, String desc) {

        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(" [" + done + "] " + desc);
    }

    public static void deleteEntry(String output, int size) {

        System.out.println("Noted. I've removed this task:");
        System.out.println(output);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public static void searchEntry(ArrayList<Integer> found, ArrayList<Task> tasklist) {

        System.out.println(" Here are the matching tasks in your list:");

        if (found.size() != 0) {

            for (Integer i : found) {

                Task toprint = tasklist.get(i - 1);
                String output = i + "." + toprint.connect();
                System.out.println(output);

            }

        }
    }
}

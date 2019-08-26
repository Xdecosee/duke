import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner echotext = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String input = echotext.nextLine();
        while(!input.equals("bye")){

            System.out.println(input);
            input = echotext.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}

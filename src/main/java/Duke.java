import java.util.*;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner echotext = new Scanner(System.in);
        ArrayList<Task> inputlist = new ArrayList<Task>();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String input = echotext.nextLine();
        while(!input.equals("bye")){

            if(input.equals("list")) {

                System.out.println("Here are the tasks in your list:");

                for (Task p : inputlist) {

                    int index = inputlist.indexOf(p) + 1;
                    String done = p.getStatusIcon();
                    System.out.println(index + ".[" + done + "] " + p.getDescription());

                }
            }
            else if (input.length() > 4 && input.substring(0 , 4).equals("done")){

                String complete = input.substring(5);
                System.out.println("Nice! I've marked this task as done: ");

                try{
                    Integer index = Integer.valueOf(complete);
                    inputlist.get(index-1).setDone(true);
                    String done = inputlist.get(index-1).getStatusIcon();
                    System.out.println(" [" + done + "] " + inputlist.get(index-1).getDescription());

                }catch(Exception e){

                    System.out.println("Error with input for done command");
                }
            }
            else{

                Task newtask = new Task(input);
                inputlist.add(newtask);
                System.out.println("added: " + input);
            }

            input = echotext.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
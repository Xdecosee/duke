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

        //Init Input
        ArrayList<Task> inputlist = new ArrayList<Task>();
        String input = echotext.nextLine();
        String command;

        while(!input.equals("bye")){

            //Extract command from first word
            String[] breakdown = input.split("\\s+");
            command = breakdown[0];


            if(command.equals("todo")){

                try {
                    String desc = input.substring(5);
                    //if(desc.length() == 0){

                       // #System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                       // continue;
                    //}
                    Task newtodo = new Todo(desc);
                    inputlist.add(newtodo);
                    System.out.println(newtodo.connect());
                    System.out.println("Now you have " + inputlist.size() + " tasks in the list.");
                }catch(Exception e){

                    System.out.println("☹ OOPS!!! The format of your input is wrong. Please type 'todo <desc> ' (e.g. todo buy book");
                }

            }
            else if(command.equals("list")){

                System.out.println("Here are the tasks in your list:");

                for (Task p : inputlist) {

                    int index = inputlist.indexOf(p) + 1;
                    System.out.println(index + p.connect());
                }

            }
            else if(command.equals("deadline")){
                try {
                String[] deadbreak = input.split("\\s+/by\\s+");
                String desc = deadbreak[0];
                String by = deadbreak[1];
                Task newdeadline = new Deadline(desc, by);
                inputlist.add(newdeadline);
                System.out.println(newdeadline.connect());
                System.out.println("Now you have " + inputlist.size() + " tasks in the list.");
                }catch(Exception e){

                    System.out.println("☹ OOPS!!! The format of your input is wrong. Please type 'deadline <desc> /by <date/time>' (e.g. deadline return book /by Sunday)");
                }


            }
            else if(command.equals("event")){

                try {
                String[] eventbreak = input.split("\\s+/at\\s+");
                String desc = eventbreak[0];
                String at = eventbreak[1];
                Task newdeadline = new Deadline(desc, at);
                inputlist.add(newdeadline);
                System.out.println(newdeadline.connect());
                System.out.println("Now you have " + inputlist.size() + " tasks in the list.");
                }catch(Exception e){

                    System.out.println("☹ OOPS!!! The format of your input is wrong. Please type 'event <desc> /at <date/time>' (e.g. event project meeting /at Mon 2-4pm)");
                }
            }
            else if(command.equals("done")){

                try {
                    String complete = input.substring(5);
                    Integer index = Integer.valueOf(complete);
                    inputlist.get(index-1).setDone(true);
                    String done = inputlist.get(index-1).getStatusIcon();
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(" [" + done + "] " + inputlist.get(index-1).getDescription());

                }catch(Exception e){

                System.out.println("☹ OOPS!!! The format of your input is wrong. Please type 'done <index of task>' (e.g. done 4) ");
            }

            }
            else{

                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }



            input = echotext.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");


    }
}
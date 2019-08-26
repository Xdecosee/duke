import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner echotext = new Scanner(System.in);
        ArrayList<String> inputlist = new ArrayList<String>();
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String input = echotext.nextLine();
        while(!input.equals("bye")){

            if(input.equals("list")){

                for(int i = 0; i < inputlist.size(); i++){

                    int index = i + 1;
                    System.out.println(index + ". " + inputlist.get(i));
                }
            }
            else{

                inputlist.add(input);
                System.out.println("added: " + input);
            }

            input = echotext.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}

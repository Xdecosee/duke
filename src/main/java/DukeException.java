public class DukeException {

    public void emptyDesc(String type){

        System.out.println("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }

    public void wrongCommand(){

        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void wrongFormat(String type){

        System.out.println("☹ OOPS!!! Wrong format for " + type);
    }

    public void outOfBounds(){

        System.out.println("☹ OOPS!!! Choose a number within the list!");
    }

}

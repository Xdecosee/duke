public class DukeException {

    private String type;

    public DukeException() {
        this.type = null;

    }

    public DukeException(String type) {
        this.type = type;

    }

    public void emptyDesc() {

        System.out.println("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }

    public void wrongCommand() {

        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void wrongFormat() {

        System.out.println("☹ OOPS!!! Wrong format for " + type);
    }

    public void outOfBounds() {

        System.out.println("☹ OOPS!!! Choose a number within the list!");
    }


    public void dbError() {

        System.out.println("☹ OOPS!!! Error accessing db!");
    }
}

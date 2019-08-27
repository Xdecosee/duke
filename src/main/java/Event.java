public class Event extends Task{

    private String at;

    public Event(String description) {
        super(description);
        this.at = at;
    }

    @Override
    public String connect(){

        return "[E]" + super.connect() + " (at: " + at+ ")";

    }
}

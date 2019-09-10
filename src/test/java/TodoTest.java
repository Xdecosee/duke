import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void newTodo(){

        Todo newentry = new Todo("test");
        newentry.setDone(true);
        String output = newentry.connect();


        assertEquals("[T][\u2713] test", output);
    }
}

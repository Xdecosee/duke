import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Storage {

    private String directory;

    public Storage(String directory) {
        this.directory = directory;
    }

    public String getDirectory() {
        return directory;
    }

    /**
     * Retrieve copy of data at start up
     *
     * @throws IOException if database error
     */
    public ArrayList<Task> retrieveData() throws IOException {

        ArrayList<Task> dataCopy = new ArrayList<Task>();
        BufferedReader br = new BufferedReader(new FileReader(directory + "\\DB.txt"));
        String record;

        //While Loop
        while ((record = br.readLine()) != null) {

            //Retrieve from txt file line by line
            StringTokenizer st = new StringTokenizer(record, "|");
            String id = st.nextToken();
            String type = st.nextToken();


            if (type.equals("[T]")) {

                int done = Integer.parseInt(st.nextToken());
                Task todo = new Todo(st.nextToken());
                boolean doneUpdate = (done == 1);
                todo.setDone(doneUpdate);
                dataCopy.add(todo);


            } else if (type.equals("[D]")) {

                int done = Integer.parseInt(st.nextToken());
                Task dl = new Deadline(st.nextToken(), st.nextToken());
                boolean doneUpdate = (done == 1);
                dl.setDone(doneUpdate);
                dataCopy.add(dl);


            } else if (type.equals("[E]")) {

                int done = Integer.parseInt(st.nextToken());
                Task event = new Event(st.nextToken(), st.nextToken());
                boolean doneUpdate = (done == 1);
                event.setDone(doneUpdate);
                dataCopy.add(event);

            }

        }

        br.close();
        return dataCopy;

    }

    /**
     * Update record
     *
     * @id id of record to be updated
     * @throws IOException if database error
     */
    public void updateRecord(int id) throws IOException {

        File db = new File(directory + "\\DB.txt");
        File tempDB = new File(directory + "\\tempDB.txt");

        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        String record;

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, "|");
            int savedid = Integer.parseInt(st.nextToken());

            if (savedid == id) {

                String first = id + "|" + st.nextToken() + "|" + 1;
                String oldbool = st.nextToken();
                while (st.hasMoreTokens()) {

                    first = first + "|" + st.nextToken();
                }
                bw.write(first);

            } else {

                bw.write(record);
            }
            bw.flush();
            bw.newLine();

        }

        bw.close();
        br.close();

        db.delete();
        tempDB.renameTo(new File(directory + "\\DB.txt"));

    }

    /**
     * delete record
     *
     * @id id of record to be deleted
     * @throws IOException if database error
     */
    public void deleteRecord(int id) throws IOException {

        File db = new File(directory + "\\DB.txt");
        File tempDB = new File(directory + "\\tempDB.txt");


        BufferedReader br = new BufferedReader(new FileReader(db));
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempDB));

        String record;
        int index = 1;

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, "|");
            int savedid = Integer.parseInt(st.nextToken());

            if (savedid == id) {

                continue;

            }

            String change = Integer.toString(index);

            while (st.hasMoreTokens()) {

                change = change + "|" + st.nextToken();
            }

            bw.write(change);
            bw.flush();
            bw.newLine();
            index += 1;

        }


        bw.close();
        br.close();

        db.delete();
        tempDB.renameTo(new File(directory + "\\DB.txt"));
    }

    /**
     * Search record
     *
     * @param term to search for among list of tasks
     * @throws IOException if database error
     */
    public ArrayList<Integer> search(String term) throws IOException {

        File db = new File(directory + "\\DB.txt");
        ArrayList<Integer> found = new ArrayList<Integer>();

        BufferedReader br = new BufferedReader(new FileReader(db));

        String record;

        while ((record = br.readLine()) != null) {

            StringTokenizer st = new StringTokenizer(record, "|");
            int savedid = Integer.parseInt(st.nextToken());
            String type = st.nextToken();
            String oldbool = st.nextToken();
            while (st.hasMoreTokens()) {

                if (st.nextToken().contains(term)) {
                    found.add(savedid);
                    break;
                }
            }

        }

        br.close();
        return found;

    }

}

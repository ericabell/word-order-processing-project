import java.util.HashMap;
import java.util.HashSet;

public class Processor implements Runnable {

    HashMap<Status, HashSet<WorkOrder>> orders = new HashMap<Status, HashSet<WorkOrder>>();

    public void processWorkOrders() {
        System.out.println("Begin processWorkOrders");

        // always processing
        while(true) {
            printOrders();
            //moveIt();
            //readIt();

            // delay of 5 seconds betweeen processing
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printOrders() {
        // print each order for each of the statuses
        for( Status s : orders.keySet() ) {
            System.out.println(s + " orders");
        }
    }

    private void moveIt() {
        // move work orders in map from one state to another
    }

    private void readIt() {
        // read the json files into WorkOrders and put in map
    }

    public void run() {
        Processor processor = new Processor();
        processor.processWorkOrders();
    }

}

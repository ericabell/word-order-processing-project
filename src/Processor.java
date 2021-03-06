import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Processor implements Runnable {

    HashSet<WorkOrder> orders = new HashSet<WorkOrder>();

    public void processWorkOrders() {
        System.out.println("Begin processWorkOrders");

        // always processing
        while(true) {
            System.out.println("Checking...");
            // print out the map of current work orders (everything except the unprocessed files)
            printOrders();
            // read in the new json files and create objects in our orders HashSet
            readIt();


            // loop through all the orders and increment their status,
            moveIt();

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
        System.out.println("CURRENT WORK ORDERS IN PROCESSOR:");
        for(WorkOrder order : orders) {
            System.out.println(order);
        }
    }

    private void moveIt() {
        // move work orders in map from one state to another
        // INITIAL -> ASSIGNED -> IN_PROGRESS -> DONE

        // loop through the orders
        for( WorkOrder order : orders ) {
            if( order.getStatus().equals(Status.IN_PROGRESS) ) {
                order.setStatus(Status.DONE);
            }
            if( order.getStatus().equals(Status.ASSIGNED) ) {
                order.setStatus(Status.IN_PROGRESS);
            }
            if( order.getStatus().equals(Status.INITIAL) ) {
                order.setStatus(Status.ASSIGNED);
            }


        }
    }

    private void readIt() {
        File file = new File(".");

        // read the json files into WorkOrders and put in map, then delete the json file
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                // Now you have a File object named "f".
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    WorkOrder order = mapper.readValue(f, WorkOrder.class);

                    System.out.println("NEW ORDER FOUND!");
                    System.out.println(order);

                    // delete the file once it has been added to our orders
                    f.delete();
                    orders.add(order);
                } catch (FileNotFoundException ex) {
                    System.out.println("Could not find file *" + f + "*");
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    public void run() {
        Processor processor = new Processor();
        processor.processWorkOrders();
    }

}

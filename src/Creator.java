import java.util.Scanner;

public class Creator implements Runnable {
    public void createWorkOrders() {
        // read input, create work orders and write as json files
        Scanner scanner = new Scanner(System.in);

        System.out.println("****************************************");
        System.out.println("Please enter an id for the new workorder");
        int newId = Integer.parseInt(scanner.nextLine());

        System.out.println("Please enter a new workorder description");
        String newDescription = scanner.nextLine();

        System.out.println("Please enter the sender name");
        String newSender = scanner.nextLine();

        // create our new workorder
        WorkOrder newWorkOrder = new WorkOrder(newId, newDescription, newSender);

        // write to file
        newWorkOrder.saveToJSON();
    }

    public void run() {
        Creator creator = new Creator();
        while(true) {
            creator.createWorkOrders();
        }

    }

}
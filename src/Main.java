public class Main {

    // kick off the creator and processor in separate threads

    public static void main(String args[]) {
        (new Thread(new Creator())).start();
        (new Thread(new Processor())).start();
    }
}

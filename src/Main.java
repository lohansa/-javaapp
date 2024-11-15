import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.initialize();

        // Initialize the ticket pool with max capacity from the config
        TicketPool.getInstance(config.getMaxTicketCapacity());

        // Start vendor threads
        List<Thread> vendorThreads = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Vendor vendor = new Vendor("Vendor" + (i + 1), config.getTicketReleaseRate(), config.getTotalTickets());
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        // Start customer threads
        List<Thread> customerThreads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Customer customer = new Customer("Customer" + (i + 1), config.getCustomerRetrievalRate());
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread);
            customerThread.start();
        }

        // Wait for all threads to complete
        try {
            for (Thread vendorThread : vendorThreads) {
                vendorThread.join();
            }
            for (Thread customerThread : customerThreads) {
                customerThread.join();
            }
        } catch (InterruptedException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        System.out.println("Simulation completed.");
    }
}

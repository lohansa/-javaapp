import java.util.Scanner;

// Configuration class to collect initial settings via CLI
public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Prompt for user input to initialize settings
    public void initialize() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of tickets: ");
        totalTickets = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter ticket release rate (ms between releases): ");
        ticketReleaseRate = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter customer retrieval rate (ms between purchases): ");
        customerRetrievalRate = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter maximum ticket capacity in the pool: ");
        maxTicketCapacity = Integer.parseInt(scanner.nextLine());

        scanner.close();
    }

    // Getters to access configuration values
    public int getTotalTickets() { return totalTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
}


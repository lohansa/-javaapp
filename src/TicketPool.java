import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Singleton TicketPool class for thread-safe ticket management
public class TicketPool {
    private static TicketPool instance;
    private final List<EventTicket> freshTickets;
    private final List<EventTicket> soldTickets;
    private final int maxTicketCapacity;

    private TicketPool(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        freshTickets = Collections.synchronizedList(new ArrayList<>());
        soldTickets = Collections.synchronizedList(new ArrayList<>());
    }

    // Singleton pattern to ensure only one instance exists
    public static synchronized TicketPool getInstance(int maxTicketCapacity) {
        if (instance == null) {
            instance = new TicketPool(maxTicketCapacity);
        }
        return instance;
    }

    // Adds tickets to the pool with capacity check
    public synchronized void addTickets(Event event) {
        if (freshTickets.size() + event.getRemainingTicketCount() > maxTicketCapacity) {
            System.out.println("Ticket pool at capacity, cannot add more tickets.");
            return;
        }
        EventTicket[] tickets = event.issueTickets();
        Collections.addAll(freshTickets, tickets);
        System.out.println("Tickets added to pool for event: " + event.getName());
    }

    // Sells a random available ticket to a customer
    public synchronized EventTicket buyRandomTicket(Customer customer) {
        if (freshTickets.isEmpty()) {
            return null;
        }
        int randomTicket = (int) (Math.random() * freshTickets.size());
        EventTicket ticket = freshTickets.remove(randomTicket);
        ticket.sellTicket(customer);
        soldTickets.add(ticket);
        return ticket;
    }

    // Returns count of available tickets
    public int getAvailableTicketCount() { return freshTickets.size(); }
}

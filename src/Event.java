// Represents an event that can issue tickets
public class Event {
    private final String name;
    private final String date;
    private final Vendor vendor;
    private int ticketCount;
    private final int price;

    // Constructor initializes event properties
    public Event(String name, String date, Vendor vendor, int ticketCount, int price) {
        this.name = name;
        this.date = date;
        this.vendor = vendor;
        this.ticketCount = ticketCount;
        this.price = price;
    }

    // Issues tickets for the event and returns them in an array
    public EventTicket[] issueTickets() {
        EventTicket[] tickets = new EventTicket[ticketCount];
        for (int i = 0; i < ticketCount; i++) {
            tickets[i] = new EventTicket(this, price);
        }
        ticketCount = 0;  // Reset ticket count after issuing
        return tickets;
    }

    // Getters for event details
    public String getName() { return name; }
    public String getDate() { return date; }
    public Vendor getVendor() { return vendor; }
    public int getRemainingTicketCount() { return ticketCount; }
}

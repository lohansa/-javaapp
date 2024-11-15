// Represents a ticket for an event, with information about the event and its ownership
public class EventTicket {
    private final Event event;
    private Customer customer;
    private final int price;
    private boolean isSold = false;

    // Constructor initializes the ticket with its event and price
    public EventTicket(Event event, int price) {
        this.event = event;
        this.price = price;
    }

    // Method to mark the ticket as sold to a specific customer
    public void sellTicket(Customer customer) {
        if (isSold) {
            throw new IllegalStateException("Ticket is already sold");
        }
        this.customer = customer;
        isSold = true;
    }

    // Getters for ticket information
    public Event getEvent() { return event; }
    public Customer getCustomer() { return customer; }
    public int getPrice() { return price; }
    public boolean isSold() { return isSold; }
}

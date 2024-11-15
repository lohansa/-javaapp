import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable {
    private final String name;
    private final int retrievalRate;
    private final List<EventTicket> pocket;

    public Customer(String name, int retrievalRate) {
        this.name = name;
        this.retrievalRate = retrievalRate;
        this.pocket = new ArrayList<>();
    }

    private boolean buyTicket() {
        TicketPool ticketPool = TicketPool.getInstance(100);
        EventTicket ticket = ticketPool.buyRandomTicket(this);

        if (ticket == null) {
            System.out.println("No tickets available for " + name);
            return false;
        }
        pocket.add(ticket);
        System.out.println(name + " bought ticket for " + ticket.getEvent().getName());
        return true;
    }

    @Override
    public void run() {
        while (pocket.size() < 5) {
            if (!buyTicket()) {
                try {
                    Thread.sleep(retrievalRate);
                } catch (InterruptedException e) {
                    //noinspection CallToPrintStackTrace
                    e.printStackTrace();
                }
            }
        }
        System.out.println(name + " has filled their pocket and is leaving.");
    }
}

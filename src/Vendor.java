public class Vendor implements Runnable {
    private final String name;
    private final int ticketReleaseRate;
    private final int totalTickets;
    private int ticketsAdded = 0;

    public Vendor(String name, int ticketReleaseRate, int totalTickets) {
        this.name = name;
        this.ticketReleaseRate = ticketReleaseRate;
        this.totalTickets = totalTickets;
    }

    // Vendor creates events and adds tickets to the pool
    private void releaseTickets() {
        TicketPool ticketPool = TicketPool.getInstance(100);
        while (ticketsAdded < totalTickets) {
            Event event = new Event("Event by " + name, "2024-12-31", this, 10, 100);
            ticketPool.addTickets(event);
            ticketsAdded += 10;
            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        releaseTickets();
        System.out.println("Vendor " + name + " has finished releasing tickets.");
    }
}

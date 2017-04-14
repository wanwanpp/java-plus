package proxy.推演.demo;

public abstract class TicketSeller {

    protected String theme;

    protected TicketSeller(String theme) {
        this.theme = theme;
    }

    public String getTicketTheme() {
        return this.theme;
    }

    public void setTicketTheme(String theme) {
        this.theme = theme;
    }

    public abstract int getTicketPrice();

    public abstract int buy(int ticketNumber, int money) throws Exception;
}
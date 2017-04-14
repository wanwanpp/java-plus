package proxy.推演.demo;

public class Expo2010TicketSeller extends TicketSeller {
    protected int price;
    protected int numTicketForSale;

    public Expo2010TicketSeller() {
        super("World Expo 2010");
        this.price = 180;
        this.numTicketForSale = 200;
    }

    public int getTicketPrice() {
        return price;
    }

    public  int buy(int ticketNumber, int money) throws Exception {
        if (ticketNumber > numTicketForSale) {
            throw new Exception("There is no enough ticket available for sale, only "
                    + numTicketForSale + " ticket(s) left");
        }
        int charge = money - ticketNumber * price;
        if (charge < 0) {
            throw new Exception("Money is not enough. Still needs "
                    + (-charge) + " RMB.");
        }
        numTicketForSale -= ticketNumber;
        return charge;
    }
}
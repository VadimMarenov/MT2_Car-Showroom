public class Buyer extends Thread {
    Dealer dealer;

    public Buyer(Dealer dealer, String name) {
        super(name);
        this.dealer = dealer;
    }

    @Override
    public void run() {
        dealer.sellCar();
    }
}

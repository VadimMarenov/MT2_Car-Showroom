public class Manufacturer extends Thread{
    Dealer dealer;

    public Manufacturer(Dealer dealer, String name) {
        super(name);
        this.dealer = dealer;
    }

    @Override
    public void run() {
        dealer.recieveCar();
    }
}

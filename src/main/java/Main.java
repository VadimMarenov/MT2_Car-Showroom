public class Main {
    static final int COUNT_OF_BUYERS = 10;
    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        for (int i = 1; i <= COUNT_OF_BUYERS; i++) {
            new Buyer(dealer, "Buyer " + i).start();
        }
        new Manufacturer(dealer, "Manufacturer TOYOTA").start();
    }
}

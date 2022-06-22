import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Dealer {

    static final int BUILD_TIME = 2000;
    static final int PURCHASE_TIME = 1000;
    static final int COUNT_OF_CARS = 10;
    List<Car> cars = new ArrayList<>();
    ReentrantLock sellLock = new ReentrantLock(true);
    Condition sellCondition = sellLock.newCondition();

    public void sellCar() {
        try {
            sellLock.lock();
            System.out.println(Thread.currentThread().getName() + " went to a car dealership");
            while (cars.size() == 0) {
                System.out.println("There aren't any car");
                sellCondition.await();
            }
            Thread.sleep(PURCHASE_TIME);
            System.out.println(Thread.currentThread().getName() + " left in a brand new car");
            cars.remove(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            sellLock.unlock();
        }
    }

    public void recieveCar() {
        for (int i = 0; i < COUNT_OF_CARS; i++) {
            try {
                Thread.sleep(BUILD_TIME);
                cars.add(new Car());
                System.out.println(Thread.currentThread().getName() + " produced a new car");
                sellLock.lock();
                sellCondition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                sellLock.unlock();
            }
        }
    }

}

import java.util.ArrayList;
import java.util.List;

public class Dealer {

    static final int BUILD_TIME = 2000;
    static final int PURCHASE_TIME = 1000;
    static final int COUNT_OF_CARS = 10;
    List<Car> cars = new ArrayList<>();

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            if (cars.size() == 0) {
                System.out.println("Машин нет");
                    wait();

                Thread.sleep(PURCHASE_TIME);
                System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
                cars.remove(0);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void recieveCar() {
        for (int i = 0; i < COUNT_OF_CARS; i++) {
            try {
                Thread.sleep(BUILD_TIME);
                cars.add(new Car());
                System.out.print(Thread.currentThread().getName() + " выпустил автомобиль");
                synchronized (this) {
                    notify();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
        }
    }


}

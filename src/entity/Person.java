package entity;

/**
 * Created by Administrator on 2020/12/2.
 */
public class Person {

    String name;
    Car car;

    public Person(String name, double price) {
        this.name = name;
        this.car = new Car(price);
    }

    class Car{
        double price;

        public Car(double price) {
            this.price = price;
        }
    }

}

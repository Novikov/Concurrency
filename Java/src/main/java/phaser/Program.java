package phaser;

import java.util.concurrent.Phaser;

/**
 * Phasers нужны когда необходимо выполнить пофазно некоторую работу с помощью нескольких потоков.
 * Например помыть мышину. Один мойщик моет Корпус, другой салон, а гаражное место одно.
 * Пока они оба не закончат - не могут приступить к следующей машине.
 * */

public class Program {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(2);
        Car [] cars = {new Car("Chaser"),new Car("Mark 2"),new Car("Subaru")};
        new BodyWasher(phaser,cars);
        new SalonWasher(phaser,cars);
    }

    static class BodyWasher extends Thread{
        Phaser phaser;
        Car[] cars;

        public BodyWasher(Phaser phaser, Car[] cars) {
            this.phaser = phaser;
            this.cars = cars;
            start();
        }

        @Override
        public void run() {
            for (Car car : cars) {
                System.out.println("BodyWasher washes body of - " + car.model);
                phaser.arriveAndAwaitAdvance();
            }
        }
    }

    static class SalonWasher extends Thread{
        Phaser phaser;
        Car[] cars;

        public SalonWasher(Phaser phaser,Car[] cars) {
            this.phaser = phaser;
            this.cars = cars;
            start();
        }

        @Override
        public void run() {
            for (Car car : cars) {
                System.out.println("SalonWasher washes salon of - " + car.model);
                phaser.arriveAndAwaitAdvance();
            }
        }
    }

    static class Car {
        String model;

        public Car(String model) {
            this.model = model;
        }
    }
}

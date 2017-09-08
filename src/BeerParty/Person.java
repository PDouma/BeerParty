package BeerParty;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Person implements Runnable {
    private int beerLimit;
    private String name;
    private int drinkSpeed;
    private Beerkeg beerkeg;
    private Boolean glassFilled = false;

    public Person(Beerkeg beerkeg) {
        this.generateBeerLimit();
        this.generateName();
        this.generateDrinkSpeed();
        this.beerkeg = beerkeg;
    }

    private void generateBeerLimit() {
        Random rand = new Random();
        this.beerLimit = rand.nextInt(30);
    }

    private void generateName() {
        Random rand = new Random();
        this.name = this.generateRandomString(rand.nextInt(12) + 3);
    }

    private synchronized void tap() {
        this.beerkeg.tap();
        this.glassFilled = true;
        System.out.println(this.name + " tapped a beer!");
    }

    private void drink() {
        this.beerLimit--;
        try {
            System.out.println(this.name + " drank for " + this.drinkSpeed);
            this.glassFilled = false;
            Thread.sleep(this.drinkSpeed);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void run() {
        while (this.getBeerLimit() > 0) {
            if (this.beerkeg.getLiters() < 1) {
                return;
            }
            if (!this.glassFilled) {
                this.tap();
            }
            if (this.glassFilled) {
                this.drink();
            }
        }
        System.out.println(this.name + " is done.");
        return;
    }

    private String generateRandomString(int length) {
        Random rand = new Random();
        String pool = "abcdefghijklmnopqrstuvwxyz";
        String string = "";
        for (int i = 0; i < length; i++) {
            string += pool.charAt(rand.nextInt(length) + 1);
        }
        return string;
    }

    private void generateDrinkSpeed() {
        Random rand = new Random();
        this.drinkSpeed = rand.nextInt(240) + 1;
    }

    public String toString() {
        return "name: " + this.name + " beer limit: " + this.beerLimit + " drink speed: " + this.drinkSpeed;
    }

    public String getName() {
        return this.name;
    }

    public int getBeerLimit() {
        return this.beerLimit;
    }
}

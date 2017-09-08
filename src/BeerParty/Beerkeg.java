package BeerParty;

public class Beerkeg {
    private int liters;

    public Beerkeg(int liters) {
        this.liters = liters;
    }

    public synchronized int getLiters() {
        return this.liters;
    }

    public synchronized void tap() {
        this.liters--;
        System.out.println("liters: " + this.liters);
    }

    public String toString() {
        return "liters: " + this.liters;
    }
}

package BeerParty;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.Arrays;

public class BeerParty {
    private Beerkeg beerkeg;

    public static void main(String[] args) {
        BeerParty beerParty = new BeerParty();
        beerParty.startBeerParty();
    }

    private void startBeerParty() {
        this.beerkeg = new Beerkeg(20);
        Person[] people = this.getPeople();

        int beerLimit = this.getBeerLimit(people);
        int beerkegLimit = this.beerkeg.getLiters();

        this.emptyKeg(beerkegLimit, people, beerLimit);

        Person heaviestDrinker = this.getHeaviestDrinker(people);
    }

    private void emptyKeg(int beerkegLimit, Person[] people, int beerLimit) {
        for (Person person : people) {
            (new Thread(new Person(this.beerkeg))).start();
        }
    }

    private Person[] getPeople() {
        int amountPeople = this.getNumberOfPeople();
        Person[] peopleList = new Person[amountPeople];
        for (int i = 0; i < amountPeople; i++) {
            Person tempPerson = new Person(this.beerkeg);
            peopleList[i] = tempPerson;
        }
        return peopleList;
    }

    private int getNumberOfPeople() {
        Random rand = new Random();
        return rand.nextInt(8) + 3;
    }

    private int getBeerLimit(Person[] people) {
        int totalBeerLimit = 0;
        for (Person person : people) {
            totalBeerLimit += person.getBeerLimit();
        }
        return totalBeerLimit;
    }

    private Person getHeaviestDrinker(Person[] people) {
        int highestLimit = 0;
        Person highestDrinker = people[0];
        for (Person person : people) {
            if (person.getBeerLimit() > highestLimit) {
                highestLimit = person.getBeerLimit();
                highestDrinker = person;
            }
        }
        return highestDrinker;
    }
}

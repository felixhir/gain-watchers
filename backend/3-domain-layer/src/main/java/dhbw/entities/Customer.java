package dhbw.entities;

import dhbw.valueObjects.Weight;

import javax.persistence.*;
import java.util.HashMap;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private final String id;

    private String name;
    private double height;
    private Weight weight;
    private int bodyFatPercentage;
    private int daysAvailablePerWeek;

    private HashMap<Workout, Integer> workouts = new HashMap<>();

    public Customer(String name, double height, Weight weight, int bodyFatPercentage, int daysAvailablePerWeek) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Every customer must have a name");
        }
        if (height < 50 || height > 250) {
            throw new IllegalArgumentException("Entered invalid height");
        }
        if (bodyFatPercentage < 1) {
            throw new IllegalArgumentException("A persons body fat cannot be less than 1%");
        }
        if (daysAvailablePerWeek < 0 || daysAvailablePerWeek > 7) {
            throw new IllegalArgumentException("A person can only be available 0 to 7 days per week");
        }

        this.name = name;
        this.height = height;
        this.weight = weight;
        this.bodyFatPercentage = bodyFatPercentage;
        this.daysAvailablePerWeek = daysAvailablePerWeek;
        this.id = UUID.randomUUID().toString();
    }

    public Customer() {
        this.id = UUID.randomUUID().toString();
        // default
    }

    public String getId() {
        return this.id;
    }

    public void addWorkout(Workout workout, int daysPerWeek) {
        if (daysPerWeek > this.availableDays()) {
            throw new IllegalArgumentException("The amount done of a workout must not exceed the weekly limit");
        }
        if (workouts.containsKey(workout)) {
            throw new IllegalArgumentException("This workout has already been added and updating the amount is no feature of the current version");
        }
        workouts.put(workout, daysPerWeek);
    }

    private int availableDays() {
        int total = this.daysAvailablePerWeek;
        for(int days: workouts.values()) {
            total -= days;
        }
        return total;
    }

}

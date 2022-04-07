package dhbw.entities;

import dhbw.valueObjects.Weight;

import javax.persistence.*;
import java.util.HashMap;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int height;
    @Transient
    private Weight weight;
    private int bodyFatPercentage;
    private int daysAvailablePerWeek;
    @Transient
    private HashMap<Workout, Integer> workouts = new HashMap<>();

    public Customer(String name, int height, Weight weight, int bodyFatPercentage, int daysAvailablePerWeek) {
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
    }

    public Customer() {
        // default
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public int getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public int getDaysAvailablePerWeek() {
        return daysAvailablePerWeek;
    }

    public HashMap<Workout, Integer> getWorkouts() {
        return workouts;
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

package dhbw.entities;

import dhbw.valueObjects.Weight;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int height;

    @Embedded
    private Weight weight;
    private int bodyFatPercentage;
    private int availability;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Workout> workouts;

    public Customer(String name, int height, double weight, int bodyFatPercentage, int availability) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Every customer must have a name");
        }
        if (height < 100 || height > 250) {
            throw new IllegalArgumentException("A persons height must lie within a reasonable range (100cm-250cm)");
        }
        if (bodyFatPercentage < 1 || bodyFatPercentage > 100) {
            throw new IllegalArgumentException("A persons body fat cannot be less than 1% or more than 100%");
        }
        if (availability < 0 || availability > 7) {
            throw new IllegalArgumentException("A week cannot have negative or more than 7 days");
        }

        this.name = name;
        this.height = height;
        this.weight = new Weight(weight, true);
        this.bodyFatPercentage = bodyFatPercentage;
        this.availability = availability;
        this.workouts = new LinkedList<>();
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

    public double getWeight() {
        return weight.getWeightInKg();
    }

    public int getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public int getAvailability() {
        return availability;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        if (workouts.contains(workout)) {
            throw new IllegalArgumentException("This workout has already been assigned to the customer");
        }
        if (availableDays() < workout.getDays()) {
            throw new IllegalArgumentException("This workout does not fit the customers schedule");
        }
        workouts.add(workout);
    }

    private int availableDays() {
        int total = this.availability;
        for(Workout workout: workouts) {
            total -= workout.getDays();
        }
        return total;
    }
}

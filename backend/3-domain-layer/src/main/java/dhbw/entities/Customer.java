package dhbw.entities;

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
    private double weight;
    private int bodyFatPercentage;
    private int daysAvailablePerWeek;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Workout> workouts;

    public Customer(String name, int height, double weight, int bodyFatPercentage, int daysAvailablePerWeek) {
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
        return weight;
    }

    public int getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public int getDaysAvailablePerWeek() {
        return daysAvailablePerWeek;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        if (workouts.contains(workout)) {
            throw new IllegalArgumentException("This workout has already been added and updating the amount is no feature of the current version");
        }
        if (availableDays() < workout.getDays()) {
            throw new IllegalArgumentException("This workout does not fit the customers schedule");
        }
        workouts.add(workout);
    }

    private int availableDays() {
        int total = this.daysAvailablePerWeek;
        for(Workout workout: workouts) {
            total -= workout.getDays();
        }
        return total;
    }
}

package dhbw.entities;

import dhbw.valueObjects.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Embedded
    private Name name;
    @Embedded
    private Height height;
    @Embedded
    private Weight weight;
    @Embedded
    private BodyFatPercentage bodyFatPercentage;
    @Embedded
    private Availability availability;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Workout> workouts;

    public Customer(String name, int height, double weight, int bodyFatPercentage, int availability) {
        this.name = new Name(name);
        this.height = new Height(height);
        this.weight = new Weight(weight, true);
        this.bodyFatPercentage = new BodyFatPercentage(bodyFatPercentage);
        this.availability = new Availability(availability);
        this.workouts = new LinkedList<>();
    }

    public Customer() {
        // default
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name.getName();
    }

    public int getHeight() {
        return height.getHeightInCm();
    }

    public double getWeight() {
        return weight.getWeightInKg();
    }

    public int getBodyFatPercentage() {
        return bodyFatPercentage.getBodyFatInPercent();
    }

    public int getAvailability() {
        return availability.getAvailability();
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
        int total = this.availability.getAvailability();
        for(Workout workout: workouts) {
            total -= workout.getDays();
        }
        return total;
    }
}

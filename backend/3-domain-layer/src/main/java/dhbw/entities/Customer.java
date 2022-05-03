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
    private DaysPerWeek availability;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Workout> workouts;

    public Customer(Name name, Height height, Weight weight, BodyFatPercentage bodyFatPercentage, DaysPerWeek availability) {
        this.name = name;
        this.height = height;
        this.weight = weight;
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

    public int getTotalAvailability() {
        return availability.getValue();
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void addWorkout(Workout workout) {
        if (workouts.contains(workout)) {
            throw new IllegalArgumentException("This workout has already been assigned to the customer");
        }
        if (getAvailableDays() < workout.getDays().getValue()) {
            throw new IllegalArgumentException("This workout does not fit the customers schedule");
        }
        workouts.add(workout);
    }

    public int getAvailableDays() {
        int total = this.availability.getValue();
        for(Workout workout: workouts) {
            total -= workout.getDays().getValue();
        }
        return total;
    }
}

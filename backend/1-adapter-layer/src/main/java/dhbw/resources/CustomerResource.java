package dhbw.resources;

import java.util.List;

public class CustomerResource {

    private Long id;
    private String name;
    private int availability;
    private double weight;
    private int height;
    private int bodyFatPercentage;
    private List<String> workouts;

    public CustomerResource(Long id, String name, int daysAvailablePerWeek, int bodyFatPercentage, double weight, int height, List<String> workouts) {
        this.id = id;
        this.name = name;
        this.availability = daysAvailablePerWeek;
        this.weight = weight;
        this.height = height;
        this.workouts = workouts;
        this.bodyFatPercentage = bodyFatPercentage;
    }

    public String getName() {
        return name;
    }

    public int getAvailability() {
        return availability;
    }

    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getBodyFatPercentage() {
        return bodyFatPercentage;
    }

    public Long getId() {
        return id;
    }

    public List<String> getWorkouts() {
        return workouts;
    }
}

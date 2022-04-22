package dhbw.resources;

import java.util.Map;

public class CustomerResource {

    private Long id;
    private String name;
    private int availability;
    private double weight;
    private int height;
    private int bodyFatPercentage;
    private Map<String, Integer> workouts;

    public CustomerResource(Long id, String name, int availability, int bodyFatPercentage, double weight, int height, Map<String, Integer> workouts) {
        this.id = id;
        this.name = name;
        this.availability = availability;
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

    public Map<String, Integer> getWorkouts() {
        return workouts;
    }
}

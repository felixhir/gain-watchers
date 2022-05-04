package dhbw.resources;

import java.util.List;

public class CustomerResource {

    private Long id;
    private String name;
    private int availability;
    private int totalAvailability;
    private double weight;
    private int height;
    private int bodyFatPercentage;
    private List<String> workouts;

    public CustomerResource(Long id,
                            String name,
                            int availability,
                            int totalAvailability,
                            int bodyFatPercentage,
                            double weight,
                            int height,
                            List<String> workouts) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.totalAvailability = totalAvailability;
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

    public int getTotalAvailability() {
        return totalAvailability;
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

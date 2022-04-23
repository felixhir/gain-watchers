package dhbw.resources;

import java.util.List;

public class WorkoutResource {

    private String name;
    private String description;
    private int days;
    private List<WorkoutExerciseResource> exercises;

    public WorkoutResource(String name, String description, int days, List<WorkoutExerciseResource> exercises) {
        this.name = name;
        this.description = description;
        this.days = days;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDays() {
        return days;
    }

    public List<WorkoutExerciseResource> getExercises() {
        return exercises;
    }
}

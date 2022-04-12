package dhbw.resources;

import dhbw.entities.WorkoutExercise;

import java.util.List;

public class WorkoutResource {

    private String name;
    private String description;
    private List<WorkoutExercise> exercises;

    public WorkoutResource(String name, String description, List<WorkoutExercise> exercises) {
        this.name = name;
        this.description = description;
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }
}

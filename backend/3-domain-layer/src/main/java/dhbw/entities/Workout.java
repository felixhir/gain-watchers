package dhbw.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Workout {

    @Id
    private String name;
    private String description;
    private int days;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<WorkoutExercise> exercises;

    public Workout(String name, String description, int days, List<WorkoutExercise> exercises) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every workout must have a name");
        }
        if(exercises.isEmpty()) {
            throw new IllegalArgumentException("A workout must contain at least 1 exercise");
        }
        if(days < 1) {
            throw new IllegalArgumentException("A workout must be done at least once per week");
        }
        this.name = name;
        this.description = description;
        this.days = days;
        this.exercises = new LinkedList<>();
        this.exercises.addAll(exercises);
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

    public int getDays() {
        return this.days;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    public Workout() {
        //default
    }

    public void addExercise(WorkoutExercise exercise) {
        if(!exercises.contains(exercise)) {
            throw new IllegalArgumentException("An exercise can only be part of a workout once");
        }
        exercises.add(exercise);
    }

    public void removeExercise(WorkoutExercise exercise) {
        if(!exercises.contains(exercise)) {
            throw new IllegalArgumentException("Exercise is not part of the workout and thus cannot be removed");
        }
        exercises.remove(exercise);
    }

    public void changeExerciseTo(WorkoutExercise newExercise) {
        WorkoutExercise oldExercise = null;
        for (WorkoutExercise exercise: exercises) {
            if(exercise.getExercise().equals(newExercise.getExercise())) {
                oldExercise = exercise;
            }
        }
        if(oldExercise == null) {
            throw new IllegalArgumentException("Cannot change exercise that is not part of the workout");
        }
        exercises.remove(oldExercise);
        exercises.add(newExercise);
    }

    public void changeName(String newName) {
        if(newName.isEmpty()) {
            throw new IllegalArgumentException("The new name cannot be empty");
        }
        this.name = newName;
    }
}

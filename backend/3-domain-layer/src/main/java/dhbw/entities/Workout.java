package dhbw.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collections;
import java.util.LinkedList;

@Entity
public class Workout {

    @Id
    private String name;
    private String description;

    @SuppressWarnings("JpaAttributeTypeInspection")
    private LinkedList<WorkoutExercise> exercises;

    public Workout(String name, String description, WorkoutExercise[] exercises) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every workout must have a name");
        }
        if(exercises.length == 0) {
            throw new IllegalArgumentException("A workout must contain at least 1 exercise");
        }
        this.name = name;
        this.description = description;
        Collections.addAll(this.exercises, exercises);
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

package dhbw.entities;

import dhbw.valueObjects.Name;
import dhbw.valueObjects.WorkoutExercise;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Workout {

    @Id
    private Name name;
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
        this.name = new Name(name);
        this.description = description;
        this.days = days;
        this.exercises = new LinkedList<>();
        this.exercises.addAll(exercises);
    }

    public String getName() {
        return name.getName();
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
        this.name = new Name(name);
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

}

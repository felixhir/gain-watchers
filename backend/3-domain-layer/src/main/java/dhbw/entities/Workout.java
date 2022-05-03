package dhbw.entities;

import dhbw.valueObjects.DaysPerWeek;
import dhbw.valueObjects.Name;
import dhbw.valueObjects.WorkoutExercise;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Workout {

    @EmbeddedId
    private Name name;
    private String description;
    private DaysPerWeek days;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<WorkoutExercise> exercises;

    public Workout(Name name, String description, DaysPerWeek days, List<WorkoutExercise> exercises) {
        if(exercises.isEmpty()) {
            throw new IllegalArgumentException("A workout must contain at least 1 exercise");
        }
        if(days.getValue() < 1) {
            throw new IllegalArgumentException("A workout must be done at least once a week");
        }
        this.name = name;
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

    public DaysPerWeek getDays() {
        return this.days;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDays(DaysPerWeek days) {
        if(days.getValue() < 1) {
            throw new IllegalArgumentException("A workout must be done at least once a week");
        }
        this.days = days;
    }

    public void setExercises(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    public Workout() {
        //default
    }

}

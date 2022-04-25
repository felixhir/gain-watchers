package dhbw.entities;

import javax.persistence.*;

@Entity
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Exercise exercise;
    private int sets;
    private int reps;

    public WorkoutExercise(Exercise exercise, int sets, int reps) {
        if(sets < 1) {
            throw new IllegalArgumentException("A planned exercise has to contain at least 1 set");
        }
        if(reps < 1) {
            throw new IllegalArgumentException("A planned exercises sets have to be of at least 1 rep");
        }
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }

    public WorkoutExercise() {
        // default
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public Exercise getExercise() {
        return exercise;
    }

}

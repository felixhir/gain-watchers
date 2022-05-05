package dhbw.valueObjects;

import javax.persistence.*;

import static java.util.Objects.hash;

@Entity
public class WorkoutExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private final Exercise exercise;
    private final int sets;
    private final int reps;

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
        exercise = null;
        sets = 0;
        reps = 0;
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

    @Override
    public boolean equals(Object object) {
        if (object instanceof WorkoutExercise) {
            WorkoutExercise workoutExerciseObject = (WorkoutExercise) object;
            return (workoutExerciseObject.exercise == this.exercise
                    && workoutExerciseObject.getReps() == this.reps
                    && workoutExerciseObject.getSets() == this.sets);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(exercise, sets, reps);
    }

}

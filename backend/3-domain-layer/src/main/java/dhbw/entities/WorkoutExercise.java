package dhbw.entities;

import java.util.Objects;

public class WorkoutExercise {

    private final Exercise exercise;
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

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void addSets(int amount) {
        if(this.sets + amount <= 0) {
            throw new IllegalArgumentException();
        }
        this.sets += amount;
    }

    public void subtractSets(int amount) {
        if(this.sets - amount <= 0) {
            throw new IllegalArgumentException();
        }
        this.sets -= amount;
    }

    public void addReps(int amount) {
        if(this.reps + amount <= 0) {
            throw new IllegalArgumentException();
        }
        this.reps += amount;
    }

    public void subtractReps(int amount) {
        if(this.reps + amount < 0) {
            throw new IllegalArgumentException();
        }
        this.reps += amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkoutExercise that = (WorkoutExercise) o;
        return sets == that.sets && reps == that.reps && Objects.equals(exercise, that.exercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exercise, sets, reps);
    }
}

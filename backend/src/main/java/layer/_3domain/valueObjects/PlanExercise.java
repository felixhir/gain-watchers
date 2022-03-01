package layer._3domain.valueObjects;

import layer._3domain.entities.Exercise;

import java.util.Objects;

public class PlanExercise {

    private final Exercise exercise;
    private final int sets;
    private final int reps;

    public PlanExercise(Exercise exercise, int sets, int reps) {
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

    public PlanExercise reduceReps(int amount) {
        return new PlanExercise(this.exercise, this.sets, this.reps - amount);
    }

    public PlanExercise increaseReps(int amount) {
        return new PlanExercise(this.exercise, this.sets, this.reps + amount);
    }

    public PlanExercise reduceSets(int amount) {
        return new PlanExercise(this.exercise, this.sets - amount, this.reps);
    }

    public PlanExercise increaseSets(int amount) {
        return new PlanExercise(this.exercise, this.sets + amount, this.reps - amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanExercise that = (PlanExercise) o;
        return sets == that.sets && reps == that.reps && Objects.equals(exercise, that.exercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exercise, sets, reps);
    }
}

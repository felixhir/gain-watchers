package dhbw.resources;

public class WorkoutExerciseResource {

    private String exerciseName;
    private String exerciseVariant;

    private int reps;

    private int sets;

    public WorkoutExerciseResource(String exerciseName, String exerciseVariant, int reps, int sets) {
        this.exerciseName = exerciseName;
        this.exerciseVariant = exerciseVariant;
        this.reps = reps;
        this.sets = sets;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public String getExerciseVariant() {
        return exerciseVariant;
    }
}

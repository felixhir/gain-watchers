package dhbw.resources;

public class ExerciseResource {

    private String name;
    private String exerciseType;
    private String exerciseVariant;

    public ExerciseResource(String name, String exerciseType, String exerciseVariant) {
        this.name = name;
        this.exerciseType = exerciseType;
        this.exerciseVariant = exerciseVariant;
    }

    public String getName() {
        return name;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public String getExerciseVariant() {
        return exerciseVariant;
    }

}

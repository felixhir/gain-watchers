package dhbw.resources;

public class ExerciseResource {

    private String name;
    private String exerciseVariant;

    public ExerciseResource(String name, String exerciseVariant) {
        this.name = name;
        this.exerciseVariant = exerciseVariant;
    }

    public String getName() {
        return name;
    }

    public String getExerciseVariant() {
        return exerciseVariant;
    }

}

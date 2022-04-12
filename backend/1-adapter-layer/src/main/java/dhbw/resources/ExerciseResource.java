package dhbw.resources;

import dhbw.entities.ExerciseType;
import dhbw.entities.ExerciseVariant;

public class ExerciseResource {

    private String name;
    private ExerciseType exerciseType;
    private ExerciseVariant exerciseVariant;

    public ExerciseResource(String name, ExerciseType exerciseType, ExerciseVariant exerciseVariant) {
        this.name = name;
        this.exerciseType = exerciseType;
        this.exerciseVariant = exerciseVariant;
    }

    public String getName() {
        return name;
    }

    public ExerciseType getExerciseType() {
        return exerciseType;
    }

    public ExerciseVariant getExerciseVariant() {
        return exerciseVariant;
    }

}

package dhbw.helper;

import dhbw.entities.ExerciseType;
import dhbw.entities.ExerciseVariant;

import java.io.Serializable;

public class ExerciseKey implements Serializable {

    private String name;
    private ExerciseType type;
    private ExerciseVariant variant;

}

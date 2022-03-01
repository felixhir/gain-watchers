package layer._3domain.entities;

import java.util.Collections;
import java.util.LinkedList;

public class Exercise {

    private final String name;
    private final ExerciseType type;
    private final LinkedList<ExerciseVariant> variants;

    public Exercise(String name, ExerciseType type, ExerciseVariant[] variants) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every exercise has to have a name");
        }
        if(variants.length <= 0) {
            throw new IllegalArgumentException("An exercise has to contain at least 1 variant");
        }
        this.name = name;
        this.type = type;
        this.variants = new LinkedList<>();
        Collections.addAll(this.variants, variants);
    }
}

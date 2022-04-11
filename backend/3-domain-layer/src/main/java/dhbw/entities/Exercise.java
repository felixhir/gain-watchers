package dhbw.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Exercise {

    @Id
    private String name;
    private ExerciseType type;
    private ExerciseVariant variant;

    public Exercise(String name, ExerciseType type, ExerciseVariant variant) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every exercise has to have a name");
        }
        this.name = name;
        this.type = type;
        this.variant = variant;
    }

    public Exercise() {
        // default
    }

    public String getName() {
        return name;
    }

    public ExerciseType getType() {
        return type;
    }

    public ExerciseVariant getVariant() {
        return variant;
    }
}

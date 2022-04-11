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

    @Transient
    private LinkedList<ExerciseVariant> variants;

    public Exercise(String name, ExerciseType type, List<ExerciseVariant> variants) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Every exercise has to have a name");
        }
        if(variants.isEmpty()) {
            throw new IllegalArgumentException("An exercise has to contain at least 1 variant");
        }
        this.name = name;
        this.type = type;
        this.variants = new LinkedList<>();
        this.variants.addAll(variants.stream().distinct().collect(Collectors.toList()));
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

    public LinkedList<ExerciseVariant> getVariants() {
        return variants;
    }
}

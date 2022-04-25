package dhbw.entities;

public enum ExerciseVariant {
    CABLE("Cable"),
    BARBELL("Barbell"),
    DUMBBELL("Dumbbell"),
    BODY_WEIGHT("Body Weight"),
    MACHINE("Machine");

    private String name;

    ExerciseVariant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

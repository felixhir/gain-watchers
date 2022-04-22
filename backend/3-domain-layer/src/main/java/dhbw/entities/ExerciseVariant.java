package dhbw.entities;

public enum ExerciseVariant {
    PRONATED_GRIP("Pronated Grip"),
    SUPINATED_GRIP("Supinated Grip"),
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

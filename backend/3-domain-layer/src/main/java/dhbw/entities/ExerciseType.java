package dhbw.entities;

public enum ExerciseType {
    CARDIO("Cardio"),
    FREE_WEIGHT("Free Weight"),
    MACHINE("Machine"),
    MOBILITY("Mobility");

    private String name;

    ExerciseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

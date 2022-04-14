package dhbw.rest.bodies;

public class WorkoutAssignmentBody {

    private Long customerId;
    private String workoutName;
    private int amount;

    public WorkoutAssignmentBody(Long customerId, String workoutName, int amount) {
        this.customerId = customerId;
        this.workoutName = workoutName;
        this.amount = amount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public int getAmount() {
        return amount;
    }
}

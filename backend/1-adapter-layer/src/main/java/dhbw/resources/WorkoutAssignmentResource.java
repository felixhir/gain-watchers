package dhbw.resources;

public class WorkoutAssignmentResource {

    private CustomerResource customer;
    private WorkoutResource workout;
    private int amount;

    public WorkoutAssignmentResource(CustomerResource customerResource, WorkoutResource workoutResource, int amount) {
        this.customer = customerResource;
        this.workout = workoutResource;
        this.amount = amount;
    }

    public CustomerResource getCustomer() {
        return customer;
    }

    public WorkoutResource getWorkout() {
        return workout;
    }

    public int getAmount() {
        return amount;
    }
}

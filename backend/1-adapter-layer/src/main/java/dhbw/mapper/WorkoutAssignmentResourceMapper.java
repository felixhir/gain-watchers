package dhbw.mapper;

import dhbw.entities.WorkoutAssignment;
import dhbw.resources.CustomerResource;
import dhbw.resources.WorkoutAssignmentResource;
import dhbw.resources.WorkoutResource;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class WorkoutAssignmentResourceMapper implements Function<WorkoutAssignment, WorkoutAssignmentResource> {

    @Override
    public WorkoutAssignmentResource apply(WorkoutAssignment workoutAssignment) {
        return map(workoutAssignment);
    }

    private WorkoutAssignmentResource map(WorkoutAssignment workoutAssignment) {
        WorkoutResource workoutResource = new WorkoutResourceMapper().apply(workoutAssignment.getWorkout());
        CustomerResource customerResource = new CustomerResourceMapper().apply(workoutAssignment.getCustomer());
        return new WorkoutAssignmentResource(customerResource, workoutResource, workoutAssignment.getAmount());
    }
}

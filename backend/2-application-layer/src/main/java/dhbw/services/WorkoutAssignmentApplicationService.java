package dhbw.services;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.entities.WorkoutAssignment;
import dhbw.repositories.WorkoutAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutAssignmentApplicationService {

    private WorkoutAssignmentRepository workoutAssignmentRepository;

    @Autowired
    public WorkoutAssignmentApplicationService(WorkoutAssignmentRepository workoutAssignmentRepository) {
        this.workoutAssignmentRepository = workoutAssignmentRepository;
    }

    public List<WorkoutAssignment> findAll() {
        return this.workoutAssignmentRepository.findAll();
    }

    public WorkoutAssignment save(WorkoutAssignment workoutAssignment) {
        return this.workoutAssignmentRepository.save(workoutAssignment);
    }

    public List<Workout> getWorkoutsForCustomer(Customer customer) {
        return this.workoutAssignmentRepository.getWorkoutsForCustomer(customer);
    }
}

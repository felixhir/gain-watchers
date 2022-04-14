package dhbw.persistence.bridges;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.entities.WorkoutAssignment;
import dhbw.persistence.repositories.SpringDataWorkoutAssignmentRepository;
import dhbw.repositories.WorkoutAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkoutAssignmentRepositoryBridge implements WorkoutAssignmentRepository {

    private SpringDataWorkoutAssignmentRepository springDataWorkoutAssignmentRepository;

    @Autowired
    public WorkoutAssignmentRepositoryBridge(SpringDataWorkoutAssignmentRepository springDataWorkoutAssignmentRepository) {
        this.springDataWorkoutAssignmentRepository = springDataWorkoutAssignmentRepository;
    }

    @Override
    public List<Workout> getWorkoutsForCustomer(Customer customer) {
        return this.springDataWorkoutAssignmentRepository.findByCustomer(customer);
    }

    @Override
    public List<WorkoutAssignment> findAll() {
        return this.springDataWorkoutAssignmentRepository.findAll();
    }

    @Override
    public WorkoutAssignment save(WorkoutAssignment workoutAssignment) {
        return this.springDataWorkoutAssignmentRepository.save(workoutAssignment);
    }
}

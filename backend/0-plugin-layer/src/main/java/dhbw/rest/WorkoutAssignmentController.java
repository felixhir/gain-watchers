package dhbw.rest;

import dhbw.entities.Customer;
import dhbw.entities.Workout;
import dhbw.entities.WorkoutAssignment;
import dhbw.mapper.WorkoutAssignmentResourceMapper;
import dhbw.resources.WorkoutAssignmentResource;
import dhbw.rest.bodies.WorkoutAssignmentBody;
import dhbw.services.CustomerApplicationService;
import dhbw.services.WorkoutApplicationService;
import dhbw.services.WorkoutAssignmentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/assignments")
public class WorkoutAssignmentController {

    private WorkoutAssignmentApplicationService workoutAssignmentApplicationService;
    private WorkoutApplicationService workoutApplicationService;
    private CustomerApplicationService customerApplicationService;
    private WorkoutAssignmentResourceMapper workoutAssignmentResourceMapper;

    @Autowired
    public WorkoutAssignmentController(WorkoutAssignmentApplicationService workoutAssignmentApplicationService,
                                       WorkoutAssignmentResourceMapper workoutAssignmentResourceMapper,
                                       WorkoutApplicationService workoutApplicationService,
                                       CustomerApplicationService customerApplicationService) {
        this.workoutAssignmentApplicationService = workoutAssignmentApplicationService;
        this.workoutAssignmentResourceMapper = workoutAssignmentResourceMapper;
        this.workoutApplicationService = workoutApplicationService;
        this.customerApplicationService = customerApplicationService;
    }

    @GetMapping
    public List<WorkoutAssignmentResource> getAllAssignments() {
        return this.workoutAssignmentApplicationService.findAll().stream()
                .map(workoutAssignmentResourceMapper)
                .collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> assignWorkout(@RequestBody WorkoutAssignmentBody newAssignment) {
        Customer customer = customerApplicationService.getById(newAssignment.getCustomerId());
        Workout workout = workoutApplicationService.getByName(newAssignment.getWorkoutName());
        WorkoutAssignment assignment = workoutAssignmentApplicationService.save(new WorkoutAssignment(customer, workout, newAssignment.getAmount()));
        return new ResponseEntity<>(assignment, HttpStatus.CREATED);
    }
}

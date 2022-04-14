package dhbw.rest;

import dhbw.entities.WorkoutAssignment;
import dhbw.mapper.WorkoutAssignmentResourceMapper;
import dhbw.resources.WorkoutAssignmentResource;
import dhbw.services.WorkoutAssignmentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/assignments")
public class WorkoutAssignmentController {

    private WorkoutAssignmentApplicationService workoutAssignmentApplicationService;
    private WorkoutAssignmentResourceMapper workoutAssignmentResourceMapper;

    @Autowired
    public WorkoutAssignmentController(WorkoutAssignmentApplicationService workoutAssignmentApplicationService, WorkoutAssignmentResourceMapper workoutAssignmentResourceMapper) {
        this.workoutAssignmentApplicationService = workoutAssignmentApplicationService;
        this.workoutAssignmentResourceMapper = workoutAssignmentResourceMapper;
    }

    @GetMapping
    public List<WorkoutAssignmentResource> getAllAssignments() {
        return this.workoutAssignmentApplicationService.findAll().stream()
                .map(workoutAssignmentResourceMapper)
                .collect(Collectors.toList());
    }
}

package dhbw.rest;

import dhbw.entities.Workout;
import dhbw.mapper.WorkoutResourceMapper;
import dhbw.resources.WorkoutResource;
import dhbw.services.WorkoutApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/workouts")
public class WorkoutController {

    private WorkoutApplicationService workoutApplicationService;
    private WorkoutResourceMapper workoutResourceMapper;

    @Autowired
    public WorkoutController(WorkoutApplicationService workoutApplicationService, WorkoutResourceMapper workoutResourceMapper) {
        this.workoutApplicationService = workoutApplicationService;
        this.workoutResourceMapper = workoutResourceMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<WorkoutResource> getWorkouts() {
        return workoutApplicationService.findAll().stream()
                .map(workoutResourceMapper)
                .collect(Collectors.toList());
    }
}

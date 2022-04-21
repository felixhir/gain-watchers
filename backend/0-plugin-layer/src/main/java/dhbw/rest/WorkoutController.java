package dhbw.rest;

import dhbw.entities.Workout;
import dhbw.mapper.WorkoutResourceMapper;
import dhbw.resources.WorkoutResource;
import dhbw.services.WorkoutApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<WorkoutResource> getWorkouts() {
        return workoutApplicationService.findAll().stream()
                .map(workoutResourceMapper)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createWorkout(@RequestBody Workout newWorkout) {
        Workout workout = workoutApplicationService.save(newWorkout);
        return new ResponseEntity<>(workout, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateWorkout(@PathVariable String id, @RequestBody Workout newWorkout) {
        Workout oldWorkout = this.workoutApplicationService.getByName(id);
        Workout workout = this.workoutApplicationService.replaceWith(oldWorkout, newWorkout);
        return new ResponseEntity<>(workout, HttpStatus.OK);
    }
}

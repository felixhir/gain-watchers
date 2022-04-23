package dhbw.rest;

import dhbw.entities.Exercise;
import dhbw.entities.ExerciseVariant;
import dhbw.entities.Workout;
import dhbw.entities.WorkoutExercise;
import dhbw.mapper.WorkoutExerciseResourceMapper;
import dhbw.mapper.WorkoutResourceMapper;
import dhbw.resources.WorkoutExerciseResource;
import dhbw.resources.WorkoutResource;
import dhbw.services.ExerciseApplicationService;
import dhbw.services.WorkoutApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(path = "api/workouts")
public class WorkoutController {

    private WorkoutApplicationService workoutApplicationService;
    private ExerciseApplicationService exerciseApplicationService;
    private WorkoutResourceMapper workoutResourceMapper;

    @Autowired
    public WorkoutController(WorkoutApplicationService workoutApplicationService, WorkoutResourceMapper workoutResourceMapper, ExerciseApplicationService exerciseApplicationService) {
        this.workoutApplicationService = workoutApplicationService;
        this.workoutResourceMapper = workoutResourceMapper;
        this.exerciseApplicationService = exerciseApplicationService;
    }

    @GetMapping
    public List<WorkoutResource> getWorkouts() {
        return workoutApplicationService.findAll().stream()
                .map(workoutResourceMapper)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutResource newWorkout) {
        List<WorkoutExercise> exercises = new LinkedList<>();
        WorkoutExerciseResourceMapper mapper = new WorkoutExerciseResourceMapper();
        for (WorkoutExerciseResource resource: newWorkout.getExercises()) {
            Exercise exercise = this.exerciseApplicationService.getById(resource.getExerciseName(), ExerciseVariant.valueOf(resource.getExerciseVariant()));
            exercises.add(mapper.reverse(resource, exercise));
        }
        Workout toSave = new Workout(newWorkout.getName(), newWorkout.getDescription(), newWorkout.getDays(), exercises);
        Workout workout = workoutApplicationService.save(toSave);
        return new ResponseEntity<>(workout, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateWorkout(@PathVariable String id, @RequestBody Workout newWorkout) {
        Workout oldWorkout = this.workoutApplicationService.getByName(id);
        Workout workout = this.workoutApplicationService.replaceWith(oldWorkout, newWorkout);
        return new ResponseEntity<>(workout, HttpStatus.OK);
    }
}

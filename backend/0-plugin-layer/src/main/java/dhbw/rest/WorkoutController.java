package dhbw.rest;

import dhbw.entities.Customer;
import dhbw.services.CustomerApplicationService;
import dhbw.valueObjects.DaysPerWeek;
import dhbw.valueObjects.Exercise;
import dhbw.helper.ExerciseVariant;
import dhbw.entities.Workout;
import dhbw.valueObjects.Name;
import dhbw.valueObjects.WorkoutExercise;
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
    private CustomerApplicationService customerApplicationService;
    private WorkoutResourceMapper workoutResourceMapper;

    @Autowired
    public WorkoutController(WorkoutApplicationService workoutApplicationService,
                             WorkoutResourceMapper workoutResourceMapper,
                             ExerciseApplicationService exerciseApplicationService,
                             CustomerApplicationService customerApplicationService) {
        this.workoutApplicationService = workoutApplicationService;
        this.workoutResourceMapper = workoutResourceMapper;
        this.exerciseApplicationService = exerciseApplicationService;
        this.customerApplicationService = customerApplicationService;
    }

    @GetMapping
    public List<WorkoutResource> getWorkouts() {
        return workoutApplicationService.findAll().stream()
                .map(workoutResourceMapper)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutResource newWorkout) {
        try {
            Workout toSave = new Workout(new Name(newWorkout.getName()),
                    newWorkout.getDescription(),
                    new DaysPerWeek(newWorkout.getDays()),
                    getWorkoutExercisesFromWorkoutResource(newWorkout)
            );
            Workout workout = workoutApplicationService.save(toSave);
            return new ResponseEntity<>(workout, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateWorkout(@PathVariable String id, @RequestBody WorkoutResource newWorkoutResource) {
        List<String> customerNames = new LinkedList<>();
        try {
            Workout workout = this.workoutApplicationService.getByName(new Name(id));
            int daysMore = newWorkoutResource.getDays() - workout.getDays().getValue();
            List<Customer> customers = customerApplicationService.getByWorkout(workout);
            for(Customer customer: customers) {
                if(customer.getAvailableDays() < daysMore) {
                    customerNames.add(customer.getName());
                }
            }
            if(!customerNames.isEmpty()) {
                return new ResponseEntity<>(String.format("This change would exceed some customers availability (%s)", String.join(", ", customerNames)),
                        HttpStatus.BAD_REQUEST);
            }
            workout.setName(new Name(newWorkoutResource.getName()));
            workout.setDescription(newWorkoutResource.getDescription());
            workout.setDays(new DaysPerWeek(newWorkoutResource.getDays()));
            workout.setExercises(getWorkoutExercisesFromWorkoutResource(newWorkoutResource));
            return new ResponseEntity<>(this.workoutApplicationService.save(workout), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private List<WorkoutExercise> getWorkoutExercisesFromWorkoutResource(WorkoutResource workoutResource) {
        List<WorkoutExercise> exercises = new LinkedList<>();
        WorkoutExerciseResourceMapper mapper = new WorkoutExerciseResourceMapper();
        for (WorkoutExerciseResource resource: workoutResource.getExercises()) {
            Exercise exercise = this.exerciseApplicationService.getById(new Name(resource.getExerciseName()),
                    ExerciseVariant.valueOf(resource.getExerciseVariant())
            );
            exercises.add(mapper.reverse(resource, exercise));
        }
        return exercises;
    }
}

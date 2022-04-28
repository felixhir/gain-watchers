package dhbw.rest;

import dhbw.entities.Exercise;
import dhbw.helper.ExerciseVariant;
import dhbw.mapper.ExerciseResourceMapper;
import dhbw.resources.ExerciseResource;
import dhbw.services.ExerciseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("api/exercises")
public class ExerciseController {

    private ExerciseApplicationService exerciseApplicationService;
    private ExerciseResourceMapper exerciseResourceMapper;

    @Autowired
    public ExerciseController(ExerciseApplicationService exerciseApplicationService, ExerciseResourceMapper exerciseResourceMapper) {
        this.exerciseApplicationService = exerciseApplicationService;
        this.exerciseResourceMapper = exerciseResourceMapper;
    }

    @GetMapping
    public List<ExerciseResource> getExercises() {
        return this.exerciseApplicationService.getExercises().stream()
                .map(exerciseResourceMapper)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> createExercise(@RequestBody ExerciseResource newExercise) {
        try {
            Exercise exercise = exerciseApplicationService.save(this.exerciseResourceMapper.reverse(newExercise));
            return new ResponseEntity<>(exerciseResourceMapper.apply(exercise), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/variants")
    public List<ExerciseVariant> getVariants() {
        return Arrays.stream(ExerciseVariant.values()).collect(Collectors.toList());
    }
}

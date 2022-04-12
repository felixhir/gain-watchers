package dhbw.rest;

import dhbw.mapper.ExerciseResourceMapper;
import dhbw.resources.ExerciseResource;
import dhbw.services.ExerciseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/exercises")
public class ExerciseController {

    private ExerciseApplicationService exerciseApplicationService;
    private ExerciseResourceMapper exerciseResourceMapper;

    @Autowired
    public ExerciseController(ExerciseApplicationService exerciseApplicationService, ExerciseResourceMapper exerciseResourceMapper) {
        this.exerciseApplicationService = exerciseApplicationService;
        this.exerciseResourceMapper = exerciseResourceMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ExerciseResource> getExercises() {
        return this.exerciseApplicationService.getExercises().stream()
                .map(exerciseResourceMapper)
                .collect(Collectors.toList());
    }
}

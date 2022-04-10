package dhbw.rest;

import dhbw.entities.Exercise;
import dhbw.services.ExerciseApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/exercises")
public class ExerciseController {

    private ExerciseApplicationService exerciseApplicationService;

    @Autowired
    public ExerciseController(ExerciseApplicationService exerciseApplicationService) {
        this.exerciseApplicationService = exerciseApplicationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Exercise> getExercises() {
        return this.exerciseApplicationService.getExercises();
    }
}

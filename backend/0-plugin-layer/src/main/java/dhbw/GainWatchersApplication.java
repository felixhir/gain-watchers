package dhbw;

import dhbw.entities.*;
import dhbw.repositories.CustomerRepository;
import dhbw.repositories.ExerciseRepository;
import dhbw.repositories.WorkoutRepository;
import dhbw.valueObjects.Weight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class GainWatchersApplication {

    public static void main(String[] args) {
        SpringApplication.run(GainWatchersApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository,
                                  ExerciseRepository exerciseRepository,
                                  WorkoutRepository workoutRepository) {
        return (args -> {
            customerRepository.save(new Customer("Max Mustermann", 180, new Weight(175, false), 15, 5));
            customerRepository.save(new Customer("Maxime Musterfrau", 175, new Weight(170, false), 20, 3));
            customerRepository.save(new Customer("Maggus Rühl", 180, new Weight(125, true), 25, 7));

            exerciseRepository.save(new Exercise("Bankdrücken", ExerciseType.FREE_WEIGHT, ExerciseVariant.BARBELL));
            exerciseRepository.save(new Exercise("Laufen", ExerciseType.CARDIO, ExerciseVariant.MACHINE));
            exerciseRepository.save(new Exercise("Hüftöffner", ExerciseType.MOBILITY, ExerciseVariant.BODY_WEIGHT));

            List<WorkoutExercise> exercises = exerciseRepository.findAll()
                    .stream()
                    .map(exercise -> new WorkoutExercise(exercise, 5, 5))
                    .collect(Collectors.toList());
            workoutRepository.save(new Workout("5x5 Basic", "", exercises));
        });
    }
}

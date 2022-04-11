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

import java.util.LinkedList;
import java.util.List;

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

            LinkedList variants = new LinkedList();
            variants.add(ExerciseVariant.BARBELL);
            variants.add(ExerciseVariant.DUMBBELL);
            Exercise benchPress = new Exercise("Bankdrücken", ExerciseType.FREE_WEIGHT, variants);
            exerciseRepository.save(benchPress);

            List<WorkoutExercise> exercises = new LinkedList<>();
            exercises.add(new WorkoutExercise(benchPress, 5, 5));
            workoutRepository.save(new Workout("Brust 5x5", "", exercises));
        });
    }
}

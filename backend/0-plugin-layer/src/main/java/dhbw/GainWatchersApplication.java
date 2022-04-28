package dhbw;

import dhbw.entities.*;
import dhbw.helper.ExerciseVariant;
import dhbw.repositories.CustomerRepository;
import dhbw.repositories.ExerciseRepository;
import dhbw.repositories.WorkoutRepository;
import dhbw.valueObjects.Exercise;
import dhbw.valueObjects.WorkoutExercise;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
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
            exerciseRepository.save(new Exercise("Bench Press", ExerciseVariant.BARBELL));
            Exercise deadlift = exerciseRepository.save(new Exercise("Deadlift", ExerciseVariant.BARBELL));
            exerciseRepository.save(new Exercise("Squat", ExerciseVariant.BARBELL));
            exerciseRepository.save(new Exercise("Lat Pulldown", ExerciseVariant.MACHINE));
            exerciseRepository.save(new Exercise("Push-Ups", ExerciseVariant.BODY_WEIGHT));

            List<WorkoutExercise> exercises = exerciseRepository.findAll()
                    .stream()
                    .map(exercise -> new WorkoutExercise(exercise, 5, 5))
                    .collect(Collectors.toList());
            Workout workout = workoutRepository.save(new Workout("5x5 Basic", "", 3, exercises));
            workoutRepository.save(new Workout("DEADLIFT EVERY DAY",
                    "This program is nothing but deadlifts, 7 times a week. It will probably wreck your spine",
                    7,
                    Arrays.asList(new WorkoutExercise(deadlift, 10, 5))));

            Customer customer = new Customer("Max Mustermann", 180, 80, 15, 5);
            customer.addWorkout(workout);
            customerRepository.save(customer);
            customerRepository.save(new Customer("Maxime Musterfrau", 175, 65.5, 20, 3));
            customerRepository.save(new Customer("Maggus Rühl", 180, 125, 25, 7));
        });
    }
}

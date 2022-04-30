package dhbw;

import dhbw.entities.*;
import dhbw.helper.ExerciseVariant;
import dhbw.repositories.CustomerRepository;
import dhbw.repositories.ExerciseRepository;
import dhbw.repositories.WorkoutRepository;
import dhbw.entities.Exercise;
import dhbw.valueObjects.*;
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
            exerciseRepository.save(new Exercise(new Name("Bench Press"), ExerciseVariant.BARBELL));
            Exercise deadlift = exerciseRepository.save(new Exercise(new Name("Deadlift"), ExerciseVariant.BARBELL));
            exerciseRepository.save(new Exercise(new Name("Squat"), ExerciseVariant.BARBELL));
            exerciseRepository.save(new Exercise(new Name("Lat Pulldown"), ExerciseVariant.MACHINE));
            exerciseRepository.save(new Exercise(new Name("Push-Ups"), ExerciseVariant.BODY_WEIGHT));

            List<WorkoutExercise> exercises = exerciseRepository.findAll()
                    .stream()
                    .map(exercise -> new WorkoutExercise(exercise, 5, 5))
                    .collect(Collectors.toList());
            Workout workout = workoutRepository.save(new Workout(new Name("5x5 Basic"), "", 3, exercises));
            workoutRepository.save(new Workout(new Name("DEADLIFT EVERY DAY"),
                    "This program is nothing but deadlifts, 7 times a week. It will probably wreck your spine",
                    7,
                    Arrays.asList(new WorkoutExercise(deadlift, 10, 5))));

            Customer customer = new Customer(new Name("Max Mustermann"), new Height(180), new Weight(80, true), new BodyFatPercentage(15), new Availability(5));
            customer.addWorkout(workout);
            customerRepository.save(customer);
            customerRepository.save(new Customer(new Name("Maxime Musterfrau"), new Height(175), new Weight(65.5, true), new BodyFatPercentage(20), new Availability(3)));
            customerRepository.save(new Customer(new Name("Maggus Rühl"), new Height(180), new Weight(125, true), new BodyFatPercentage(25), new Availability(7)));
        });
    }
}

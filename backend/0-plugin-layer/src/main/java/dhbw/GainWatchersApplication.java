package dhbw;

import dhbw.entities.Customer;
import dhbw.entities.Exercise;
import dhbw.entities.ExerciseType;
import dhbw.entities.ExerciseVariant;
import dhbw.repositories.CustomerRepository;
import dhbw.repositories.ExerciseRepository;
import dhbw.valueObjects.Weight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;

@SpringBootApplication
public class GainWatchersApplication {

    public static void main(String[] args) {
        SpringApplication.run(GainWatchersApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository, ExerciseRepository exerciseRepository) {
        return (args -> {
            customerRepository.save(new Customer("Max Mustermann", 180, new Weight(80, false), 15, 5));
            LinkedList variants = new LinkedList();
            variants.add(ExerciseVariant.BARBELL);
            exerciseRepository.getByName("Test");
            exerciseRepository.save(new Exercise("Bankdrücken", ExerciseType.FREE_WEIGHT, variants));
        });
    }
}

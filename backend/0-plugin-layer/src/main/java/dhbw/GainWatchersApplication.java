package dhbw;

import dhbw.entities.Customer;
import dhbw.repositories.CustomerRepository;
import dhbw.valueObjects.Weight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GainWatchersApplication {

    public static void main(String[] args) {
        SpringApplication.run(GainWatchersApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository customerRepository) {
        return (args -> {
            customerRepository.save(new Customer("Max Mustermann", 1.80, new Weight(80, false), 15, 5));
        });
    }
}

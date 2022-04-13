package dhbw.entities;

import javax.persistence.*;

@Entity
public class WorkoutAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Customer customer;
    @ManyToOne
    private Workout workout;
    private int amount;

    public WorkoutAssignment(Customer customer, Workout workout, int amount) {
        this.customer = customer;
        this.workout = workout;
        this.amount = amount;
    }

    public WorkoutAssignment() {
        // default constructor
    }
}

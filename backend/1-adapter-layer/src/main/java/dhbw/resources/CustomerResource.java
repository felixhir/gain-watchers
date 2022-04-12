package dhbw.resources;

public class CustomerResource {

    private String name;
    private int availability;
    private double weight;
    private float height;

    public CustomerResource(String name, int availability, double weight, float height) {
        this.name = name;
        this.availability = availability;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getAvailability() {
        return availability;
    }

    public double getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }
}

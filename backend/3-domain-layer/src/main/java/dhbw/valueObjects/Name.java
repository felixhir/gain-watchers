package dhbw.valueObjects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

import static java.util.Objects.hash;

@Embeddable
public final class Name implements Serializable {

    private final String name;

    public Name(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("A name cannot be empty");
        }
        this.name = name;
    }

    public Name() {
        // JPA default
        name = null;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        // empty method for JPA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return hash(name);
    }
}

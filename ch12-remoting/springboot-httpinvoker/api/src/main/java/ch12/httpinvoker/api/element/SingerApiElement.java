package ch12.httpinvoker.api.element;

import java.io.Serializable;
import java.time.LocalDate;

// Immutable Api Element
public class SingerApiElement implements Serializable {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public SingerApiElement(final String firstName, final String lastName, final LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}

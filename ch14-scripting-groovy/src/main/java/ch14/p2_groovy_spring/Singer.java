package ch14.p2_groovy_spring;

import java.time.LocalDate;

/*
 * ageCategory is set by a dynamic rule which calculates
 * the age based on the birthDate and assigns the ageCategory property
 */
public class Singer {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String ageCategory;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(final String ageCategory) {
        this.ageCategory = ageCategory;
    }

    @Override
    public String toString() {
        return "ch14.p1_groovy_basics.Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", ageCategory='" + ageCategory + '\'' +
                '}';
    }
}

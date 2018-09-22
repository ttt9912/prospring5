package ch10.p3_validation.with_jsr349_bean_validation.custom;

import ch10.p3_validation.with_jsr349_bean_validation.Gender;
import ch10.p3_validation.with_jsr349_bean_validation.Genre;

@CheckCountrySinger
class Singer {

    private String firstName;
    private String lastName;
    private Genre genre;
    private Gender gender;


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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(final Genre genre) {
        this.genre = genre;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", genre=" + genre +
                ", gender=" + gender +
                '}';
    }
}

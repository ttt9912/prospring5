package ch8.p1_jpa2.view;

import java.io.Serializable;

public class SummarySinger implements Serializable {
    private String firstName;
    private String lastName;
    private String latestAlbum;

    public SummarySinger(final String firstName, final String lastName, final String latestAlbum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.latestAlbum = latestAlbum;
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

    public String getLatestAlbum() {
        return latestAlbum;
    }

    public void setLatestAlbum(final String latestAlbum) {
        this.latestAlbum = latestAlbum;
    }

    @Override
    public String toString() {
        return "SummarySinger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", latestAlbum='" + latestAlbum + '\'' +
                '}';
    }
}

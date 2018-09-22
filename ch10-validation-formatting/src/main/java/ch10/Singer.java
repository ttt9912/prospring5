package ch10;

import org.joda.time.DateTime;

import java.net.URL;
import java.text.SimpleDateFormat;

public class Singer {
    private String firstName;
    private String lastName;
    private DateTime birthDate;
    private URL personalSite;

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

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public URL getPersonalSite() {
        return personalSite;
    }

    public void setPersonalSite(final URL personalSite) {
        this.personalSite = personalSite;
    }

    @Override
    public String toString() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return String.format("%s{" +
                        "First Name: %s, " +
                        "Last Name: %s, " +
                        "Birthday: %s, " +
                        "Site: %s}",
                getClass().getSimpleName(),
                firstName,
                lastName,
                sdf.format(birthDate.toDate()),
                personalSite);
    }
}

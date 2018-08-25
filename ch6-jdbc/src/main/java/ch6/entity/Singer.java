package ch6.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Singer implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    // relationship
    private List<Album> albums;

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", albums=" + extractAlbumNames() +
                '}';
    }

    private String extractAlbumNames() {
        if (albums == null) {
            return null;
        }

        return albums.stream()
                .map(Album::getTitle)
                .collect(Collectors.joining(","));
    }
}

package ch9.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = Singer.FIND_ALL, query = "select s from Singer s"),
        @NamedQuery(name = Singer.COUND_ALL, query = "select count(s) from Singer s")
})
public class Singer implements Serializable {
    static final String FIND_ALL = "Singer.findAll";
    static final String COUND_ALL = "Singer.countAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Version
    private int version;

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Album> albums;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final Set<Album> albums) {
        this.albums = albums;
    }

    public void addAlbums(final Album... albums) {
        if (this.albums == null) {
            this.albums = new HashSet<>();
        }

        this.albums.addAll(Arrays.asList(albums));
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", version=" + version +
                '}';
    }

    public String withRelationships() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", version=" + version +
                ", \n\t albums=" + albums +
                '}';
    }
}

package ch7.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/*
 * @Version: used by Hibernate for optimistic locking
 *
 * @Table, @Column: obsolete if names are equal
 *
 * All the Field Annotations can also be placed on the Getters.
 *
 * orphanRemoval: after albums are updated, those entries that no longer
 *                exist in the Set should be deleted from the database.
 *
 * select distinct: avoid duplicates; without distinct, a Singer is returned
 *                  twice if he has two albums
 *
 * left join fetch: eager loading: loads relationships
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Singer.findAllWithAlbums",
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i"),
        @NamedQuery(name = "Singer.findById",
                query = "select distinct s from Singer s " +
                        "left join fetch s.albums a " +
                        "left join fetch s.instruments i " +
                        "where s.id = :id")
})
public class Singer implements Serializable {
    // leave public

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

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "singer_id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id"))
    private Set<Instrument> instruments;

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

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final Set<Album> albums) {
        this.albums = albums;
    }

    public boolean addAlbum(final Album album) {
        album.setSinger(this);
        return getAlbums().add(album);
    }

    public void removeAlbum(final Album album) {
        getAlbums().remove(album);
    }

    public Set<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(final Set<Instrument> instruments) {
        this.instruments = instruments;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public String withRelationships() {
        return "Singer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", version=" + version +
                ", \n\talbums=" + albums +
                ", \n\tinstruments=" + instruments +
                '}';
    }
}

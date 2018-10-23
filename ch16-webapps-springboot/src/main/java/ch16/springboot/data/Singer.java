package ch16.springboot.data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "singer")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private int version;

    @NotBlank(message = "{validation.firstname.NotBlank.message}")
    @Size(min = 2, max = 60, message = "{validation.firstname.Size.message}")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "{validation.lastname.NotBlank.message}")
    @Size(min = 1, max = 40, message = "{validation.lastname.Size.message}")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "instruments")
    private Collection<String> instruments;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
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

    public Collection<String> getInstruments() {
        return instruments;
    }

    public void setInstruments(final Collection<String> instruments) {
        this.instruments = instruments;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", instruments=" + instruments +
                '}';
    }
}

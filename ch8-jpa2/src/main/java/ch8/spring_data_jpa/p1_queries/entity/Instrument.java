package ch8.spring_data_jpa.p1_queries.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Instrument implements Serializable {
    // leave public

    @Id
    @Column(name = "instrument_id")
    private String instrumentId;

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "instrument_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id"))
    private Set<Singer> singers;


    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(final String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Set<Singer> getSingers() {
        return singers;
    }

    public void setSingers(final Set<Singer> singers) {
        this.singers = singers;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "instrumentId='" + instrumentId + '\'' +
                '}';
    }
}

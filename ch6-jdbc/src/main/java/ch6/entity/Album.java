package ch6.entity;

import java.io.Serializable;
import java.sql.Date;

public class Album implements Serializable {
    private Long id;
    private Long singerId;
    private String title;
    private Date releaseDate;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(final Long singerId) {
        this.singerId = singerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", singerId=" + singerId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}

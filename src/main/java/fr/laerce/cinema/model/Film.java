package fr.laerce.cinema.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="films")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "title", nullable = true, length = 150)
    private String title;
    @Basic
    @Column(name = "rating", nullable = true, precision = 1)
    private double rating;
    @Basic
    @Column(name = "image_path", nullable = true, length = 120)
    private String imagePath;
    @Basic
    @Column(name = "summary", nullable = true, length = -1)
    private String summary;
    @Basic
    @Column(name="release_date", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    @Basic                          //#
    @Column(name = "idtmdb", nullable = true)        //#
    private Long idtmdb;      //#
    @ManyToOne
    @JoinColumn(name ="film_director", nullable = true)
    @JsonIgnore         //#
    //@JsonManagedReference //#
    private Person director;


    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = false)
    //@JsonBackReference
    @JsonIgnore     //#
    private Set<Play> roles;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="film_genre", joinColumns = @JoinColumn(name="film_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id"))
    //@JsonManagedReference
    @JsonIgnore
    private Set<Genre> genres;


    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private Set<Review> reviews;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person person) {
        this.director = person;
    }

    public Set<Play> getRoles() {
        return roles;
    }

    public void setRoles(Set<Play> roles) {
        this.roles = roles;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }


    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getIdtmdb() {                 //#
        return idtmdb;                              //#
    }                                               //#

    public void setIdtmdb(Long idtmdb) {      //#
        this.idtmdb = idtmdb;                       //#
    }                                               //#

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (title != null ? !title.equals(film.title) : film.title != null) return false;
        if (rating != film.rating ) return false;
        if (imagePath != null ? !imagePath.equals(film.imagePath) : film.imagePath != null) return false;
        if (summary != null ? !summary.equals(film.summary) : film.summary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + ( new Double(rating).hashCode());
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        return result;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId() &&
                Double.compare(film.getRating(), getRating()) == 0 &&
                Objects.equals(getTitle(), film.getTitle()) &&
                Objects.equals(getImagePath(), film.getImagePath()) &&
                Objects.equals(getSummary(), film.getSummary()) &&
                Objects.equals(getReleaseDate(), film.getReleaseDate()) &&
                Objects.equals(getIdtmdb(), film.getIdtmdb());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getRating(), getImagePath(), getSummary(), getReleaseDate(), getIdtmdb());
    }
}

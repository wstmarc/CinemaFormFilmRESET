package fr.laerce.cinema.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="play")

public class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private long id;
    @Basic
    @Column(name = "rank", nullable = false)
    private int rank;
    //@Column(name = "name", nullable = false, length = 90)
    @Basic
    @Column(name = "name", nullable = false)//#
    private String name;
    @ManyToOne
    @JoinColumn(name="person_id")
    @JsonManagedReference
    private Person actor;
    @ManyToOne
    @JoinColumn(name="film_id")
    @JsonManagedReference
    private Film film;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Person getActor() {
        return actor;
    }

    public void setActor(Person acteur) {
        this.actor = acteur;
    }

   public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Play play = (Play) o;

//        if (id != play.id) return false;
        if (id == play.id) return true;
    Play that=(Play)o;
        /*if(
                this.getFilm().equals(that.getFilm())
                &&
                this.getActor().equals((that.getActor()))
                &&
                this.getName().equals(that.getName())
        ){return true;}*/
        return (this.getFilm().equals(that.getFilm())
                &&
                this.getActor().equals((that.getActor()))
                &&
                this.getName().equals(that.getName())
        );

        /*return false;*/
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        return result;
    }
}

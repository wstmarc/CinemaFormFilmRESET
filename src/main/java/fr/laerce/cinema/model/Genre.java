package fr.laerce.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

/**
 * Entité pour les Genres des films
 */
@Entity
@Table(name = "genres")
public class Genre {
    /**
     * L'identifiant unique dans la base
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Le nom du genre
     */
    //Exemples annotations contraintes
    //@Unique
    //@NotBlank
    //@NotNull
    //@Size.List({/* Contraintes */
    //    @Size(min = 3, message = "\"Name is 'too short'.\""),
    //    @Size(max = 30, message = "\"Name is 'TOO LONG'.\"")
    //})
    //    @Size(min=5, max=15, message="Le nom doit faire entre \\{{min}\\} et \\{{max}\\} caractères")//////////
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Basic
    @Column(name = "idtmdb")
    private Long idtmdb;                      //Biggy#

    public Long getIdtmdb() {                 //Biggy#
        return idtmdb;
    }

    public void setIdtmdb(Long idtmdb) {      //Biggy#
        this.idtmdb = idtmdb;
    }

    /**
     * L'ensemble des films associés au genre
     */
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Film> films;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(getId(), genre.getId()) &&
                Objects.equals(getName(), genre.getName()) &&
                Objects.equals(getIdtmdb(), genre.getIdtmdb());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getIdtmdb());
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idtmdb=" + idtmdb +
                '}';
    }
}

/*
package fr.laerce.cinema.model;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "TmdbFilmTitres")
public class TmdbFilm {

    @Id
    @NotNull
    @GeneratedValue
//    @Column(name = "Id", nullable = false)
    private long id; // identifiant interne BDD
    @NotNull
//    @Column(name = "title", nullable = true, length = 60)
    private String title;
    @NotNull
    @Unique
    //@Column(name = "Id", nullable = false, updatable = false)
    private long tmdbid; //identifiant externe extrait de TMDB

    public TmdbFilm(){

    }

    public TmdbFilm(String title, long tmdbid){
        this.title = title;

        this.tmdbid = tmdbid;
    }

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

    public long getTmdbid() {
        return tmdbid;
    }

    public void setTmdbid(long tmdbid) {
        this.tmdbid = tmdbid;
    }
}
*/

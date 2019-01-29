package fr.laerce.cinema.model;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "tmdbfilmtitres")
public class TmdbFilm {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id; // identifiant interne BDD
    @NotNull
    @Column(name = "title")
    private String title;
    @NotNull
    //@Unique
    @Column(name = "tmdbid")
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

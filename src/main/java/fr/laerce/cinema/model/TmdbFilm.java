package fr.laerce.cinema.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name= "tmdb_films")
public class TmdbFilm {
    @Id
    @Column(name = "id", nullable = false)
    private long id; // identifiant interne BDD //Longgy#
    @Basic
    @Column(name = "original_title")
    private String original_title;
    @Basic
    @Column (name="popularity")
    private Double popularity;
    @Basic
    @Column (name="adult")
    private Boolean adult;
    @Basic
    @Column (name="video")
    private Boolean video;

    public TmdbFilm(){}

//    public TmdbFilm (Long id,String original_title,Double popularity, Boolean adult,Boolean video){     //#
    public TmdbFilm (long id,String original_title,Double popularity, Boolean adult,Boolean video){     //Longgy id#
        this.id = id;
        this.original_title= original_title;
        this.popularity= popularity;
        this.adult = adult;
        this.video=video;
    }

/*    public TmdbFilm(String title, long tmdbid){
        this.original_title = title;
        this.id = tmdbid;
    }*/

    public long getId() { //#
        return id;
    }//Longgy#

    public void setId(long id) {
        this.id = id;
    }//Longgy#

    public String getOriginal_title() { //#
        return original_title;
    }
    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }
    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
    public Boolean getAdult() {
        return adult;
    }
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
    public Boolean getVideo() {
        return video;
    }
    public void setVideo(Boolean video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TmdbFilm tmdbFilm = (TmdbFilm) o;
        return Double.compare(tmdbFilm.popularity, popularity) == 0 &&
                Objects.equals(id, tmdbFilm.id) &&
                Objects.equals(original_title, tmdbFilm.original_title) &&
                Objects.equals(adult, tmdbFilm.adult) &&
                Objects.equals(video, tmdbFilm.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, original_title, popularity, adult, video);
    }

    @Override
    public String toString() {
        return "TmdbFilm{" +
                "id=" + id +
                ", original_title='" + original_title + '\'' +
                ", popularity=" + popularity +
                ", adult=" + adult +
                ", video=" + video +
                '}';
    }
}

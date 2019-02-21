package fr.laerce.cinema.model;

import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
//@Table(name= "tmdbfilmtitres")
@Table(name= "tmdb_films")          //#
public class TmdbFilm {
    @Id
    //@NotNull
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
//    private long id; // identifiant interne BDD
    private long id; // identifiant interne BDD
    @Basic                              //#
    //@NotNull
//    @Column(name = "title")
    @Column(name = "original_title")    //#
//    private String title;
    private String original_title;      //#
    //@NotNull
    //@Unique
    /*@Column(name = "tmdbid")
    private long tmdbid; //identifiant externe extrait de TMDB*/

    @Basic                              //#
    @Column (name="popularity")         //#
    private Double popularity;          //#
    @Basic                              //#
    @Column (name="adult")              //#
    private Boolean adult;              //#
    @Basic                              //#
    @Column (name="video")              //#
    private Boolean video;              //#

    public TmdbFilm(){}

//    public TmdbFilm (Long id,String original_title,Double popularity, Boolean adult,Boolean video){     //#
    public TmdbFilm (long id,String original_title,Double popularity, Boolean adult,Boolean video){     //#
        this.id = id;                                                                                   //#
        this.original_title= original_title;                                                            //#
        this.popularity= popularity;                                                                    //#
        this.adult = adult;                                                                             //#
        this.video=video;                                                                               //#
    }                                                                                                   //#

    public TmdbFilm(String title, long tmdbid){
        this.original_title = title;
        this.id = tmdbid;
    }

//    public long getId() {
//        return id;
//    }
    public long getId() { //#
        return id;              //#
    }                           //#

//    public void setId(long id) {
//        this.id = id;
//    }
    public void setId(long id) {
        this.id = id;
    }

    public String getOriginal_title() { //#
        return original_title;          //#
    }                                   //#
    public void setOriginal_title(String original_title) {  //#
        this.original_title = original_title;               //#
    }                                                       //#
    public double getPopularity() {                         //#
        return popularity;                                  //#
    }                                                       //#
    public void setPopularity(double popularity) {          //#
        this.popularity = popularity;                       //#
    }                                                       //#
    public Boolean getAdult() {                             //#
        return adult;                                       //#
    }                                                       //#
    public void setAdult(Boolean adult) {                   //#
        this.adult = adult;                                 //#
    }                                                       //#
    public Boolean getVideo() {                             //#
        return video;                                       //#
    }                                                       //#
    public void setVideo(Boolean video) {                   //#
        this.video = video;                                 //#
    }                                                       //#

    @Override                                                               //#
    public boolean equals(Object o) {                                       //#
        if (this == o) return true;                                         //#
        if (o == null || getClass() != o.getClass()) return false;          //#
        TmdbFilm tmdbFilm = (TmdbFilm) o;                                   //#
        return Double.compare(tmdbFilm.popularity, popularity) == 0 &&      //#
                Objects.equals(id, tmdbFilm.id) &&                          //#
                Objects.equals(original_title, tmdbFilm.original_title) &&  //#
                Objects.equals(adult, tmdbFilm.adult) &&                    //#
                Objects.equals(video, tmdbFilm.video);                      //#
    }                                                                       //#

    @Override                                                               //#
    public int hashCode() {                                                 //#
        return Objects.hash(id, original_title, popularity, adult, video);  //#
    }                                                                       //#

    @Override                                                       //#
    public String toString() {                                      //#
        return "TmdbFilm{" +                                        //#
                "id=" + id +                                        //#
                ", original_title='" + original_title + '\'' +      //#
                ", popularity=" + popularity +                      //#
                ", adult=" + adult +                                //#
                ", video=" + video +                                //#
                '}';                                                //#
    }                                                               //#

/*    public String getTitle() {
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
    }*/
}

package fr.laerce.cinema.api;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Genre;
import fr.laerce.cinema.service.FilmManager;
import fr.laerce.cinema.service.TmdbClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/api/film")
public class FilmRestController {
    @Autowired
    private FilmManager filmManager;

    @Autowired
    TmdbClient tmdbClient;

   /* public FilmRestController(){
        //assert(filmManager != null);
        //this.filmManager = filmManager;
    }*/

    @PostMapping("/add")
    public Film submit(@RequestBody Film film) {
        Film filmloc = filmManager.getById(film.getId());
        filmloc.setTitle(film.getTitle());
        filmManager.saveFilm(filmloc);
        return filmloc;
    }

    @GetMapping("")
    public List<Film> getAll(){
        return filmManager.getAll();
    }

    @GetMapping("/{id}")
    public Film getById(@PathVariable("id")long id){
        return filmManager.getById(id);
    }

    /**                                             //#
     * @param id                                    //#
     * @return                                      //#
     */                                             //#
    @DeleteMapping("/{id}")                                                                                                     //#
    public Film remove(@PathVariable("id") long id) {                                                                           //#
        return filmManager.delete(id);                                                                                          //#
    }                                                                                                                           //#
    @GetMapping("/tmdb/{idtmdb}")                                                                                               //#
    public Film tmdbfilm(@PathVariable("idtmdb") long idtmdb, RedirectAttributes redirectAttrs) throws Exception {        //#
        tmdbClient.getMovieByTmdbId(idtmdb);                                                                                    //#
        redirectAttrs.addAttribute("message","film ajouté !!!");                                                               //#
        Film film = filmManager.findByIdTmdb(idtmdb);                                                                           //#
        return film;                                                                                                            //#
    }                                                                                                                           //#

/*    @PutMapping("/genres")
    public List<Genre> updateGenres() {
        *//* TODO *//*
        *//* 1) Je récupère l'ID du film (et les ids des genres)
         * 2) findById du film
         * 3) suppression des genres du film
         * 4) pour chaque genre
         *          findById du genre et ajout du film
         * 5) Save du film*//*

    }*/

   /* *//**
     *
     * @param film
     * @return
     *//*
    @PostMapping("")
    public Film add(@RequestBody Film film){
        if (film.getTitle().isEmpty()) throw new IllegalArgumentException("Title is empty");
*//*        if (genre.getName().length() < 3) throw new IllegalArgumentException("Name is 'too short'");
        if (genre.getName().length() > 30) throw new IllegalArgumentException("Name is 'TOO LONG'");*//*
        return filmManager.save(film);
    }

    *//**
     *
     * @param film
     * @return
     *//*
    @PutMapping("")
    public Long mod(@RequestBody Film film){
        return filmManager.save(film);
    }

    *//**
     *
     * @param id
     * @return
     *//*
    @DeleteMapping("/{id}")
    public Film remove(@PathVariable("id") long id){
        return filmManager.delete(id);
    }

    *//**
     *
     * @param id
     * @return
     *//*
    @GetMapping("/rm/{id}")
    public Film rm(@PathVariable("id")long id){
        return filmManager.delete(id);
    }*/
}

package fr.laerce.cinema.api;

import fr.laerce.cinema.model.Film;
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

    public FilmRestController(FilmManager filmManager){
        assert(filmManager != null);
        this.filmManager = filmManager;
    }

//    @PostMapping("/add")
//    public Film submit(@RequestBody Film film) {
//        Film filmloc = filmManager.getById(film.getId());
//        filmloc.setTitle(film.getTitle());
//        filmManager.saveFilm(filmloc);
//        return filmloc;
//    }

    @GetMapping("")
    public List<Film> getAll(){
        return filmManager.getAll();
    }

    @GetMapping("/{id}")
    public Film getById(@PathVariable("id")long id){
        return filmManager.getById(id);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Film remove(@PathVariable("id") long id) {
        return filmManager.delete(id);
    }
    @GetMapping("/tmdb/{idtmdb}")
    public Film tmdbfilm(@PathVariable("idtmdb") long idtmdb, RedirectAttributes redirectAttrs) throws Exception {
        tmdbClient.getMovieByTmdbId(idtmdb);
        redirectAttrs.addAttribute("message","film ajouté !!!");
        Film film = filmManager.findByIdTmdb(idtmdb);
        return film;
    }
}

package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.TmdbFilmDao;
//import fr.laerce.cinema.service.ImportFilmsFromTMDB;
import fr.laerce.cinema.service.TmdbFilmManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {
    @Autowired
    private TmdbFilmDao tmdbFilmDao;

    @Autowired
    //équivalent
//    private ImportFilmsFromTMDB importFilmsFromTMDB;
    private TmdbFilmManager importFilmsFromTMDB;

    @GetMapping("/populate")
    public String populate(Model model) {

        model.addAttribute("nom", "FriteMoule");

//        ImportFilmsFromTMDB importFilmsFromTMDB = new ImportFilmsFromTMDB(tmdbFilmDao);
//        importFilmsFromTMDB.ImportMoviesViaOnlineTmdbFile();
        importFilmsFromTMDB.ImportMoviesViaLocalTempTmdbFile();

        //TODO: Retourner vers une interface d'administration plutôt que l'index
        return "index";
    }
}
package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.TmdbFilmDao;
import fr.laerce.cinema.model.User;
import fr.laerce.cinema.service.ImportFilmsFromTMDB;
import fr.laerce.cinema.service.JpaUserService;
//import fr.laerce.cinema.service.TmdbFilmManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;//bibliothèque utile pour les MESSAGES FLASH.

import java.math.BigInteger;

//@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    private TmdbFilmDao tmdbFilmDao;


    //équivalent
//    private ImportFilmsFromTMDB importFilmsFromTMDB;
    @Autowired
    private ImportFilmsFromTMDB importFilmsFromTMDB;//#
//@Autowired
//    private TmdbFilmManager importFilmsFromTMDB;

    //    private UserDao userDao;
    @Autowired
    private JpaUserService jpaUserService;

    @GetMapping("/populate")
    public String populate(Model model) {

        model.addAttribute("nom", "FriteMoule");

//        ImportFilmsFromTMDB importFilmsFromTMDB = _new ImportFilmsFromTMDB(tmdbFilmDao);
//        importFilmsFromTMDB.ImportMoviesViaOnlineTmdbFile();
        importFilmsFromTMDB.ImportMoviesViaLocalTempTmdbFile();

        //TODO: Retourner vers une interface d'administration plutôt que l'index
        return "importfilm";
    }

    @GetMapping("/accueil")
    public String accueil(){ return "accueil"; }

    @GetMapping("/import")
    public String rechercher(){return "importfilm";}

    /* MAPPINGS gérés avec SPRING SECURITY */
    @GetMapping("/userlist")
    public String userlist(Model model) {
//        Iterable<User> users = userDao.findAll();
        Iterable<User> users = jpaUserService.list();
        model.addAttribute("userList", users);
        System.out.println("liste des utilisateurs: " + users);//
//        return "admin/userlist";
        return "userlist";
//        return "/newpass";
    }

    @GetMapping("/newpass/{id}")
    public String newpassId(@PathVariable Long id, Model model){
        System.out.println("Mapping GET newpass");
        model.addAttribute("userId", id);
        return "newpass";
    }

/*    @GetMapping("/newpass")
    public String newpass(){
        return "newpass";
    }*/

//Le name du formulaire doit correspondre au @RequestParam

    @PostMapping("/newpass")
    //public String changepass(@RequestParam Long id, @RequestParam String userPass1, @RequestParam String userPass2, RedirectAttributes attributes){
    public String changepass(@RequestParam Long id, @RequestParam String userPass1, @RequestParam String userPass2, RedirectAttributes attributes){
        System.out.println("Mapping POST newpass");
        User user = jpaUserService.findById(id);
        if(!userPass1.equals(userPass2)){
            attributes.addFlashAttribute("messageFlash","Assurez-vous que vos mots de passe soient identiques!");
            return "redirect:/newpass/"+id;
        } else {
            user.setPassword(userPass1);//instance d'utilisateur avec le mdp modifié
            jpaUserService.save(user);//sauvegarde de l'utilisateur en BDD
        }
        return "redirect:/home";
    }
}
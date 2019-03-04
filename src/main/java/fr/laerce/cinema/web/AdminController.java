package fr.laerce.cinema.web;

import fr.laerce.cinema.dao.TmdbFilmDao;
//import fr.laerce.cinema.service.ImportFilmsFromTMDB;
import fr.laerce.cinema.model.User;
import fr.laerce.cinema.service.JpaUserService;
import fr.laerce.cinema.service.TmdbFilmManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class AdminController {
    @Autowired
    private TmdbFilmDao tmdbFilmDao;

    @Autowired
    //équivalent
//    private ImportFilmsFromTMDB importFilmsFromTMDB;
    private TmdbFilmManager importFilmsFromTMDB;

    //    private UserDao userDao;
    @Autowired
    private JpaUserService jpaUserService;

    @GetMapping("/populate")
    public String populate(Model model) {

        model.addAttribute("nom", "FriteMoule");

//        ImportFilmsFromTMDB importFilmsFromTMDB = new ImportFilmsFromTMDB(tmdbFilmDao);
//        importFilmsFromTMDB.ImportMoviesViaOnlineTmdbFile();
        importFilmsFromTMDB.ImportMoviesViaLocalTempTmdbFile();

        //TODO: Retourner vers une interface d'administration plutôt que l'index
        return "index";
    }


    //    @GetMapping("admin/newpass")
    @GetMapping("/userlist")
    public String userlist(Model model) {
//        Iterable<User> users = userDao.findAll();
        Iterable<User> users = jpaUserService.list();
        model.addAttribute("userList", users);
        System.out.println("liste des utilisateurs: " + users);//
//        return "redirect:/admin/userlist";
//        return "admin/userlist";
        return "/userlist";
//        return "/newpass";
    }

    @GetMapping("/newpass/{id}")
    public String newpass(@PathVariable Long id, Model model){
        model.addAttribute("userId", id);
        return "/newpass";
    }

    @PostMapping("/newpass")
    public String changepass(@RequestParam Long id, @RequestParam String userPass1, @RequestParam String userPass2, RedirectAttributes attributes){

        System.out.println("Entrée POST newpass");
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
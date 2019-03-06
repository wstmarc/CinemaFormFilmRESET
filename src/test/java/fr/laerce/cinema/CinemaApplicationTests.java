package fr.laerce.cinema;

import fr.laerce.cinema.dao.UserDao;
import fr.laerce.cinema.model.Groups;
import fr.laerce.cinema.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CinemaApplicationTests {

    @Test
    public void contextLoads() {
    }

    private UserDao userDao;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Test
    @Rollback
    public void testGetAdmin(){

//        User user = userDao.findOne(1L);
        User user = userDao.findById(1L);//1L c'est l'entier 1 converti en Long
        assertEquals("L'utilisateur est Admin",user.getName(),"Admin");
        user = userDao.findByName("Admin");
        assertNotNull("Admin est bien récupéré en minucsules", user);
        log.info("Nom utilisateur : "+ user.getName());
        Collection<Groups> groupss = user.getGroups();
        assertEquals("Admin appartient exactement à 2 groupes",
                groupss.size(),2);
        for(Groups grp: groupss){
            log.info("Groupe : "+ grp.getName()+" | Rôle : "+ grp.getRole());
        }
        log.info("Mot de passe : "+user.getPassword());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(user.getPassword()));
        log.info("p@ssword :"+passwordEncoder.encode("p@ssw0rd"));
//        log.info("p@ssword :"+passwordEncoder.encode("p@ssw0rd"));
        assertTrue("Mot de passe est 'p@ssw0rd' :",
                passwordEncoder.matches("p@ssw0rd", user.getPassword()));
    }

}


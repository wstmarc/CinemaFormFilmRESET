package fr.laerce.cinema.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Projet thyme-security
 * Pour LAERCE SAS
 * <p>
 * Créé le  22/03/2017.
 *
 * @author fred
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        TODO configuration MvcConfig : Retirer les pseudo-mappings
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/index").setViewName("index");//#
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/userlist").setViewName("userlist");//#
        //registry.addViewController("/newpass").setViewName("newpass");//#
    }

}

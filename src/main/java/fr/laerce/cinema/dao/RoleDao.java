package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Person;
import fr.laerce.cinema.model.Play;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends CrudRepository<Play, Long> {
    public List<Play> findByFilm_IdOrderByRankAsc(long id);
    public boolean existsByFilmAndActorAndName(Film film, Person actor, String name);//#
    //public List<Play> findAllByRankOrderByRank(int min, int max);//coté BDD
}

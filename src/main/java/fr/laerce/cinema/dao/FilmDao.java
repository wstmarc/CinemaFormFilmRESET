package fr.laerce.cinema.dao;

import fr.laerce.cinema.model.Film;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmDao extends CrudRepository<Film, Long> {
    public List<Film> findAllByOrderByTitle();
    public Film findByTitle(String name);
    public boolean existsByIdtmdb(long id);   //Biggy#
    public Film findByIdtmdb(long id);        //Biggy#
}

package fr.laerce.cinema.dao;


import fr.laerce.cinema.model.TmdbFilm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface TmdbFilmDao extends CrudRepository<TmdbFilm, Long> {
//    public TmdbFilm findByTmdbid(long id);//#
    public TmdbFilm findById(long id);//Biggy#
}

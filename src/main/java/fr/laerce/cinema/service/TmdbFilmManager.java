/*
package fr.laerce.cinema.service;

import fr.laerce.cinema.dao.TmdbFilmDao;
import fr.laerce.cinema.model.TmdbFilm;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.zip.GZIPInputStream;

@Component
public class TmdbFilmManager {
    private TmdbFilmDao tmdbFilmDao;

    public TmdbFilmManager(TmdbFilmDao tmdbFilmDao){
        this.tmdbFilmDao = tmdbFilmDao;
    }

    public void importMovies(){
        LocalDate date = LocalDate.now().minusDays(1);
        String day = String.format("%02d", date.getDayOfMonth());
        String month = String.format("%02d", date.getMonthValue());
        String year = String.valueOf(date.getYear());
        String fileName = "movie_ids_" + month + "_" + year + ".json.gz";
        String url = "http://files.tmdb.org/p/exports/" + fileName;
        try{
            BufferedInputStream bis = new BufferedInputStream(
                    new GZIPInputStream(
                            new URL(url).openStream()
                    )
            );
            BufferedReader br = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8));
            String line;
            while((line = br.readLine()) != null){
                JSONObject json = new JSONObject(line);
                String title = json.get("original_title").toString();
                long tmmdgId = Long.valueOf(json.get("id").toString());
                boolean adult = Boolean.valueOf(json.get("adult").toString());
                TmdbFilm film = new TmdbFilm(title, tmmdgId);
                if(!adult && tmdbFilmDao.findByTmdbId(tmmdgId) == null){
                    tmdbFilmDao.save(film);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException j){
            j.printStackTrace();
        }
    }
}
*/

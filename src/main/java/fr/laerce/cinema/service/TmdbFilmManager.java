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
        String fileName = "movie_ids_" + month + "_" + day + "_" + year + ".json.gz";
        //On a l'url dynamique
        String url = "http://files.tmdb.org/p/exports/" + fileName;

        try{
            //1 Téléchargement
            //2 Dézipper
            //3 Lire bloc par bloc le fichier
            BufferedInputStream bis = new BufferedInputStream(//3 fichier temporaire (tampon de TMDB)
                    new GZIPInputStream(//2 décompression du fichier gzip
                            new URL(url).openStream()//1 ouverture du stream, en fonction de l'url
                    )
            );

            //Parsing du fichier
            BufferedReader br = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8));
            String line;
            while((line = br.readLine()) != null){
                JSONObject json = new JSONObject(line);
                String title = json.get("original_title").toString();
                long tmmdgId = Long.valueOf(json.get("id").toString());
                boolean adult = Boolean.valueOf(json.get("adult").toString());
                TmdbFilm film = new TmdbFilm(title, tmmdgId);
                if(!adult && tmdbFilmDao.findByTmdbid(tmmdgId) == null){
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

package fr.laerce.cinema.service;

import fr.laerce.cinema.dao.TmdbFilmDao;
import fr.laerce.cinema.model.TmdbFilm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.zip.GZIPInputStream;

@Component
public class TmdbFilmManager {
    private TmdbFilmDao tmdbFilmDao;

    /*  TODO: recréer une méthode qui prend en compte la connexion (authentification TMDB)*/
    //   !!!!!!!! AVEC AUTHENTIFICATION  !!!!!!!
    @Value("${tmdb.api.key}") // <<--
    private String apiKey;

    public TmdbFilmManager(TmdbFilmDao tmdbFilmDao){
        this.tmdbFilmDao = tmdbFilmDao;
    }

    public void importMovies(){
//       !!!! SANS AUTHENTIFICATION  !!!!!!!
        LocalDate date = LocalDate.now().minusDays(1);
        String day = String.format("%02d", date.getDayOfMonth());
        String month = String.format("%02d", date.getMonthValue());
        String year = String.valueOf(date.getYear());
        String fileName = "movie_ids_" + month + "_" + day + "_" + year + ".json.gz";
        //URL DYNAMIQUE
        //url sans authentification
        String url = "http://files.tmdb.org/p/exports/" + fileName; // <<-- All of the exported files are available for download from http://files.tmdb.org
        //There is currently no authentication on these files since they are mostly useless unless you're a user of our service.

        //url sans authentification
        url = "http://files.tmdb.org/p/exports/" + fileName; // <<-- All of the exported files are available for download from http://files.tmdb.org

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

    /*private long secondsBeforeReset(String value){
        long timestamp = Long.valueOf(stripBraces(value));
        LocalDateTime resetTime =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();
        return now.until( resetTime, ChronoUnit.SECONDS);
    }

    private String stripBraces(String value){
        return value.substring(0, value.length()-1).substring(1);
    }

//    TODO adapter le code de la méthode getMovieByTmdbId à la méthode importMovieById
    public void importMovieById(long id) throws Exception{
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response;
        long reset;

        String resourceUrl = "https://api.themoviedb.org/3/movie/"+id+"?api_key="+apiKey+"&language=fr-FR";
        response = template.getForEntity(resourceUrl, String.class);
        System.out.println(response.getBody());
        JSONObject film = new JSONObject(response.getBody());
        JSONArray genres = (JSONArray) film.get("genres");
        System.out.println("Titre : "+film.getString("title"));
        for(int i = 0; i < genres.length(); i++){
            JSONObject genre = (JSONObject) genres.get(i);
            System.out.println("- Genre : "+genre.getString("name"));
        }

        System.out.println("--------\nRequetes restantes : "+stripBraces(response.getHeaders().get("x-ratelimit-remaining").toString())); //stripBraces() pour enlever les crochets.
        reset = secondsBeforeReset(response.getHeaders().get("x-ratelimit-reset").toString());
        System.out.println("Temps restant avant reset : "+reset+"\n\n");

        String resourceCredit = "https://api.themoviedb.org/3/movie/"+id+"/credits?api_key="+apiKey+"&language=fr-FR";
        response = template.getForEntity(resourceCredit, String.class);
        JSONObject credit = new JSONObject(response.getBody());
        JSONArray cast = (JSONArray) credit.get("cast");
        for (int i = 0; i < cast.length(); i++ ) {
            JSONObject role = (JSONObject) cast.get(i);
            System.out.println(role.getString("name")+" joue "+ role.getString("character"));
        }
        System.out.println("--------\nRequetes restantes : "+stripBraces(response.getHeaders().get("x-ratelimit-remaining").toString()));
        reset = reset = secondsBeforeReset(response.getHeaders().get("x-ratelimit-reset").toString());
        System.out.println("Temps restant avant reset : "+reset+"\n\n");
    }*/


}

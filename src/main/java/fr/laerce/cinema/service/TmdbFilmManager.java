/*
package fr.laerce.cinema.service;

import com.google.common.io.ByteStreams;
import fr.laerce.cinema.dao.TmdbFilmDao;
import fr.laerce.cinema.model.TmdbFilm;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
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
    @Value("${cinema.tmp.path}")        //#
    private String tmpFilePath;         //#

    @Autowired  //#
    private TmdbFilmDao tmdbFilmDao;

    //   !!!!!!!! AVEC AUTHENTIFICATION  !!!!!!!
    @Value("${tmdb.api.key}") // <<-- //TODO vérifier la clé TMDB : tmdbfilmmanager
    private String apiKey;

    public TmdbFilmManager(TmdbFilmDao tmdbFilmDao){
        this.tmdbFilmDao = tmdbFilmDao;
    }

    public void importMovies(){
        //On récupère la date du jour pour éviter de charger des fichiers déjà dépubliés de TMDb.
        LocalDate date = LocalDate.now().minusDays(1);
        //Formatage de la date pour ajouter des 0 pour les mois et les jours inférieurs à 10
        String day = String.format("%02d", date.getDayOfMonth());
        String month = String.format("%02d", date.getMonthValue());
        String year = String.valueOf(date.getYear());
        //Construction de l'URL DYNAMIQUE
        //url sans authentification
        String fileName = "movie_ids_" + month + "_" + day + "_" + year + ".json.gz";
        String url = "http://files.tmdb.org/p/exports/" + fileName; // <<-- All of the exported files are available for download from http://files.tmdb.org

        try{
            //1 Téléchargement
            //2 Dézipper
            //3 Lire bloc par bloc le fichier
            BufferedInputStream bis = _new BufferedInputStream(//3 fichier temporaire (tampon de TMDB)
                    _new GZIPInputStream(//2 décompression du fichier gzip
                            _new URL(url).openStream()//1 ouverture du stream, en fonction de l'url
                    )
            );
            //AUTRE MÉTHODE
*/
/*            // file stream
            InputStream httpIS = _new URL(url).openStream();
            // unzip file
            InputStream gzipIS = _new GZIPInputStream(httpIS);
            // buffering the file : read block by block to gain performance
            InputStream bufferedIS = _new BufferedInputStream(gzipIS);

            System.out.println(bufferedIS);*//*


            //Parsing du fichier avec la bibliothèque json.org
            BufferedReader br = _new BufferedReader(_new InputStreamReader(bis, StandardCharsets.UTF_8));
            String line;
            while((line = br.readLine()) != null){
                JSONObject json = _new JSONObject(line);
                String title = json.get("original_title").toString();
                long tmdbId = Long.valueOf(json.get("id").toString());
//                long tmdbId = Long.parseLong(json.get("id").toString());
                boolean adult = Boolean.valueOf(json.get("adult").toString());
                TmdbFilm film = _new TmdbFilm(title, tmdbId);
                if(!adult && tmdbFilmDao.findById(tmdbId) == null){
                    tmdbFilmDao.save(film);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException j){
            j.printStackTrace();
        }
    }

    public void ImportMoviesViaLocalTempTmdbFile() {
        // we recover the date of the day before to avoid loading a file still unpublished by TMDV
        LocalDate date = LocalDate.now().minusDays(1);
        // date format to add zeros for months and days less than 10
        String day = String.format("%02d", date.getDayOfMonth());
        String month = String.format("%02d", date.getMonthValue());
        String year = String.valueOf(date.getYear());


        // url construction
        String fileName = "movie_ids_"+month+"_"+day+"_"+year+".json.gz";
        String url = "http://files.tmdb.org/p/exports/"+fileName;

        String tmpFile = tmpFilePath+""+fileName;

        try {
            // file stream
            InputStream is = _new URL(url).openStream();
            // write file stream datas in a temp local file (tmpFile)
            FileOutputStream fos = _new FileOutputStream(tmpFile);
            // do it with Guava (Google API for Java)
            ByteStreams.copy(is, fos);
            is.close();
            fos.flush();
            fos.close();
            BufferedInputStream bis = _new BufferedInputStream(_new GZIPInputStream(_new FileInputStream(tmpFile)));

            // parsing the file with json.org library
            BufferedReader br = _new BufferedReader(_new InputStreamReader(bis, StandardCharsets.UTF_8));
            String line;
            while((line = br.readLine()) != null) {
                JSONObject json = _new JSONObject(line);
                String title = json.get("original_title").toString();
                long tmdbId = Long.valueOf(json.get("id").toString());
                boolean adult = Boolean.valueOf(json.get("adult").toString());
                TmdbFilm film = _new TmdbFilm(title, tmdbId);
                if(!adult && tmdbFilmDao.findById(tmdbId) == null) {
                    tmdbFilmDao.save(film);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
*/

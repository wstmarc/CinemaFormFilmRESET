package fr.laerce.cinema.service;

import fr.laerce.cinema.model.Film;
import fr.laerce.cinema.model.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageManager {
    @Value("${cinema.img.path}")//chemin d'accès au répertoire où les images sont stockées.
    String path;

    //Sauvegarde la photo d'un acteur dans le dossier "${cinema.img.path} + /personnesS"
    public int savePhoto(Person p, InputStream fi){
        p.setImagePath(save("p", "personnesS", fi));
        return 0;
    }

    //Sauvegarde le poster d'un film dans le dossier "${cinema.img.path} + /affichesS"
    public int savePoster(Film f, InputStream fi) {
        f.setImagePath(save("f","affichesS", fi));
        return 0;
    }

    //Méthode permettant de sauvegarder un fichier :*automatiquement numéroté et *préfixé d'une lettre + un caractère spécial (*).
    private String save(String prefix, String subPath, InputStream fi){
        String fileName = "";
        //"dir" correspond au chemin d'accès au répertoire désiré: "'${cinema.img.path}' + '/' + 'subPath(selon sa valeur)' + 'prefix(selon sa valeur)' + '*'"
        try(DirectoryStream<Path> dir = Files.newDirectoryStream(Paths.get(path+"/"+subPath),prefix+"*")){
            //analyse de tous les fichiers du répertoire "dir".
            for (Path file: dir
            ) {
//compareTo: <0 -> la valeur de l'instance fileName précède la valeur indiquée en paramètres.
//           =0 -> la valeur de l'instance fileName vaut la même valeur indiquée en paramètres.
//           >0 -> la valeur de l'instance fileName suit la valeur indiquée en paramètres.
                if(fileName.compareTo(file.getFileName().toString())<0){//si le nom du fichier a créer ne précède pas l'un de ceux existants dans le dossier:
//autrement dit: si le nom de fichier est le dernier de la liste des fichiers analysés:
                    fileName = file.getFileName().toString();//alors "fileName" prend comme valeur le nom de ce fichier(le dernier dont le nom a été lu dans le dossier).
                }
            }
            String numStr = fileName.substring(1, fileName.indexOf(".jpg"));

            Integer num = Integer.parseInt(numStr);

            numStr = String.format("%04d",num+1);//le numéro de l'image est incrémenté

            fileName = prefix+numStr+".jpg";

            String filePath = path+"/"+subPath+"/"+fileName;

            Files.copy(fi, new File(filePath).toPath());

        }catch (IOException ioe){
            System.out.println("Erreur sur nom d'image : "+ioe.getMessage());
        }


        return fileName;
    }

    public InputStream retreivePhoto(String fileName){
        return retreiveImage("personnesS", fileName);
    }

    public InputStream retreivePoster(String fileName){
        return retreiveImage("affichesS", fileName);
    }

    private InputStream retreiveImage(String subPath, String fileName){
        InputStream is = null;
        try {
            is = new FileInputStream(path+"/"+subPath+"/"+fileName);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Erreur récupération de l'image "+fileName+" : "+fnfe.getMessage());
        }
        return is;
    }
}

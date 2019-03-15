/*
//déclaration d'un tableau statique
tableau = ['ima1', 'ima2', 'ima3', 'ima4', 'ima5', 'ima6'];

//Au chargement du document
//On affiche les images

$(document).ready(
    function () {
        for (var i = 0; i < tableau.length; i++) {
            var src = tableau[i] + ".png";
            alert(src);
            var chaine = "<img src='" + src + "' width='150' height='100' class='img-thumbnail' />";
            alert(chaine);
            $("#album").append(chaine);
        }
    }
);
*/


//FREDERiC HELPS
// déclaration d'un tableau statique
tableau = ['ima1','ima2','ima3','ima4','ima5'];

// au chargement du document on affiche les images
$(document).ready
(
    function()
    {
        for(var i=0; i<tableau.length; i++)
        {
            var src=tableau[i]+".png";
            var chaine="<img src='img/"+src+"' width='150' height='100' class='img-thumbnail' />"
            $("#album").append(chaine);
        }
    }
);
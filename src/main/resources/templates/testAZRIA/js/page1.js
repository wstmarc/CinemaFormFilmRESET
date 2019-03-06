$("#valider").click
(
    function()
    {
        var resultat = estComplet();

        //récupération des valeurs
        var nom = $("#nom").val();
        var email1 = $("#email1").val();
        var email2 = $("#email2").val();
        var mdp1 = $("#mdp1").val();
        var mdp2 = $("#mdp2").val();

        if(resultat)
        {// az@er.ty
            // 12345678
            if(!concordance(email1, email2)){
            //MAILS NON CONCORDANTS.
                //on colore en orange les champs correspondants à email1 et email2.
                alert("PROBLEME: emails non identiques !!!");
                $("#email1, #email2").addClass('bg-warning');
            }  if(!concordance(mdp1, mdp2)){
            //MOTS DE PASSE NON CONCORDANTS.
                //on colore en orange les champs correspondants à mdp1 et mdp2.
                alert("PROBLEME: mots de passe non identiques !!!");
                $("#mdp1, #mdp2").addClass('bg-warning');
            }  if(!nom.length >= 3){
            //NOM TROP COURT.
                //console.log("nom.size(): ",nom.size());//FAKE
                //console.log("nom.size: ",nom.size);//FAKE
                //console.log("nom.length(): ",nom.length());//FAKE
                console.log("nom.length: ",nom.length);//GOOD
                //on colore en orange le champs correspondant nom.
                alert("PROBLEME: la longueur du nom est inférieure à 3!!!");
                $("#nom").addClass('bg-warning');

            } else {
                console.log("nom: ",nom, "longueur: ", nom.length);
                //MOTS DE PASSE CONCORDANTS ET MAILS CONCORDANTS ET LONGUEUR(NOM) >= 3.
                //on colore en vert tous les champs.
                $(".saisie").addClass('bg-success');
                // $(this).removeAttr('class').attr('class','');
                //$(this).attr('class').addClass('btn-success');
                $(this).addClass('btn-success');
            }
        }
        //BONUS : test changement de couleur : BONUS /////
        if($(this).hasClass('btn-info')){				//
            $(this).addClass('btn-danger');			//
            $(this).removeClass('btn-info','btn-success');
        } else {										//
            $(this).addClass('btn-info');			//
            $(this).removeClass('btn-danger','btn-success');
        }/////////////////////////////////////////////////
    }

);

//click sur des éléments de saisie
$(".saisie").focus
(
    function()
    {
        $(this).removeClass('bg-warning');
    }
);

//fonctions définies
function estComplet()
{
    resultat = true;
    //parcours de tous les éléments de la classe 'saisie'.
    $(".saisie").each
    (
        function()
        {
            //on recupère la valuer de l'objet courant
            var valeur = $(this).val();
            if(!valeur){
                resultat=false;
                $(this).addClass('bg-warning');
            }
        }
    );
    //alert("le formulaire est complété"); //debug
    return resultat;
}

//concordance de deux champs
function concordance(champ1, champ2)
{
     	console.log("champ1: ", champ1);
        console.log("champ2: ", champ2);
        console.log("concordance: ", champ1==champ2);
    return champ1 == champ2;
}



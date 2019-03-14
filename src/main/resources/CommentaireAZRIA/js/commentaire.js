$("#comm").keyup
(
    function()
    {
        var texte = $("#comm").val();

        if(texte.length==0){
            alert("Invalidable !!");
            $("#bouton").prop("disabled",true);
        } else {
            alert("Validable");
            $("#bouton").prop("disabled", false);
        }
    }
);

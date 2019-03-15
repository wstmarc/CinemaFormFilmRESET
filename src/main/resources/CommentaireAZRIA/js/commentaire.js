$("#comm").keyup
(
    function()
    {
        var texte = $("#comm").val();

        if(texte.length==0){
            alert("Invalidable !!");
            $("#bouton").prop("disabled",true);
            $("#bouton").css('background-color','red');
        } else {
            alert("Validable");
            $("#bouton").prop("disabled", false);
            $("#bouton").css('background-color','green');
        }
    }
);

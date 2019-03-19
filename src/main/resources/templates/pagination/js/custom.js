(function($){

    $(document).ready(function(){
        // alert("Bienvenue dans l'exercice de pagination!");

        $(".pagination").customPaginate({
            itemsToPaginate : ".post"
        });
    });
})(jQuery)
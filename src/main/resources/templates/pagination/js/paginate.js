(function($){

    // alert("Bienvenue dans l'exercice de pagination!");

    $.fn.customPaginate = function(options){
        var paginationContainer = this;
        var itemsToPaginate;

        var defaults = {
            itemsPerPage : 5
        };

        var settings = {};

        $.extend(settings, defaults, options);
        console.log("items to paginate initial:", itemsToPaginate);

        var itemsPerPage = settings.itemsPerPage;

        itemsToPaginate = $(settings.itemsToPaginate);
        console.log("items to paginate after:", itemsToPaginate);
        var numberOfPaginationLinks = Math.ceil((itemsToPaginate.length / itemsPerPage));

        // $("<ul></ul>").prependTo(paginationContainer).addClass("navcenter");
        $("<ul></ul>").prependTo(paginationContainer);

        for(var index = 0; index < numberOfPaginationLinks; index++){
            paginationContainer.find("ul").append("<li>"+ (index+1) +"</li>");
        }

        itemsToPaginate.filter(":gt(" + (itemsPerPage - 1) + ")").hide();

        // paginationContainer.find("ul li").on('click', function(){
        paginationContainer.find("ul li").first().addClass(settings.activeClass).end().on('click', function(){//#
            var $this = $(this);//#
            $this.addClass(settings.activeClass);//#
            $this.siblings().removeClass(settings.activeClass);
            // var linkNumber = $(this).text();
            var linkNumber = $this.text();//#

            var itemsToHide = itemsToPaginate.filter(":lt(" + ((linkNumber- 1) * itemsPerPage) + ")");
            $.merge(itemsToHide, itemsToPaginate.filter(":gt(" + ((linkNumber * itemsPerPage) - 1) + ")"));
            itemsToHide.hide();

            var itemsToShow = itemsToPaginate.not(itemsToHide);
            itemsToShow.show();
            // $(this).setAttribute("class",'paginActuelle');
            //TODO le lien sélectionné se fait ajouter la classe paginActuelle, les autres la classe paginAutres
            // $(this).addClass('paginActuelle');//-#-
            // $(this).css({"background-color":"white", "color": "blue", "border":"blue"});//-#-
        });
    }
}(jQuery));
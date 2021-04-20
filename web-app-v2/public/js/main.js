'use strict';

$( document ).ready(function() {

    console.log(document.location.pathname);
    var fadeSlideClasses = "remove-fade-slide-right remove-fade-slide-left remove-fade-slide-top remove-fade-slide-bottom";
    var fadeWidthClasses = "remove-fade-x remove-fade";
    var fadeOnlyClasses = "remove-fade";

    if (document.location.pathname === "/spilledcoffee-mvc/") {
        $(".fade-1s").removeClass(fadeSlideClasses + " " + fadeWidthClasses);
    }

    if (document.URL.indexOf("products") >= 0){
        var timeout = 100;
        var items = $( ".fade-1s").toArray();
        for (let i = 0; i < items.length ; i++) {
            setTimeout(() => {
                console.log(items[i]);
                $(items[i]).removeClass(fadeSlideClasses + " " + fadeWidthClasses);
            }, timeout);
            timeout += 150;
        }
        console.log('Done!');
    }

    if (document.URL.indexOf("cart") >= 0) {
        $(".fade-1s").removeClass(fadeSlideClasses + " " + fadeWidthClasses);
    }

    if (document.URL.indexOf("orders") >= 0) {
        $(".fade-1s").removeClass(fadeSlideClasses + " " + fadeWidthClasses);
    }

    if (document.URL.indexOf("checkout") >= 0) {
        $(".fade-1s").removeClass(fadeSlideClasses + " " + fadeWidthClasses);
    }

    if (document.URL.indexOf("about") >= 0) {
        $(".fade-1s").removeClass(fadeSlideClasses + " " + fadeWidthClasses);
    } 

});
window.onload = function(){
    var wrap = document.getElementsByClassName("wrap")[0];
    var cover = document.getElementsByClassName("cover")[0];
    var close = document.getElementsByClassName("close")[0];
    var hidden = document.getElementsByClassName("hidden")[0];
    wrap.onclick = function(){
        hidden.style.display = "block";
        cover.style.display = "block";
    };
    close.onclick = function(e){
        hidden.style.display = "none";
        cover.style.display = "none";
        e.stopPropagation();
    }
};
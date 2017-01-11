window.onload = function(){
    var li = document.getElementsByTagName("li");
    for(var i=0;i<li.length;i++){
        (function(i){
            li[i].onmouseover = function(){
                li[i].style.width =  475+"px";

                for(var j=0;j<li.length;j++){
                    if(j!=i){
                        li[j].style.width = 160 + "px";
                    }
                }
            }
        })(i)
    }
};
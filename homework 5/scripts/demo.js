/**
 * Created by Administrator on 2017-01-11.
 */
window.onload =function(){
    var airmain = document.getElementsByClassName("air_main");
    for(var i=0;i<airmain.length;i++){
        (function(i){
            airmain[i].onmouseover = function(){
                var className = this.getAttribute("class");
                className = className + " on";
                this.setAttribute("class",className);
                this.getElementsByTagName("div")[0].style.display = "block";
                for(var j=0;j<airmain.length;j++){
                    if(j!=i){
                        airmain[j].setAttribute("class","air_main");
                        airmain[j].getElementsByTagName("div")[0].style.display = "none";
                    }
                }
            }
        })(i);
    }
}
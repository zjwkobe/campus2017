window.onload = function(){
    var li1 = document.getElementsByClassName("blue")[0];
    var li2 = document.getElementsByClassName("green")[0];
    var li3 = document.getElementsByClassName("pink")[0];
    var blue = li1.getElementsByTagName("div")[0];
    var green = li2.getElementsByTagName("div")[0];
    var pink = li3.getElementsByTagName("div")[0];
    var str1 = blue.getElementsByTagName("p")[0].innerHTML+" 23:59:59";
    var str2 = green.getElementsByTagName("p")[0].innerHTML+" 23:59:59";
    var str3 = pink.getElementsByTagName("p")[0].innerHTML+" 23:59:59";
    var back = document.getElementsByClassName("back")[0];
    var date1 = parseInt(new Date(str1))||getDate(str1);
    var date2 = parseInt(new Date(str2))||getDate(str2);
    var date3 = parseInt(new Date(str3))||getDate(str3);
    var nowDate = new Date();
    //判断当前时间在哪个时间段
    if(number(nowDate)-number(date1)<0){
        blue.style.backgroundPosition = -16 + "px "+(-16)+"px";
        green.style.backgroundPosition = -370 + "px "+(-19)+"px";
        pink.style.backgroundPosition = -33 + "px "+(-354)+"px";
    }else if(number(nowDate)-number(date1)>=0&&number(nowDate)-number(date2)<0){
        blue.style.backgroundPosition = -32 + "px "+(-167)+"px";
        green.style.backgroundPosition = -370 + "px "+(-19)+"px";
        pink.style.backgroundPosition = -33 + "px "+(-354)+"px";
    }else if(number(nowDate)-number(date2)>=0&&number(nowDate)-number(date3)<=0){
        blue.style.backgroundPosition = -32 + "px "+(-167)+"px";
        green.style.backgroundPosition = -370 + "px "+(-167)+"px";
        pink.style.backgroundPosition = -33 + "px "+(-354)+"px";
    }else if(number(nowDate)-number(date3)>0){
        blue.style.backgroundPosition = -32 + "px "+(-167)+"px";
        green.style.backgroundPosition = -370 + "px "+(-167)+"px";
        pink.style.backgroundPosition = -356 + "px "+(-354)+"px";
    }
    //返回顶部
    back.onclick = function(){
        var speed = 100;
        if(document.documentElement.scrollTop){
            var timer = setInterval(function(){
                if(document.documentElement.scrollTop<=0){
                    document.documentElement.scrollTop = 0;
                    clearInterval(timer);
                }
                document.documentElement.scrollTop -= speed;
            },20)
        }else if(document.body.scrollTop){
            var timer = setInterval(function(){
                if(document.body.scrollTop<=0){
                    document.body.scrollTop = 0;
                    clearInterval(timer);
                }
                document.body.scrollTop -= speed;
            },20)
        }

    };
    //tab切换
    var top_nav = document.getElementsByClassName("top_nav")[0];
    var nav_hover = document.getElementsByClassName("nav_hover")[0];
    top_nav.onmouseover = function(){
        window.onkeydown = function(e){
            e.preventDefault();
            if(e.keyCode == 9){
                if(nav_hover.offsetLeft>=603){
                    nav_hover.style.left = 23 + "px";
                }else{
                    nav_hover.style.left = nav_hover.offsetLeft + 58 + "px";
                }
            }
        }
    };
    //日期字符串转整数
    function number(obj){
        return parseInt(obj.valueOf());
    }
    //解决firefox和ie中new date(str)无效
    function getDate(date){
        var s = date.split(" ");
        var s1 = s[0].split(".");
        var s2 = s[1].split(":");
        return new Date(s1[0],s1[1]-1,s1[2],s2[0],s2[1],s2[2]);
    }
};

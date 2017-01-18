window.onload=function () {
    var oNav = document.getElementsByClassName('nav')[0];
    var oAddress = oNav.getElementsByTagName('li');
    var now = 6;
    for(var i=0;i<oAddress.length;i++){
        (function (i) {
            oAddress[i].onclick=function () {
                for(var j=0;j<oAddress.length;j++){
                    oAddress[j].className = '';
                }
                oAddress[i].className = 'active';
            }
        })(i);
    }

    document.onkeydown=function (ev) {
        var oev = event || ev;
        if (oev.keyCode == 9) {
            stopEvent(oev);
            for(var i=0;i<oAddress.length;i++){
                oAddress[i].className="";
            }
                oAddress[now].className="active";
            if(now == oAddress.length - 1){
                now =0;
            }else{
                now =now+1;
            }
            function stopEvent(e)
            {
                if(e.stopPropagation)
                {
                    e.stopPropagation();
                }
                else
                {
                    e.cancelBubble=true;
                }

                if(e.preventDefault)
                {
                    e.preventDefault();
                }
                else
                {
                    e.returnValue=false;
                }
            }
        }
    };

    var oGift=document.getElementsByClassName('hotSpringGift')[0];
    oGift.onclick=function () {
        window.open('https://www.qunar.com/');
    };
    var oImg =document.getElementsByTagName('img');
    for(var i=0;i<oImg.length;i++){
        oImg[i].onclick=function () {
            window.open('https://www.qunar.com/');
        }
    }


};

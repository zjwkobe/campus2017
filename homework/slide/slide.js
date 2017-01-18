window.onload = function () {
    var oBox = document.getElementById("box");
    var oImg = document.getElementsByTagName("img");
    var imgWidth = oImg[0].offsetWidth;
    var exposeWidth = 160;
    oBox.style.width = imgWidth + exposeWidth * (oImg.length - 1) + "px";
    for (var i = 1; i < oImg.length; i++) {
        oImg[i].style.left = imgWidth + exposeWidth * (i - 1) + "px";
    }

    for (var i = 0; i < oImg.length; i++) {
        (function (i) {
            oImg[i].onmouseover = function () {
                for (var j = 1; j <= i; j++) {
                    leftMove(oImg[j], j, exposeWidth);
                }
                for (var j = i + 1; j < oImg.length; j++) {
                    rightMove(oImg[j], j, exposeWidth, imgWidth);
                }
            }
        })(i);
    }
};

function leftMove(el, index, exposeWidth) {
    var timer = null;
    var speed = 10;
    timer = setInterval(function () {
        el.style.left = parseInt(el.style.left) - speed + 'px';
        speed *= 1.5;
        if (parseInt(el.style.left) <= index * exposeWidth) {
            clearInterval(timer);
            el.style.left = index * exposeWidth + 'px';
        }
    }, 25);
}

function rightMove(el, index, exposeWidth, imgWidth) {
    var timerr = null;
    var speed = 50;
    timerr = setInterval(function () {
        el.style.left = parseInt(el.style.left) + speed + 'px';
        speed *= 1.5;
        if (parseInt(el.style.left) >= imgWidth + (index - 1) * exposeWidth) {
            clearInterval(timerr);
            el.style.left = imgWidth + (index - 1) * exposeWidth + 'px';
        }
    }, 25);
}
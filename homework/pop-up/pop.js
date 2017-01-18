window.onload = function () {
    var clickUl = document.getElementsByClassName("clickUl")[0];
    clickUl.onclick = function () {
        openNew();
    };
};

function openNew() {
    var pageHeight = document.documentElement.scrollHeight;
    var pageWidth = document.documentElement.scrollWidth;
    var cHeight = document.documentElement.clientHeight;

    var oshadow = document.createElement("div");
    oshadow.setAttribute("class", "shadow");
    oshadow.style.height = pageHeight + "px";
    oshadow.style.width = pageWidth + "px";
    document.body.appendChild(oshadow);

    var opopLayer = document.createElement("div");
    document.body.appendChild(opopLayer);
    opopLayer.innerHTML = "<img id='layer' src='image/layer.png'><img class='close' src='image/close.png'>";
    var oclose = document.getElementsByClassName('close')[0];
    var olayer = document.getElementById('layer');
    olayer.onload = function () {
        var lWidth = olayer.offsetWidth;
        var lHeight = olayer.offsetHeight;
        olayer.style.left = (pageWidth - lWidth) / 2 + "px";
        olayer.style.top = (cHeight - lHeight) / 2 + 'px';
        oclose.onload = function () {
            oclose.style.left = (parseInt(olayer.style.left) + lWidth - 18) + 'px';
            oclose.style.top = (parseInt(olayer.style.top) - 10) + 'px';
            oclose.onmouseover = function () {
                oclose.src = 'image/close_hover.png';
            };
            oclose.onmouseout = function () {
                oclose.src = 'image/close.png';
            };
            oclose.onclick = oshadow.onclick = function () {
                document.body.removeChild(opopLayer);
                document.body.removeChild(oshadow);
            };
        }
    };
}
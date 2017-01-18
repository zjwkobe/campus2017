window.onload = function () {

    ajax('informations.json', fnSucc);

    function fnSucc(data) {

        // alert(typeof data);
        // var data = eval(data);
        var data = JSON.parse(data);
        // alert(typeof data);
        data.forEach(function (item) {
            var contentInf = document.getElementsByClassName("content")[0];
            var oul = document.createElement("ul");
            oul.className = "information";
            contentInf.appendChild(oul);

            var oli = document.createElement('li');
            var oli1 = document.createElement('li');
            var oli2 = document.createElement('li');
            var oli3 = document.createElement('li');
            var oli4 = document.createElement('li');
            var oli5 = document.createElement('li');
            var oli6 = document.createElement('li');

            oli.setAttribute("class", "n1");
            oli1.setAttribute("class", "n2 inf");
            oli2.setAttribute("class", "n3 inf");
            oli3.setAttribute("class", "n3 inf");
            oli4.setAttribute("class", "n4 inf");
            oli5.setAttribute("class", "n5 inf");
            oli6.setAttribute("class", "n6 inf");

            oli.innerHTML = "<p>" + item.date + "</p><p>" + item.time + "</p>";
            oli1.innerHTML = item.name;
            oli2.innerHTML = item.number;
            oli3.innerHTML = item.E;
            oli4.innerHTML = item.state;
            oli5.innerHTML = "<span class='test'>" + item.inf + "</span>";
            oli6.innerHTML = item.exchange;

            oul.appendChild(oli);
            oul.appendChild(oli1);
            oul.appendChild(oli2);
            oul.appendChild(oli3);
            oul.appendChild(oli4);
            oul.appendChild(oli5);
            oul.appendChild(oli6);
        });
    }

    function ajax(url, fnSucc) {
        var oAjax = null;
        if (window.XMLHttpRequest) {
            oAjax = new XMLHttpRequest();
        }
        else {
            oAjax = new ActiveXObject("Microsoft.XMLHTTP");
        }
        oAjax.open('GET', url, 'true');
        oAjax.send();
        oAjax.onreadystatechange = function () {
            if (oAjax.readyState === 4) {
                if (oAjax.status === 200) {
                    // alert("success"+oAjax.responseText);
                    fnSucc(oAjax.responseText);
                }
                else {
                    alert("fail");
                }
            }
        };
    }
};
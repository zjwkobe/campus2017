window.onload = function(){
    var upload = document.getElementById("upload");
    var entry = document.getElementById("entry");
    var input_file = document.getElementsByClassName("input_file")[0];
    var input_text = document.getElementsByClassName("input_text")[0];
    var clear = input_text.getElementsByClassName("clear")[0];
    var text_area = input_text.getElementsByClassName("text_area")[0];
    upload.onclick = function(){
        input_file.style.display = "block";
        input_text.style.display = "none";
    };
    entry.onclick = function(){
        input_file.style.display = "none";
        input_text.style.display = "block";
    };
    clear.onclick = function(){
        text_area.value = "";
    }
};

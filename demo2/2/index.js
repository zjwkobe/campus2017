
  var targetW = 475;
  var doorItem = document.querySelectorAll('.door-item');
  var oldItem = doorItem[0];
  var newItem = null;
  var id = null;
  var step = 0;
  var flag = true;
  
  for (var i = 0; i < doorItem.length; i++) {
    doorItem[i].onmouseenter = function () {

      if(flag) {
        newItem = this;
        if(newItem.classList.contains('active')) {
          return false;
        }
        step = 0;
        move();
      }else {
        return false;
      }

    }
  }

  function move() {
    flag = false;
    var wnew = newItem.offsetWidth;
    var wold = oldItem.offsetWidth;
    step ++;
    oldItem.style.width = wold - step + 'px';
    newItem.style.width = wnew + step + 'px';
    if(Math.abs(targetW - newItem.offsetWidth) > step) {
      id = requestAnimationFrame(move);
    }else {
      flag = true;
      cancelAnimationFrame(id);
      oldItem.classList.remove('active');
      newItem.classList.add('active');
      oldItem = newItem;
    }
  }

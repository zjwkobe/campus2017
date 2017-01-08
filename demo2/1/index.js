Modal.prototype.init = function () {
  var _this = this;
  var wrap = this.el.querySelector('.modal-wrap');
  var container = this.el.querySelector('.modal-container');
  var closeBtn = this.el.querySelector('.modal-close');

  wrap.onclick = function (e) {
    e.stopPropagation();
    _this.hide();
  };
  container.onclick = function (e) {
    e.stopPropagation();
  };
  closeBtn.onclick = function (e) {
    e.stopPropagation();
    _this.hide();
  };
}
Modal.prototype.show = function () {
 this.el.classList.remove('g-hide');
 this.el.classList.add('g-show');
}
Modal.prototype.hide = function () {
  this.el.classList.remove('g-show');
  this.el.classList.add('g-hide');
}


var main = document.querySelector('.ui-main');
var modal = document.querySelector('.m-modal');
var cModal = new Modal(modal);

main.onclick = function (e) {
  e.stopPropagation();
  cModal.show();
};

function Modal(el) {
 this.el = el;
 this.init();
}

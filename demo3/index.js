
var tbody = document.querySelector('.main-table tbody');
var xhr = new XMLHttpRequest();

xhr.onreadystatechange = function () {
  if(xhr.readyState == 4 && xhr.status == 200) {
    var reply = JSON.parse(xhr.responseText);
    if(reply.code == 200) {
      var data = reply.data;
      var tr = '';
      for (var i = 0, len = data.length; i < len; i++) {
        tr += '<tr><td><span>'+ data[i].date +'</span><br><span>'+ data[i].time +'</span></td>'+
                  '<td>'+ data[i].name +'</td>'+
                  '<td>'+ data[i].quantity +'</td>'+
                  '<td>'+ data[i].Ecoin +'</td>'+
                  '<td>'+ statusFormatter(data[i].status) +'</td>'+
                  '<td>'+ data[i].msg +'</td>'+
                  '<td><button class="g-btn btn-primary"><a href="#'+ data[i].id +'">兑换信息</a></button></td>'+
                  '<td>&nbsp;</td></tr>';
      }
      tbody.innerHTML = tr;
    }else {
      console.log('数据加载失败！');
    }
  }
};
xhr.open('GET', 'table.json', true);
xhr.send();

function statusFormatter(s) {
  if(s == 0) {
    return '兑换失败';
  }else {
    return '兑换成功';
  }
}

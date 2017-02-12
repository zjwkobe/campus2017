/**
 * Created by ruiyang.wan on 2017/2/11.
 */
function doTextCount() {
    var textValue = document.getElementById('textValue').value;
    var a =$("#textValue").val(); //检测
    //var textFinal = URLencode(textValue)
    var textValueDecode = encodeURIComponent(encodeURIComponent(textValue));
    $.ajax({
        url:"countChar/text/" + textValueDecode,
        type:"GET",
        async:false,
        dataType:"json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success:function (data) {
            finalData(data);

        },
        error:function(textStatus,e){
            alert(e.status);
        }
    });
};

function finalData(data) {
    if (data != null) {
        document.getElementById("textValue").innerHTML = data.message;
        var title1 = "<h4>各统计内容的个数如下</h4>";
        var title2 = "<h4>文字中出现频率最高的三个字是：</h4>";
        var table1Tr1 = '<table width="400" border="1"> ' +
            '<tr> <th align="left">统计项</th> ' +
            '<th>个数</th> </tr>';
        var table1Tr2 = '<tr> <th align="left">英文字母</th> ' +
            '<th>' + data.count.zmCount + '</th> </tr>';
        var table1Tr3 = '<tr> <th align="left">数字</th> ' +
            '<th>' + data.count.szCount + '</th> </tr>';
        var table1Tr4 = '<tr> <th align="left">中文汉字</th> ' +
            '<th>' + data.count.zwCount + '</th> </tr>';
        var table1Tr5 = '<tr> <th align="left">中英文标点符号</th> ' +
            '<th>' + data.count.qtCount + '</th> </tr>' +
            '</table>';
        var i = 0;
        var table2Tr1 = '<table width="400" border="1"> ' +
            '<tr> <th align="left">名称</th> ' +
            '<th>个数</th> </tr>';
        var table2Tr2;
        var table2Tr3;
        var table2Tr4;
        for (var key in data["max"]) {
            if (i==0) {
                table2Tr2 = '<tr> <th align="left">' + key + '</th> ' +
                    '<th>' + data.max[key] + '</th> </tr>';
            }
            if (i==1) {
                table2Tr3 = '<tr> <th align="left">' + key + '</th> ' +
                    '<th>' + data.max[key] + '</th> </tr>';
            }
            if (i==2) {
                table2Tr4 = '<tr> <th align="left">' + key + '</th> ' +
                    '<th>' + data.max[key]+ '</th> </tr>' +
                    '</table>';
            }
            i++;
        }
        $("#result").html(title1 + table1Tr1 + table1Tr2 + table1Tr3 + table1Tr4 + table1Tr5
            + title2 + table2Tr1 + table2Tr2 + table2Tr3 + table2Tr4);

    }
};


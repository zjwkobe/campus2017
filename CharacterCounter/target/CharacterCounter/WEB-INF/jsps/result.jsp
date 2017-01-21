<%--
  Created by IntelliJ IDEA.
  User: NULL
  Date: 2017/1/20
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>统计结果页</title>
</head>

<body>

    <h3>统计结果页</h3>
    <br/><br/>

    各项统计内容的个数如下：<br/><br/>
    <table  border="1px" cellspacing="0px" style="border-collapse:collapse;width:300px;">
        <tr>
            <th>统计项</th>
            <th>个数</th>
        </tr>
        <tr>
            <td>英文字母</td>
            <td>${countResultModel.sumOfEnglishCharacter}</td>
        </tr>
        <tr>
            <td>数字</td>
            <td>${countResultModel.sumOfNumber}</td>
        </tr>
        <tr>
            <td>汉字</td>
            <td>${countResultModel.sumOfChineseCharacter}</td>
        </tr>
        <tr>
            <td>中英文标点符号</td>
            <td>${countResultModel.sumOfPunctuation}</td>
        </tr>
    </table>

    <br/><br/><br/>
    文本中出现频率最高的3个字是：<br/><br/>
    <table  border="1px" cellspacing="0px" style="border-collapse:collapse;width:300px;">
        <tr>
            <th>字符</th>
            <th>个数</th>
        </tr>
        <tr>
            <td>${countResultModel.top1.key}</td>
            <td>${countResultModel.top1.value}</td>
        </tr>
        <tr>
            <td>${countResultModel.top2.key}</td>
            <td>${countResultModel.top2.value}</td>
        </tr>
        <tr>
            <td>${countResultModel.top3.key}</td>
            <td>${countResultModel.top3.value}</td>
        </tr>
    </table>


</body>

</html>

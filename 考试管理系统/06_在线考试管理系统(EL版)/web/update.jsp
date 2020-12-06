<%@ page import="com.mystudy.entity.Questions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<center>
    <form action="/myweb/question/update" method="get">
        <table border="2">
            <tr>
                <td>题目编号：</td>
                <td><input type="text" name="id" value="${ques.id}" readonly></td>
            </tr>
            <tr>
                <td>题目：</td>
                <td><input type="text" name="title" value="${ques.title}"></td>
            </tr>
            <tr>
                <td>A：</td>
                <td><input type="text" name="optionA" value="${ques.optionA}"></td>
            </tr>
            <tr>
                <td>B：</td>
                <td><input type="text" name="optionB" value="${ques.optionB}"></td>
            </tr>
            <tr>
                <td>C：</td>
                <td><input type="text" name="optionC" value="${ques.optionC}"></td>
            </tr>
            <tr>
                <td>D：</td>
                <td><input type="text" name="optionD" value="${ques.optionD}"></td>
            </tr>
            <tr>
                <td>正确答案</td>
                <td><input type="radio" name="answer" value="A" ${"A" eq ques.answer?"checked":""}>A
                    <input type="radio" name="answer" value="B" ${"B" eq ques.answer?"checked":""}>B
                    <input type="radio" name="answer" value="C" ${"C" eq ques.answer?"checked":""}>C
                    <input type="radio" name="answer" value="D" ${"D" eq ques.answer?"checked":""}>D
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="更新试题"></td>
                <td><input type="reset"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>

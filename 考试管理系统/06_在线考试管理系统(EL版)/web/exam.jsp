<%@ page import="java.util.List" %>
<%@ page import="com.mystudy.entity.Questions" %><%--
  Created by IntelliJ IDEA.
  User: 何小帅
  Date: 2020/12/5
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <form action="/myweb/score">
            <table border="2">
                <tr>
                    <td>试题编号</td>
                    <td>题目信息</td>
                    <td>A</td>
                    <td>B</td>
                    <td>C</td>
                    <td>D</td>
                    <td>答案</td>
                </tr>
                <%
                    List<Questions> list = (List) session.getAttribute("exam");
                    for (Questions q:list) {
                %>
                    <tr>
                        <td><%=q.getId()%></td>
                        <td><%=q.getTitle()%></td>
                        <td><%=q.getOptionA()%></td>
                        <td><%=q.getOptionB()%></td>
                        <td><%=q.getOptionC()%></td>
                        <td><%=q.getOptionD()%></td>
                        <td>
                            <input type="radio" name="answer_<%=q.getId()%>" value="A">A
                            <input type="radio" name="answer_<%=q.getId()%>" value="B">B
                            <input type="radio" name="answer_<%=q.getId()%>" value="C">C
                            <input type="radio" name="answer_<%=q.getId()%>" value="D">D
                        </td>
                    </tr>
                <%
                    }
                %>
                <tr align="center">
                    <td colspan="3"><input type="submit" value="交卷"></td>
                    <td colspan="4"><input type="reset" value="重做"></td>
                </tr>

            </table>
        </form>
    </center>
</body>
</html>

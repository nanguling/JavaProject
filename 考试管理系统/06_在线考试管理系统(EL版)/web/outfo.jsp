<%@ page import="com.mystudy.entity.Questions" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Questions> list = (List) request.getAttribute("outfo");
    //JSTL标签可以有效的弥补EL不能遍历集合输出问题
%>
    <center>
        <table border="2">
            <tr>
                <td>试题编号</td>
                <td>试题内容</td>
                <td>答案A</td>
                <td>答案B</td>
                <td>答案C</td>
                <td>答案D</td>
                <td>正确答案</td>
                <td>删除试题</td>
                <td>更新试题</td>
            </tr>
            <%
                for (Questions q:list) {
            %>
                <tr>
                    <td><%=q.getId()%></td>
                    <td><%=q.getTitle()%></td>
                    <td><%=q.getOptionA()%></td>
                    <td><%=q.getOptionB()%></td>
                    <td><%=q.getOptionC()%></td>
                    <td><%=q.getOptionD()%></td>
                    <td><%=q.getAnswer()%></td>
                    <td><a href="/myweb/question/delete?id=<%=q.getId()%>" >删除试题</a></td>
                    <td><a href="/myweb/question/select?id=<%=q.getId()%>" >更新试题</a></td>
                </tr>
            <%
                }
            %>
        </table>
    </center>
</body>
</html>

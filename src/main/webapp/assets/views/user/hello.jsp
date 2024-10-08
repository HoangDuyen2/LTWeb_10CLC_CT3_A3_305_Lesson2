<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/28/2024
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>User Home</h1>
    <c:if test="${message==true}"> Hello ${user}</c:if>
    <li>
        <a href="${pageContext.request.contextPath }/logout">Đăng Xuất</a>
    </li>
</body>
</html>

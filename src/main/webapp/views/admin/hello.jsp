<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/28/2024
  Time: 9:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Admin Home</h1>
    <c:if test="${message==true}"> Hello ${sessionScope.account.fullName}</c:if>
    <li>
        <a href="${pageContext.request.contextPath }/logout">Đăng Xuất</a>
    </li>
</body>
</html>

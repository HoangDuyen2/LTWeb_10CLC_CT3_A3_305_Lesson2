<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/1/2024
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--Khi gan enctype phai co upload file--%>
<form action="<c:url value="/admin/video/insert"/>" method="post">
    <label for="css">Active</label><br>
    <input type="radio" id="on" name="active" value="1">
    <label for="javascript">Hoat dong</label>
    <input type="radio" id="off" name="active" value="0">
    <label for="html">Khoa</label>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>

    <label for="poster">Poster:</label><br>
    <input type="text" id="poster" name="poster"><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views"><br>

    <br><br>
    <input type="submit" value="Insert">
</form>

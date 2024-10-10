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

<form action="${pageContext.request.contextPath}/admin/video/upload" method="post">
    <input type="text" id="videoid" name="videoid" value="${video.videoid}" hidden="hidden">

    <label for="statuson">Active:</label><br>
    <input type="radio" id="statuson" name="active" value="1" ${video.active==true ? 'checked' : ''}>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="active" value="0" ${video.active == false ? 'checked' : ''}>
    <label for="statusoff">Inactive</label><br><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description" value="${video.description}"><br>

    <label for="poster">Poster:</label><br>
    <input type="text" id="poster" name="poster" value="${video.poster}"><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" value="${video.title}"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views" value="${video.views}"><br>

    <input type="submit" value="Edit">
</form>

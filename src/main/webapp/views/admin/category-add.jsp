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
<form action="<c:url value="/admin/category/insert"/>" method="post" enctype="multipart/form-data">
    <label for="categoryname">Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname"><br>

    <label for="images">Link images:</label><br>
    <input type="text" id="images" name="images"><br>

    <label for="images">Upload files:</label><br>
    <input type="file" onchange="chooseFile(this)" id="images1" name="images1"><br>


    <label for="css">Status</label><br>
    <input type="radio" id="on" name="status" value="1">
    <label for="javascript">Hoat dong</label>
    <input type="radio" id="off" name="status" value="0">
    <label for="html">Khoa</label>

    <br><br>
    <input type="submit" value="Insert">
</form>

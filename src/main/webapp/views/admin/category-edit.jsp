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

<form action="${pageContext.request.contextPath}/admin/category/edit" method="post" enctype="multipart/form-data">
    <input type="text" id="categoryid" name="categoryid" value="${category.categoryid}" hidden="hidden">

    <label for="categoryname">Name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${category.categoryname}"><br>
    <label for="images">Images:</label><br>
        <c:if test="${category.images.substring(0,5) != 'https'}">
            <c:url value="/images?fname=${category.images}" var="imgUrl"></c:url>
        </c:if>
        <c:if test="${category.images.substring(0,5) == 'https'}">
            <c:url value="${category.images}" var="imgUrl"></c:url>
        </c:if>
        <img id="images" height="150" width="200" src="${imgUrl}"/>
    <input type="file" onchange="chooseFile(this)" id="images1" name="images1" value="${category.images}"><br><br>

    <label for="statuson">Status:</label><br>
    <input type="radio" id="statuson" name="status" value="1" ${category.status==1 ? 'checked' : ''}>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="status" value="0" ${category.status == 0 ? 'checked' : ''}>
    <label for="statusoff">Inactive</label><br><br>


    <input type="submit" value="Edit">
</form>

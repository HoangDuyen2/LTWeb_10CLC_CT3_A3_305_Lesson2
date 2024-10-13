<%@ page import="vn.iostar.entity.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.iostar.entity.Video" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/1/2024
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--Khi gan enctype phai co upload file--%>

<form action="${pageContext.request.contextPath}/admin/video/upload" method="post" enctype="multipart/form-data">
    <input type="text" id="videoid" name="videoid" value="${video.videoid}" hidden="hidden">

    <label for="statuson">Active:</label><br>
    <input type="radio" id="statuson" name="active" value="1" ${video.active==true ? 'checked' : ''}>
    <label for="statuson">Active</label><br>
    <input type="radio" id="statusoff" name="active" value="0" ${video.active == false ? 'checked' : ''}>
    <label for="statusoff">Inactive</label><br><br>

    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description" value="${video.description}"><br>

    <label for="poster">Poster:</label><br>
        <c:if test="${video.poster.substring(0,5) != 'https'}">
            <c:url value="/images?fname=${video.poster}" var="imgUrl"></c:url>
        </c:if>
        <c:if test="${video.poster.substring(0,5) == 'https'}">
            <c:url value="${video.poster}" var="imgUrl"></c:url>
        </c:if>
        <img id="poster" height="200" width="150" src="${imgUrl}">
    <input type="file" onchange="chooseFile(this)" id="poster1" name="poster1" value="${video.poster}"><br><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" value="${video.title}"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views" value="${video.views}"><br>

    <label for="categoryid">Select a category:</label>
    <select name="selectedCategoryId" id="categoryid">
        <%
            // Lấy danh sách category từ request attribute
            ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categoryList");

            // Giả sử video là đối tượng Video đã được đặt vào request
            Video video = (Video) request.getAttribute("video");
            int currentCategoryId = video.getCategory().getCategoryid(); // Lấy categoryId hiện tại của video
        %>
        <%
            for (Category category : categories) {
                String categoryName = category.getCategoryname();
                int categoryId = category.getCategoryid();
        %>
        <option value="<%= categoryId %>" <%= categoryId == currentCategoryId ? "selected" : "" %>><%= categoryName %></option>
        <%
            }
        %>
    </select>


    <input type="submit" value="Edit">
</form>

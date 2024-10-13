<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.iostar.entity.Category" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/1/2024
  Time: 2:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%--Khi gan enctype phai co upload file--%>
<form action="<c:url value="/admin/video/insert"/>" method="post" enctype="multipart/form-data">
    <label for="css">Active</label><br>
    <input type="radio" id="on" name="active" value="1" checked>
    <label for="javascript">Hoạt động</label>
    <input type="radio" id="off" name="active" value="0">
    <label for="html">Khóa</label>

    <br>
    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>

    <label for="poster">Link poster:</label><br>
    <input type="text" id="poster" name="poster"><br>

    <label for="poster">Upload files:</label><br>
    <input type="file" onchange="chooseFile(this)" id="poster1" name="poster1"><br>

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title"><br>

    <label for="views">Views:</label><br>
    <input type="text" id="views" name="views"><br>

    <label for="categoryid">Select a category:</label>
    <select name="selectedCategoryId" id="categoryid">
        <%
            // Lấy danh sách category từ request attribute
            ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categoryList");
            for (Category category : categories) {
                String categoryName = category.getCategoryname();
                int categoryId = category.getCategoryid(); // Giả sử bạn có phương thức getCategoryId()
        %>
        <option value="<%=categoryId%>"><%=categoryName%></option>
        <%
            }
        %>
    </select>


    <br><br>
    <input type="submit" value="Insert">
</form>

<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/1/2024
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value="/admin/category/add"/>">Add category</a><br>
<hr>
<table border="1" width="100%">
  <tr>
    <th>STT</th>
    <th>Images</th>
    <th>Category Name</th>
    <th>Status</th>
    <th>Action</th>
      <%--    Trong action de chua nut xoa va sua--%>
  </tr>
<%--    varStatus: được dùng để theo dõi trạng thái của vòng lặp.
 Biến STT này có thể cung cấp thông tin như số lần lặp hiện tại,
  kiểm tra xem đó có phải là vòng lặp đầu tiên hoặc cuối cùng không--%>
<%--    items: Đây là danh sách (hoặc tập hợp các phần tử) mà vòng lặp sẽ lặp qua.
 listcategory là một biến danh sách đã được khởi tạo và chứa các phần tử mà bạn muốn lặp.--%>
<%--    var: Trong mỗi vòng lặp, một phần tử của listcategory sẽ được gán cho biến category.
Biến này sẽ đại diện cho phần tử hiện tại trong lần lặp đó.--%>
    <c:forEach items="${listcategory}" var="category" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <c:if test="${category.images.substring(0,5) == 'https'}">
                <c:url value="${category.images}" var="imgUrl"></c:url>
            </c:if>
            <c:if test="${category.images.substring(0,5) != 'https'}">
                <c:url value="/images?fname=${category.images}" var="imgUrl"></c:url>
            </c:if>
            <td>
                <img id="images" height="200" width="150" src="${imgUrl}">
            </td>
            <td>${category.categoryname}</td>
            <td>
                <c:if test="${category.status == 1}">Active</c:if>
                <c:if test="${category.status != 1}">Inactive</c:if>
            </td>
            <td>
                <a href="<c:url value="/admin/category/edit?id=${category.categoryid}"/>">Sua</a>
                |
                <a href="<c:url value="/admin/category/delete?id=${category.categoryid}"/>">Xoa</a>
            </td>
        </tr>
    </c:forEach>
</table>


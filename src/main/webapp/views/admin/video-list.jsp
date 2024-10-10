<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 10/1/2024
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<a href="<c:url value="/admin/video/add"/>">Add video</a><br>
<hr>
<table border="1" width="100%">
    <tr>
        <th>STT</th>
        <th>Description</th>
        <th>Poster</th>
        <th>Active</th>
        <th>Title</th>
        <th>Views</th>
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
    <c:forEach items="${videoList}" var="video" varStatus="index">
        <tr>
            <td>${index.index+1}</td>
            <td>${video.description}</td>
            <td>${video.poster}</td>
            <td>
                <c:if test="${video.active == true}">Active</c:if>
                <c:if test="${video.active != true}">Inactive</c:if>
            </td>
            <td>${video.title}</td>
            <td>${video.views}</td>
            <td>
                <a href="<c:url value="/admin/video/edit?id=${video.videoid}"/>">Sua</a>
                |
                <a href="<c:url value="/admin/video/delete?id=${video.videoid}"/>">Xoa</a>
            </td>
        </tr>
    </c:forEach>
</table>


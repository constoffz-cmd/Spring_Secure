<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <style>
        /* Ваши стили остаются такими же */
        a.add-btn { background-color: lightgreen; color: black; padding: 5px 15px; text-decoration: none; display: inline-block;}
        .table { width: 100%; border-collapse: collapse; }
        .table th, .table td { border: 1px solid #ddd; padding: 8px; }
    </style>
</head>
<body>
<h2>User Management</h2>
<table class="table">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    <%-- В контроллере проверьте имя атрибута: "users" или "userss" --%>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>
                <a href="/edit?id=${user.id}">Edit</a>
                |
                <form action="/delete" method="post" style="display:inline">
                    <input type="hidden" name="id" value="${user.id}"/>
                        <%-- Добавляем CSRF токен, иначе кнопка удаления не сработает --%>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<%-- Кнопка добавления --%>
<a href="/add" class="add-btn">Add New User</a>
</body>
</html>
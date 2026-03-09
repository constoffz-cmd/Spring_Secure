<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Form</title>
    <style>
        label { display: block; margin-top: 10px; font-weight: bold; }
        input { display: block; width: 300px; margin-bottom: 10px; padding: 5px; }
        .btn { background: lightgreen; padding: 10px 20px; border: none; cursor: pointer; margin-top: 20px; }
        .role-group { margin-top: 15px; border: 1px solid #ccc; padding: 10px; width: 300px; }
    </style>
</head>
<body>

<h2>${user.id == null ? "Add New User" : "Edit User"}</h2>

<%-- ОДНА общая форма для всех полей --%>
<form action="/admin/save" method="post">

    <%-- Скрытое поле ID (Критически важно для Edit!) --%>
    <input type="hidden" name="id" value="${user.id}">

    <label>Username (Login):</label>
    <input type="text" name="username" value="${user.username}" required>

    <label>First Name:</label>
    <input type="text" name="firstName" value="${user.firstName}" required>

    <label>Last Name:</label>
    <input type="text" name="lastName" value="${user.lastName}" required>

    <label>Email:</label>
    <input type="email" name="email" value="${user.email}" required>

    <label>Password:</label>
    <%-- При редактировании пароль не обязателен (required убираем) --%>
    <input type="password" name="password" ${user.id == null ? "required" : ""}>

    <div class="role-group">
        <label>Roles:</label>
        <c:forEach items="${allRoles}" var="role">
            <div>
                <input type="checkbox" name="selectedRoles" value="${role.id}"
                <c:forEach items="${user.roles}" var="userRole">
                       <c:if test="${userRole.id == role.id}">checked</c:if>
                </c:forEach>
                >
                <span>${role.name}</span>
            </div>
        </c:forEach>
    </div>

    <%-- CSRF Токен --%>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <button type="submit" class="btn">Save User</button>
</form>

<br>
<a href="/admin/users">Back to list</a>

</body>
</html>
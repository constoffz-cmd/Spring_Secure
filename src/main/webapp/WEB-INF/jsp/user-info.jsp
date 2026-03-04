<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Form</title>
    <style>
        input { display: block; width: 300px; margin-bottom: 10px; padding: 5px; }
        .btn { background: lightgreen; padding: 5px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>

<h2>${user.id == null ? "Add User" : "Edit User"}</h2>

<form action="/save" method="post">
    <!-- Скрытое поле ID -->
    <input type="hidden" name="id" value="${user.id}">

    <!-- Поля ввода с предзаполненными значениями через EL-выражения -->
    Name:
    <input type="text" name="firstName" value="${user.firstName}" required>

    Last Name:
    <input type="text" name="lastName" value="${user.lastName}" required>

    Email:
    <input type="text" name="email" value="${user.email}" required>

    <!-- Если в SecurityConfig НЕ отключен CSRF, добавьте токен вручную -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <!-- Добавьте эти строки в user-info.jsp или registration.jsp -->
    <!-- user-info.jsp или registration.jsp -->
    <form action="/save" method="post">
        <input type="hidden" name="id" value="${user.id}">

        Username:
        <input type="text" name="username" value="${user.username}" required>
    Password:
    <input type="password" name="password" value="${user.password}" required>
    <br>
    Confirm Password:
    <input type="password" name="passwordConfirm" value="${user.passwordConfirm}" required>
    <br>
    <button type="submit" class="btn">Save</button>
</form>

<br>
<a href="/">Back to list</a>
</body>
</html>
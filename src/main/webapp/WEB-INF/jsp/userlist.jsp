<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ include file="/common/taglibs.jsp"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>用户信息列表</title>
</head>
<body>
<table style="border:1px solid #3C95FF">
    <tr>
        <td>用户id</td>
        <td>用户名称</td>
        <td>时间</td>
    </tr>
<c:forEach items="${userlist}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.password}</td>
        </tr>
</c:forEach>
</table>
</body>
</html>
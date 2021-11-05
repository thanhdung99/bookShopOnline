
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        Details of Order ID: ${order.orderId}
    </title>
    <jsp:include page="/import.jsp"/>
    <link href="/css/global.css" rel="stylesheet" type="text/css">
    <link href="/css/full_height_scroll.css" rel="stylesheet" type="text/css">
    <link href="/css/icon.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${message != null}">
    <jsp:include page="/components/toast.jsp"/>
</c:if>
<jsp:include page="/admin/header.jsp"/>
<div class="container-fluid p-0">
    <div class="row">
        <jsp:include page="/admin/sidebar.jsp"/>
        <div class="col-10 offset-2">

        </div>
    </div>
</div>
</body>
</html>
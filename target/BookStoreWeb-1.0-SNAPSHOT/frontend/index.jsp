<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>

    <title>Categories Search</title>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="shortcut icon" href="">
    <jsp:include page="/importCss.jsp" />
</head>
<body>
<%@include file="/components/Header.jsp"%>

<%@include file="/frontend/SectionHomePage/MostRecent.jsp"%>
<%@include file="/frontend/SectionHomePage/BestSelling.jsp"%>
<%@include file="/frontend/SectionHomePage/MostFavored.jsp"%>

<%@include file="/components/Footer.jsp"%>


</body>
    <jsp:include page="/importLib.jsp"/>
</html>
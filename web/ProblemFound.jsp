<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>خطا رخ داد</title>
</head>
<body dir="rtl">
<link rel="stylesheet" type="text/css" href="LoanStyles.css" media="screen"/>

<h1 align="center"><%=request.getAttribute("errorMessage")%></h1>

<p align="center">
    <a href= <%=request.getAttribute("parentURL")%> >
        <button class="returnButton"></button>
    </a>
</p>

</body>
</html>

<%@ page import="business.LoanTypeController" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="model.entity.LoanTypeEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>تسهیلات موجود</title>
</head>
<body dir="rtl">
<link rel="stylesheet" type="text/css" href="LoanStyles.css" media="screen"/>
<p align="center">
<table id="grantConditions">
    <tr>
        <th></th>
        <th>شماره</th>
        <th>نام</th>
        <th>نرخ سود</th>
    </tr>

    <%
        List allLoans = LoanTypeController.getInstance().getAllLoans();
        for (Iterator iterator = allLoans.iterator(); iterator.hasNext(); ) {
            LoanTypeEntity loanTypeEntity = (LoanTypeEntity) iterator.next();
    %>
    <tr>
        <form method="post" action="LoanTypeDetails.jsp">
            <td>
                <button type="submit" class="viewDetailsButton" name="loanTypeId"
                        value= <%=loanTypeEntity.getLoanTypeId()%>>
            </td>
            <td><%=loanTypeEntity.getLoanTypeId()%>
            </td>
            <td><%=loanTypeEntity.getLoanTypeName()%>
            </td>
            <td><%=loanTypeEntity.getInterestRate()%>
            </td>
        </form>
    </tr>
    <%}%>
</table>
</p>

<p align="right">
    <a href=/createLoanType>
        <button class="returnButton"></button>
    </a>
</p>
</body>
</html>

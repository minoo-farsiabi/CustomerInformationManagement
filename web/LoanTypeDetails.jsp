<%@ page import="business.LoanTypeController" %>
<%@ page import="java.util.Set" %>
<%@ page import="model.entity.GrantConditionEntity" %>
<%@ page import="model.entity.LoanTypeEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>جزییات تسهیلات</title>
</head>
<body dir="rtl">
<link rel="stylesheet" type="text/css" href="LoanStyles.css" media="screen"/>
<p align="center">

        <%LoanTypeEntity loanTypeEntity = LoanTypeController.getInstance().getLoanTypeById(Integer.parseInt(request.getParameter("loanTypeId")));%>

    <label class="customLabel" for="loanTypeName">نام تسهیلات</label>
    <input class="conditionField" type="text" id="loanTypeName" name="loanTypeName"
           value=<%=loanTypeEntity.getLoanTypeName()%> readonly>&nbsp
    <label class="customLabel" for="interestRate">نرخ سود</label>
    <input class="conditionField" type="text" id="interestRate" name="interestRate"
           value=<%=loanTypeEntity.getInterestRate()%> readonly> <br> <br>

<table id="allLoans">
    <tr>
        <th>شماره</th>
        <th>نام</th>
        <th>حداقل مدت قرارداد</th>
        <th>حداکثر مدت قرارداد</th>
        <th>حداقل مبلغ قرارداد</th>
        <th>حداکثر مبلغ قرارداد</th>
    </tr>

    <%
        Set<GrantConditionEntity> grantConditions = loanTypeEntity.getGrantConditions();
        for (GrantConditionEntity grantConditionEntity : grantConditions) { %>
    <tr>
        <td><%=grantConditionEntity.getGrantConditionId()%>
        </td>
        <td><%=grantConditionEntity.getGrantConditionName()%>
        </td>
        <td><%=grantConditionEntity.getMinimumDurationDays()%>
        </td>
        <td><%=grantConditionEntity.getMaximumDurationDays()%>
        </td>
        <td><%=grantConditionEntity.getMinimumBalance()%>
        </td>
        <td><%=grantConditionEntity.getMaximumBalance()%>
        </td>
    </tr>
    <%}%>

</table>
</p>

<p align="right">
    <a href=/allLoanTypes>
        <button class="pinkReturnButton"></button>
    </a>
</p>

</body>
</html>

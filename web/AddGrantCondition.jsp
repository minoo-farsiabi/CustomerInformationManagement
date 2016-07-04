<%@ page import="model.entity.GrantConditionEntity" %>
<%@ page import="util.LoanUtil" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>تعریف شروط اعطا</title>
    <script type="text/javascript">
        function validateForm(form) {
            if (form["grantConditionName"].value == null || form["grantConditionName"].value == "") {
                alert("نام شرط را وارد کنید");
                return false;
            }
            else if (form["minimumDurationDays"].value == null || form["minimumDurationDays"].value == "") {
                alert("حداقل مدت قرارداد را وارد کنید");
                return false;
            }
            else if (form["maximumDurationDays"].value == null || form["maximumDurationDays"].value == "") {
                alert("حداکثر مدت قرارداد را وارد کنید");
                return false;
            }
            else if (form["minimumBalance"].value == null || form["minimumBalance"].value == "") {
                alert("حداقل مبلغ قرارداد را وارد کنید");
                return false;
            }
            else if (form["maximumBalance"].value == null || form["maximumBalance"].value == "") {
                alert("حداکثر مبلغ قرارداد را وارد کنید");
                return false;
            }
            else if (parseInt(form["maximumDurationDays"].value) < parseInt(form["minimumDurationDays"].value)) {
                alert("حداکثر مدت کوچکتر از حداقل است!!!");
                return false;
            }
            else if (parseInt(form["maximumBalance"].value) < parseInt(form["minimumBalance"].value)) {
                alert("حداکثر مبلغ کوچکتر از حداقل است!!!");
                return false;
            }
        }
    </script>
</head>
<body dir="rtl">
<link rel="stylesheet" type="text/css" href="LoanStyles.css" media="screen"/>
<p align="center">

    <label class="customLabel" for="loanTypeName">نام تسهیلات</label>
    <input class="conditionField" type="text" id="loanTypeName" name="loanTypeName"
           value=<%=LoanUtil.getCurrentLoanTypeEntity().getLoanTypeName()%> readonly>&nbsp
    <label class="customLabel" for="interestRate">نرخ سود</label>
    <input class="conditionField" type="text" id="interestRate" name="interestRate"
           value=<%=LoanUtil.getCurrentLoanTypeEntity().getInterestRate()%> readonly> <br>

<form method="get" onsubmit="return validateForm(this)">
    <fieldset>
        <legend>شرط اعطای جدید</legend>
        <input class="conditionField" type="text" id="grantConditionName" name="grantConditionName" placeholder="نام"/>&nbsp;
        <input class="conditionField" type="text" id="minimumDurationDays" name="minimumDurationDays"
               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
               placeholder="حداقل مدت قرارداد"/>&nbsp;
        <input class="conditionField" type="text" id="maximumDurationDays" name="maximumDurationDays"
               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
               placeholder="حداکثر مدت قرارداد"/>&nbsp;
        <input class="conditionField" type="text" id="minimumBalance" name="minimumBalance"
               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
               placeholder="حداقل مبلغ قرارداد"/>&nbsp;
        <input class="conditionField" type="text" id="maximumBalance" name="maximumBalance"
               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
               placeholder="حداکثر مبلغ قرارداد"/>&nbsp;
        <button class="continueButton" type="submit" id="addGrantCondition" name="status" value="addGrant">اضافه
        </button>
        <button class="resetButton" type="reset" id="resetGrantCondition" name="resetGrantCondition">شروع مجدد</button>
    </fieldset>
</form>
<table id="grantConditions">
    <tr>
        <th>شماره</th>
        <th>نام</th>
        <th>حداقل مدت قرارداد</th>
        <th>حداکثر مدت قرارداد</th>
        <th>حداقل مبلغ قرارداد</th>
        <th>حداکثر مبلغ قرارداد</th>
    </tr>

    <%
        Set<GrantConditionEntity> grantConditions = LoanUtil.getCurrentLoanTypeEntity().getGrantConditions();
        if (grantConditions != null) {
            int counter = 1;
        for (GrantConditionEntity grantConditionEntity : grantConditions) { %>
    <tr>
        <td><%=counter%>
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
    <%  counter = counter + 1;} }%>

</table>
</p>
<p align="right">
    <a href=/createLoanType>
        <button class="returnButton"></button>
    </a>
</p>
<p align="left">
    <a href="addGrantCondition?status=final">
        <button class="purpleContinueButton">تایید نهایی</button>
    </a>
</p>
</body>
</html>

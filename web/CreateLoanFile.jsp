<%@ page import="model.entity.LoanTypeEntity" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="business.LoanTypeController" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>تسهیلات جدید</title>
    <script type="text/javascript">
        function checkCustomerFunction(form) {
            if (form["customerNumberField"].value == null || form["customerNumberField"].value == "") {
                alert("شماره مشتری را وارد کنید");
                return false;
            }
        }
        function checkLoanFile(form) {
            if (form["customerNumberField"].value == null || form["customerNumberField"].value == "") {
                alert("مشتری را تعیین کنید");
                return false;
            }
            if (form["firstNameField"].value == null || form["firstNameField"].value == "") {
                alert("مشتری را تعیین کنید");
                return false;
            }
            if (form["lastNameField"].value == null || form["lastNameField"].value == "") {
                alert("مشتری را تعیین کنید");
                return false;
            }
            if (form["selectedLoanType"].value == null || form["selectedLoanType"].value == "") {
                alert("تسهیلات موجود نیست");
                return false;
            }
            if (form["durationField"].value == null || form["durationField"].value == "") {
                alert("مدت قرارداد را مشخص کنید");
                return false;
            }
            if (form["priceField"].value == null || form["priceField"].value == "") {
                alert("مبلغ قرارداد را مشخص کنید");
                return false;
            }
        }
    </script>
</head>
<body dir="rtl">
<link rel="stylesheet" type="text/css" href="LoanStyles.css" media="screen"/>

<br><br>

<form method="post">
    <%
        if (request.getAttribute("result") != null && request.getAttribute("result").equals("success")) {
    %>
    <p align="center">
        <label class="messageLabel">تسهیلات اضافه شد</label>
    </p>
    <%
        }
    %>
    <p align="center">
        <label class="customLabel" for="customerNumberField">شماره مشتری</label>
        <input class="searchBox" type="text" id="customerNumberField" name="customerNumberField"
               value=<%=((request.getParameter("customerNumberField") == null || request.getParameter("customerNumberField").equals("")) ? "" : request.getParameter("customerNumberField"))%>>
        <input type="submit" class="continueButton" name="getInfo" value="بازیابی"
               onclick="return checkCustomerFunction(this.form)"/>
        <br><br>
    </p>
    <br><br>

    <p align="center">
        <label class="customLabel" for="firstNameField">نام</label>
        <input readonly class="searchBox" type="text" id="firstNameField" name="firstNameField"
               value=<%=((request.getAttribute("firstNameField") == null || request.getAttribute("firstNameField").equals("")) ? "" : request.getAttribute("firstNameField"))%>>
        <label class="customLabel" for="lastNameField" style="margin-right: 10em">نام خانوادگی</label>
        <input readonly class="searchBox" type="text" id="lastNameField" name="lastNameField"
               value=<%=((request.getAttribute("lastNameField") == null || request.getAttribute("lastNameField").equals("")) ? "" : request.getAttribute("lastNameField"))%>>
    </p>

    <p align="center">
        <label class="customLabel" for="selectedLoanType">نوع تسهیلات</label><br>
        <select class="select-style" id="selectedLoanType" name="selectedLoanType">
            <%
                List allLoans = LoanTypeController.getInstance().getAllLoans();
                for (Iterator iterator = allLoans.iterator(); iterator.hasNext(); ) {
                    LoanTypeEntity loanTypeEntity = (LoanTypeEntity) iterator.next();
            %>
            <option><%=loanTypeEntity.getLoanTypeName()%>
            </option>
            <%}%>
        </select>
    </p>
    <br>

    <p align="center">
        <input class="input loanField" type="text" id="durationField" name="durationField" placeholder="مدت قرارداد"
               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
               value=<%=((request.getAttribute("durationField") == null || request.getAttribute("durationField").equals("")) ? "" : request.getAttribute("durationField"))%>>
        <br><br>
        <input class="input loanField" type="text" id="priceField" name="priceField" placeholder="مبلغ قرارداد"
               onkeypress='return event.charCode >= 48 && event.charCode <= 57'
               value=<%=((request.getAttribute("priceField") == null || request.getAttribute("priceField").equals("")) ? "" : request.getAttribute("priceField"))%>>
        <br><br>
        <input type="submit" class="purpleContinueButton" name="createLoanFile" value="ایجاد"
                onclick="return checkLoanFile(this.form)" />
    </p>
</form>

<a href="realCustomersHome">
    <button class="returnButton" style="float:right;"></button>
</a>


</body>
</html>

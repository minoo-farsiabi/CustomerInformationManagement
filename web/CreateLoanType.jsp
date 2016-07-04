<%@ page import="business.LoanTypeController" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>تسهیلات جدید</title>
    <script type="text/javascript">
        function validateForm(form) {
            if (form["loanTypeName"].value == null || form["loanTypeName"].value == "") {
                alert("نام تسهیلات را وارد کنید");
                return false;
            }
            else if (form["interestRate"].value == null || form["interestRate"].value == "") {
                alert("نرخ سود را وارد کنید");
                return false;
            }
            else if (parseInt(form["interestRate"].value) > 100) {
                alert("نرخ سود کمتر از 100 می باشد!");
                return false;
            }
        }
    </script>
</head>
<body dir="rtl">
<link rel="stylesheet" type="text/css" href="LoanStyles.css" media="screen"/>

<form method="post" onsubmit="return validateForm(this)">
    <p align="center">
        <br> <br>
        <label class="loanLabel" for="loanTypeName">نام تسهیلات</label> <br>
        <input class="input loanField" type="text" id="loanTypeName" name="loanTypeName"/> <br> <br> <br> <br>
        <label class="loanLabel" for="interestRate">نرخ سود</label> <br>
        <input class="input loanField" type="text" id="interestRate" name="interestRate"
               onkeypress='return event.charCode >= 48 && event.charCode <= 57'/> <br> <br> <br> <br>

        <button type="submit" name="status" value="addLoanType" class="continueButton">ادامه</button>
    </p>

</form>

<p align="center">
    <a href="allLoanTypes">
        <button class="resetButton" id="listAllLoanTypes" name="listAllLoanTypes">مشاهده تمامی تسهیلات</button>
    </a>
</p>

<p align="center">
    <a href="index.html">
        <button class="goldReturnButton">بازگشت</button>
    </a>
</p>

</body>
</html>

package util.old;

import business.old.RealCustomerController;
import model.entity.old.RealCustomerEntity;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class RealCustomerHTMLCreator {
    public static String customerManagement () {
        String result = "";
        List<RealCustomerEntity> allRealCustomers = RealCustomerController.getInstance().getAllCustomers();

        result += "<HTML>\n<meta charset=\"UTF-8\">\n<HEAD>";
        result += "<TITLE>" + "مشتریان حقیقی" + "</TITLE></HEAD>";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<p align='left'><a href=\"/createLoanFile\">  <button class=\"continueButton\" >تشکیل پرونده تسهیلاتی</button> </a></p>";

        result += "<P ALIGN='center'><TABLE id = \"realCustomers\">";

        result += "<TR>";

        result += "<TH>" + "    " + "</TH>";
        result += "<TH>" + "    " + "</TH>";
        result += "<TH>" + "شماره مشتری" + "</TH>";
        result += "<TH>" + "نام" + "</TH>";
        result += "<TH>" + "نام خانوادگی" + "</TH>";
        result += "<TH>" + "نام پدر" + "</TH>";
        result += "<TH>" + "تاریخ تولد" + "</TH>";
        result += "<TH>" + "کد ملی" + "</TH>";

        result += "</TR>";

        for (RealCustomerEntity realCustomerEntity : allRealCustomers) {
            result += "<form>";
            result += "<TR>";
            result += "<TD> <button type=\"submit\" class=\"button deleteButton\" formmethod=\"POST\" name=\"act\" value=\"deleteCustomer&" + realCustomerEntity.getCustomerNumber().toString() + "\"/> </TD>";
            result += "<TD> <P> <button type=\"submit\" class=\"button editButton\" formmethod=\"GET\" formaction=\"editRealCustomer\" name=\"customerNumber\" value=\"" + realCustomerEntity.getCustomerNumber().toString() + "\"/>  </P> </TD>";
            result += "<TD>" + realCustomerEntity.getCustomerNumber().toString() + "</TD>";
            result += "<TD>" + realCustomerEntity.getFirstname() + "</TD>";
            result += "<TD>" + realCustomerEntity.getLastname() + "</TD>";
            result += "<TD>" + realCustomerEntity.getFathername() + "</TD>";
            result += "<TD>" + realCustomerEntity.getDateOfBirth() + "</TD>";
            result += "<TD>" + realCustomerEntity.getNationalId() + "</TD>";
            result += "</TR>";
            result += "</form>";
        }
        result += "</TABLE></P>";

        result += "<P><a href=\"/addRealCustomer\">";
        result += "<button class=\"button addRealCustomerButton\" style=\"float: right;\">+</button>";

        result += "<a href=\"/searchRealCustomer\">";
        result += "<button align='center' formmethod = \"GET\" class=\"button searchRealCustomerButton\" name=\"act\" value=\"search\" style=\"float: left;\" ></button>";
        result += "</a>";

        result += "</a></P>";

        result += "</BODY></HTML>";

        return result;
    }

    public static String getAllCustomersWithFilters (String firstNameFilterText, String lastNameFilterText, String nationalIdFilterText, String realCustomerNumberFilterText) {
        String result = "";
        List <RealCustomerEntity> allRealCustomers = RealCustomerController.getInstance().getAllCustomersWithFilters(firstNameFilterText,lastNameFilterText,nationalIdFilterText,realCustomerNumberFilterText);

        result += "<HTML lang=\"fa\">\n<meta charset=\"UTF-8\">\n<HEAD>";
        result += "<TITLE>" + "مشتریان حقیقی" + "</TITLE></HEAD>";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<P align='center'> <form accept-charset=\"UTF-8\">";
        result += "<input class=\"text searchBox\" name=\"firstNameFilterText\" name=\"firstNameFilterText\" placeholder=\"نام\" value=\"" + firstNameFilterText + "\">";
        result += "<input class=\"text searchBox\" name=\"lastNameFilterText\" name=\"lastNameFilterText\" placeholder=\"نام خانوادگی\" value=\"" + lastNameFilterText + "\">";
        result += "<input class=\"text searchBox\" name=\"nationalIdFilterText\" name=\"nationalIdFilterText\" placeholder=\"کد ملی\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value=\"" + nationalIdFilterText + "\">";
        result += "<input class=\"text searchBox\" name=\"realCustomerNumberFilterText\" name=\"realCustomerNumberFilterText\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder=\"شماره مشتری\" value=\"" + realCustomerNumberFilterText + "\"> &nbsp;";
        result += "<button align='center' formmethod = \"GET\" class=\"button searchRealButton\" name=\"act\" value=\"search\" >بگرد</button>";
        result += "</form> </P>";

        result += "<P ALIGN='center'><TABLE id = \"realCustomers\">";

        result += "<TR>";

        result += "<TH>" + "شماره مشتری" + "</TH>";
        result += "<TH>" + "نام" + "</TH>";
        result += "<TH>" + "نام خانوادگی" + "</TH>";
        result += "<TH>" + "نام پدر" + "</TH>";
        result += "<TH>" + "تاریخ تولد" + "</TH>";
        result += "<TH>" + "کد ملی" + "</TH>";

        result += "</TR>";

        for (RealCustomerEntity realCustomerEntity : allRealCustomers) {
            result += "<form>";
            result += "<TR>";
            result += "<TD>" + realCustomerEntity.getCustomerNumber().toString() + "</TD>";
            result += "<TD>" + realCustomerEntity.getFirstname() + "</TD>";
            result += "<TD>" + realCustomerEntity.getLastname() + "</TD>";
            result += "<TD>" + realCustomerEntity.getFathername() + "</TD>";
            result += "<TD>" + realCustomerEntity.getDateOfBirth() + "</TD>";
            result += "<TD>" + realCustomerEntity.getNationalId() + "</TD>";
            result += "</TR>";
            result += "</form>";
        }
        result += "</TABLE></P>";

        result += "<P><a href=\"/realCustomersHome\">";
        result += "<button class=\"button cancelRealSearch\">+</button>";
        result += "</a></P>";

        result += "</BODY></HTML>";

        return result;
    }

    public static String addCustomer (String firstName, String lastName, String fatherName, String dateOfBirth, String nationalId,boolean isRedundantNationalId) throws UnsupportedEncodingException {

        String result = "";

        result += "<HTML>\n<HEAD>\n";
        result += "<TITLE>" + "مشتری جدید" + "</TITLE>\n";
        result += validateRealCustomerJS();
        result += "</HEAD>\n";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<form name=\"customerForm\" align='center' action=\"/addRealCustomer\" method=\"Post\" onsubmit=\"return validateForm()\">";
        result += "<label class=\"newCustomerLabel\" for=\"firstName\">نام</label><br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" id=\"firstName\" name=\"firstName\" value=\"" + firstName + "\"> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"lastName\">نام خانوادگی</label> <br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" id=\"lastName\" name=\"lastName\" value=\"" + lastName + "\"> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"fatherName\">نام پدر</label> <br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" id=\"fatherName\" name=\"fatherName\" value=\"" + fatherName + "\"> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"dateOfBirth\">تاریخ تولد</label> <br>";
        result += "<input class=\"input realCustomerField\" type = \"datetime\" id=\"dateOfBirth\" name=\"dateOfBirth\" data-format=\"13YY-MM-DD\" placeholder=\"1366-05-01\" value=\"" + dateOfBirth +"\"> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"nationalId\">کد ملی</label> <br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' id=\"nationalId\" name=\"nationalId\" value=\"" + nationalId + "\"> <br> <br>";
        if (isRedundantNationalId) {
            result += "<label class=\"errorLabel\" for=\"nationalId\">کد ملی تکراری است</label>";
        }

        result += "<P align='center'>";
        result += "<button align='center' type=\"submit\" class=\"button submitNewCustomer\" name=\"act\" value=\"addCustomer\">تایید</button>";
        result += "<br> <br>";
        result += "</P>";

        result += "</form>";


        result += "<P align='center'><a href=\"/realCustomersHome\">";
        result += "<button align='center' class=\"button returnButton\" >بازگشت</button>";
        result += "</a></P>";

        result += "</BODY></HTML>";

        System.out.println("HTML: " + result);

        return result;
    }

    public static String editCustomer(RealCustomerEntity realCustomerEntity) {
        String result = "";

        result += "<HTML>\n<HEAD>";
        result += "<TITLE>" + "ویرایش اطلاعات" + "</TITLE> + " + validateRealCustomerJS() + "</HEAD>";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<form name=\"customerForm\" align='center' action=\"/editRealCustomer\" method=\"Post\"  onsubmit=\"return validateForm()\">";
        result += "<label class=\"newCustomerLabel\" for=\"customerNumber\">شماره مشتری</label><br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" id=\"customerNumber\" name=\"customerNumber\" value=" + realCustomerEntity.getCustomerNumber().toString() + " readonly> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"firstName\">نام</label><br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" id=\"firstName\" name=\"firstName\" value=" + realCustomerEntity.getFirstname() + "> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"lastName\">نام خانوادگی</label> <br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" id=\"lastName\" name=\"lastName\" value=" + realCustomerEntity.getLastname() + "> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"fatherName\">نام پدر</label> <br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" id=\"fatherName\" name=\"fatherName\" value=" + realCustomerEntity.getFathername() + "> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"dateOfBirth\">تاریخ تولد</label> <br>";
        result += "<input class=\"input realCustomerField\" type = \"datetime\" id=\"dateOfBirth\" name=\"dateOfBirth\" data-format=\"13YY--MM-DD\" value=" + realCustomerEntity.getDateOfBirth() + "> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"nationalId\">کد ملی</label> <br>";
        result += "<input class=\"input realCustomerField\" type=\"text\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' id=\"nationalId\" name=\"nationalId\" value=" + realCustomerEntity.getNationalId().toString() + " readonly> <br> <br>";

        result += "<P align='center'>";
        result += "<button align='center' type=\"submit\" class=\"button submitNewCustomer\" name=\"act\" value=\"editCustomer\" name=\"customerNumber\" value=\"" + realCustomerEntity.getCustomerNumber() + "\">ویرایش</button>";
        result += "<br> <br>";
        result += "</P>";

        result += "</form>";

        result += "<P align='center'><a href=\"/realCustomersHome\">";
        result += "<button align='center' class=\"button returnButton\" >بازگشت</button>";
        result += "</a></P>";

        result += "</BODY></HTML>";

        return result;
    }

    private static String validateRealCustomerJS() {
        String scriptString = "<script type=\"text/javascript\">\n";
        scriptString += "function validateForm() {\n" +
                "    var customerForm = document.forms[\"customerForm\"];\n" +
                "    if (customerForm[\"firstName\"].value == null || customerForm[\"firstName\"].value == \"\") {\n" +
                "        alert(\"نام را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"lastName\"].value == null || customerForm[\"lastName\"].value == \"\") {\n" +
                "        alert(\"نام خانوادگی را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"fatherName\"].value == null || customerForm[\"fatherName\"].value == \"\") {\n" +
                "        alert(\"نام پدر را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"dateOfBirth\"].value == null || customerForm[\"dateOfBirth\"].value == \"\") {\n" +
                "        alert(\"تاریخ تولد را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"nationalId\"].value == null || customerForm[\"nationalId\"].value == \"\") {\n" +
                "        alert(\"کد ملی را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"nationalId\"].value.length != 10) {\n" +
                "        alert(\"کد ملی نامعتبر است\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    var str = customerForm[\"nationalId\"].value;" +
                "    var arr = str.split(\"\"); \n" +
                "    var i , j; var sum = 0;\n" +
                "    for(j=0; j<arr.length; j++) { arr[j] = parseInt(arr[j], 10); } \n " +
                "    for (i = 0; i < arr.length - 1; i++) {\n " +
                "    sum = sum + (arr[i] * (i + 1)); \n" +
                "    }\n" +
                "    if ((sum % 11) != arr[arr.length - 1] && (sum % 11) != 11 - arr[arr.length - 1]) {\n" +
                "        alert(\"کد ملی نامعتبر است\");\n" +
                "        return false;\n" +
                "    }\n" +
                "}\n" +
                "</script>";
        return scriptString;
    }
}
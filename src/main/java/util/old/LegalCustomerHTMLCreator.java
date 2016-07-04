package util.old;

import business.old.LegalCustomerController;
import model.entity.old.LegalCustomerEntity;

import java.util.List;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class LegalCustomerHTMLCreator {
    public static String customerManagement () {
        String result = "";
        List<LegalCustomerEntity> allLegalCustomers = LegalCustomerController.getInstance().getAllCustomers();

        result += "<HTML>\n<HEAD>";
        result += "<TITLE>" + "مشتریان حقوقی" + "</TITLE></HEAD>";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<P ALIGN='center'><TABLE id = \"legalCustomers\">";

        result += "<TR>";

        result += "<TH>" + "    " + "</TH>";
        result += "<TH>" + "    " + "</TH>";
        result += "<TH>" + "شماره مشتری" + "</TH>";
        result += "<TH>" + "نام شرکت" + "</TH>";
        result += "<TH>" + "تاریخ ثبت" + "</TH>";
        result += "<TH>" + "کد اقتصادی" + "</TH>";

        result += "</TR>";

        for (LegalCustomerEntity legalCustomerEntity : allLegalCustomers) {
            result += "<form>";
            result += "<TR>";
            result += "<TD> <button type=\"submit\" class=\"button deleteButton\" formmethod=\"POST\" name=\"act\" value=\"deleteCustomer&" + legalCustomerEntity.getCustomerNumber().toString() + "\"/> </TD>";
            result += "<TD> <P> <button type=\"submit\" class=\"button editButton\" formmethod=\"GET\" formaction=\"editLegalCustomer\" name=\"customerNumber\" value=\"" + legalCustomerEntity.getCustomerNumber().toString() + "\"/>  </P> </TD>";
            result += "<TD>" + legalCustomerEntity.getCustomerNumber().toString() + "</TD>";
            result += "<TD>" + legalCustomerEntity.getCompanyName() + "</TD>";
            result += "<TD>" + legalCustomerEntity.getDateOfConfirmation() + "</TD>";
            result += "<TD>" + legalCustomerEntity.getEconomicalId() + "</TD>";
            result += "</TR>";
            result += "</form>";
        }
        result += "</TABLE></P>";

        result += "<P><a href=\"/addLegalCustomer\">";
        result += "<button class=\"button addLegalCustomerButton\" style=\"float: right;\">+</button>";

        result += "<a href=\"/searchLegalCustomer\">";
        result += "<button align='center' formmethod = \"GET\" class=\"button searchLegalCustomerButton\" name=\"act\" value=\"search\" style=\"float: left;\" ></button>";
        result += "</a>";

        result += "</a></P>";

        result += "</BODY></HTML>";

        return result;
    }

    public static String getAllCustomersWithFilters (String companyNameFilterText, String economicalIdFilterText, String legalCustomerNumberFilterText) {
        String result = "";
        List <LegalCustomerEntity> allLegalCustomers = LegalCustomerController.getInstance().getAllCustomersWithFilters(companyNameFilterText, economicalIdFilterText,legalCustomerNumberFilterText);

        result += "<HTML>\n<HEAD>";
        result += "<TITLE>" +"مشتریان حقوقی" + "</TITLE></HEAD>";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<P align='center'> <form accept-charset=\"UTF-8\">";
        result += "<input class=\"text searchBox\" name=\"companyNameFilterText\" name=\"companyNameFilterText\" placeholder=\"نام شرکت\" value=\"" + companyNameFilterText + "\">";
        result += "<input class=\"text searchBox\" name=\"economicalIdFilterText\" name=\"economicalIdFilterText\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder=\"کد اقتصادی\" value=\"" + economicalIdFilterText + "\">";
        result += "<input class=\"text searchBox\" name=\"legalCustomerNumberFilterText\" name=\"legalCustomerNumberFilterText\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' placeholder=\"شماره مشتری\" value=\"" + legalCustomerNumberFilterText + "\"> &nbsp;";
        result += "<button align='center' formmethod = \"GET\" class=\"button searchLegalButton\" name=\"act\" value=\"search\" >بگرد</button>";
        result += "</form> </P>";

        result += "<P ALIGN='center'><TABLE id = \"legalCustomers\">";

        result += "<TR>";

        result += "<TH>" + "شماره مشتری" + "</TH>";
        result += "<TH>" + "نام شرکت" + "</TH>";
        result += "<TH>" + "تاریخ ثبت" + "</TH>";
        result += "<TH>" + "کد اقتصادی" + "</TH>";

        result += "</TR>";

        for (LegalCustomerEntity legalCustomerEntity : allLegalCustomers) {
            result += "<form>";
            result += "<TR>";
            result += "<TD>" + legalCustomerEntity.getCustomerNumber().toString() + "</TD>";
            result += "<TD>" + legalCustomerEntity.getCompanyName() + "</TD>";
            result += "<TD>" + legalCustomerEntity.getDateOfConfirmation() + "</TD>";
            result += "<TD>" + legalCustomerEntity.getEconomicalId() + "</TD>";
            result += "</TR>";
            result += "</form>";
        }
        result += "</TABLE></P>";

        result += "<P><a href=\"/legalCustomersHome\">";
        result += "<button class=\"button cancelLegalSearch\">+</button>";
        result += "</a></P>";

        result += "</BODY></HTML>";

        return result;
    }

    public static String addCustomer (String companyName, String dateOfConfirmation, String economicalId, boolean isRedundantEconomicalId) {
        String result = "";

        result += "<HTML>\n<HEAD>\n";
        result += "<TITLE>" + "مشتری جدید" + "</TITLE>\n";
        result += validateLegalCustomerJS();
        result += "</HEAD>\n";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<form name=\"customerForm\" align='center' action=\"/addLegalCustomer\" method=\"Post\" onsubmit=\"return validateForm()\">";
        result += "<label class=\"newCustomerLabel\" for=\"companyName\">نام شرکت</label><br>";
        result += "<input class=\"input legalCustomerField\" type=\"text\" id=\"companyName\" name=\"companyName\" value=\"" + companyName + "\"> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"dateOfConfirmation\">تاریخ ثبت</label> <br>";
        result += "<input class=\"input legalCustomerField\" type = \"datetime\" id=\"dateOfConfirmation\" name=\"dateOfConfirmation\" data-format=\"13YY-MM-DD\" placeholder=\"1366-05-01\" value=\"" + dateOfConfirmation +"\"> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"economicalId\">کد اقتصادی</label> <br>";
        result += "<input class=\"input legalCustomerField\" type=\"text\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' id=\"economicalId\" name=\"economicalId\" value=\"" + economicalId + "\"> <br> <br>";
        if (isRedundantEconomicalId) {
            result += "<label class=\"errorLabel\" for=\"economicalId\">کد اقتصادی تکراری است</label>";
        }

        result += "<P align='center'>";
        result += "<button align='center' type=\"submit\" class=\"button submitNewCustomer\" name=\"act\" value=\"addCustomer\">تایید</button>";
        result += "<br> <br>";
        result += "</P>";

        result += "</form>";


        result += "<P align='center'><a href=\"/legalCustomersHome\">";
        result += "<button align='center' class=\"button returnButton\" >بازگشت</button>";
        result += "</a></P>";

        result += "</BODY></HTML>";

        System.out.println("HTML: " + result);

        return result;
    }

    public static String editCustomer(LegalCustomerEntity legalCustomerEntity) {
        String result = "";

        result += "<HTML>\n<HEAD>";
        result += "<TITLE>" + "ویرایش اطلاعات" + "</TITLE> + " + validateLegalCustomerJS() + "</HEAD>";
        result += "<BODY dir=\"rtl\">\n";
        result += "<link rel=\"stylesheet\" type=\"text/css\" href=\"MyStyles.css\" media=\"screen\" />";

        result += "<form align='center' action=\"/editLegalCustomer\" method=\"Post\"  onsubmit=\"return validateForm()\">";
        result += "<label class=\"newCustomerLabel\" for=\"customerNumber\">شماره مشتری</label><br>";
        result += "<input class=\"input legalCustomerField\" type=\"text\" id=\"customerNumber\" name=\"customerNumber\" value=" + legalCustomerEntity.getCustomerNumber().toString() + " readonly> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"companyName\">نام شرکت</label><br>";
        result += "<input class=\"input legalCustomerField\" type=\"text\" id=\"companyName\" name=\"companyName\" value=" + legalCustomerEntity.getCompanyName() + "> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"dateOfConfirmation\">تاریخ ثبت</label> <br>";
        result += "<input class=\"input legalCustomerField\" type = \"datetime\" id=\"dateOfConfirmation\" name=\"dateOfConfirmation\" data-format=\"13YY--MM-DD\" value=" + legalCustomerEntity.getDateOfConfirmation() + "> <br> <br>";
        result += "<label class=\"newCustomerLabel\" for=\"economicalId\">کد اقتصادی</label> <br>";
        result += "<input class=\"input legalCustomerField\" type=\"text\" onkeypress='return event.charCode >= 48 && event.charCode <= 57' id=\"nationalId\" name=\"economicalId\" value=" + legalCustomerEntity.getEconomicalId().toString() + " readonly> <br> <br>";

        result += "<P align='center'>";
        result += "<button align='center' type=\"submit\" class=\"button submitNewCustomer\" name=\"act\" value=\"editCustomer\" name=\"customerNumber\" value=\"" + legalCustomerEntity.getCustomerNumber() + "\">ویرایش</button>";
        result += "<br> <br>";
        result += "</P>";

        result += "</form>";

        result += "<P align='center'><a href=\"/legalCustomersHome\">";
        result += "<button align='center' class=\"button returnButton\" >بازگشت</button>";
        result += "</a></P>";

        result += "</BODY></HTML>";

        return result;
    }

    private static String validateLegalCustomerJS() {
        String scriptString = "<script type=\"text/javascript\">\n";
        scriptString += "function validateForm() {\n" +
                "    var customerForm = document.forms[\"customerForm\"];\n" +
                "    if (customerForm[\"companyName\"].value == null || customerForm[\"companyName\"].value == \"\") {\n" +
                "        alert(\"نام شرکت را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"dateOfConfirmation\"].value == null || customerForm[\"dateOfConfirmation\"].value == \"\") {\n" +
                "        alert(\"تاریخ ثبت شرکت را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"economicalId\"].value == null || customerForm[\"economicalId\"].value == \"\") {\n" +
                "        alert(\"کد اقتصادی را وارد کنید\");\n" +
                "        return false;\n" +
                "    }\n" +
                "    else if (customerForm[\"economicalId\"].value.length != 10) {\n" +
                "        alert(\"کد اقتصادی نامعتبر است\");\n" +
                "        return false;\n" +
                "    }\n" +
                "}\n" +
                "</script>";
        return scriptString;
    }
}

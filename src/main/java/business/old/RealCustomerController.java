package business.old;

import model.dao.old.RealCustomerDAO;
import model.entity.old.RealCustomerEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class RealCustomerController {
    private static RealCustomerController ourInstance = new RealCustomerController();

    public static RealCustomerController getInstance() {
        return ourInstance;
    }

    private RealCustomerController() {
        RealCustomerDAO.getInstance().initializeConnection();
    }

    public List<RealCustomerEntity> getAllCustomers() {
        return RealCustomerDAO.getInstance().getAllCustomers();
    }

    public List<RealCustomerEntity> getAllCustomersWithFilters(String firstNameFilterText, String lastNameFilterText, String nationalIdFilterText, String realCustomerNumberFilterText) {
        return RealCustomerDAO.getInstance().getAllCustomersWithFilters(firstNameFilterText,lastNameFilterText,nationalIdFilterText,realCustomerNumberFilterText);
    }

    public int addCustomer (String firstName, String lastName, String fatherName, Date dateOfBirth, String nationalId) {
        if (RealCustomerController.getInstance().getCustomerWithNationalId(nationalId) == null) {
            RealCustomerDAO.getInstance().addCustomer(firstName,lastName,fatherName,dateOfBirth,nationalId);
            return 0;
        }
        return 1;
    }

    public RealCustomerEntity findCustomerByCustomerNumber (BigDecimal customerNumber) {
        return RealCustomerDAO.getInstance().findCustomerByCustomerNumber(customerNumber);
    }

    public RealCustomerEntity getCustomerWithNationalId (String nationalId) {
        return RealCustomerDAO.getInstance().getCustomerWithNationalId(nationalId);
    }

    public void editCustomer (BigDecimal customerNumber, String firstName, String lastName, String fatherName, Date dateOfBirth, String nationalId) {
        RealCustomerDAO.getInstance().editCustomer(customerNumber,firstName,lastName,fatherName,dateOfBirth,nationalId);
    }

    public void deleteCustomer (BigDecimal customerNumber) {
        RealCustomerDAO.getInstance().deleteCustomer(customerNumber);
    }
}
